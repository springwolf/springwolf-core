package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ConfigurationClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.bindings.EmptyChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.EmptyOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.bindings.EmptyMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import org.apache.kafka.streams.kstream.KStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.config.BindingProperties;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ConfigurationClassScanner.class,
        DefaultBeanMethodsScanner.class,
        DefaultSchemasService.class,
        DefaultAsyncApiDocketService.class,
        CloudStreamFunctionChannelsScanner.class
})
@Import(CloudStreamFunctionChannelsScannerTest.Configuration.class)
class CloudStreamFunctionChannelsScannerTest {

    @MockBean
    private BindingServiceProperties bindingServiceProperties;

    @Autowired
    private CloudStreamFunctionChannelsScanner scanner;
    private Map<String, EmptyMessageBinding> messageBinding = Map.of("kafka", new EmptyMessageBinding());
    private Map<String, Object> operationBinding = Map.of("kafka", new EmptyOperationBinding());
    private Map<String, Object> channelBinding = Map.of("kafka", new EmptyChannelBinding());

    @Test
    void testNoBindings() {
        when(bindingServiceProperties.getBindings()).thenReturn(Collections.emptyMap());
        Map<String, ChannelItem> channels = scanner.scan();
        assertThat(channels).isEmpty();
    }

    @Test
    void testConsumerBinding() {
        // Given a binding "spring.cloud.stream.bindings.testConsumer-in-0.destination=test-consumer-input-topic"
        BindingProperties testConsumerInBinding = new BindingProperties();
        String topicName = "test-consumer-input-topic";
        testConsumerInBinding.setDestination(topicName);
        when(bindingServiceProperties.getBindings()).thenReturn(Map.of(
                "testConsumer-in-0", testConsumerInBinding
        ));

        // When scan is called
        Map<String, ChannelItem> channels = scanner.scan();

        // Then the returned channels contain a ChannelItem with the correct data
        Message message = Message.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(messageBinding)
                .build();

        Operation operation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                .operationId("test-consumer-input-topic_publish_testConsumer")
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(channelBinding)
                .publish(operation)
                .build();

        assertThat(channels)
                .containsExactly(Map.entry(topicName, expectedChannel));
    }


    @Test
    void testSupplierBinding() {
        // Given a binding "spring.cloud.stream.bindings.testSupplier-out-0.destination=test-supplier-output-topic"
        BindingProperties testSupplierOutBinding = new BindingProperties();
        String topicName = "test-supplier-output-topic";
        testSupplierOutBinding.setDestination(topicName);
        when(bindingServiceProperties.getBindings()).thenReturn(Map.of(
                "testSupplier-out-0", testSupplierOutBinding
        ));

        // When scan is called
        Map<String, ChannelItem> channels = scanner.scan();

        // Then the returned channels contain a ChannelItem with the correct data

        Message message = Message.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(messageBinding)
                .build();

        Operation operation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                .operationId("test-supplier-output-topic_subscribe_testSupplier")
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(channelBinding)
                .subscribe(operation)
                .build();

        assertThat(channels)
                .containsExactly(Map.entry(topicName, expectedChannel));
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
        testFunctionOutBinding.setDestination(outputTopicName)
        ;
        when(bindingServiceProperties.getBindings()).thenReturn(Map.of(
                "testFunction-in-0", testFunctionInBinding,
                "testFunction-out-0", testFunctionOutBinding
        ));

        // When scan is called
        Map<String, ChannelItem> channels = scanner.scan();

        // Then the returned channels contain a publish ChannelItem and a subscribe ChannelItem
        Message subscribeMessage = Message.builder()
                .name(Integer.class.getName())
                .title(Integer.class.getSimpleName())
                .payload(PayloadReference.fromModelName(Integer.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(messageBinding)
                .build();

        Operation subscribeOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                .operationId("test-out-topic_subscribe_testFunction")
                .message(subscribeMessage)
                .build();

        ChannelItem subscribeChannel = ChannelItem.builder()
                .bindings(channelBinding)
                .subscribe(subscribeOperation)
                .build();


        Message publishMessage = Message.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(messageBinding)
                .build();

        Operation publishOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                .operationId("test-in-topic_publish_testFunction")
                .message(publishMessage)
                .build();

        ChannelItem publishChannel = ChannelItem.builder()
                .bindings(channelBinding)
                .publish(publishOperation)
                .build();

        assertThat(channels).contains(
                Map.entry(inputTopicName, publishChannel),
                Map.entry(outputTopicName, subscribeChannel)
        );
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
        testFunctionOutBinding.setDestination(outputTopicName)
        ;
        when(bindingServiceProperties.getBindings()).thenReturn(Map.of(
                "kStreamTestFunction-in-0", testFunctionInBinding,
                "kStreamTestFunction-out-0", testFunctionOutBinding
        ));

        // When scan is called
        Map<String, ChannelItem> channels = scanner.scan();

        // Then the returned channels contain a publish ChannelItem and a subscribe ChannelItem
        Message subscribeMessage = Message.builder()
                .name(Integer.class.getName())
                .title(Integer.class.getSimpleName())
                .payload(PayloadReference.fromModelName(Integer.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(messageBinding)
                .build();

        Operation subscribeOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                .operationId("test-out-topic_subscribe_kStreamTestFunction")
                .message(subscribeMessage)
                .build();

        ChannelItem subscribeChannel = ChannelItem.builder()
                .bindings(channelBinding)
                .subscribe(subscribeOperation)
                .build();


        Message publishMessage = Message.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(messageBinding)
                .build();

        Operation publishOperation = Operation.builder()
                .bindings(operationBinding)
                .description("Auto-generated description")
                .operationId("test-in-topic_publish_kStreamTestFunction")
                .message(publishMessage)
                .build();

        ChannelItem publishChannel = ChannelItem.builder()
                .bindings(channelBinding)
                .publish(publishOperation)
                .build();

        assertThat(channels).contains(
                Map.entry(inputTopicName, publishChannel),
                Map.entry(outputTopicName, subscribeChannel)
        );
    }


    @TestConfiguration
    public static class Configuration {

        @Bean
        public AsyncApiDocket docket() {
            Info info = Info.builder()
                    .title("Test")
                    .version("1.0.0")
                    .build();

            return AsyncApiDocket.builder()
                    .info(info)
                    .basePackage(this.getClass().getPackage().getName())
                    .server("kafka", Server.builder().protocol("kafka").url("kafka:9092").build())
                    .build();
        }

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
