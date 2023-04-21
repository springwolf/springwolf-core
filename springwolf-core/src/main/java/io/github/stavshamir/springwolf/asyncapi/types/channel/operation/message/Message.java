package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message;

import com.asyncapi.v2.binding.message.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

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

    @Builder.Default
    private String schemaFormat = "application/vnd.oai.openapi+json;version=3.0.0";

    /**
     * A machine-friendly name for the message.
     */
    private String name;

    /**
     * A human-friendly title for the message.
     */
    private String title;

    /**
     * A human-friendly description for the message.
     */
    private String description;

    private PayloadReference payload;

    private HeaderReference headers;

    private Map<String, ? extends MessageBinding> bindings;
}
