// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.googlepubsub;

import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ChannelBinding;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object MUST NOT contain any properties. Its name is reserved for future use.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/googlepubsub/README.md#operation-binding-object">GooglePubSub Operation</a>
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GooglePubSubOperationBinding extends ChannelBinding {}
