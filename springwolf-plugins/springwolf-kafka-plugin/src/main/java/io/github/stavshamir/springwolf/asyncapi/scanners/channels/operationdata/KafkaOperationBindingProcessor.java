package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import com.asyncapi.v2.schema.Schema;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.KafkaListenerUtil;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class KafkaOperationBindingProcessor extends AbstractOperationBindingProcessor<KafkaAsyncOperationBinding> {
    @Override
    protected ProcessedOperationBinding mapToOperationBinding(KafkaAsyncOperationBinding bindingAnnotation) {
        String clientId = resolveOrNull(bindingAnnotation.clientId());
        Schema clientIdSchema = KafkaListenerUtil.buildKafkaClientIdSchema(clientId);
        String groupId = resolveOrNull(bindingAnnotation.groupId());
        Schema groupIdSchema = KafkaListenerUtil.buildKafkaGroupIdSchema(groupId);

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
