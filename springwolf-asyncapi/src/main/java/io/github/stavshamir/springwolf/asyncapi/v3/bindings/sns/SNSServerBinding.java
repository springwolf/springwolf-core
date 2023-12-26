// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns;

import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ServerBinding;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class MUST NOT contain any properties. Its name is reserved for future use.
 * <p>
 * Describes SNS server binding.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/sns/README.md#server-binding-object">SNS Server binding</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SNSServerBinding extends ServerBinding {}
