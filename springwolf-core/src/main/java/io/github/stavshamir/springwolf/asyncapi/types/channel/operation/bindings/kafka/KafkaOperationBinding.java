package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.kafka;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.OperationBinding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object contains information about the operation representation in Kafka.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/kafka/README.md#operation">Kafka Operation Binding specification</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaOperationBinding implements OperationBinding {

    public static final String KAFKA_BINDING_KEY = "kafka";

    private GroupId groupId;

    public static KafkaOperationBinding withGroupId(String groupId) {
        return new KafkaOperationBinding(GroupId.of(groupId));
    }

}
