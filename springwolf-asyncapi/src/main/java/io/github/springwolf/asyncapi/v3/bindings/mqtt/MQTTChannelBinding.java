// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.mqtt;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Protocol-specific information for a JMS channel.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#channel-binding-object">MQTT Channel</a>
 *
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MQTTChannelBinding extends ChannelBinding {}
