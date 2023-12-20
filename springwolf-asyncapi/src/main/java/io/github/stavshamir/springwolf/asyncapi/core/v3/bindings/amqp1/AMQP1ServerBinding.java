// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.amqp1;

import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ServerBinding;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object MUST NOT contain any properties. Its name is reserved for future use.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/amqp1/README.md#channel-binding-object">AMQP1 Server</a>
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AMQP1ServerBinding extends ServerBinding {}
