// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;

import java.util.Map;

/**
 * Provides the schemas and messages to include in the AsyncAPI components block.
 */
public interface ComponentsService {

    /**
     * Provides a map of all registered schemas.
     *
     * @return the registered payload and header schemas
     */
    Map<String, ComponentSchema> getSchemas();

    /**
     * Provides a map of all registered messages.
     *
     * @return the registered messages
     */
    Map<String, Message> getMessages();
}
