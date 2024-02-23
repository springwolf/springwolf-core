// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotation;

import io.github.springwolf.core.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.TestOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncMessage;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.springwolf.core.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.springwolf.core.configuration.AsyncApiDocket;
import io.github.springwolf.core.configuration.AsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.schemas.ComponentsService;
import io.github.springwolf.core.schemas.DefaultComponentsService;
import io.github.springwolf.core.schemas.SwaggerSchemaUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ServerReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.info.Info;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.server.Server;
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

    private final AsyncAnnotationScanner.AsyncAnnotationProvider<AsyncListener> asyncAnnotationProvider =
            new AsyncAnnotationScanner.AsyncAnnotationProvider<>() {
                @Override
                public Class<AsyncListener> getAnnotation() {
                    return AsyncListener.class;
                }

                @Override
                public AsyncOperation getAsyncOperation(AsyncListener annotation) {
                    return annotation.operation();
                }

                @Override
                public OperationAction getOperationType() {
                    return OperationAction.SEND;
                }
            };
    private final SwaggerSchemaUtil swaggerSchemaUtil = new SwaggerSchemaUtil();
    private final SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
    private final ClassScanner classScanner = mock(ClassScanner.class);
    private final ComponentsService componentsService =
            new DefaultComponentsService(emptyList(), emptyList(), swaggerSchemaUtil, properties);

    private final AsyncApiDocketService asyncApiDocketService = mock(AsyncApiDocketService.class);
    private final PayloadClassExtractor payloadClassExtractor = new PayloadClassExtractor(properties);

    private final List<OperationBindingProcessor> operationBindingProcessors =
            List.of(new TestOperationBindingProcessor());
    private final List<MessageBindingProcessor> messageBindingProcessors = emptyList();

    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);

    private final AsyncAnnotationChannelsScanner<AsyncListener> channelScanner = new AsyncAnnotationChannelsScanner<>(
            asyncAnnotationProvider,
            classScanner,
            componentsService,
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

        Map<String, ChannelObject> channels = channelScanner.scan();

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentChannelHasListenerMethod() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithListenerAnnotation.class);

        // When scan is called
        Map<String, ChannelObject> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(SimpleFoo.class.getName())
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .description("SimpleFoo Message Description")
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                .bindings(EMPTY_MAP)
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(null)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .build();

        assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
    }

    @Test
    void scan_componentHasListenerMethodWithUnknownServer() {
        // Given a class with method annotated with AsyncListener, with an unknown servername
        setClassToScan(ClassWithListenerAnnotationWithInvalidServer.class);

        assertThatThrownBy(channelScanner::scan)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(
                        "Operation 'test-channel_send' defines unknown server ref 'server3'. This AsyncApi defines these server(s): [server1, server2]");
    }

    @Test
    void scan_componentHasListenerMethodWithAllAttributes() {
        // Given a class with method annotated with AsyncListener, where all attributes are set
        setClassToScan(ClassWithListenerAnnotationWithAllAttributes.class);

        // When scan is called
        Map<String, ChannelObject> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(String.class.getName())
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .description(null)
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.toSchema("TestSchema")))
                .bindings(EMPTY_MAP)
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(null)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .servers(List.of(
                        ServerReference.builder().ref("server1").build(),
                        ServerReference.builder().ref("server2").build()))
                .build();

        Operation expectedOperation = Operation.builder()
                .action(OperationAction.SEND)
                .title("test-channel_send")
                .channel(ChannelReference.fromChannel("test-channel"))
                .description("description")
                .bindings(Map.of(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING))
                .messages(List.of(MessageReference.toChannelMessage("test-channel", message)))
                .build();

        assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
    }

    @Test
    void scan_componentHasMultipleListenerAnnotations() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithMultipleListenerAnnotations.class);

        // When scan is called
        Map<String, ChannelObject> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(SimpleFoo.class.getName())
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                .bindings(EMPTY_MAP)
                .description("SimpleFoo Message Description")
                .build();

        Operation expectedOperation1 = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel("test-channel-1"))
                .description("test-channel-1-description")
                .title("test-channel-1_send")
                .bindings(EMPTY_MAP)
                .messages(List.of(MessageReference.toChannelMessage("test-channel-1", message)))
                .build();

        ChannelObject expectedChannel1 = ChannelObject.builder()
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .bindings(null)
                .build();

        Operation expectedOperation2 = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel("test-channel-2"))
                .description("test-channel-2-description")
                .title("test-channel-2_send")
                .bindings(EMPTY_MAP)
                .messages(List.of(MessageReference.toChannelMessage("test-channel-2", message)))
                .build();

        ChannelObject expectedChannel2 = ChannelObject.builder()
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .bindings(null)
                .build();

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
        Map<String, ChannelObject> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId("simpleFoo")
                .name("SimpleFooPayLoad")
                .title("Message Title")
                .description("Message description")
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                .bindings(EMPTY_MAP)
                .build();

        Operation expectedOperation = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel("test-channel"))
                .description("test channel operation description")
                .title("test-channel_send")
                .bindings(EMPTY_MAP)
                .messages(List.of(MessageReference.toChannelMessage("test-channel", message)))
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(null)
                .messages(Map.of(message.getName(), MessageReference.toComponentMessage(message)))
                .build();

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
                                                contentType = "application/json",
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
            Map<String, ChannelObject> actualChannels = channelScanner.scan();

            // Then the returned collection contains the channel with the actual method, excluding type erased methods
            var messagePayload = MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                    .build());
            MessageObject message = MessageObject.builder()
                    .messageId(String.class.getName())
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    .description(null)
                    .payload(messagePayload)
                    .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                    .bindings(EMPTY_MAP)
                    .build();

            Operation expectedOperation = Operation.builder()
                    .action(OperationAction.SEND)
                    .channel(ChannelReference.fromChannel("test-channel"))
                    .description("test channel operation description")
                    .title("test-channel_send")
                    .bindings(EMPTY_MAP)
                    .messages(List.of(MessageReference.toChannelMessage("test-channel", message)))
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(null)
                    .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                    .build();

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
            Map<String, ChannelObject> actualChannels = channelScanner.scan();

            // Then the returned collection contains the channel
            var messagePayload = MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                    .build());

            MessageObject message = MessageObject.builder()
                    .messageId(String.class.getName())
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    .description(null)
                    .payload(messagePayload)
                    .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                    .bindings(EMPTY_MAP)
                    .build();

            Operation expectedOperation = Operation.builder()
                    .action(OperationAction.SEND)
                    .channel(ChannelReference.fromChannel("test-channel"))
                    .description("test channel operation description")
                    .title("test-channel_send")
                    .bindings(EMPTY_MAP)
                    .messages(List.of(MessageReference.toChannelMessage("test-channel", message)))
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(null)
                    .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                    .build();

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
