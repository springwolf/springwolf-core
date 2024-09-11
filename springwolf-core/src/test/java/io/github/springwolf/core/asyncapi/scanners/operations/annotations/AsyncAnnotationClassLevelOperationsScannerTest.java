// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

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
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.DefaultComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.TestOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeToClassConverter;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.oas.annotations.Hidden;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_MAP;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AsyncAnnotationClassLevelOperationsScannerTest {

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
            return OperationAction.RECEIVE;
        }
    };
    private final SwaggerSchemaUtil swaggerSchemaUtil = new SwaggerSchemaUtil();
    private final SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
    private final SwaggerSchemaService schemaService =
            new SwaggerSchemaService(emptyList(), emptyList(), swaggerSchemaUtil, properties);
    private final ComponentsService componentsService = new DefaultComponentsService(schemaService);
    private final AsyncApiDocketService asyncApiDocketService = mock(AsyncApiDocketService.class);
    private final TypeToClassConverter typeToClassConverter = new TypeToClassConverter(properties);
    private final PayloadClassExtractor payloadClassExtractor = new PayloadClassExtractor(typeToClassConverter);
    private final PayloadService payloadService = new PayloadService(componentsService, properties);
    private final PayloadAsyncOperationService payloadAsyncOperationService =
            new PayloadAsyncOperationService(payloadClassExtractor, payloadService);

    private final List<OperationBindingProcessor> operationBindingProcessors =
            List.of(new TestOperationBindingProcessor());
    private final List<MessageBindingProcessor> messageBindingProcessors = emptyList();
    private final OperationCustomizer operationCustomizer = mock(OperationCustomizer.class);

    private final StringValueResolverProxy stringValueResolver = mock(StringValueResolverProxy.class);

    private final AsyncAnnotationClassLevelOperationsScanner<AsyncListener> operationsScanner =
            new AsyncAnnotationClassLevelOperationsScanner<>(
                    AsyncListener.class,
                    asyncAnnotationProvider,
                    operationBindingProcessors,
                    new AsyncAnnotationMessageService(
                            payloadAsyncOperationService,
                            componentsService,
                            messageBindingProcessors,
                            stringValueResolver),
                    List.of(operationCustomizer),
                    stringValueResolver);

    @BeforeEach
    public void setup() {
        when(asyncApiDocketService.getAsyncApiDocket())
                .thenReturn(AsyncApiDocket.builder().info(new Info()).build());

        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> switch ((String) invocation.getArgument(0)) {
                    case "${test.property.test-channel}" -> "test-channel";
                    case "${test.property.description}" -> "description";
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
        MessagePayload stringPayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(String.class.getName()))
                .build());
        MessagePayload numberPayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(Number.class.getName()))
                .build());

        MessageObject stringMessage = MessageObject.builder()
                .messageId(String.class.getName())
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .description(null)
                .payload(stringPayload)
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(EMPTY_MAP)
                .build();
        MessageObject integerMessage = MessageObject.builder()
                .messageId(Number.class.getName())
                .name(Number.class.getName())
                .title(Integer.class.getSimpleName())
                .description(null)
                .payload(numberPayload)
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(EMPTY_MAP)
                .build();

        Operation expectedOperation = Operation.builder()
                .title("test-channel_receive")
                .action(OperationAction.RECEIVE)
                .description("description")
                .channel(ChannelReference.fromChannel("test-channel"))
                .messages(List.of(
                        MessageReference.toChannelMessage("test-channel", integerMessage),
                        MessageReference.toChannelMessage("test-channel", stringMessage)))
                .bindings(EMPTY_MAP)
                .build();

        assertThat(actualOperations)
                .containsExactly(Map.entry("test-channel_receive_ClassWithListenerAnnotation", expectedOperation));

        assertThat(componentsService.getMessages())
                .containsExactly(
                        Map.entry(stringMessage.getMessageId(), stringMessage),
                        Map.entry(integerMessage.getMessageId(), integerMessage));
    }

    @Test
    void operationCustomizerIsCalled() {
        // when
        operationsScanner.scan(ClassWithListenerAnnotation.class).toList();

        // then
        verify(operationCustomizer, times(2)).customize(any(), any());
    }

    private static class ClassWithoutListenerAnnotation {

        private void methodWithoutAnnotation() {}
    }

    @AsyncListener(
            operation = @AsyncOperation(channelName = "test-channel", description = "${test.property.description}"))
    private static class ClassWithListenerAnnotation {
        private void method(String payload) {}

        private void secondMethod(Integer payload) {}

        @Hidden
        private void methodIsHidden() {}
    }
}
