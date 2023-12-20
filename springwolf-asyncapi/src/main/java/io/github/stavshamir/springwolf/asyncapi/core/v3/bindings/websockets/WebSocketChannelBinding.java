// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.websockets;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.schema.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * When using WebSockets, the channel represents the connection. Unlike other protocols that support multiple virtual
 * channels (topics, routing keys, etc.) per connection, WebSockets doesn't support virtual channels or, put it another
 * way, there's only one channel and its characteristics are strongly related to the protocol used for the handshake, i.e., HTTP.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/websockets/README.md#channel-binding-object">WebSocket Channel</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WebSocketChannelBinding extends ChannelBinding {
    /**
     * The HTTP method to use when establishing the connection. Its value MUST be either GET or POST.
     */
    @JsonProperty("method")
    private WebSocketsChannelMethod method;

    /**
     * A Schema object containing the definitions for each query parameter. This schema MUST be of type object and
     * have a properties key.
     */
    @JsonProperty("query")
    private Schema query;

    /**
     * A Schema object containing the definitions of the HTTP headers to use when establishing the connection. This
     * schema MUST be of type object and have a properties key.
     */
    @JsonProperty("headers")
    private Schema headers;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.1.0";

    public enum WebSocketsChannelMethod {
        @JsonProperty("GET")
        GET,

        @JsonProperty("POST")
        POST
    }
}
