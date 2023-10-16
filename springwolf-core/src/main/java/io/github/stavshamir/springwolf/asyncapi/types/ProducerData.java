// SPDX-License-Identifier: Apache-2.0
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
import lombok.Singular;

import java.util.List;
import java.util.Map;

/**
 * Holds information about a producer channel.
 * All fields must be set and not null.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerData implements OperationData {

    /**
     * The name of the channel (topic, queue etc.).
     */
    protected String channelName;

    /**
     * Optional, additional information about the channel and its message
     */
    protected String description;

    /**
     * Optional, List of server names the channel is assigned to. If empty, the
     * channel is available on all defined servers.
     */
    @Singular("server")
    protected List<String> servers;

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
     * The class object of the payload published by this producer.
     */
    protected Class<?> payloadType;

    /**
     * The class object of the headers published by this producer.
     */
    @Builder.Default
    protected AsyncHeaders headers = AsyncHeaders.NOT_DOCUMENTED;

    /**
     * The operation binding of the producer.
     * <br>
     * For example:
     * <code>
     *     Map.of("kafka", new KafkaOperationBinding())
     * </code>
     */
    protected Map<String, ? extends OperationBinding> operationBinding;

    /**
     * The message binding of the producer.
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
