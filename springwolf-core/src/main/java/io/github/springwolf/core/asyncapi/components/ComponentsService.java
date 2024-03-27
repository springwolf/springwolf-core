// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;

import java.util.Map;

public interface ComponentsService {

    Map<String, SchemaObject> getSchemas();

    String registerSchema(AsyncHeaders headers);

    String registerSchema(Class<?> type);

    String registerSchema(Class<?> type, String contentType);

    Map<String, Message> getMessages();

    MessageReference registerMessage(MessageObject message);
}
