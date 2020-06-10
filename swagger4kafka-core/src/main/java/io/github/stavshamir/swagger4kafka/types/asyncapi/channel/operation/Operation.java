package io.github.stavshamir.swagger4kafka.types.asyncapi.channel.operation;


import io.github.stavshamir.swagger4kafka.types.asyncapi.channel.operation.bindings.OperationBinding;
import io.github.stavshamir.swagger4kafka.types.asyncapi.channel.operation.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Describes a publish or a subscribe operation.
 * This provides a place to document how and why messages are sent and received.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#operationObject">Channel specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    /**
     * A free-form map where the keys describe the name of the protocol and the values describe protocol-specific
     * definitions for the operation.
     */
    private Map<String, ? extends OperationBinding> bindings;

    /**
     * A definition of the message that will be published or received on this channel.
     */
    private Message message;

}
