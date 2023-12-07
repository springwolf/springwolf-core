// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ServerBinding;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.ExternalDocumentation;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Reference;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Tag;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.security_scheme.SecurityScheme;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * An object representing a message broker, a server or any other kind of computer program capable of sending and/or
 * receiving data. This object is used to capture details such as URIs, protocols and security configuration.
 * Variable substitution can be used so that some details, for example usernames and passwords, can be injected by code
 * generation tools.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#serverObject">Server</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Server extends ExtendableObject implements Reference {

    /**
     * REQUIRED. The server host name. It MAY include the port. This field supports Server Variables.
     * Variable substitutions will be made when a variable is named in {braces}.
     */
    @NotNull
    @JsonProperty(value = "host")
    private String host;

    /**
     * REQUIRED. The protocol this server supports for connection.
     */
    @NotNull
    @JsonProperty(value = "protocol")
    private String protocol;

    /**
     * The version of the protocol used for connection. For instance: AMQP 0.9.1, HTTP 2.0, Kafka 1.0.0, etc.
     */
    @JsonProperty(value = "protocolVersion")
    private String protocolVersion;

    /**
     * The path to a resource in the host. This field supports Server Variables. Variable substitutions will be
     * made when a variable is named in {braces}.
     */
    @JsonProperty(value = "pathname")
    private String pathname;

    /**
     * An optional string describing the host designated by the URL.
     * <a href="https://spec.commonmark.org/">CommonMark syntax</a> MAY be used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * A human-friendly title for the server.
     */
    @JsonProperty(value = "title")
    private String title;

    /**
     * A short summary of the server.
     */
    @JsonProperty(value = "summary")
    private String summary;

    /**
     * A map between a variable name and its value. The value is used for substitution in the server's host and pathname
     * template.
     */
    @JsonProperty(value = "variables")
    private Map<String, ServerVariable> variables;

    /**
     * A declaration of which security schemes can be used with this server. The list of values includes alternative
     * security scheme objects that can be used. Only one of the security scheme objects need to be satisfied to
     * authorize a connection or operation.
     */
    @JsonProperty(value = "security")
    private List<SecurityScheme> security;

    /**
     * A list of tags for logical grouping and categorization of servers.
     */
    @JsonProperty(value = "tags")
    private List<Tag> tags;

    /**
     * Additional external documentation for this server.
     */
    @JsonProperty(value = "externalDocs")
    private ExternalDocumentation externalDocs;

    /**
     * A map where the keys describe the name of the protocol and the values describe protocol-specific definitions
     * for the server.
     */
    @JsonProperty(value = "bindings")
    private Map<String, ServerBinding> bindings;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
