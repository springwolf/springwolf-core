package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Holds information about a producer channel.
 * All fields must be set and not null.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerData {

    /**
     * The name of the channel (topic, queue etc.).
     */
    protected String channelName;

    /**
     * Optional, additional information about the channel and/or its message
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
     * The class object of the payload published by this consumer.
     */
    protected Class<?> payloadType;

    /**
     * The class object of the headers published by this consumer.
     */
    @Builder.Default
    protected AsyncHeaders headers = AsyncHeaders.NOT_DOCUMENTED;

    /**
     * The operation binding of the consumer.
     * <br>
     * For example:
     * <code>
     *     ImmutableMap.of("kafka", new KafkaOperationBinding())
     * </code>
     */
    protected Map<String, ? extends OperationBinding> operationBinding;

}
