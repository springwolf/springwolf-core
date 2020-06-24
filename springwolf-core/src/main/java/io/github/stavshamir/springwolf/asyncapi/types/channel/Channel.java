package io.github.stavshamir.springwolf.asyncapi.types.channel;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Operation subscribe;

    /**
     * A definition of the PUBLISH operation.
     */
    private Operation publish;

}
