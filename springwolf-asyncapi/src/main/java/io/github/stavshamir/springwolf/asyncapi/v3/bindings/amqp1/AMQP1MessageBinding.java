// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp1;

import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object MUST NOT contain any properties. Its name is reserved for future use.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/amqp1/README.md#message-binding-object">AMQP1 Message</a>
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AMQP1MessageBinding extends MessageBinding {}
