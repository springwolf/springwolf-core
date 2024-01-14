// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import io.github.stavshamir.springwolf.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ConfigurationClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.bindings.EmptyChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.EmptyOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.bindings.EmptyMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import org.apache.kafka.streams.kstream.KStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.config.BindingProperties;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                ConfigurationClassScanner.class,
                ComponentClassScanner.class,
                DefaultBeanMethodsScanner.class,
                DefaultSchemasService.class,
                PayloadClassExtractor.class,
                ExampleJsonGenerator.class,
                DefaultAsyncApiDocketService.class,
                CloudStreamFunctionChannelsScanner.class,
                FunctionalChannelBeanBuilder.class,
                SpringwolfConfigProperties.class
        })
@TestPropertySource(
        properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Test",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream",
                "springwolf.docket.servers.kafka.protocol=kafka",
                "springwolf.docket.servers.kafka.host=kafka:9092",
        })
@EnableConfigurationProperties
@Import(CloudStreamFunctionChannelsScannerIntegrationTest.Configuration.class)
class CloudStreamFunctionChannelsScannerIntegrationTest {

    @MockBean
    private BindingServiceProperties bindingServiceProperties;

    @Autowired
    private CloudStreamFunctionChannelsScanner scanner;

    private Map<String, EmptyMessageBinding> messageBinding = Map.of("kafka", new EmptyMessageBinding());
    private Map<String, OperationBinding> operationBinding = Map.of("kafka", new EmptyOperationBinding());
    private Map<String, ChannelBinding> channelBinding = Map.of("kafka", new EmptyChannelBinding());

    @Test
    void testNoBindings() {
        when(bindingServiceProperties.getBindings()).thenReturn(Collections.emptyMap());
        Map<String, ChannelObject> channels = scanner.scanChannels();
        assertThat(channels).isEmpty();
    }

