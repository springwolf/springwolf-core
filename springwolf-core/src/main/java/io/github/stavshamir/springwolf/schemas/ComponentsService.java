// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaObject;

import java.util.Map;

public interface ComponentsService {

    Map<String, SchemaObject> getSchemas();

    String registerSchema(AsyncHeaders headers);

    @Deprecated
    String registerSchema(Class<?> type);

    String registerSchema(Class<?> type, String contentType);

    Map<String, Message> getMessages();

    MessageReference registerMessage(MessageObject message);
}
