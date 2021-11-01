package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.OperationBinding;
import lombok.EqualsAndHashCode;

import java.util.Map;

public class TestChannelScanner extends AbstractChannelScanner<AbstractChannelScannerTest.TestChannelListener> {

    @Override
    protected String getBasePackage() {
        return "io.github.stavshamir.springwolf.asyncapi.scanners.channels";
    }

    @Override
    protected Class<AbstractChannelScannerTest.TestChannelListener> getListenerAnnotationClass() {
        return AbstractChannelScannerTest.TestChannelListener.class;
    }

    @Override
    protected String getChannelName(AbstractChannelScannerTest.TestChannelListener annotation) {
        return "test-channel";
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(AbstractChannelScannerTest.TestChannelListener annotation) {
        return ImmutableMap.of("test", new TestBinding());
    }

    @EqualsAndHashCode
    public static class TestBinding implements OperationBinding {
    }

}
