// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelMerger;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static java.util.stream.Collectors.toSet;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractClassLevelListenerScanner<
                ClassAnnotation extends Annotation, MethodAnnotation extends Annotation>
        implements ChannelsScanner {

    private final ComponentClassScanner componentClassScanner;

    private final SchemasService schemasService;

    protected final PayloadClassExtractor payloadClassExtractor;

    /**
     * This annotation is used on class level
     *
     * @return The class object of the listener annotation.
     */
    protected abstract Class<ClassAnnotation> getListenerAnnotationClass();

    /**
     * This annotation is used on the method level
     *
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
     * @return A map containing a channel binding pointed to by the protocol binding name.
     */
    protected abstract Map<String, ? extends ChannelBinding> buildChannelBinding(ClassAnnotation annotation);

    /**
     * @param method The specific method. Can be used to extract message binding from an annotation.
     * @return A map containing a message binding pointed to by the protocol binding name.
     */
    protected abstract Map<String, ? extends MessageBinding> buildMessageBinding(Method method);

    /**
     * Can be overridden by implementations
     *
     * @param method The specific method. Can be used to extract the payload type
     * @return The AsyncHeaders
     */
    protected AsyncHeaders buildHeaders(Method method) {
        return AsyncHeaders.NOT_DOCUMENTED;
    }

    @Override
    public Map<String, ChannelItem> scan() {
        Set<Class<?>> components = componentClassScanner.scan();
        List<Map.Entry<String, ChannelItem>> channels = mapToChannels(components);
        return ChannelMerger.merge(new ArrayList<>(channels));
    }

    private List<Map.Entry<String, ChannelItem>> mapToChannels(Set<Class<?>> components) {
        return components.stream()
                .filter(this::isClassAnnotated)
                .map(this::mapClassToChannel)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private boolean isClassAnnotated(Class<?> component) {
        return AnnotationUtils.findAnnotation(component, getListenerAnnotationClass()) != null;
    }

    private Optional<Map.Entry<String, ChannelItem>> mapClassToChannel(Class<?> component) {
        log.debug("Mapping class \"{}\" to channel", component.getName());

        Class<ClassAnnotation> listenerAnnotationClass = getListenerAnnotationClass();
        Set<ClassAnnotation> annotations = MergedAnnotations.from(
                        component,
                        MergedAnnotations.SearchStrategy.TYPE_HIERARCHY,
                        RepeatableContainers.standardRepeatables())
                .stream(listenerAnnotationClass)
                .filter(MergedAnnotationPredicates.firstRunOf(MergedAnnotation::getAggregateIndex))
                .map(MergedAnnotation::withNonMergedAttributes)
                .collect(MergedAnnotationCollectors.toAnnotationSet());
        ClassAnnotation annotation = annotations.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Method must be annotated with " + listenerAnnotationClass.getName()));

        String channelName = getChannelName(annotation);

        Map<String, ? extends OperationBinding> operationBinding = buildOperationBinding(annotation);
        Map<String, ? extends ChannelBinding> channelBinding = buildChannelBinding(annotation);
        Set<Method> annotatedMethods = getAnnotatedMethods(component);

        if (annotatedMethods.isEmpty()) {
            return Optional.empty();
        }

        ChannelItem channelItem =
                buildChannel(component.getSimpleName(), annotatedMethods, channelBinding, operationBinding);
        return Optional.of(Map.entry(channelName, channelItem));
    }

    private Set<Method> getAnnotatedMethods(Class<?> component) {
        Class<MethodAnnotation> methodAnnotation = getHandlerAnnotationClass();
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods", component.getName(), methodAnnotation.getName());

        return Arrays.stream(component.getDeclaredMethods())
                .filter(method -> AnnotationUtils.findAnnotation(method, methodAnnotation) != null)
                .collect(toSet());
    }

    private ChannelItem buildChannel(
            String simpleName,
            Set<Method> methods,
            Map<String, ? extends ChannelBinding> channelBinding,
            Map<String, ? extends OperationBinding> operationBinding) {
        String operationId = simpleName + "_publish";

        Map<String, Object> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        Map<String, Object> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId(operationId)
                .message(getMessageObject(methods))
                .bindings(opBinding)
                .build();

        return ChannelItem.builder().bindings(chBinding).publish(operation).build();
    }

    private Object getMessageObject(Set<Method> methods) {
        Set<Message> messages = methods.stream().map(this::buildMessage).collect(toSet());

        return toMessageObjectOrComposition(messages);
    }

    private Message buildMessage(Method method) {
        Class<?> payloadType = payloadClassExtractor.extractFrom(method);
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(this.buildHeaders(method));

        return Message.builder()
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(null)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .bindings(buildMessageBinding(method))
                .build();
    }
}
