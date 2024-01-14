// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.swagger.v3.oas.models.media.Schema;

import java.util.Map;

public interface SchemasService {

    Map<String, Schema> getSchemas();

    String register(AsyncHeaders headers);

    String register(Class<?> type);

    Map<String, Message> getMessages();

    MessageReference registerMessage(MessageObject message);
}
