// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp;

import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ServerBinding;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class MUST NOT contain any properties. Its name is reserved for future use.
 * <p>
 * Describes AMQP 0-9-1 server binding.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/amqp/README.md#server-binding-object">AMQP server binding</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AMQPServerBinding extends ServerBinding {}
