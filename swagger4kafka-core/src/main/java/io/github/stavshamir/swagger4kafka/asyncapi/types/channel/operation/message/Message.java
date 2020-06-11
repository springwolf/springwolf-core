package io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a message received on a given channel and operation.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#messageObject">Message specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    /**
     * A machine-friendly name for the message.
     */
    private String name;

    /**
     * A human-friendly title for the message.
     */
    private String title;

    private PayloadReference payload;

}
