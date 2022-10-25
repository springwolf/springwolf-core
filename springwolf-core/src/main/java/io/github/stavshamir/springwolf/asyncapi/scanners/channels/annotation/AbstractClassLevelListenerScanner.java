package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.MessageHelper;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SpringPayloadAnnotationTypeExtractor.getPayloadType;
import static java.util.stream.Collectors.toSet;

@Slf4j
public abstract class AbstractClassLevelListenerScanner<ClassAnnotation extends Annotation, MethodAnnotation extends Annotation> implements ChannelsScanner {

    @Autowired
    private AsyncApiDocket docket;

    @Autowired
    private SchemasService schemasService;

    private static final Comparator<Map.Entry<String, ChannelItem>> byPublishOperationName = Comparator.comparing(it -> it.getValue().getPublish().getOperationId());
    private static final Supplier<Set<Map.Entry<String, ChannelItem>>> channelItemSupplier = () -> new TreeSet<>(byPublishOperationName);

    /**
     * This annotation is used on class level
     * @return The class object of the listener annotation.
     */
    protected abstract Class<ClassAnnotation> getListenerAnnotationClass();
    /**
     * This annotation is used on the method level
     * @return The class object of the handler annotation.
     */
    protected abstract Class<MethodAnnotation> getHandlerAnnotationClass();

    /**
     * @param annotation An instance of a listener annotation.
     * @return The channel name associated with this instance of listener annotation.
     */
    protected abstract String getChannelName(ClassAnnotation annotation);

    /**
     * @param annotation An instance of a listener annotation.
     * @return A map containing an operation binding pointed to by the protocol binding name.
     */
    protected abstract Map<String, ? extends OperationBinding> buildOperationBinding(ClassAnnotation annotation);

    /**
     * @param annotation An instance of a listener annotation.
     * @return A map containing an channel binding pointed to by the protocol binding name.
     */
    protected abstract Map<String, ? extends ChannelBinding> buildChannelBinding(ClassAnnotation annotation);

    /**
     * Can be overriden by implementations
     * @param method The specific method. Can be used to extract the payload type
     * @return The AsyncHeaders
     */
    protected AsyncHeaders buildHeaders(Method method) {
        return AsyncHeaders.NOT_DOCUMENTED;
    }

    @Override
    public Map<String, ChannelItem> scan() {
        Set<Class<?>> components = docket.getComponentsScanner().scanForComponents();
        Set<Map.Entry<String, ChannelItem>> channels = mapToChannels(components);
        return mergeChannels(channels);
    }

    private Set<Map.Entry<String, ChannelItem>> mapToChannels(Set<Class<?>> components) {
        return components.stream()
                .filter(this::isClassAnnotated)
                .map(this::mapClassToChannel)
                .filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toCollection(channelItemSupplier));
    }

    private boolean isClassAnnotated(Class<?> component) {
        return component.isAnnotationPresent(getListenerAnnotationClass());
    }

    private Map<String, ChannelItem> mergeChannels(Set<Map.Entry<String, ChannelItem>> channelEntries) {
        Map<String, ChannelItem> mergedChannels = new TreeMap<>();

        for (Map.Entry<String, ChannelItem> entry : channelEntries) {
            if (!mergedChannels.containsKey(entry.getKey())) {
                mergedChannels.put(entry.getKey(), entry.getValue());
            } else {
                ChannelItem channelItem = mergedChannels.get(entry.getKey());
                Set<Message> mergedMessages = getChannelMessages(channelItem);
                Set<Message> currentEntryMessages = getChannelMessages(entry.getValue());
                mergedMessages.addAll(currentEntryMessages);
                channelItem.getPublish().setMessage(toMessageObjectOrComposition(mergedMessages));
            }
        }

        return mergedChannels;
    }

    private Set<Message> getChannelMessages(ChannelItem channelItem) {
        return Optional
                .ofNullable(channelItem.getPublish())
                .map(Operation::getMessage)
                .map(MessageHelper::messageObjectToSet)
                .orElseGet(TreeSet::new);
    }

    private Optional<Map.Entry<String, ChannelItem>> mapClassToChannel(Class<?> component) {
        log.debug("Mapping class \"{}\" to channel", component.getName());

        ClassAnnotation annotation = component.getAnnotation(getListenerAnnotationClass());
        String channelName = getChannelName(annotation);
        Map<String, ? extends OperationBinding> operationBinding = buildOperationBinding(annotation);
        Map<String, ? extends ChannelBinding> channelBinding = buildChannelBinding(annotation);
        Set<Method> annotatedMethods = getAnnotatedMethods(component);

        if (annotatedMethods.isEmpty()) {
            return Optional.empty();
        }

        ChannelItem channelItem = buildChannel(component.getSimpleName(), annotatedMethods, channelBinding, operationBinding);
        return Optional.of(Maps.immutableEntry(channelName, channelItem));
    }

    private Set<Method> getAnnotatedMethods(Class<?> component) {
        Class<MethodAnnotation> methodAnnotation = getHandlerAnnotationClass();
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", component.getName(), methodAnnotation.getName());

        return Arrays.stream(component.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(methodAnnotation))
                .collect(toSet());
    }

    private ChannelItem buildChannel(String simpleName, Set<Method> methods, Map<String, ? extends ChannelBinding> channelBinding, Map<String, ? extends OperationBinding> operationBinding) {
        String operationId = simpleName + "_publish";

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId(operationId)
                .message(getMessageObject(methods))
                .bindings(operationBinding)
                .build();

        return ChannelItem.builder()
                .bindings(channelBinding)
                .publish(operation)
                .build();
    }

    private Object getMessageObject(Set<Method> methods) {
        Set<Message> messages = methods.stream()
                .map(this::buildMessage)
                .collect(toSet());

        return toMessageObjectOrComposition(messages);
    }

    private Message buildMessage(Method method) {
        Class<?> payloadType = getPayloadType(method);
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(this.buildHeaders(method));

        return Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .build();
    }

}
