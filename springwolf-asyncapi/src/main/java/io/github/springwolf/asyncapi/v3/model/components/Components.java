// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.ServerBinding;
import io.github.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.springwolf.asyncapi.v3.model.Tag;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelParameter;
import io.github.springwolf.asyncapi.v3.model.channel.CorrelationID;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageTrait;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationReply;
import io.github.springwolf.asyncapi.v3.model.operation.OperationReplyAddress;
import io.github.springwolf.asyncapi.v3.model.operation.OperationTraits;
import io.github.springwolf.asyncapi.v3.model.security_scheme.SecurityScheme;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.asyncapi.v3.model.server.ServerVariable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Holds a set of reusable objects for different aspects of the AsyncAPI specification. All objects defined within the
 * components object will have no effect on the API unless they are explicitly referenced from properties outside the
 * components object.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Components extends ExtendableObject {
    /**
     * An object to hold reusable Schema Object. If this is a Schema Object, then the schemaFormat will be assumed to
     * be "application/vnd.aai.asyncapi+json;version=asyncapi" where the version is equal to the AsyncAPI Version String.
     */
    @JsonProperty(value = "schemas")
    private Map<String, ComponentSchema> schemas;

    /**
     * An object to hold reusable Server Objects.
     */
    @JsonProperty(value = "servers")
    private Map<String, Server> servers;

    /**
     * An object to hold reusable Channel Objects.
     */
    @JsonProperty(value = "channels")
    private Map<String, ChannelObject> channels;

    /**
     * An object to hold reusable Operation Objects.
     */
    @JsonProperty(value = "operations")
    private Map<String, Operation> operations;

    /**
     * An object to hold reusable Message Objects.
     */
    @JsonProperty(value = "messages")
    private Map<String, Message> messages;

    /**
     * An object to hold reusable Security Scheme Objects.
     */
    @JsonProperty(value = "securitySchemes")
    private Map<String, SecurityScheme> securitySchemes;

    /**
     * An object to hold reusable Server Variable Objects.
     */
    @JsonProperty(value = "serverVariables")
    private Map<String, ServerVariable> serverVariables;

    /**
     * An object to hold reusable Parameter Objects.
     */
    @JsonProperty(value = "parameters")
    private Map<String, ChannelParameter> parameters;

    /**
     * An object to hold reusable Correlation ID Objects.
     */
    @JsonProperty(value = "correlationIds")
    private Map<String, CorrelationID> correlationIds;

    /**
     * An object to hold reusable Operation Reply Objects.
     */
    @JsonProperty(value = "replies")
    private Map<String, OperationReply> replies;

    /**
     * An object to hold reusable Operation Reply Address Objects.
     */
    @JsonProperty(value = "replyAddresses")
    private Map<String, OperationReplyAddress> replyAddresses;

    /**
     * An object to hold reusable External Documentation Objects.
     */
    @JsonProperty(value = "externalDocs")
    private Map<String, ExternalDocumentation> externalDocs;

    /**
     * An object to hold reusable Tag Objects.
     */
    @JsonProperty(value = "tags")
    private Map<String, Tag> tags;

    /**
     * An object to hold reusable Operation Trait Objects.
     */
    @JsonProperty(value = "operationTraits")
    private Map<String, OperationTraits> operationTraits;

    /**
     * An object to hold reusable Message Trait Objects.
     */
    @JsonProperty(value = "messageTraits")
    private Map<String, MessageTrait> messageTraits;

    /**
     * An object to hold reusable Server Bindings Objects.
     */
    @JsonProperty(value = "serverBindings")
    private Map<String, ServerBinding> serverBindings;

    /**
     * An object to hold reusable Channel Bindings Objects.
     */
    @JsonProperty(value = "channelBindings")
    private Map<String, ChannelBinding> channelBindings;

    /**
     * An object to hold reusable Operation Bindings Objects.
     */
    @JsonProperty(value = "operationBindings")
    private Map<String, OperationBinding> operationBindings;

    /**
     * An object to hold reusable Message Bindings Objects.
     */
    @JsonProperty(value = "messageBindings")
    private Map<String, MessageBinding> messageBindings;
}