    @Test
    void testConsumerBinding() {
        // Given a binding "spring.cloud.stream.bindings.testConsumer-in-0.destination=test-consumer-input-topic"
        BindingProperties testConsumerInBinding = new BindingProperties();
        String topicName = "test-consumer-input-topic";
        testConsumerInBinding.setDestination(topicName);
        when(bindingServiceProperties.getBindings()).thenReturn(Map.of("testConsumer-in-0", testConsumerInBinding));

        // When scan is called
        Map<String, ChannelObject> channels = scanner.scanChannels();

        // Then the returned channels contain a ChannelItem with the correct data
        MessageObject message = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MessageReference.toSchema(String.class.getSimpleName())))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                // .bindings(messageBinding) FIXME
                .build();

        Operation operation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                // .operationId("test-consumer-input-topic_publish_testConsumer") FIXME
                // .messages(List.of(message)) FIXME
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(channelBinding)
                // .publish(operation) FIXME
                .build();

        assertThat(channels).containsExactly(Map.entry(topicName, expectedChannel));
    }

    @Test
    void testSupplierBinding() {
        // Given a binding "spring.cloud.stream.bindings.testSupplier-out-0.destination=test-supplier-output-topic"
        BindingProperties testSupplierOutBinding = new BindingProperties();
        String topicName = "test-supplier-output-topic";
        testSupplierOutBinding.setDestination(topicName);
        when(bindingServiceProperties.getBindings()).thenReturn(Map.of("testSupplier-out-0", testSupplierOutBinding));

        // When scan is called
        Map<String, ChannelObject> channels = scanner.scanChannels();

        // Then the returned channels contain a ChannelItem with the correct data

        MessageObject message = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MessageReference.toSchema(String.class.getSimpleName())))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                //                .bindings(messageBinding) FIXME
                .build();

        Operation operation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                //                .operationId("test-supplier-output-topic_subscribe_testSupplier") FIXME
                //                .message(message) FIXME
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(channelBinding)
                //                .subscribe(operation) FIXME
                .build();

        assertThat(channels).containsExactly(Map.entry(topicName, expectedChannel));
    }

    @Test
    void testFunctionBinding() {
        // Given a binding "spring.cloud.stream.bindings.testFunction-in-0.destination=test-in-topic"
        // And a binding "spring.cloud.stream.bindings.testFunction-out-0.destination=test-output-topic"
        String inputTopicName = "test-in-topic";
        BindingProperties testFunctionInBinding = new BindingProperties();
        testFunctionInBinding.setDestination(inputTopicName);

        String outputTopicName = "test-out-topic";
        BindingProperties testFunctionOutBinding = new BindingProperties();
        testFunctionOutBinding.setDestination(outputTopicName);
        when(bindingServiceProperties.getBindings())
                .thenReturn(Map.of(
                        "testFunction-in-0", testFunctionInBinding,
                        "testFunction-out-0", testFunctionOutBinding));

        // When scan is called
        Map<String, ChannelObject> channels = scanner.scanChannels();

        // Then the returned channels contain a publish ChannelItem and a subscribe ChannelItem
        MessageObject subscribeMessage = MessageObject.builder()
                .name(Integer.class.getName())
                .title(Integer.class.getSimpleName())
                .payload(MessagePayload.of(MessageReference.toSchema(String.class.getSimpleName())))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                //                .bindings(messageBinding) FIXME
                .build();

        Operation subscribeOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                //                .operationId("test-out-topic_subscribe_testFunction") FIXME
                //                .message(subscribeMessage) FIXME
                .build();

        ChannelObject subscribeChannel = ChannelObject.builder()
                .bindings(channelBinding)
                //                .subscribe(subscribeOperation) FIXME
                .build();

        MessageObject publishMessage = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MessageReference.toSchema(String.class.getSimpleName())))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                //                .bindings(messageBinding) FIXME
                .build();

        Operation publishOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                //                .operationId("test-in-topic_publish_testFunction") FIXME
                //                .message(publishMessage) FIXME
                .build();

        ChannelObject publishChannel = ChannelObject.builder()
                .bindings(channelBinding)
                //                .publish(publishOperation) FIXME
                .build();

        assertThat(channels)
                .contains(Map.entry(inputTopicName, publishChannel), Map.entry(outputTopicName, subscribeChannel));
    }

    @Test
    void testKStreamFunctionBinding() {
        // Given a binding "spring.cloud.stream.bindings.kStreamTestFunction-in-0.destination=test-in-topic"
        // And a binding "spring.cloud.stream.bindings.kStreamTestFunction-out-0.destination=test-output-topic"
        String inputTopicName = "test-in-topic";
        BindingProperties testFunctionInBinding = new BindingProperties();
        testFunctionInBinding.setDestination(inputTopicName);

        String outputTopicName = "test-out-topic";
        BindingProperties testFunctionOutBinding = new BindingProperties();
        testFunctionOutBinding.setDestination(outputTopicName);
        when(bindingServiceProperties.getBindings())
                .thenReturn(Map.of(
                        "kStreamTestFunction-in-0", testFunctionInBinding,
                        "kStreamTestFunction-out-0", testFunctionOutBinding));

        // When scan is called
        Map<String, ChannelObject> channels = scanner.scanChannels();

        // Then the returned channels contain a publish ChannelItem and a subscribe ChannelItem
        MessageObject subscribeMessage = MessageObject.builder()
                .name(Integer.class.getName())
                .title(Integer.class.getSimpleName())
                .payload(MessagePayload.of(MessageReference.toSchema(Integer.class.getSimpleName())))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                //                .bindings(messageBinding) FIXME
                .build();

        Operation subscribeOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                //                .operationId("test-out-topic_subscribe_kStreamTestFunction") FIXME
                //                .message(subscribeMessage) FIXME
                .build();

        ChannelObject subscribeChannel = ChannelObject.builder()
                .bindings(channelBinding)
                //                .subscribe(subscribeOperation) FIXME
                .build();

        MessageObject publishMessage = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MessageReference.toSchema(String.class.getSimpleName())))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                //                .bindings(messageBinding) FIXME
                .build();

        Operation publishOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                //                .operationId("test-in-topic_publish_kStreamTestFunction") FIXME
                //                .message(publishMessage) FIXME
                .build();

        ChannelObject publishChannel = ChannelObject.builder()
                .bindings(channelBinding)
                //                .publish(publishOperation) FIXME
                .build();

        assertThat(channels)
                .contains(Map.entry(inputTopicName, publishChannel), Map.entry(outputTopicName, subscribeChannel));
    }

    @Test
    void testFunctionBindingWithSameTopicName() {
        // Given a binding "spring.cloud.stream.bindings.testFunction-in-0.destination=test-topic"
        // And a binding "spring.cloud.stream.bindings.testFunction-out-0.destination=test-topic"
        String topicName = "test-topic";
        BindingProperties testFunctionInBinding = new BindingProperties();
        testFunctionInBinding.setDestination(topicName);

        BindingProperties testFunctionOutBinding = new BindingProperties();
        testFunctionOutBinding.setDestination(topicName);
        when(bindingServiceProperties.getBindings())
                .thenReturn(Map.of(
                        "testFunction-in-0", testFunctionInBinding,
                        "testFunction-out-0", testFunctionOutBinding));

        // When scan is called
        Map<String, ChannelObject> channels = scanner.scanChannels();

        // Then the returned merged channels contain a publish operation and  a subscribe operation
        MessageObject subscribeMessage = MessageObject.builder()
                .name(Integer.class.getName())
                .title(Integer.class.getSimpleName())
                .payload(MessagePayload.of(MessageReference.toSchema(Integer.class.getSimpleName())))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                //                .bindings(messageBinding) FIXME
                .build();

        Operation subscribeOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                //                .operationId("test-topic_subscribe_testFunction") FIXME
                //                .message(subscribeMessage) FIXME
                .build();

        MessageObject publishMessage = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MessageReference.toSchema(String.class.getSimpleName())))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                //                .bindings(messageBinding) FIXME
                .build();

        Operation publishOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                //                .operationId("test-topic_publish_testFunction") FIXME
                //                .message(publishMessage) FIXME
                .build();

        ChannelObject mergedChannel = ChannelObject.builder()
                .bindings(channelBinding)
                //                .publish(publishOperation) FIXME
                //                .subscribe(subscribeOperation) FIXME
                .build();

        assertThat(channels).contains(Map.entry(topicName, mergedChannel));
    }

    @TestConfiguration
    public static class Configuration {

        @Bean
        public Consumer<String> testConsumer() {
            return System.out::println;
        }

        @Bean
        public Supplier<String> testSupplier() {
            return () -> "foo";
        }

        @Bean
        public Function<String, Integer> testFunction() {
            return s -> 1;
        }

        @Bean
        public Function<KStream<Void, String>, KStream<Void, Integer>> kStreamTestFunction() {
            return stream -> stream.mapValues(s -> 1);
        }
    }
}
