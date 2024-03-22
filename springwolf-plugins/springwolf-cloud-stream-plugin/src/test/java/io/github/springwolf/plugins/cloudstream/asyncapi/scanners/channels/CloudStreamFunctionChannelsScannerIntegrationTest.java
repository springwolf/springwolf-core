// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.EmptyChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.EmptyMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.EmptyOperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.DefaultComponentsService;
import io.github.springwolf.core.asyncapi.components.SwaggerSchemaUtil;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ConfigurationClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.core.configuration.docket.DefaultAsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanBuilder;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.operations.CloudStreamFunctionOperationsScanner;
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
import java.util.List;
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
            DefaultComponentsService.class,
            SwaggerSchemaUtil.class,
            PayloadClassExtractor.class,
            DefaultSchemaWalker.class,
            SchemaWalkerProvider.class,
            ExampleJsonValueGenerator.class,
            DefaultAsyncApiDocketService.class,
            CloudStreamFunctionChannelsScanner.class,
            CloudStreamFunctionOperationsScanner.class,
            FunctionalChannelBeanBuilder.class,
            SpringwolfConfigProperties.class
        })
@TestPropertySource(
        properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Test",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.base-package=io.github.springwolf.plugins.cloudstream.asyncapi.scanners.channels",
            "springwolf.docket.servers.kafka.protocol=kafka",
            "springwolf.docket.servers.kafka.host=kafka:9092",
        })
@EnableConfigurationProperties
@Import(CloudStreamFunctionChannelsScannerIntegrationTest.Configuration.class)
class CloudStreamFunctionChannelsScannerIntegrationTest {

    @MockBean
    private BindingServiceProperties bindingServiceProperties;

    @Autowired
    private CloudStreamFunctionChannelsScanner channelsScanner;

    @Autowired
    private CloudStreamFunctionOperationsScanner operationsScanner;

    @Autowired
    private ComponentsService componentsService;

    private Map<String, EmptyMessageBinding> messageBinding = Map.of("kafka", new EmptyMessageBinding());
    private Map<String, OperationBinding> operationBinding = Map.of("kafka", new EmptyOperationBinding());
    private Map<String, ChannelBinding> channelBinding = Map.of("kafka", new EmptyChannelBinding());

