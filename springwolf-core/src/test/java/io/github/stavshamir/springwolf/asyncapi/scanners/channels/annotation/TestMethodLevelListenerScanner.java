// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;
import java.util.Map;

@Deprecated
public class TestMethodLevelListenerScanner
        extends AbstractMethodLevelListenerScanner<TestMethodLevelListenerScannerIntegrationTest.TestChannelListener> {

    public TestMethodLevelListenerScanner(ComponentClassScanner componentClassScanner, SchemasService schemasService) {
        super(componentClassScanner, schemasService);
    }

    @Override
    protected Class<TestMethodLevelListenerScannerIntegrationTest.TestChannelListener> getListenerAnnotationClass() {
        return TestMethodLevelListenerScannerIntegrationTest.TestChannelListener.class;
    }

    @Override
    protected String getChannelName(TestMethodLevelListenerScannerIntegrationTest.TestChannelListener annotation) {
        return "test-channel";
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(
            TestMethodLevelListenerScannerIntegrationTest.TestChannelListener annotation) {
        return Map.of("test-channel-binding", new TestChannelBinding());
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(
            TestMethodLevelListenerScannerIntegrationTest.TestChannelListener annotation) {
        return Map.of("test-operation-binding", new TestOperationBinding());
    }

    @Override
    protected Map<String, ? extends MessageBinding> buildMessageBinding(
            TestMethodLevelListenerScannerIntegrationTest.TestChannelListener annotation) {
        return Map.of("test-message-binding", new TestMessageBinding());
    }

    @Override
    protected Class<?> getPayloadType(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("Only single parameter listener methods are supported");
        }

        return parameterTypes[0];
    }

    @EqualsAndHashCode(callSuper = true)
    public static class TestChannelBinding extends ChannelBinding {}

    @EqualsAndHashCode(callSuper = true)
    public static class TestOperationBinding extends OperationBinding {}

    @EqualsAndHashCode(callSuper = true)
    public static class TestMessageBinding extends MessageBinding {}
}
