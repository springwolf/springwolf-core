// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.messageObjectToSet;
import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessagesMap;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MessageHelperTest {

    @Test
    void toMessageObjectOrComposition_emptySet() {
        assertThatThrownBy(() -> toMessagesMap(Collections.emptySet())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toMessagesMap_oneMessage() {
        MessageObject message = MessageObject.builder().name("foo").build();

        var messagesMap = toMessagesMap(Set.of(message));

        assertThat(messagesMap).containsExactlyInAnyOrderEntriesOf(Map.of("foo", message));
    }

    @Test
    void toMessagesMap_multipleMessages() {
        MessageObject message1 = MessageObject.builder().name("foo").build();

        MessageObject message2 = MessageObject.builder().name("bar").build();

        var messages = toMessagesMap(Set.of(message1, message2));

        assertThat(messages).containsExactlyInAnyOrderEntriesOf(Map.of("bar", message2, "foo", message1));
    }

    @Test
    void toMessagesMap_multipleMessages_remove_duplicates() {
        MessageObject message1 = MessageObject.builder()
                .name("foo")
                .description("This is message 1")
                .build();

        MessageObject message2 = MessageObject.builder()
                .name("bar")
                .description("This is message 2")
                .build();

        MessageObject message3 = MessageObject.builder()
                .name("bar")
                .description("This is message 3, but in essence the same payload type as message 2")
                .build();

        var messages = toMessagesMap(Set.of(message1, message2, message3));

        // we do not have any guarantee whether message2 or message3 won the deduplication.
        assertThat(messages)
                .hasSize(2)
                .containsValue(message1)
                .containsAnyOf(entry("bar", message2), entry("bar", message3));
    }

    @Test
    void toMessagesMap_multipleMessages_should_not_break_deep_equals() {
        MessageObject actualMessage1 = MessageObject.builder()
                .name("foo")
                .description("This is actual message 1")
                .build();

        MessageObject actualMessage2 = MessageObject.builder()
                .name("bar")
                .description("This is actual message 2")
                .build();

        Object actualObject = toMessagesMap(Set.of(actualMessage1, actualMessage2));

        MessageObject expectedMessage1 = MessageObject.builder()
                .name("foo")
                .description("This is expected message 1")
                .build();

        MessageObject expectedMessage2 = MessageObject.builder()
                .name("bar")
                .description("This is expected message 2")
                .build();

        Object expectedObject = toMessagesMap(Set.of(expectedMessage1, expectedMessage2));

        assertThat(actualObject).isNotEqualTo(expectedObject);
    }

    @Test
    void messageObjectToSet_Message() {
        MessageObject message = MessageObject.builder().name("foo").build();
        var asMap = toMessagesMap(Set.of(message));

        Set<Message> messages = messageObjectToSet(asMap);

        assertThat(messages).containsExactly(message);
    }

    @Test
    void messageObjectToSet_SetOfMessage() {
        MessageObject message1 = MessageObject.builder().name("foo").build();

        MessageObject message2 = MessageObject.builder().name("bar").build();

        var asMap = toMessagesMap(Set.of(message1, message2));

        Set<Message> messages = messageObjectToSet(asMap);

        assertThat(messages).containsExactlyInAnyOrder(message1, message2);
    }
}
