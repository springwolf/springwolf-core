package io.github.stavshamir.springwolf.asyncapi;

import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MessageHelper {
    private static final String ONE_OF = "oneOf";

    private static final Comparator<Message> byMessageName = Comparator.comparing(Message::getName);
    private static final Supplier<TreeSet<Message>> messageSupplier = () -> new TreeSet<>(byMessageName);

    public static Object toMessageObjectOrComposition(Set<Message> messages) {
        return messages.size() == 1
                ? messages.toArray()[0]
                : ImmutableMap.of(ONE_OF, messages.stream().collect(Collectors.toCollection(messageSupplier)));
    }
}
