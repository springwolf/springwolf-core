// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.mercure;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object MUST NOT contain any properties. Its name is reserved for future use.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/mercure/README.md#operation-binding-object">Mercure Operation</a>
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MercureOperationBinding extends OperationBinding {}
