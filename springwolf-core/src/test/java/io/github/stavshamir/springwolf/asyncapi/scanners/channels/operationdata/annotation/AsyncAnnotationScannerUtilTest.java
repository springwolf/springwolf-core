// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.TestAbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.TestMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.TestOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationScannerUtilTest {

    @ParameterizedTest
    @ValueSource(classes = {ClassWithOperationBindingProcessor.class, ClassWithAbstractOperationBindingProcessor.class})
    void getAsyncHeaders(Class<?> classWithOperationBindingProcessor) throws NoSuchMethodException {
        // given
        Method m = classWithOperationBindingProcessor.getDeclaredMethod("methodWithAnnotation", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver resolver = mock(StringValueResolver.class);

        // when
        when(resolver.resolveStringValue(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString() + "Resolved");

        // then
        AsyncHeaders headers = AsyncAnnotationScannerUtil.getAsyncHeaders(operation, resolver);
        assertEquals("TestSchema", headers.getSchemaName());
        assertTrue(headers.containsKey("headerResolved"));
        assertEquals("string", headers.get("headerResolved").getType());
        assertEquals("valueResolved", headers.get("headerResolved").getExample());
        assertEquals("descriptionResolved", headers.get("headerResolved").getDescription());
    }

    @Test
    void processOperationBindingFromAnnotation() throws NoSuchMethodException {
        // given
        Method m = ClassWithOperationBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);

        // when
        Map<String, OperationBinding> bindings = AsyncAnnotationScannerUtil.processOperationBindingFromAnnotation(
                m, Collections.singletonList(new TestOperationBindingProcessor()));

        // then
        assertEquals(
                Maps.newHashMap(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING), bindings);
    }

    @Test
    void processMultipleOperationBindingFromAnnotation() throws NoSuchMethodException {
        // given
        Method m = ClassWithMultipleOperationBindingProcessors.class.getDeclaredMethod(
                "methodWithAnnotation", String.class);

        // when
        Map<String, OperationBinding> bindings = AsyncAnnotationScannerUtil.processOperationBindingFromAnnotation(
                m, List.of(new TestOperationBindingProcessor(), new TestAbstractOperationBindingProcessor()));

        // then
        assertEquals(
                Maps.newHashMap(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING), bindings);
        assertNotEquals(
                Maps.newHashMap(
                        TestAbstractOperationBindingProcessor.TYPE, TestAbstractOperationBindingProcessor.BINDING),
                bindings);
    }

    @Test
    void processMessageBindingFromAnnotation() throws NoSuchMethodException {
        // given
        Method m = ClassWithOperationBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);

        // when
        Map<String, MessageBinding> bindings = AsyncAnnotationScannerUtil.processMessageBindingFromAnnotation(
                m, Collections.singletonList(new TestMessageBindingProcessor()));

        // then
        assertEquals(Maps.newHashMap(TestMessageBindingProcessor.TYPE, TestMessageBindingProcessor.BINDING), bindings);
    }

    @Test
    void processMultipleMessageBindingFromAnnotation() throws NoSuchMethodException {
        // given
        Method m = ClassWithMultipleOperationBindingProcessors.class.getDeclaredMethod(
                "methodWithAnnotation", String.class);

        // when
        Map<String, MessageBinding> bindings = AsyncAnnotationScannerUtil.processMessageBindingFromAnnotation(
                m, List.of(new TestMessageBindingProcessor()));

        // then
        assertEquals(Maps.newHashMap(TestMessageBindingProcessor.TYPE, TestMessageBindingProcessor.BINDING), bindings);
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassWithOperationBindingProcessor.class, ClassWithAbstractOperationBindingProcessor.class})
    void processMessageFromAnnotationWithoutAsyncMessage(Class<?> classWithOperationBindingProcessor)
            throws NoSuchMethodException {
        // given
        Method method = classWithOperationBindingProcessor.getDeclaredMethod("methodWithAnnotation", String.class);

        // when
        Message message = AsyncAnnotationScannerUtil.processMessageFromAnnotation(method);

        // then
        var expectedMessage = Message.builder().build();
        assertEquals(expectedMessage, message);
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassWithOperationBindingProcessor.class, ClassWithAbstractOperationBindingProcessor.class})
    void processMessageFromAnnotationWithAsyncMessage(Class<?> classWithOperationBindingProcessor)
            throws NoSuchMethodException {
        // given
        Method method =
                classWithOperationBindingProcessor.getDeclaredMethod("methodWithAsyncMessageAnnotation", String.class);

        // when
        Message message = AsyncAnnotationScannerUtil.processMessageFromAnnotation(method);

        // then
        var expectedMessage = Message.builder()
                .description("Message description")
                .messageId("simpleFoo")
                .name("SimpleFooPayLoad")
                .schemaFormat("application/schema+json;version=draft-07")
                .title("Message Title")
                .build();
        assertEquals(expectedMessage, message);
    }

    @Test
    void getServers() throws NoSuchMethodException {
        Method m = ClassWithOperationBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver resolver = mock(StringValueResolver.class);

        // when
        when(resolver.resolveStringValue("${test.property.server1}")).thenReturn("server1");

        List<String> servers = AsyncAnnotationScannerUtil.getServers(operation, resolver);
        assertEquals(List.of("server1"), servers);
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
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            value = "value",
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
                                                schemaFormat = "application/schema+json;version=draft-07",
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
                                                values = {
                                                    @AsyncOperation.Headers.Header(
                                                            name = "header",
                                                            value = "value",
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
                                                schemaFormat = "application/schema+json;version=draft-07",
                                                title = "Message Title")))
        @TestAbstractOperationBindingProcessor.TestOperationBinding()
        private void methodWithAsyncMessageAnnotation(String payload) {}
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
