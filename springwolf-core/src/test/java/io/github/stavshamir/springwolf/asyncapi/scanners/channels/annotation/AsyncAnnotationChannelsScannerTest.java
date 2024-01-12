// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.TestOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncMessage;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.EMPTY_MAP;
import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationChannelsScannerTest {

    private AsyncAnnotationChannelsScanner.AsyncAnnotationProvider<AsyncListener> asyncAnnotationProvider =
            new AsyncAnnotationChannelsScanner.AsyncAnnotationProvider<>() {
                @Override
                public Class<AsyncListener> getAnnotation() {
                    return AsyncListener.class;
                }

                @Override
                public AsyncOperation getAsyncOperation(AsyncListener annotation) {
                    return annotation.operation();
                }

                @Override
                public OperationData.OperationType getOperationType() {
                    return OperationData.OperationType.PUBLISH;
                }
            };
    private final SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
    private final ClassScanner classScanner = mock(ClassScanner.class);
    private final SchemasService schemasService = new DefaultSchemasService(emptyList(), emptyList(), properties);
    private final AsyncApiDocketService asyncApiDocketService = mock(AsyncApiDocketService.class);
    private final PayloadClassExtractor payloadClassExtractor = new PayloadClassExtractor(properties);

    private final List<OperationBindingProcessor> operationBindingProcessors =
            List.of(new TestOperationBindingProcessor());
    private final List<MessageBindingProcessor> messageBindingProcessors = emptyList();

    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);

    private final AsyncAnnotationChannelsScanner<AsyncListener> channelScanner = new AsyncAnnotationChannelsScanner<>(
            asyncAnnotationProvider,
            classScanner,
            schemasService,
            asyncApiDocketService,
            payloadClassExtractor,
            operationBindingProcessors,
            messageBindingProcessors);

    @BeforeEach
    public void setup() {
        when(asyncApiDocketService.getAsyncApiDocket())
                .thenReturn(AsyncApiDocket.builder()
                        .info(new Info())
                        .server("server1", new Server())
                        .server("server2", new Server())
                        .build());

        channelScanner.setEmbeddedValueResolver(stringValueResolver);
        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> switch ((String) invocation.getArgument(0)) {
                    case "${test.property.test-channel}" -> "test-channel";
                    case "${test.property.description}" -> "description";
                    case "${test.property.server1}" -> "server1";
                    case "${test.property.server2}" -> "server2";
                    default -> invocation.getArgument(0);
                });
    }

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(classScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    void scan_componentHasNoListenerMethods() {
        setClassToScan(ClassWithoutListenerAnnotation.class);

        Map<String, ChannelItem> channels = channelScanner.scan();

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentHasListenerMethod() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithListenerAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .description("SimpleFoo Message Description")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .schemaFormat(Message.DEFAULT_SCHEMA_FORMAT)
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel_publish")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel =
                ChannelItem.builder().bindings(null).publish(operation).build();

        assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
    }

    @Test
    void scan_componentHasListenerMethodWithUnknownServer() {
        // Given a class with method annotated with AsyncListener, with an unknown servername
        setClassToScan(ClassWithListenerAnnotationWithInvalidServer.class);

        assertThatThrownBy(() -> channelScanner.scan())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(
                        "Operation 'test-channel_publish' defines unknown server ref 'server3'. This AsyncApi defines these server(s): [server1, server2]");
    }

    @Test
    void scan_componentHasListenerMethodWithAllAttributes() {
        // Given a class with method annotated with AsyncListener, where all attributes are set
        setClassToScan(ClassWithListenerAnnotationWithAllAttributes.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .description(null)
                .schemaFormat(Message.DEFAULT_SCHEMA_FORMAT)
                .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("TestSchema"))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation = Operation.builder()
                .description("description")
                .operationId("test-channel_publish")
                .bindings(Map.of(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(null)
                .servers(List.of("server1", "server2"))
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
    }

    @Test
    void scan_componentHasMultipleListenerAnnotations() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithMultipleListenerAnnotations.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message.MessageBuilder builder = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .schemaFormat(Message.DEFAULT_SCHEMA_FORMAT)
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(EMPTY_MAP);

        Operation operation1 = Operation.builder()
                .description("test-channel-1-description")
                .operationId("test-channel-1_publish")
                .bindings(EMPTY_MAP)
                .message(builder.description("SimpleFoo Message Description").build())
                .build();

        ChannelItem expectedChannel1 =
                ChannelItem.builder().bindings(null).publish(operation1).build();

        Operation operation2 = Operation.builder()
                .description("test-channel-2-description")
                .operationId("test-channel-2_publish")
                .bindings(EMPTY_MAP)
                .message(builder.description("SimpleFoo Message Description").build())
                .build();

        ChannelItem expectedChannel2 =
                ChannelItem.builder().bindings(null).publish(operation2).build();

        assertThat(actualChannels)
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        "test-channel-1", expectedChannel1,
                        "test-channel-2", expectedChannel2));
    }

    @Test
    void scan_componentHasAsyncMethodAnnotation() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithMessageAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .messageId("simpleFoo")
                .name("SimpleFooPayLoad")
                .title("Message Title")
                .description("Message description")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .schemaFormat("application/schema+json;version=draft-07")
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation = Operation.builder()
                .description("test channel operation description")
                .operationId("test-channel_publish")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel =
                ChannelItem.builder().bindings(null).publish(operation).build();

        assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
    }

    private static class ClassWithoutListenerAnnotation {

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithListenerAnnotation {

        @AsyncListener(operation = @AsyncOperation(channelName = "test-channel"))
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithListenerAnnotationWithInvalidServer {

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "test-channel",
                                description = "test channel operation description",
                                servers = {"server3"}))
        private void methodWithAnnotation(SimpleFoo payload) {}
    }

    private static class ClassWithListenerAnnotationWithAllAttributes {

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                description = "${test.property.description}",
                                payloadType = String.class,
                                servers = {"${test.property.server1}", "${test.property.server2}"},
                                headers =
                                        @AsyncOperation.Headers(
                                                schemaName = "TestSchema",
                                                values = {
                                                    @AsyncOperation.Headers.Header(name = "header", value = "value")
                                                })))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithMultipleListenerAnnotations {

        @AsyncListener(
                operation = @AsyncOperation(channelName = "test-channel-1", description = "test-channel-1-description"))
        @AsyncListener(
                operation = @AsyncOperation(channelName = "test-channel-2", description = "test-channel-2-description"))
        private void methodWithMultipleAnnotation(SimpleFoo payload) {}
    }

    private static class ClassWithMessageAnnotation {

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "test-channel",
                                description = "test channel operation description",
                                message =
                                        @AsyncMessage(
                                                description = "Message description",
                                                messageId = "simpleFoo",
                                                name = "SimpleFooPayLoad",
                                                schemaFormat = "application/schema+json;version=draft-07",
                                                title = "Message Title")))
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    @Nested
    class ImplementingInterface {
        @ParameterizedTest
        @ValueSource(classes = {ClassImplementingInterface.class, ClassImplementingInterfaceWithAnnotation.class})
        void scan_componentHasOnlyDeclaredMethods(Class<?> clazz) {
            // Given a class with a method, which is declared in a generic interface
            setClassToScan(clazz);

            // When scan is called
            Map<String, ChannelItem> actualChannels = channelScanner.scan();

            // Then the returned collection contains the channel with the actual method, excluding type erased methods
            Message message = Message.builder()
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    .description(null)
                    .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                    .schemaFormat("application/vnd.oai.openapi+json;version=3.0.0")
                    .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(EMPTY_MAP)
                    .build();

            Operation operation = Operation.builder()
                    .description("test channel operation description")
                    .operationId("test-channel_publish")
                    .bindings(EMPTY_MAP)
                    .message(message)
                    .build();

            ChannelItem expectedChannel =
                    ChannelItem.builder().bindings(null).publish(operation).build();

            assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
        }

        private static class ClassImplementingInterface implements ClassInterface<String> {

            @AsyncListener(
                    operation =
                            @AsyncOperation(
                                    channelName = "test-channel",
                                    description = "test channel operation description"))
            @Override
            public void methodFromInterface(String payload) {}
        }

        interface ClassInterface<T> {
            void methodFromInterface(T payload);
        }

        private static class ClassImplementingInterfaceWithAnnotation implements ClassInterfaceWithAnnotation<String> {

            @Override
            public void methodFromInterface(String payload) {}
        }

        interface ClassInterfaceWithAnnotation<T> {
            @AsyncListener(
                    operation =
                            @AsyncOperation(
                                    channelName = "test-channel",
                                    description = "test channel operation description"))
            void methodFromInterface(T payload);
        }
    }

    @Nested
    class MetaAnnotation {
        @Test
        void scan_componentHasListenerMethodWithMetaAnnotation() {
            // Given a class with methods annotated with a AsyncListener meta annotation
            setClassToScan(ClassWithMetaAnnotation.class);

            // When scan is called
            Map<String, ChannelItem> actualChannels = channelScanner.scan();

            // Then the returned collection contains the channel
            Message message = Message.builder()
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    .description(null)
                    .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                    .schemaFormat("application/vnd.oai.openapi+json;version=3.0.0")
                    .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(EMPTY_MAP)
                    .build();

            Operation operation = Operation.builder()
                    .description("test channel operation description")
                    .operationId("test-channel_publish")
                    .bindings(EMPTY_MAP)
                    .message(message)
                    .build();

            ChannelItem expectedChannel =
                    ChannelItem.builder().bindings(null).publish(operation).build();

            assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
        }

        public static class ClassWithMetaAnnotation {
            @AsyncListenerMetaAnnotation
            void methodFromInterface(String payload) {}
        }

        @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
        @Retention(RetentionPolicy.RUNTIME)
        @Inherited
        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "test-channel",
                                description = "test channel operation description"))
        public @interface AsyncListenerMetaAnnotation {}
    }

    @Data
    @NoArgsConstructor
    @Schema(description = "SimpleFoo Message Description")
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }
}
