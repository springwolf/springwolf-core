package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.google.common.collect.ImmutableMap;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;
import java.util.Map;

public class TestMethodLevelListenerScanner extends AbstractMethodLevelListenerScanner<TestMethodLevelListenerScannerTest.TestChannelListener> {

    @Override
    protected Class<TestMethodLevelListenerScannerTest.TestChannelListener> getListenerAnnotationClass() {
        return TestMethodLevelListenerScannerTest.TestChannelListener.class;
    }

    @Override
    protected String getChannelName(TestMethodLevelListenerScannerTest.TestChannelListener annotation) {
        return "test-channel";
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(TestMethodLevelListenerScannerTest.TestChannelListener annotation) {
        return ImmutableMap.of("test-channel-binding", new TestChannelBinding());
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(TestMethodLevelListenerScannerTest.TestChannelListener annotation) {
        return ImmutableMap.of("test-operation-binding", new TestOperationBinding());
    }

    @Override
    protected Map<String, ? extends MessageBinding> buildMessageBinding(TestMethodLevelListenerScannerTest.TestChannelListener annotation) {
        return ImmutableMap.of("test-message-binding", new TestMessageBinding());
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
    public static class TestChannelBinding extends ChannelBinding {
    }


    @EqualsAndHashCode(callSuper = true)
    public static class TestOperationBinding extends OperationBinding {
    }

    @EqualsAndHashCode(callSuper = true)
    public static class TestMessageBinding extends MessageBinding {
    }

}
