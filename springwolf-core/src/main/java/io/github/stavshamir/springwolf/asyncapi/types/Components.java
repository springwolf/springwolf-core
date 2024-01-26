// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.swagger.v3.oas.models.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Holds a set of reusable objects for different aspects of the AsyncAPI specification.
 * All objects defined within the components object will have no effect on the API unless they are explicitly
 * referenced from properties outside the components object.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#componentsObject">Components specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Components {

    private Map<String, Schema> schemas;
    private Map<String, Message> messages;
}
