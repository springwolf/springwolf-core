// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AMQPChannelExchangeProperties {
    /**
     * The name of the exchange. It MUST NOT exceed 255 characters long.
     */
    @Size(max = 255, message = "Exchange name must not exceed 255 characters long.")
    @JsonProperty("name")
    private String name;

    /**
     * The type of the exchange. Can be either topic, direct, fanout, default or headers.
     */
    @JsonProperty("type")
    private AMQPChannelExchangeType type;

    /**
     * Whether the exchange should survive broker restarts or not.
     */
    @JsonProperty("durable")
    private Boolean durable;

    /**
     * Whether the exchange should be deleted when the last queue is unbound from it.
     */
    @JsonProperty("autoDelete")
    private Boolean autoDelete;

    /**
     * The virtual host of the exchange. Defaults to /.
     */
    @Builder.Default
    @JsonProperty(value = "vhost", defaultValue = "/")
    private String vhost = "/";
}
