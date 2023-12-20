// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.components.Components;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.info.Info;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.server.Server;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * This is the root document object for the API specification.
 * It combines resource listing and API declaration together into one document.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#A2SObject">AsyncAPI</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AsyncAPI extends ExtendableObject {

    /**
     * REQUIRED. Specifies the AsyncAPI Specification version being used. It can be used by tooling Specifications and
     * clients to interpret the version. The structure shall be major.minor.patch, where patch versions must be
     * compatible with the existing major.minor tooling. Typically patch versions will be introduced to address errors
     * in the documentation, and tooling should typically be compatible with the corresponding major.minor (1.0.*).
     * Patch versions will correspond to patches of this document.
     */
    @NotNull
    @Builder.Default
    @JsonProperty(value = "asyncapi")
    private String asyncapi = "3.0.0";

    /**
     * Identifier of the
     * <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#definitionsApplication">application</a>
     * the AsyncAPI document is defining.
     * </p>
     * This field represents a unique universal identifier of the application the AsyncAPI document is defining.
     * It must conform to the URI format, according to RFC3986.
     * </p>
     * It is RECOMMENDED to use a URN to globally and uniquely identify the application during long periods of time,
     * even after it becomes unavailable or ceases to exist.
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * REQUIRED. Provides metadata about the API. The metadata can be used by the clients if needed.
     */
    @NotNull
    @JsonProperty(value = "info")
    private Info info;

    /**
     * Provides connection details of servers.
     */
    @JsonProperty(value = "servers")
    private Map<String, Server> servers;

    /**
     * Default content type to use when encoding/decoding a message's payload.
     * </p>
     * A string representing the default content type to use when encoding/decoding a message's payload.
     * The value MUST be a specific media type (e.g. application/json). This value MUST be used by schema
     * parsers when the contentType property is omitted.
     * </p>
     * In case a message can't be encoded/decoded using this value, schema parsers MUST use their default content type.
     */
    @JsonProperty(value = "defaultContentType")
    private String defaultContentType;

    /**
     * The channels used by this
     * <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#definitionsApplication">application</a>.
     * </p>
     * An identifier for the described channel. The channelId value is case-sensitive. Tools and libraries MAY
     * use the channelId to uniquely identify a channel, therefore, it is RECOMMENDED to follow common programming
     * naming conventions.
     */
    @JsonProperty(value = "channels")
    private Map<String, Channel> channels;

    /**
     * Holds a dictionary with all the operations this application MUST implement.
     * </p>
     * If you're looking for a place to define operations that MAY or MAY NOT be implemented by the
     * <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#definitionsApplication">application</a>
     * consider defining them in components/operations.
     */
    @JsonProperty(value = "operations")
    private Map<String, Operation> operations;

    /**
     * An element to hold various reusable objects for the specification. Everything that is defined inside this
     * object represents a resource that MAY or MAY NOT be used in the rest of the document and MAY or MAY NOT be
     * used by the implemented
     * <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#definitionsApplication">Application</a>.
     */
    @JsonProperty(value = "components")
    private Components components;
}
