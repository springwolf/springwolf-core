// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.KafkaListenerUtil;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaObject;
import org.springframework.util.StringUtils;

public class KafkaOperationBindingProcessor extends AbstractOperationBindingProcessor<KafkaAsyncOperationBinding> {
    @Override
    protected ProcessedOperationBinding mapToOperationBinding(KafkaAsyncOperationBinding bindingAnnotation) {
        String clientId = resolveOrNull(bindingAnnotation.clientId());
        SchemaObject clientIdSchema = KafkaListenerUtil.buildKafkaClientIdSchema(clientId);
        String groupId = resolveOrNull(bindingAnnotation.groupId());
        SchemaObject groupIdSchema = KafkaListenerUtil.buildKafkaGroupIdSchema(groupId);

        KafkaOperationBinding.KafkaOperationBindingBuilder kafkaOperationBindingBuilder =
                KafkaOperationBinding.builder();
        kafkaOperationBindingBuilder.clientId(clientIdSchema).groupId(groupIdSchema);
        String bindingVersion = resolveOrNull(bindingAnnotation.bindingVersion());
        if (StringUtils.hasText(bindingVersion)) {
            kafkaOperationBindingBuilder.bindingVersion(bindingVersion);
        }

        return new ProcessedOperationBinding(bindingAnnotation.type(), kafkaOperationBindingBuilder.build());
    }
}
