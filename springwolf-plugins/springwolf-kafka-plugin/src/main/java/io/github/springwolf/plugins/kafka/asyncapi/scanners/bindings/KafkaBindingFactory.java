// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.KafkaListenerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@RequiredArgsConstructor
public class KafkaBindingFactory implements BindingFactory<KafkaListener> {
    private final StringValueResolver stringValueResolver;

    @Override
    public String getChannelName(KafkaListener annotation) {
        return KafkaListenerUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildChannelBinding();
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildOperationBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(KafkaListener annotation, SchemaObject headerSchema) {
        return KafkaListenerUtil.buildMessageBinding(headerSchema);
    }
}
