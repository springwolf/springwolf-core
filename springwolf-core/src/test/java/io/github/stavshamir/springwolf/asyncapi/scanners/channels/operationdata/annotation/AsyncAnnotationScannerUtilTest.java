package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationScannerUtilTest {

    @Test
    void getAsyncHeaders() throws NoSuchMethodException {
        // given
        Method m = ClassWithOperationBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);
        AsyncOperation operation = m.getAnnotation(AsyncListener.class).operation();

        StringValueResolver resolver = mock(StringValueResolver.class);

        // when
        when(resolver.resolveStringValue(any())).thenAnswer(invocation -> invocation.getArgument(0).toString()+"Resolved");

        // then
        AsyncHeaders headers = AsyncAnnotationScannerUtil.getAsyncHeaders(operation, resolver);
        assertEquals(headers.getSchemaName(), "TestSchema");
        assertTrue(headers.containsKey("headerResolved"));
        assertEquals(headers.get("headerResolved").getType(), "string");
        assertEquals(headers.get("headerResolved").getExample(), "valueResolved");
        assertEquals(headers.get("headerResolved").getDescription(), "descriptionResolved");
    }

    @Test
    void processBindingFromAnnotation() throws NoSuchMethodException {
        // given
        Method m = ClassWithOperationBindingProcessor.class.getDeclaredMethod("methodWithAnnotation", String.class);

        // when
        Map<String, OperationBinding> bindings = AsyncAnnotationScannerUtil.processOperationBindingFromAnnotation(m, Collections.singletonList(new TestOperationBindingProcessor()));

        // then
        assertEquals(Maps.newHashMap(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING), bindings);
    }

    private static class ClassWithOperationBindingProcessor {
        @AsyncListener(operation = @AsyncOperation(
                channelName = "${test.property.test-channel}",
                description = "${test.property.description}",
                headers = @AsyncOperation.Headers(
                        schemaName = "TestSchema",
                        values = {@AsyncOperation.Headers.Header(name = "header", value = "value", description = "description")}
                )
        ))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void methodWithAnnotation(String payload) {
        }
    }
}