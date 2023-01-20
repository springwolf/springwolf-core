package io.github.stavshamir.springwolf.asyncapi;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import org.junit.jupiter.api.Test;

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