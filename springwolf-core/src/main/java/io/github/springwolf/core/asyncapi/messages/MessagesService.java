// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.messages;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;

import java.util.Map;

public interface MessagesService {
    Map<String, Message> getMessages();

    MessageReference registerMessage(MessageObject message);
}
