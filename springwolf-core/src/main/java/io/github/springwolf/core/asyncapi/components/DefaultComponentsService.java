// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.core.asyncapi.messages.MessagesService;
import io.github.springwolf.core.asyncapi.schemas.SchemasService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class DefaultComponentsService implements ComponentsService {
    private final SchemasService schemasService;
    private final MessagesService messagesService;

    @Override
    public Map<String, ComponentSchema> getSchemas() {
        return schemasService.getSchemas();
    }

    @Override
    public Map<String, Message> getMessages() {
        return messagesService.getMessages();
    }
}
