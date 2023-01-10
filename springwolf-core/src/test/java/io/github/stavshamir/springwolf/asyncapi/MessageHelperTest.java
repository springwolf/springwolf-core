package io.github.stavshamir.springwolf.asyncapi;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.messageObjectToSet;
import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MessageHelperTest {

    @Test
    public void toMessageObjectOrComposition_emptySet() {
        assertThatThrownBy(() -> toMessageObjectOrComposition(Collections.emptySet()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void toMessageObjectOrComposition_oneMessage() {
        Message message = Message.builder()
                .name("foo")
                .build();

        Object asObject = toMessageObjectOrComposition(ImmutableSet.of(message));

        assertThat(asObject)
                .isInstanceOf(Message.class)
                .isEqualTo(message);
    }


    @Test
    public void toMessageObjectOrComposition_multipleMessages() {
        Message message1 = Message.builder()
                .name("foo")
                .build();

        Message message2 = Message.builder()
                .name("bar")
                .build();

        Object asObject = toMessageObjectOrComposition(ImmutableSet.of(message1, message2));

        assertThat(asObject)
                .isInstanceOf(Map.class)
                .isEqualTo(ImmutableMap.of("oneOf", ImmutableSet.of(message1, message2)));
    }

    @Test
    public void toMessageObjectOrComposition_multipleMessages_remove_duplicates() {
        Message message1 = Message.builder()
                .name("foo")
                .description("This is message 1")
                .build();

        Message message2 = Message.builder()
                .name("bar")
                .description("This is message 2")
                .build();

        Message message3 = Message.builder()
                .name("bar")
                .description("This is message 3, but in essence the same payload type as message 2")
                .build();

        Object asObject = toMessageObjectOrComposition(ImmutableSet.of(message1, message2, message3));

        // Message3 is not included as it is identical in terms of payload type (Message#name) to message 2
        assertThat(asObject)
                .isInstanceOf(Map.class)
                .isEqualTo(ImmutableMap.of("oneOf", ImmutableSet.of(message1, message2)));
    }

    @Test
    public void toMessageObjectOrComposition_multipleMessages_should_not_break_deep_equals() {
        Message actualMessage1 = Message.builder()
                .name("foo")
                .description("This is actual message 1")
                .build();

        Message actualMessage2 = Message.builder()
                .name("bar")
                .description("This is actual message 2")
                .build();

        Object actualObject = toMessageObjectOrComposition(ImmutableSet.of(actualMessage1, actualMessage2));

        Message expectedMessage1 = Message.builder()
                .name("foo")
                .description("This is expected message 1")
                .build();

        Message expectedMessage2 = Message.builder()
                .name("bar")
                .description("This is expected message 2")
                .build();

        Object expectedObject = toMessageObjectOrComposition(ImmutableSet.of(expectedMessage1, expectedMessage2));

        assertThat(actualObject).isNotEqualTo(expectedObject);
    }

    @Test
    public void messageObjectToSet_notAMessageOrAMap() {
        Object string = "foo";

        Set<Message> messages = messageObjectToSet(string);

        assertThat(messages)
                .isEmpty();
    }

    @Test
    public void messageObjectToSet_Message() {
        Message message = Message.builder()
                .name("foo")
                .build();
        Object asObject = toMessageObjectOrComposition(ImmutableSet.of(message));

        Set<Message> messages = messageObjectToSet(asObject);

        assertThat(messages)
                .containsExactly(message);
    }

    @Test
    public void messageObjectToSet_SetOfMessage() {
        Message message1 = Message.builder()
                .name("foo")
                .build();

        Message message2 = Message.builder()
                .name("bar")
                .build();

        Object asObject = toMessageObjectOrComposition(ImmutableSet.of(message1, message2));

        Set<Message> messages = messageObjectToSet(asObject);

        assertThat(messages)
                .containsExactlyInAnyOrder(message1, message2);
    }

}
