package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.kafka.KafkaOperationBinding;

import java.util.Map;

public class DefaultChannelScanner extends AbstractChannelScanner<AbstractChannelScannerTest.TestChannelListener> {

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
        KafkaOperationBinding binding = KafkaOperationBinding.withGroupId("test-group");
        return ImmutableMap.of(KafkaOperationBinding.KAFKA_BINDING_KEY, binding);
    }
}
