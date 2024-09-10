// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.server.Server;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AsyncAnnotationMethodLevelOperationsScannerTest {

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
    private final OperationCustomizer operationCustomizer = mock(OperationCustomizer.class);

    private final StringValueResolverProxy stringValueResolver = mock(StringValueResolverProxy.class);

    private final AsyncAnnotationMethodLevelOperationsScanner<AsyncListener> operationsScanner =
            new AsyncAnnotationMethodLevelOperationsScanner<>(
                    asyncAnnotationProvider,
                    componentsService,
                    payloadAsyncOperationService,
                    operationBindingProcessors,
                    messageBindingProcessors,
                    List.of(operationCustomizer),
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
                    case "${test.property.test-channel}" -> "test-channel";
                    case "${test.property.description}" -> "description";
                    case "${test.property.server1}" -> "server1";
                    case "${test.property.server2}" -> "server2";
                    default -> invocation.getArgument(0);
                });
    }

    @Test
    void scan_componentHasNoListenerMethods() {
        Map<String, Operation> channels = operationsScanner
                .scan(ClassWithoutListenerAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentOperationHasListenerMethod() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        // When scan is called
        Map<String, Operation> actualOperations = operationsScanner
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

        Operation expectedOperation = Operation.builder()
                .title("test-channel_send")
                .action(OperationAction.SEND)
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel("test-channel"))
                .messages(List.of(MessageReference.toChannelMessage("test-channel", message)))
                .bindings(EMPTY_MAP)
                .build();

        assertThat(actualOperations)
                .containsExactly(Map.entry("test-channel_send_methodWithAnnotation", expectedOperation));
    }

    @Test
    void scan_componentHasListenerMethodWithAllAttributes() {
        // Given a class with method annotated with AsyncListener, where all attributes are set
        // When scan is called
        Map<String, Operation> actualOperations = operationsScanner
                .scan(ClassWithListenerAnnotationWithAllAttributes.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

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

        Operation expectedOperation = Operation.builder()
                .action(OperationAction.SEND)
                .title("test-channel_send")
                .channel(ChannelReference.fromChannel("test-channel"))
                .description("description")
                .bindings(Map.of(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING))
                .messages(List.of(MessageReference.toChannelMessage("test-channel", message)))
                .build();

        assertThat(actualOperations)
                .containsExactly(Map.entry("test-channel_send_methodWithAnnotation", expectedOperation));
    }

    @Test
    void scan_componentHasMultipleListenerAnnotations() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        // When scan is called
        Map<String, Operation> actualOperations = operationsScanner
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

        Operation expectedOperation1 = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel("test-channel-1"))
                .description("test-channel-1-description")
                .title("test-channel-1_send")
                .bindings(EMPTY_MAP)
                .messages(List.of(MessageReference.toChannelMessage("test-channel-1", message)))
                .build();

        Operation expectedOperation2 = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel("test-channel-2"))
                .description("test-channel-2-description")
                .title("test-channel-2_send")
                .bindings(EMPTY_MAP)
                .messages(List.of(MessageReference.toChannelMessage("test-channel-2", message)))
                .build();

        assertThat(actualOperations)
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        "test-channel-1_send_methodWithMultipleAnnotation", expectedOperation1,
                        "test-channel-2_send_methodWithMultipleAnnotation", expectedOperation2));
    }

    @Test
    void scan_componentHasAsyncMethodAnnotation() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        // When scan is called
        Map<String, Operation> actualOperations = operationsScanner
                .scan(ClassWithMessageAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

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
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
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

        assertThat(actualOperations)
                .containsExactly(Map.entry("test-channel_send_methodWithAnnotation", expectedOperation));
    }

    @Test
    void scan_componentHasAsyncMethodAnnotationInAbstractClass() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        // When scan is called
        Map<String, Operation> actualOperations = operationsScanner
                .scan(ClassExtendsFromAbstractWithListenerAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Then the returned collection contains the channel
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId("simpleFooAbstract")
                .name("SimpleFooPayLoad")
                .title("Message Title")
                .description("Message description")
                .payload(payload)
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(EMPTY_MAP)
                .build();

        Operation expectedOperation = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel("abstract-test-channel"))
                .description("test abstract channel operation description")
                .title("abstract-test-channel_send")
                .bindings(EMPTY_MAP)
                .messages(List.of(MessageReference.toChannelMessage("abstract-test-channel", message)))
                .build();

        assertThat(actualOperations)
                .containsExactly(Map.entry("abstract-test-channel_send_methodWithAnnotation", expectedOperation));
    }

    @Test
    void operationCustomizerIsCalled() {
        // when
        operationsScanner
                .scan(ClassWithListenerAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // then
        verify(operationCustomizer).customize(any(), any());
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

    private static class ClassExtendsFromAbstractWithListenerAnnotation extends AbstractClassWithListenerAnnotation {}

    private abstract static class AbstractClassWithListenerAnnotation {

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "abstract-test-channel",
                                description = "test abstract channel operation description",
                                message =
                                        @AsyncMessage(
                                                description = "Message description",
                                                messageId = "simpleFooAbstract",
                                                name = "SimpleFooPayLoad",
                                                contentType = "application/json",
                                                title = "Message Title")))
        protected void methodWithAnnotation(SimpleFoo payload) {}
    }

    @Nested
    class ImplementingInterface {
        @ParameterizedTest
        @ValueSource(classes = {ClassImplementingInterface.class, ClassImplementingInterfaceWithAnnotation.class})
        void scan_componentHasOnlyDeclaredMethods(Class<?> clazz) {
            // Given a class with a method, which is declared in a generic interface
            // When scan is called
            Map<String, Operation> actualOperations =
                    operationsScanner.scan(clazz).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

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

            assertThat(actualOperations)
                    .containsExactly(Map.entry("test-channel_send_methodFromInterface", expectedOperation));
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
            // When scan is called
            Map<String, Operation> actualOperations = operationsScanner
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

            assertThat(actualOperations)
                    .containsExactly(Map.entry("test-channel_send_methodFromInterface", expectedOperation));
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
