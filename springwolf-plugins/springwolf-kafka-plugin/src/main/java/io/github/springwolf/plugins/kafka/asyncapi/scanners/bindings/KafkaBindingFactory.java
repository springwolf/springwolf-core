// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.bindings.common.BindingContext;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.KafkaListenerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

@RequiredArgsConstructor
public class KafkaBindingFactory implements BindingFactory<KafkaListener> {
    private final KafkaBeanRefHelper kafkaBeanRefHelper;

    @Override
    public String getChannelName(KafkaListener annotation) {
        return KafkaListenerUtil.getChannelName(
                annotation, kafkaBeanRefHelper.getStringValueResolver(annotation, null));
    }

    @Override
    public String getChannelName(KafkaListener annotation, BindingContext bindingContext) {
        return KafkaListenerUtil.getChannelName(
                annotation, kafkaBeanRefHelper.getStringValueResolver(annotation, bindingContext));
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildChannelBinding();
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildOperationBinding(
                annotation, kafkaBeanRefHelper.getStringValueResolver(annotation, null));
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(KafkaListener annotation, SchemaObject headerSchema) {
        return KafkaListenerUtil.buildMessageBinding(headerSchema);
    }
}
