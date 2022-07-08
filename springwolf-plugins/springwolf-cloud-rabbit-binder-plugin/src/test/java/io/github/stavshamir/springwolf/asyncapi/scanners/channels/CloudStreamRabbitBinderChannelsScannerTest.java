package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitBindingProperties;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitConsumerProperties;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitExtendedBindingProperties;
import org.springframework.cloud.stream.config.BindingProperties;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;

import static java.util.Collections.singleton;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CloudStreamRabbitBinderChannelsScannerTest {
    private RabbitExtendedBindingProperties rabbitExtendedBindingProperties;
    private BindingServiceProperties bindingServiceProperties;
    private SchemasService schemasService;
    private AsyncApiDocket docket;
    private CloudStreamRabbitBinderChannelsScanner cloudStreamRabbitBinderChannelsScanner;

    private String channelName;
    private String functionName;
    private String routingKey;
    private String exchangeName;
    private String queueName;
    @Before
    public void setUp() {
        rabbitExtendedBindingProperties = mock(RabbitExtendedBindingProperties.class);
        bindingServiceProperties = mock(BindingServiceProperties.class);
        schemasService = mock(SchemasService.class);
        docket = mock(AsyncApiDocket.class);


        functionName = "functionName";
        channelName = functionName+"-in-0";
        routingKey = "some.routing.key";
        exchangeName = "exchangeName";
        queueName = "queueName";

        Map<String, RabbitBindingProperties> bindingPropertiesMap = new HashMap<>();
        RabbitBindingProperties rabbitBindingProperties = mock(RabbitBindingProperties.class);
        RabbitConsumerProperties rabbitConsumerProperties = mock(RabbitConsumerProperties.class);
        when(rabbitConsumerProperties.getBindingRoutingKey()).thenReturn(routingKey);
        when(rabbitBindingProperties.getConsumer()).thenReturn(rabbitConsumerProperties);
        bindingPropertiesMap.put(channelName, rabbitBindingProperties);
        when(rabbitExtendedBindingProperties.getBindings()).thenReturn(bindingPropertiesMap);

        Map<String, BindingProperties> bindingServicePropertiesMap = new HashMap<>();
        BindingProperties bindingProperties = mock(BindingProperties.class);
        bindingServicePropertiesMap.put(channelName, bindingProperties);
        when(bindingServiceProperties.getBindings()).thenReturn(bindingServicePropertiesMap);
        when(bindingProperties.getDestination()).thenReturn(exchangeName);
        when(bindingProperties.getGroup()).thenReturn(queueName);

        cloudStreamRabbitBinderChannelsScanner = new CloudStreamRabbitBinderChannelsScanner(docket, rabbitExtendedBindingProperties, bindingServiceProperties, schemasService);
    }

    @Test
    public void shouldInitializeBindingMappingFromConfigProperties() {
        Assertions.assertThat(cloudStreamRabbitBinderChannelsScanner.bindingsMap).isNotNull().hasSize(1);
        Binding binding = cloudStreamRabbitBinderChannelsScanner.getBindingsMap().values().stream().findFirst().orElse(null);
        Assertions.assertThat(binding.getDestination()).isEqualTo(queueName);
        Assertions.assertThat(binding.getExchange()).isEqualTo(exchangeName);
        Assertions.assertThat(binding.getRoutingKey()).isEqualTo(routingKey);
        Assertions.assertThat(binding.getDestinationType()).isEqualTo(Binding.DestinationType.QUEUE);
    }

    @Test
    public void scan_shouldMatchBeansThatReturnConsumersToBindingMappingsToGenerateConsumers() {
        ComponentsScanner componentsScanner = mock(ComponentsScanner.class);
        Set<Class<?>> classesToScan = singleton(SomeClass.class);
        when(docket.getComponentsScanner()).thenReturn(componentsScanner);
        when(componentsScanner.scanForComponents()).thenReturn(classesToScan);
        Map<String, ChannelItem> response =  cloudStreamRabbitBinderChannelsScanner.scan();
        Assertions.assertThat(response.containsKey("functionName-in-0")).isTrue();
        ChannelItem channelItem = response.get("functionName-in-0");
        Assertions.assertThat(channelItem.getBindings()).hasSize(1);
        Assertions.assertThat(channelItem.getBindings().containsKey("amqp")).isTrue();
        AMQPChannelBinding binding = (AMQPChannelBinding)channelItem.getBindings().get("amqp");
        Assertions.assertThat(binding.getExchange().getName()).isEqualTo(exchangeName);
        Assertions.assertThat(binding.getQueue().getName()).isEqualTo(queueName);
        Assertions.assertThat(channelItem.getPublish()).isNotNull();
        AMQPOperationBinding amqpOperationBinding = (AMQPOperationBinding)channelItem.getPublish().getBindings().entrySet().stream().findFirst().orElse(null).getValue();
        Assertions.assertThat(amqpOperationBinding.getCc()).containsExactly(routingKey);

    }

    @Test
    public void scan_shouldNotReturnMethodIfItDoesNotMatchABinding() {
        ComponentsScanner componentsScanner = mock(ComponentsScanner.class);
        Set<Class<?>> classesToScan = singleton(SomeClassWithNonMatchingFunctionName.class);
        when(docket.getComponentsScanner()).thenReturn(componentsScanner);
        when(componentsScanner.scanForComponents()).thenReturn(classesToScan);
        Map<String, ChannelItem> response =  cloudStreamRabbitBinderChannelsScanner.scan();
        Assertions.assertThat(response).hasSize(0);
    }

    public static class SomeClass {
        @Bean
        public Consumer<SomePayload> functionName() {
            return (SomePayload somePayload) -> {};
        }
    }

    public static class SomeClassWithNonMatchingFunctionName {
        @Bean
        public Consumer<SomePayload> nonMatchingFunctionName() {
            return (SomePayload somePayload) -> {};
        }
    }

    public static class SomePayload {
        private String payloadString;
    }
}