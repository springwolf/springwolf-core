package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import lombok.*;

import java.util.Map;

/**
 * Holds information about a producer channel.
 * All fields must be set and not null.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerData {

    /**
     * The name of the channel (topic, queue etc.).
     */
    protected String channelName;

    /**
     * Optional, additional information about the channel and its message
     */
    protected String description;

    /**
     * The channel binding of the producer.
     * <br>
     * For example:
     * <code>
     *     ImmutableMap.of("kafka", new KafkaChannelBinding())
     * </code>
     */
    protected Map<String, ? extends ChannelBinding> channelBinding;

    /**
     * The class object of the payload published by this producer.
     */
    protected Class<?> payloadType;

    /**
     * The operation binding of the producer.
     * <br>
     * For example:
     * <code>
     *     ImmutableMap.of("kafka", new KafkaOperationBinding())
     * </code>
     */
    protected Map<String, ? extends OperationBinding> operationBinding;

}
