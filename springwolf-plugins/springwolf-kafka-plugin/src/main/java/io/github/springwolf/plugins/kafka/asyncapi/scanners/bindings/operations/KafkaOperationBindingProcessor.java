// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings.operations;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.AbstractOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.plugins.kafka.asyncapi.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.KafkaListenerUtil;
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

        return new ProcessedOperationBinding("kafka", kafkaOperationBindingBuilder.build());
    }
}
