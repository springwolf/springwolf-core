package io.github.stavshamir.swagger4kafka.asyncapi.types.channel;

import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.Operation;
import lombok.*;

/**
 * Describes the operations available on a single channel.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#channelItemObject">Channel specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    /**
     * An optional description of this channel item. CommonMark syntax can be used for rich text representation.
     */
    private String description;

    /**
     * A definition of the SUBSCRIBE operation.
     */
    @Getter(value=AccessLevel.NONE)
    private Operation subscribe;

    /**
     * A definition of the PUBLISH operation.
     */
    @Getter(value=AccessLevel.NONE)
    private Operation publish;

    public Operation getOperation() {
        return subscribe != null ? subscribe : publish;
    }

}