    @Test
    void testNoBindings() {
        when(bindingServiceProperties.getBindings()).thenReturn(Collections.emptyMap());
        Map<String, ChannelObject> channels = channelsScanner.scan();
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
        Map<String, ChannelObject> actualChannels = channelsScanner.scan();
        Map<String, Operation> actualOperations = operationsScanner.scan();

        // Then the returned channels contain a ChannelItem with the correct data
        MessageObject message = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("kafka", new EmptyMessageBinding()))
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(message.getName(), MessageReference.toComponentMessage(message)))
                .build();

        Operation expectedOperation = Operation.builder()
                .action(OperationAction.RECEIVE)
                .bindings(operationBinding)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(topicName))
                .messages(List.of(MessageReference.toChannelMessage(topicName, message)))
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(topicName, expectedChannel));
        assertThat(actualOperations)
                .containsExactly(Map.entry("test-consumer-input-topic_publish_testConsumer", expectedOperation));
        assertThat(componentsService.getMessages()).contains(Map.entry(String.class.getName(), message));
    }

    @Test
    void testSupplierBinding() {
        // Given a binding "spring.cloud.stream.bindings.testSupplier-out-0.destination=test-supplier-output-topic"
        BindingProperties testSupplierOutBinding = new BindingProperties();
        String topicName = "test-supplier-output-topic";
        testSupplierOutBinding.setDestination(topicName);
        when(bindingServiceProperties.getBindings()).thenReturn(Map.of("testSupplier-out-0", testSupplierOutBinding));

        // When scan is called
        Map<String, ChannelObject> actualChannels = channelsScanner.scan();
        Map<String, Operation> actualOperations = operationsScanner.scan();

        // Then the returned channels contain a ChannelItem with the correct data

        MessageObject message = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("kafka", new EmptyMessageBinding()))
                .build();

        Operation expectedOperation = Operation.builder()
                .bindings(operationBinding)
                .action(OperationAction.SEND)
                .bindings(operationBinding)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(topicName))
                .messages(List.of(MessageReference.toChannelMessage(topicName, message)))
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(message.getName(), MessageReference.toComponentMessage(message)))
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(topicName, expectedChannel));
        assertThat(actualOperations)
                .containsExactly(Map.entry("test-supplier-output-topic_subscribe_testSupplier", expectedOperation));
        assertThat(componentsService.getMessages()).contains(Map.entry(String.class.getName(), message));
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
        Map<String, ChannelObject> actualChannels = channelsScanner.scan();
        Map<String, Operation> actualOperations = operationsScanner.scan();

        // Then the returned channels contain a publish ChannelItem and a subscribe ChannelItem
        MessageObject subscribeMessage = MessageObject.builder()
                .name(Integer.class.getName())
                .title(Integer.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(Integer.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("kafka", new EmptyMessageBinding()))
                .build();

        Operation subscribeOperation = Operation.builder()
                .bindings(operationBinding)
                .action(OperationAction.SEND)
                .bindings(operationBinding)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(outputTopicName))
                .messages(List.of(MessageReference.toChannelMessage(outputTopicName, subscribeMessage)))
                .build();

        ChannelObject subscribeChannel = ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(subscribeMessage.getName(), MessageReference.toComponentMessage(subscribeMessage)))
                .build();

        MessageObject publishMessage = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("kafka", new EmptyMessageBinding()))
                .build();

        Operation publishOperation = Operation.builder()
                .bindings(operationBinding)
                .action(OperationAction.RECEIVE)
                .bindings(operationBinding)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(inputTopicName))
                .messages(List.of(MessageReference.toChannelMessage(inputTopicName, publishMessage)))
                .build();

        ChannelObject publishChannel = ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(publishMessage.getName(), MessageReference.toComponentMessage(publishMessage)))
                .build();

        assertThat(actualChannels)
                .contains(Map.entry(inputTopicName, publishChannel), Map.entry(outputTopicName, subscribeChannel));
        assertThat(actualOperations)
                .contains(
                        Map.entry("test-in-topic_publish_testFunction", publishOperation),
                        Map.entry("test-out-topic_subscribe_testFunction", subscribeOperation));
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
        Map<String, ChannelObject> actualChannels = channelsScanner.scan();
        Map<String, Operation> actualOperations = operationsScanner.scan();

        // Then the returned channels contain a publish ChannelItem and a subscribe ChannelItem
        MessageObject subscribeMessage = MessageObject.builder()
                .name(Integer.class.getName())
                .title(Integer.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(Integer.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("kafka", new EmptyMessageBinding()))
                .build();

        Operation subscribeOperation = Operation.builder()
                .bindings(operationBinding)
                .action(OperationAction.SEND)
                .bindings(operationBinding)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(outputTopicName))
                .messages(List.of(MessageReference.toChannelMessage(outputTopicName, subscribeMessage)))
                .build();

        ChannelObject subscribeChannel = ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(subscribeMessage.getName(), MessageReference.toComponentMessage(subscribeMessage)))
                .build();

        MessageObject publishMessage = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("kafka", new EmptyMessageBinding()))
                .build();

        Operation publishOperation = Operation.builder()
                .bindings(operationBinding)
                .action(OperationAction.RECEIVE)
                .bindings(operationBinding)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(inputTopicName))
                .messages(List.of(MessageReference.toChannelMessage(inputTopicName, publishMessage)))
                .build();

        ChannelObject publishChannel = ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(publishMessage.getName(), MessageReference.toComponentMessage(publishMessage)))
                .build();

        assertThat(actualChannels)
                .contains(Map.entry(inputTopicName, publishChannel), Map.entry(outputTopicName, subscribeChannel));
        assertThat(actualOperations)
                .contains(
                        Map.entry("test-in-topic_publish_kStreamTestFunction", publishOperation),
                        Map.entry("test-out-topic_subscribe_kStreamTestFunction", subscribeOperation));
        assertThat(componentsService.getMessages()).contains(Map.entry(String.class.getName(), publishMessage));
        assertThat(componentsService.getMessages()).contains(Map.entry(Integer.class.getName(), subscribeMessage));
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
        Map<String, ChannelObject> actualChannels = channelsScanner.scan();
        Map<String, Operation> actualOperations = operationsScanner.scan();

        // Then the returned merged channels contain a publish operation and  a subscribe operation
        MessageObject subscribeMessage = MessageObject.builder()
                .name(Integer.class.getName())
                .title(Integer.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(Integer.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("kafka", new EmptyMessageBinding()))
                .build();

        Operation subscribeOperation = Operation.builder()
                .bindings(operationBinding)
                .action(OperationAction.SEND)
                .bindings(operationBinding)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(topicName))
                .messages(List.of(MessageReference.toChannelMessage(topicName, subscribeMessage)))
                .build();

        MessageObject publishMessage = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("kafka", new EmptyMessageBinding()))
                .build();

        // "test-topic_publish_testFunction"
        Operation publishOperation = Operation.builder()
                .bindings(operationBinding)
                .action(OperationAction.RECEIVE)
                .bindings(operationBinding)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(topicName))
                .messages(List.of(MessageReference.toChannelMessage(topicName, publishMessage)))
                .build();

        ChannelObject mergedChannel = ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(
                        publishMessage.getName(),
                        MessageReference.toComponentMessage(publishMessage),
                        subscribeMessage.getName(),
                        MessageReference.toComponentMessage(subscribeMessage)))
                .build();

        assertThat(actualChannels).contains(Map.entry(topicName, mergedChannel));
        assertThat(actualOperations)
                .contains(
                        Map.entry("test-topic_publish_testFunction", publishOperation),
                        Map.entry("test-topic_subscribe_testFunction", subscribeOperation));
        assertThat(componentsService.getMessages()).contains(Map.entry(String.class.getName(), publishMessage));
        assertThat(componentsService.getMessages()).contains(Map.entry(Integer.class.getName(), subscribeMessage));
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
