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
public class AMQPChannelQueueProperties {

    /**
     * The name of the queue. It MUST NOT exceed 255 characters long.
     */
    @Size(max = 255, message = "Queue name must not exceed 255 characters long.")
    @JsonProperty("name")
    private String name;

    /**
     * Whether the queue should survive broker restarts or not.
     */
    @JsonProperty("durable")
    private Boolean durable;

    /**
     * Whether the queue should be used only by one connection or not.
     */
    @JsonProperty("exclusive")
    private Boolean exclusive;

    /**
     * Whether the queue should be deleted when the last consumer unsubscribes.
     */
    @JsonProperty("autoDelete")
    private Boolean autoDelete;

    /**
     * The virtual host of the queue. Defaults to /.
     */
    @Builder.Default
    @JsonProperty(value = "vhost", defaultValue = "/")
    private String vhost = "/";
}
