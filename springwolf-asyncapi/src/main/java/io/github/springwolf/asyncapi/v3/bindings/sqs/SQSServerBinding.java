// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sqs;

import io.github.springwolf.asyncapi.v3.bindings.ServerBinding;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object MUST NOT contain any properties. Its name is reserved for future use.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/sqs/README.md#server-binding-object">SQS Server</a>
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SQSServerBinding extends ServerBinding {}
