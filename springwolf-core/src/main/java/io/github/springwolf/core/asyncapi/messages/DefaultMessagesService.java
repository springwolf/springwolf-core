// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.messages;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DefaultMessagesService implements MessagesService {
    private final Map<String, Message> messages = new HashMap<>();

    @Override
    public Map<String, Message> getMessages() {
        return messages;
    }

    @Override
    public MessageReference registerMessage(MessageObject message) {
        log.trace("Registering message for {}", message.getMessageId());
        messages.putIfAbsent(message.getMessageId(), message);
        return MessageReference.toComponentMessage(message);
    }
}
