// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.asyncapi.v3.model.server.ServerReference;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.DefaultComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.TestOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_MAP;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationMethodLevelChannelsScannerTest {
    private static final String CHANNEL = "test/channel";
    private static final String CHANNEL_ID = ReferenceUtil.toValidId(CHANNEL);

    private final AsyncAnnotationProvider<AsyncListener> asyncAnnotationProvider = new AsyncAnnotationProvider<>() {
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
    private final SwaggerSchemaService schemaService =
            new SwaggerSchemaService(emptyList(), emptyList(), swaggerSchemaUtil, properties);
    private final ComponentsService componentsService = new DefaultComponentsService(schemaService);
    private final AsyncApiDocketService asyncApiDocketService = mock(AsyncApiDocketService.class);
    private final PayloadClassExtractor payloadClassExtractor = new PayloadClassExtractor(properties);
    private final PayloadService payloadService = new PayloadService(componentsService, properties);
    private final PayloadAsyncOperationService payloadAsyncOperationService =
            new PayloadAsyncOperationService(payloadClassExtractor, payloadService);

    private final List<OperationBindingProcessor> operationBindingProcessors =
            List.of(new TestOperationBindingProcessor());
    private final List<MessageBindingProcessor> messageBindingProcessors = emptyList();

    private final StringValueResolverProxy stringValueResolver = mock(StringValueResolverProxy.class);

    private final AsyncAnnotationMethodLevelChannelsScanner<AsyncListener> channelScanner =
            new AsyncAnnotationMethodLevelChannelsScanner<>(
                    asyncAnnotationProvider,
                    componentsService,
                    asyncApiDocketService,
                    payloadAsyncOperationService,
                    operationBindingProcessors,
                    messageBindingProcessors,
                    stringValueResolver);

    @BeforeEach
    public void setup() {
        when(asyncApiDocketService.getAsyncApiDocket())
                .thenReturn(AsyncApiDocket.builder()
                        .info(new Info())
                        .server("server1", new Server())
                        .server("server2", new Server())
                        .build());

        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> switch ((String) invocation.getArgument(0)) {
                    case "${test.property.test-channel}" -> CHANNEL;
                    case "${test.property.description}" -> "description";
                    case "${test.property.server1}" -> "server1";
                    case "${test.property.server2}" -> "server2";
                    default -> invocation.getArgument(0);
                });
    }

    @Test
    void scan_componentHasNoListenerMethods() {
        Map<String, ChannelObject> channels = channelScanner
                .scan(ClassWithoutListenerAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentChannelHasListenerMethod() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        // When
        Map<String, ChannelObject> actualChannels = channelScanner
                .scan(ClassWithListenerAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

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
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(EMPTY_MAP)
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .channelId(CHANNEL_ID)
                .address(CHANNEL)
                .bindings(null)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(CHANNEL_ID, expectedChannel));
    }

    @Test
    void scan_componentHasListenerMethodWithUnknownServer() {
        // Given a class with method annotated with AsyncListener, with an unknown servername
        // when
        assertThatThrownBy(() -> channelScanner
                        .scan(ClassWithListenerAnnotationWithInvalidServer.class)
                        .toList())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(
                        "Operation 'test_channel_send' defines unknown server ref 'server3'. This AsyncApi defines these server(s): [server1, server2]");
    }

    @Test
    void scan_componentHasListenerMethodWithAllAttributes() {
        // Given a class with method annotated with AsyncListener, where all attributes are set
        // When scan is called
        Map<String, ChannelObject> actualChannels = channelScanner
                .scan(ClassWithListenerAnnotationWithAllAttributes.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Then the returned collection contains the channel
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(List.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(List.class.getName())
                .name(List.class.getName())
                .title(List.class.getSimpleName())
                .description(null)
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.toSchema("TestSchema")))
                .bindings(EMPTY_MAP)
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .channelId(CHANNEL_ID)
                .address(CHANNEL)
                .bindings(null)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .servers(List.of(ServerReference.fromServer("server1"), ServerReference.fromServer("server2")))
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(CHANNEL_ID, expectedChannel));
    }

    @Test
    void scan_componentHasMultipleListenerAnnotations() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        // When scan is called
        Map<String, ChannelObject> actualChannels = channelScanner
                .scan(ClassWithMultipleListenerAnnotations.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Then the returned collection contains the channel
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(SimpleFoo.class.getName())
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(payload)
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(EMPTY_MAP)
                .description("SimpleFoo Message Description")
                .build();

        ChannelObject expectedChannel1 = ChannelObject.builder()
                .channelId("test-channel-1")
                .address("test-channel-1")
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .bindings(null)
                .build();

        ChannelObject expectedChannel2 = ChannelObject.builder()
                .channelId("test-channel-2")
                .address("test-channel-2")
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
        // When scan is called
        Map<String, ChannelObject> actualChannels = channelScanner
                .scan(ClassWithMessageAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Then the returned collection contains the channel
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId("simpleFooId")
                .name("SimpleFooPayLoad")
                .title("Message Title")
                .description("Message description")
                .payload(payload)
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(EMPTY_MAP)
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .channelId(CHANNEL_ID)
                .address(CHANNEL)
                .bindings(null)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(CHANNEL_ID, expectedChannel));
    }

    private static class ClassWithoutListenerAnnotation {

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithListenerAnnotation {

        @AsyncListener(operation = @AsyncOperation(channelName = CHANNEL))
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithListenerAnnotationWithInvalidServer {

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = CHANNEL,
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
                                payloadType = List.class,
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
                                channelName = CHANNEL,
                                description = "test channel operation description",
                                message =
                                        @AsyncMessage(
                                                description = "Message description",
                                                messageId = "simpleFooId",
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
            // When scan is called
            Map<String, ChannelObject> actualChannels =
                    channelScanner.scan(clazz).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

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
                    .headers(MessageHeaders.of(
                            MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                    .bindings(EMPTY_MAP)
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .channelId(CHANNEL_ID)
                    .address(CHANNEL)
                    .bindings(null)
                    .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                    .build();

            assertThat(actualChannels).containsExactly(Map.entry(CHANNEL_ID, expectedChannel));
        }

        private static class ClassImplementingInterface implements ClassInterface<String> {

            @AsyncListener(
                    operation =
                            @AsyncOperation(channelName = CHANNEL, description = "test channel operation description"))
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
                            @AsyncOperation(channelName = CHANNEL, description = "test channel operation description"))
            void methodFromInterface(T payload);
        }
    }

    @Nested
    class MetaAnnotation {
        @Test
        void scan_componentHasListenerMethodWithMetaAnnotation() {
            // Given a class with methods annotated with a AsyncListener meta annotation
            // When scan is called
            Map<String, ChannelObject> actualChannels = channelScanner
                    .scan(ClassWithMetaAnnotation.class)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

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
                    .headers(MessageHeaders.of(
                            MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                    .bindings(EMPTY_MAP)
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .channelId(CHANNEL_ID)
                    .address(CHANNEL)
                    .bindings(null)
                    .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                    .build();

            assertThat(actualChannels).containsExactly(Map.entry(CHANNEL_ID, expectedChannel));
        }

        public static class ClassWithMetaAnnotation {
            @AsyncListenerMetaAnnotation
            void methodFromInterface(String payload) {}
        }

        @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
        @Retention(RetentionPolicy.RUNTIME)
        @Inherited
        @AsyncListener(
                operation = @AsyncOperation(channelName = CHANNEL, description = "test channel operation description"))
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
