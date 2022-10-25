package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import com.google.common.collect.ImmutableMap;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;
import java.util.Map;

public class TestListenerScanner extends AbstractMethodLevelListenerScanner<AbstractMethodLevelListenerScannerTest.TestChannelListener> {

    @Override
    protected Class<AbstractMethodLevelListenerScannerTest.TestChannelListener> getListenerAnnotationClass() {
        return AbstractMethodLevelListenerScannerTest.TestChannelListener.class;
    }

    @Override
    protected String getChannelName(AbstractMethodLevelListenerScannerTest.TestChannelListener annotation) {
        return "test-channel";
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(AbstractMethodLevelListenerScannerTest.TestChannelListener annotation) {
        return ImmutableMap.of("test-channel-binding", new TestChannelBinding());
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(AbstractMethodLevelListenerScannerTest.TestChannelListener annotation) {
        return ImmutableMap.of("test-operation-binding", new TestOperationBinding());
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

}
