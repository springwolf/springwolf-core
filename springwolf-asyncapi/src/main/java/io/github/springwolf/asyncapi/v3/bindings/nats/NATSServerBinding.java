// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.nats;

import io.github.springwolf.asyncapi.v3.bindings.ServerBinding;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object contains information about the server representation in NATS.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/nats/README.md#server-binding-object">NATS Message</a>
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NATSServerBinding extends ServerBinding {}
