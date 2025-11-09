// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.annotation;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.TestAbstractChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.TestAbstractOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.TestChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.TestMessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.TestOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationUtilTest {
    StringValueResolver stringValueResolver = mock(StringValueResolver.class);

    @ParameterizedTest
    @ValueSource(classes = {ClassWithOperationBindingProcessor.class, ClassWithAbstractOperationBindingProcessor.class})
    void getAsyncHeaders(Class<?> classWithOperationBindingProcessor) throws Exception {
        // given
        Method m = classWithOperationBindingProcessor.getDeclaredMethod("methodWithAnnotation", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString() + "Resolved");

        // when
        SchemaObject headers = AsyncAnnotationUtil.getAsyncHeaders(operation, stringValueResolver);

        // then
        assertThat(headers.getTitle()).isEqualTo("TestSchema");
        assertThat(headers.getDescription()).isEqualTo("header-descriptionResolved");
        assertThat(headers.getProperties().containsKey("headerResolved"))
                .as(headers.getProperties() + " does not contain key 'headerResolved'")
                .isTrue();
        SchemaObject headerResolved = (SchemaObject) headers.getProperties().get("headerResolved");
        assertThat(headerResolved.getType()).containsExactly("string");
        assertThat(headerResolved.getExamples().get(0)).isEqualTo("valueResolved");
        assertThat(headerResolved.getDescription()).isEqualTo("descriptionResolved");
        assertThat(headerResolved.getFormat()).isEqualTo("int32Resolved");

        assertThat(headers.getProperties().containsKey("headerWithoutValueResolved"))
                .as(headers.getProperties() + " does not contain key 'headerWithoutValueResolved'")
                .isTrue();
        SchemaObject headerWithoutValueResolved =
                (SchemaObject) headers.getProperties().get("headerWithoutValueResolved");
        assertThat(headerWithoutValueResolved.getType()).containsExactly("string");
        assertThat(headerWithoutValueResolved.getExamples()).isNull();
        assertThat(headerWithoutValueResolved.getEnumValues()).isNull();
        assertThat(headerWithoutValueResolved.getDescription()).isEqualTo("descriptionResolved");
        assertThat(headerWithoutValueResolved.getFormat()).isEqualTo("Resolved");
    }

    @Test
    void getAsyncHeadersWithEmptyHeaders() throws Exception {
        // given
        Method m = ClassWithHeaders.class.getDeclaredMethod("emptyHeaders", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver stringValueResolver = mock(StringValueResolver.class);
        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString() + "Resolved");

        // when
        SchemaObject headers = AsyncAnnotationUtil.getAsyncHeaders(operation, stringValueResolver);

        // then
        assertThat(headers).isEqualTo(AsyncHeadersNotDocumented.NOT_DOCUMENTED);
    }

    @Test
    void getAsyncHeadersWithoutSchemaName() throws Exception {
        // given
        Method m = ClassWithHeaders.class.getDeclaredMethod("withoutSchemaName", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver stringValueResolver = mock(StringValueResolver.class);
        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString() + "Resolved");

        // when
        SchemaObject headers = AsyncAnnotationUtil.getAsyncHeaders(operation, stringValueResolver);

        // then
        assertThat(headers)
                .isEqualTo(SchemaObject.builder()
                        .type(SchemaType.OBJECT)
                        .title("Headers-1585401221")
                        .properties(Map.of(
                                "headerResolved",
                                SchemaObject.builder()
                                        .type(SchemaType.STRING)
                                        .title("headerResolved")
                                        .description("descriptionResolved")
                                        .format("Resolved")
                                        .enumValues(List.of("valueResolved"))
                                        .examples(List.of("valueResolved"))
                                        .build()))
                        .build());
    }

    @Test
    void getAsyncHeadersWithoutValue() throws Exception {
        // given
        Method m = ClassWithHeaders.class.getDeclaredMethod("withoutValue", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver stringValueResolver = mock(StringValueResolver.class);
        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString() + "Resolved");

        // when
        SchemaObject headers = AsyncAnnotationUtil.getAsyncHeaders(operation, stringValueResolver);

        // then
        assertThat(headers)
                .isEqualTo(SchemaObject.builder()
                        .type(SchemaType.OBJECT)
                        .title("Headers-1612438838")
                        .properties(Map.of(
                                "headerResolved",
                                SchemaObject.builder()
                                        .type(SchemaType.STRING)
                                        .title("headerResolved")
                                        .description("descriptionResolved")
                                        .format("Resolved")
                                        .enumValues(null)
                                        .examples(null)
                                        .build()))
                        .build());
    }

    @Test
    void getAsyncHeadersWithFormat() throws Exception {
        // given
        Method m = ClassWithHeaders.class.getDeclaredMethod("withFormat", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver stringValueResolver = mock(StringValueResolver.class);
        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString() + "Resolved");

        // when
        SchemaObject headers = AsyncAnnotationUtil.getAsyncHeaders(operation, stringValueResolver);

        // then
        assertThat(headers)
                .isEqualTo(SchemaObject.builder()
                        .type(SchemaType.OBJECT)
                        .title("Headers-1701213112")
                        .properties(Map.of(
                                "headerResolved",
                                SchemaObject.builder()
                                        .type(SchemaType.STRING)
                                        .format("int32Resolved")
                                        .title("headerResolved")
                                        .description("descriptionResolved")
                                        .enumValues(null)
                                        .examples(null)
                                        .build()))
                        .build());
    }

    @Test
    void getAsyncHeadersWithEmptyFormat() throws Exception {
        // given
        Method m = ClassWithHeaders.class.getDeclaredMethod("withEmptyFormat", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver stringValueResolver = mock(StringValueResolver.class);
        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString());

        // when
        SchemaObject headers = AsyncAnnotationUtil.getAsyncHeaders(operation, stringValueResolver);

        // then
        SchemaObject headerProperty = (SchemaObject) headers.getProperties().get("header");
        assertThat(headerProperty.getFormat()).isNull();
    }

    @Test
    void generatedHeaderSchemaNameShouldBeUnique() throws Exception {
        // given
        Method m1 = ClassWithHeaders.class.getDeclaredMethod("withoutSchemaName", String.class);
        AsyncOperation operation1 = m1.getAnnotation(AsyncListener.class).operation();

        Method m2 = ClassWithHeaders.class.getDeclaredMethod("differentHeadersWithoutSchemaName", String.class);
        AsyncOperation operation2 = m2.getAnnotation(AsyncListener.class).operation();

        StringValueResolver stringValueResolver = mock(StringValueResolver.class);
        when(stringValueResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString() + "Resolved");

        // when
        SchemaObject headers1 = AsyncAnnotationUtil.getAsyncHeaders(operation1, stringValueResolver);
        SchemaObject headers2 = AsyncAnnotationUtil.getAsyncHeaders(operation2, stringValueResolver);

        // then
        assertThat(headers1.getTitle()).isNotEqualTo(headers2.getTitle());
    }

    @Test
    void processOperationBindingFromAnnotation() throws Exception {
        // given
        Method m = ClassWithOperationBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);

        // when
        Map<String, OperationBinding> bindings = AsyncAnnotationUtil.processOperationBindingFromAnnotation(
                m, Collections.singletonList(new TestOperationBindingProcessor()));

        // then
        assertThat(bindings)
                .isEqualTo(Maps.newHashMap(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING));
    }

    @Test
    void processMultipleOperationBindingFromAnnotation() throws Exception {
        // given
        Method m = ClassWithMultipleOperationBindingProcessors.class.getDeclaredMethod(
                "methodWithAnnotation", String.class);

        // when
        Map<String, OperationBinding> bindings = AsyncAnnotationUtil.processOperationBindingFromAnnotation(
                m,
                List.of(
                        new TestOperationBindingProcessor(),
                        new TestAbstractOperationBindingProcessor(stringValueResolver)));

        // then
        assertThat(bindings)
                .isEqualTo(Maps.newHashMap(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING));
        assertThat(bindings)
                .isNotEqualTo(Maps.newHashMap(
                        TestAbstractOperationBindingProcessor.TYPE, TestAbstractOperationBindingProcessor.BINDING));
    }

    @Test
    void processMessageBindingFromAnnotation() throws Exception {
        // given
        Method m = ClassWithOperationBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);

        // when
        Map<String, MessageBinding> bindings = AsyncAnnotationUtil.processMessageBindingFromAnnotation(
                m, Collections.singletonList(new TestMessageBindingProcessor()));

        // then
        assertThat(bindings)
                .isEqualTo(Maps.newHashMap(TestMessageBindingProcessor.TYPE, TestMessageBindingProcessor.BINDING));
    }

    @Test
    void processMultipleMessageBindingFromAnnotation() throws Exception {
        // given
        Method m = ClassWithMultipleOperationBindingProcessors.class.getDeclaredMethod(
                "methodWithAnnotation", String.class);

        // when
        Map<String, MessageBinding> bindings =
                AsyncAnnotationUtil.processMessageBindingFromAnnotation(m, List.of(new TestMessageBindingProcessor()));

        // then
        assertThat(bindings)
                .isEqualTo(Maps.newHashMap(TestMessageBindingProcessor.TYPE, TestMessageBindingProcessor.BINDING));
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassWithOperationBindingProcessor.class, ClassWithAbstractOperationBindingProcessor.class})
    void processMessageFromAnnotationWithoutAsyncMessage(Class<?> classWithOperationBindingProcessor) throws Exception {
        // given
        Method method = classWithOperationBindingProcessor.getDeclaredMethod("methodWithAnnotation", String.class);
        AsyncMessage message =
                method.getAnnotation(AsyncListener.class).operation().message();

        StringValueResolver stringResolver = mock(StringValueResolver.class);
        when(stringResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString());

        // when
        MessageObject.MessageObjectBuilder actual = MessageObject.builder();
        AsyncAnnotationUtil.processAsyncMessageAnnotation(actual, message, stringResolver);

        // then
        var expectedMessage = MessageObject.builder().build();
        assertThat(actual.build()).isEqualTo(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassWithOperationBindingProcessor.class, ClassWithAbstractOperationBindingProcessor.class})
    void processMessageFromAnnotationWithAsyncMessage(Class<?> classWithOperationBindingProcessor) throws Exception {
        // given
        Method method =
                classWithOperationBindingProcessor.getDeclaredMethod("methodWithAsyncMessageAnnotation", String.class);
        AsyncMessage message =
                method.getAnnotation(AsyncListener.class).operation().message();

        StringValueResolver stringResolver = mock(StringValueResolver.class);
        when(stringResolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString());

        // when
        MessageObject.MessageObjectBuilder actual = MessageObject.builder();
        AsyncAnnotationUtil.processAsyncMessageAnnotation(actual, message, stringResolver);

        // then
        var expectedMessage = MessageObject.builder()
                .description("Message description")
                .messageId("simpleFoo")
                .name("SimpleFooPayLoad")
                .title("Message Title")
                .contentType("application/json")
                .build();
        assertThat(actual.build()).isEqualTo(expectedMessage);
    }

    @Test
    void getServers() throws Exception {
        Method m = ClassWithOperationBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver stringValueResolver = mock(StringValueResolver.class);

        // when
        when(stringValueResolver.resolveStringValue("${test.property.server1}")).thenReturn("server1");

        List<String> servers = AsyncAnnotationUtil.getServers(operation, stringValueResolver);
        assertThat(servers).isEqualTo(List.of("server1"));
    }

    @Test
    void processChannelBindingFromAnnotation() throws Exception {
        // given
        Method m = ClassWithChannelBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);

        // when
        Map<String, ChannelBinding> bindings = AsyncAnnotationUtil.processChannelBindingFromAnnotation(
                m, Collections.singletonList(new TestChannelBindingProcessor()));

        // then
        assertThat(bindings)
                .isEqualTo(Maps.newHashMap(TestChannelBindingProcessor.TYPE, TestChannelBindingProcessor.BINDING));
    }

    @Test
    void processAbstractChannelBindingFromAnnotation() throws Exception {
        // given
        Method m =
                ClassWithAbstractChannelBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);

        // when
        Map<String, ChannelBinding> bindings = AsyncAnnotationUtil.processChannelBindingFromAnnotation(
                m, Collections.singletonList(new TestAbstractChannelBindingProcessor(stringValueResolver)));

        // then
        assertThat(bindings)
                .isEqualTo(Maps.newHashMap(
                        TestAbstractChannelBindingProcessor.TYPE, TestAbstractChannelBindingProcessor.BINDING));
    }

    private static class ClassWithChannelBindingProcessor {
        @TestChannelBindingProcessor.TestChannelBinding()
        private void methodWithAnnotation(String payload) {}
    }

    private static class ClassWithAbstractChannelBindingProcessor {
        @AsyncListener(operation = @AsyncOperation(channelName = "${test.property.test-channel}"))
        @TestAbstractChannelBindingProcessor.TestChannelBinding()
        private void methodWithAnnotation(String payload) {}
    }

    private static class ClassWithOperationBindingProcessor {
        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                description = "${test.property.description}",
                                servers = {"${test.property.server1}"},
                                headers =
                                        @AsyncOperation.Headers(
                                                schemaName = "TestSchema",
                                                description = "header-description",
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            value = "value",
                                                            description = "description",
                                                            format = "int32"),
                                                    @AsyncOperation.Headers.Header(
                                                            name = "headerWithoutValue",
                                                            description = "description")
                                                })))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void methodWithAnnotation(String payload) {}

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                description = "${test.property.description}",
                                headers =
                                        @AsyncOperation.Headers(
                                                schemaName = "TestSchema",
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            value = "value",
                                                            description = "description")
                                                }),
                                message =
                                        @AsyncMessage(
                                                description = "Message description",
                                                messageId = "simpleFoo",
                                                name = "SimpleFooPayLoad",
                                                contentType = "application/json",
                                                title = "Message Title")))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void methodWithAsyncMessageAnnotation(String payload) {}
    }

    private static class ClassWithAbstractOperationBindingProcessor {
        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                description = "${test.property.description}",
                                headers =
                                        @AsyncOperation.Headers(
                                                schemaName = "TestSchema",
                                                description = "header-description",
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            value = "value",
                                                            description = "description",
                                                            format = "int32"),
                                                    @AsyncOperation.Headers.Header(
                                                            name = "headerWithoutValue",
                                                            description = "description")
                                                })))
        @TestAbstractOperationBindingProcessor.TestOperationBinding()
        private void methodWithAnnotation(String payload) {}

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                description = "${test.property.description}",
                                headers =
                                        @AsyncOperation.Headers(
                                                schemaName = "TestSchema",
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            value = "value",
                                                            description = "description")
                                                }),
                                message =
                                        @AsyncMessage(
                                                description = "Message description",
                                                messageId = "simpleFoo",
                                                name = "SimpleFooPayLoad",
                                                contentType = "application/json",
                                                title = "Message Title")))
        @TestAbstractOperationBindingProcessor.TestOperationBinding()
        private void methodWithAsyncMessageAnnotation(String payload) {}
    }

    private static class ClassWithHeaders {
        @AsyncListener(operation = @AsyncOperation(channelName = "${test.property.test-channel}"))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void emptyHeaders(String payload) {}

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                headers =
                                        @AsyncOperation.Headers(
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            value = "value",
                                                            description = "description")
                                                })))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void withoutSchemaName(String payload) {}

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                headers =
                                        @AsyncOperation.Headers(
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            description = "description")
                                                })))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void withoutValue(String payload) {}

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                headers =
                                        @AsyncOperation.Headers(
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            description = "description",
                                                            format = "int32")
                                                })))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void withFormat(String payload) {}

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                headers =
                                        @AsyncOperation.Headers(
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            description = "description",
                                                            format = "")
                                                })))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void withEmptyFormat(String payload) {}

        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                headers =
                                        @AsyncOperation.Headers(
                                                values = {
                                                    @AsyncOperation.Headers.Header(name = "header", value = "value")
                                                })))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void differentHeadersWithoutSchemaName(String payload) {}
    }

    private static class ClassWithMultipleOperationBindingProcessors {
        @AsyncListener(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                description = "${test.property.description}"))
        @TestOperationBindingProcessor.TestOperationBinding()
        @TestAbstractOperationBindingProcessor.TestOperationBinding()
        private void methodWithAnnotation(String payload) {}
    }
}
