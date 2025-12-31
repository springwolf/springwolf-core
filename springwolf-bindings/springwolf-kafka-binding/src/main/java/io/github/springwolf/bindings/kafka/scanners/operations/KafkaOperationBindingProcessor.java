// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.kafka.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.AbstractOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import jakarta.annotation.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Set;

public class KafkaOperationBindingProcessor extends AbstractOperationBindingProcessor<KafkaAsyncOperationBinding> {

    public KafkaOperationBindingProcessor(StringValueResolver stringValueResolver) {
        super(stringValueResolver);
    }

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(KafkaAsyncOperationBinding bindingAnnotation) {
        String clientId = resolveOrNull(bindingAnnotation.clientId());
        SchemaObject clientIdSchema = createStringSchema(clientId);
        String groupId = resolveOrNull(bindingAnnotation.groupId());
        SchemaObject groupIdSchema = createStringSchema(groupId);

        KafkaOperationBinding.KafkaOperationBindingBuilder kafkaOperationBindingBuilder =
                KafkaOperationBinding.builder();
        kafkaOperationBindingBuilder.clientId(clientIdSchema).groupId(groupIdSchema);
        String bindingVersion = resolveOrNull(bindingAnnotation.bindingVersion());
        if (StringUtils.hasText(bindingVersion)) {
            kafkaOperationBindingBuilder.bindingVersion(bindingVersion);
        }

        return new ProcessedOperationBinding("kafka", kafkaOperationBindingBuilder.build());
    }

    @Nullable
    private static SchemaObject createStringSchema(String value) {
        if (value != null && !value.isEmpty()) {
            SchemaObject schema = new SchemaObject();
            schema.setEnumValues(List.of(value));
            schema.setType(Set.of(SchemaType.STRING));
            return schema;
        }
        return null;
    }
}
