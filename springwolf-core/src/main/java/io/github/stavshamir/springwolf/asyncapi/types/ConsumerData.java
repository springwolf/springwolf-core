package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
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
public class ConsumerData implements OperationData {

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
     *     Map.of("kafka", new KafkaChannelBinding())
     * </code>
     */
    protected Map<String, ? extends ChannelBinding> channelBinding;

    /**
     * The class object of the payload published by this consumer.
     */
    protected Class<?> payloadType;

    /**
     * The message headers, usually describing the payload.
     */
    @Builder.Default
    protected AsyncHeaders headers = AsyncHeaders.NOT_DOCUMENTED;

    /**
     * The operation binding of the consumer.
     * <br>
     * For example:
     * <code>
     *     Map.of("kafka", new KafkaOperationBinding())
     * </code>
     */
    protected Map<String, ? extends OperationBinding> operationBinding;

    /**
     * The message binding of the consumer.
     * <br>
     * For example:
     * <code>
     *     Map.of("kafka", new KafkaMessageBinding())
     * </code>
     */
    protected Map<String, ? extends MessageBinding> messageBinding;

    /**
     * Operation message.
     */
    protected Message message;
}
