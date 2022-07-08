package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitExtendedBindingProperties;
import org.springframework.cloud.stream.config.BindingProperties;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
@EnableConfigurationProperties({RabbitBinderConfigurationProperties.class, RabbitExtendedBindingProperties.class, BindingServiceProperties.class})
public class CloudStreamRabbitBinderChannelsScanner implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;
    @Getter
    public final Map<String, Binding> bindingsMap;
    private RabbitExtendedBindingProperties rabbitExtendedBindingProperties;
    private BindingServiceProperties bindingServiceProperties;
    private SchemasService schemasService;
    private AsyncApiDocket docket;

    public CloudStreamRabbitBinderChannelsScanner(AsyncApiDocket docket, RabbitExtendedBindingProperties rabbitExtendedBindingProperties, BindingServiceProperties bindingServiceProperties, SchemasService schemasService) {
        this.docket = docket;
        this.rabbitExtendedBindingProperties = rabbitExtendedBindingProperties;
        this.bindingServiceProperties = bindingServiceProperties;
        this.bindingsMap = makeBindingMap(rabbitExtendedBindingProperties, bindingServiceProperties);
        this.schemasService = schemasService;
    }

    private Map<String, Binding> makeBindingMap(RabbitExtendedBindingProperties rabbitExtendedBindingProperties, BindingServiceProperties bindingServiceProperties) {
        Map<String, Binding> returnMap = new HashMap<>();
        try {
            Map<String, StreamRabbitBindingInfo> bindingInfoMap = new HashMap<>();
            rabbitExtendedBindingProperties.getBindings().entrySet().stream().forEach(entry -> {
                StreamRabbitBindingInfo binding = new StreamRabbitBindingInfo();
                binding.routingKey = entry.getValue() != null && entry.getValue().getConsumer() != null ? entry.getValue().getConsumer().getBindingRoutingKey() : null;
                bindingInfoMap.put(entry.getKey(), binding);
            });
            bindingServiceProperties.getBindings().entrySet().stream().forEach(entry -> {
                StreamRabbitBindingInfo binding = bindingInfoMap.containsKey(entry.getKey()) ? bindingInfoMap.get(entry.getKey()) : new StreamRabbitBindingInfo();
                binding.exchange = Optional.ofNullable(entry.getValue()).map(BindingProperties::getDestination).orElse(null);
                binding.queue = Optional.ofNullable(entry.getValue()).map(BindingProperties::getGroup).orElse(null);
                bindingInfoMap.put(entry.getKey(), binding);
            });
            bindingInfoMap.entrySet().forEach(entry -> {
                if (entry != null) {
                    returnMap.put(entry.getKey(), new Binding(entry.getValue().queue, Binding.DestinationType.QUEUE, entry.getValue().exchange, entry.getValue().routingKey, null));
                }
            });
        } catch(Exception e) {
            log.warn("error creating binding map for spring cloud stream rabbit binder.  this will result in no consumers being automatically mapped", e);
        }
        return returnMap;
    }

    @Override
    public Map<String, ChannelItem> scan() {
        return docket.getComponentsScanner().scanForComponents().stream()
                .map(this::getAnnotatedMethods).flatMap(Collection::stream)
                .map(method -> this.mapMethodToChannel(method))
                .filter(Objects::nonNull)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map.Entry<String, ChannelItem> mapMethodToChannel(Method method) {
        log.debug("Attempting to map method \"{}\" to channels", method.getName());



        String methodName = method.getName();
        Class<?> returnType = getReturnTypeOfMethod(method);
        Optional<String> channelNameOptional = getChannelNameForMethod(methodName);
        if(returnType != null && channelNameOptional.isPresent()) {
            String channelName = channelNameOptional.get();
            Map<String, ? extends  ChannelBinding> channelBindings = buildChannelBinding(channelName);
            Map<String, ? extends OperationBinding> operationBindings = buildOperationBinding(channelName);
            ChannelItem channelItem = this.buildChannel(channelBindings, returnType, operationBindings);
            return Maps.immutableEntry(channelName, channelItem);
        }
        if(returnType == null) {
            log.warn("method "+methodName+ " not mapped because its return type could not be determined.");
        }
        if(!channelNameOptional.isPresent()) {
            log.warn("method "+methodName+ " not mapped because its channel name could not be determined/located from application.properties");
        }
        return null;
    }

    private ChannelItem buildChannel(Map<String, ? extends ChannelBinding> channelBinding,
                                     Class<?> payloadType,
                                     Map<String, ? extends OperationBinding> operationBinding) {
        String modelName = schemasService.register(payloadType);

        Message message = Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(PayloadReference.fromModelName(modelName))
                .build();

        Operation operation = Operation.builder()
                .message(message)
                .bindings(operationBinding)
                .build();

        return ChannelItem.builder()
                .bindings(channelBinding)
                .publish(operation)
                .build();
    }

    protected Map<String, ? extends OperationBinding> buildOperationBinding(String channelName) {
        List<String> routingKeys = new ArrayList<>();
        routingKeys.add(this.bindingsMap.get(channelName).getRoutingKey());
        return ImmutableMap.of("amqp", AMQPOperationBinding.builder()
                .cc(routingKeys)
                .build());
    }

    private Optional<String> getChannelNameForMethod(String methodName) {
        return this.bindingsMap.keySet().stream().filter(channelName -> channelName.startsWith(methodName+"-in-")).findFirst();
    }

    protected Map<String, ? extends ChannelBinding> buildChannelBinding(String channelName) {
        AMQPChannelBinding.ExchangeProperties exchangeProperties = new AMQPChannelBinding.ExchangeProperties();
        AMQPChannelBinding.QueueProperties queueProperties = new AMQPChannelBinding.QueueProperties();
        Binding binding = bindingsMap.get(channelName);
        exchangeProperties.setName(binding.getExchange());
        queueProperties.setName(binding.getDestination());
        return ImmutableMap.of("amqp", AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(exchangeProperties)
                .queue(queueProperties)
                .build());
    }

    private Class<?> getReturnTypeOfMethod(Method method) {
        Type genericReturnType = method.getGenericReturnType();
        if(genericReturnType != null && genericReturnType instanceof ParameterizedTypeImpl) {
            Type[] typeArguments = ((ParameterizedTypeImpl) genericReturnType).getActualTypeArguments();
            if(typeArguments != null && typeArguments.length == 1) {
                String typeName = typeArguments[0].getTypeName();
                try {
                    return Class.forName(typeName);
                } catch(ClassNotFoundException classNotFoundException) {
                    //should never happen;
                }
            }
        }
        return null;
    }

    private Set<Method> getAnnotatedMethods(Class<?> type) {
        Class<Bean> annotationClass = Bean.class;
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        Set<Method> declaredBeans = Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(toSet());



        return declaredBeans.stream().filter((method) ->
                Consumer.class.equals(method.getReturnType())).collect(toSet());
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    private static class StreamRabbitBindingInfo {
        String queue;
        String exchange;
        String routingKey;

    }

}
