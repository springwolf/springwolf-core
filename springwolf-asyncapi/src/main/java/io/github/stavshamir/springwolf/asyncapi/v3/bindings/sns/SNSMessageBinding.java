// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns;

import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object MUST NOT contain any properties. Its name is reserved for future use.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/sns/README.md#message-binding-object">SNS Message</a>
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SNSMessageBinding extends MessageBinding {}
