// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.messageObjectToSet;
import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MessageHelperTest {

    @Test
    void toMessageObjectOrComposition_emptySet() {
        assertThatThrownBy(() -> toMessageObjectOrComposition(Collections.emptySet()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toMessageObjectOrComposition_oneMessage() {
        Message message = Message.builder().name("foo").build();

        Object asObject = toMessageObjectOrComposition(Set.of(message));

        assertThat(asObject).isInstanceOf(Message.class).isEqualTo(message);
    }

    @Test
    void toMessageObjectOrComposition_multipleMessages() {
        Message message1 = Message.builder().name("foo").build();

        Message message2 = Message.builder().name("bar").build();

        Object asObject = toMessageObjectOrComposition(Set.of(message1, message2));

        assertThat(asObject).isInstanceOf(Map.class).isEqualTo(Map.of("oneOf", List.of(message2, message1)));
    }

    @Test
    void toMessageObjectOrComposition_multipleMessages_remove_duplicates() {
        Message message1 =
                Message.builder().name("foo").description("This is message 1").build();

        Message message2 =
                Message.builder().name("bar").description("This is message 2").build();

        Message message3 = Message.builder()
                .name("bar")
                .description("This is message 3, but in essence the same payload type as message 2")
                .build();

        Object asObject = toMessageObjectOrComposition(Set.of(message1, message2, message3));

        Map<String, List<Message>> oneOfMap = (Map<String, List<Message>>) asObject;
        assertThat(oneOfMap).hasSize(1);
        List<Message> deduplicatedMessageList = oneOfMap.get("oneOf");
        // we do not have any guarantee wether message2 or message3 won the deduplication.
        assertThat(deduplicatedMessageList).hasSize(2).contains(message1).containsAnyOf(message2, message3);
    }

    @Test
    void toMessageObjectOrComposition_multipleMessages_should_not_break_deep_equals() {
        Message actualMessage1 = Message.builder()
                .name("foo")
                .description("This is actual message 1")
                .build();

        Message actualMessage2 = Message.builder()
                .name("bar")
                .description("This is actual message 2")
                .build();

        Object actualObject = toMessageObjectOrComposition(Set.of(actualMessage1, actualMessage2));

        Message expectedMessage1 = Message.builder()
                .name("foo")
                .description("This is expected message 1")
                .build();

        Message expectedMessage2 = Message.builder()
                .name("bar")
                .description("This is expected message 2")
                .build();

        Object expectedObject = toMessageObjectOrComposition(Set.of(expectedMessage1, expectedMessage2));

        assertThat(actualObject).isNotEqualTo(expectedObject);
    }

    @Test
    void messageObjectToSet_notAMessageOrAMap() {
        Object string = "foo";

        Set<Message> messages = messageObjectToSet(string);

        assertThat(messages).isEmpty();
    }

    @Test
    void messageObjectToSet_Message() {
        Message message = Message.builder().name("foo").build();
        Object asObject = toMessageObjectOrComposition(Set.of(message));

        Set<Message> messages = messageObjectToSet(asObject);

        assertThat(messages).containsExactly(message);
    }

    @Test
    void messageObjectToSet_SetOfMessage() {
        Message message1 = Message.builder().name("foo").build();

        Message message2 = Message.builder().name("bar").build();

        Object asObject = toMessageObjectOrComposition(Set.of(message1, message2));

        Set<Message> messages = messageObjectToSet(asObject);

        assertThat(messages).containsExactlyInAnyOrder(message1, message2);
    }
}
