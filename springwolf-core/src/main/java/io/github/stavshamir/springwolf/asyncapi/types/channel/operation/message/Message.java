// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Describes a message received on a given channel and operation.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#messageObject">Message specification</a>
 */
// FIXME: DELETE THIS CLASS
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    public static final String DEFAULT_SCHEMA_FORMAT = "application/vnd.oai.openapi+json;version=3.0.0";

    @Builder.Default
    private String schemaFormat = DEFAULT_SCHEMA_FORMAT;

    /**
     * Unique string used to identify the message.
     * <p>
     * The id MUST be unique among all messages described in the API. The messageId value is case-sensitive.
     * Tools and libraries MAY use the messageId to uniquely identify a message, therefore, it is RECOMMENDED to
     * follow common programming naming conventions.
     */
    private String messageId;

    /**
     * A machine-friendly name for the message.
     */
    private String name;

    /**
     * A human-friendly title for the message.
     */
    private String title;

    /**
     * A human-friendly description for the message.
     */
    private String description;

    private PayloadReference payload;

    private HeaderReference headers;

    private Map<String, MessageBinding> bindings;

    // Why do we add this empty class if Lombok @Builder is doing this job? Because this class is used as an argument
    // in one method. Since Lombok works as an annotation Processor, the JavaDoc tool cannot find the generated class
    // and fails.
    // The alternative to define this class would be to use `delombok` during the Javadoc generation. This is really
    // easy
    // in Maven, but with Gradle seems to be more complicated. Creating an empty class that Lombok overrides and expands
    // is much cleaner.
    public static class MessageBuilder {
        MessageBuilder() {}
    }
}
