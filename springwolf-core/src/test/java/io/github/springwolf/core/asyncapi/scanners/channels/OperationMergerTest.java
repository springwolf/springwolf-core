// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OperationMergerTest {

    @Test
    void shouldNotMergeDifferentoperationIds() {
        // given
        String operationId1 = "operation1";
        String operationId2 = "operation2";
        Operation publisherOperation = Operation.builder().build();
        Operation subscriberOperation = Operation.builder().build();

        // when
        Map<String, Operation> mergedOperations = OperationMerger.mergeOperations(Arrays.asList(
                Map.entry(operationId1, publisherOperation), Map.entry(operationId2, subscriberOperation)));

        // then
        assertThat(mergedOperations).hasSize(2);
    }

    @Test
    void shouldMergeEqualoperationIdsIntoOneOperation() {
        // given
        String operationId = "operation";
        Operation publishOperation = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher")
                .build();
        Operation subscribeOperation = Operation.builder()
                .action(OperationAction.RECEIVE)
                .title("subscribe")
                .build();

        // when
        Map<String, Operation> mergedOperations = OperationMerger.mergeOperations(
                Arrays.asList(Map.entry(operationId, publishOperation), Map.entry(operationId, subscribeOperation)));

        // then
        assertThat(mergedOperations).hasSize(1);
    }

    @Test
    void shouldUseFirstOperationFound() {
        // given
        String operationId = "operation";
        Operation senderOperation =
                Operation.builder().action(OperationAction.SEND).build();
        Operation receiverOperation =
                Operation.builder().action(OperationAction.RECEIVE).build();

        // when
        Map<String, Operation> mergedOperations = OperationMerger.mergeOperations(
                Arrays.asList(Map.entry(operationId, senderOperation), Map.entry(operationId, receiverOperation)));

        // then
        assertThat(mergedOperations).hasSize(1).hasEntrySatisfying(operationId, it -> {
            assertThat(it.getAction()).isEqualTo(OperationAction.SEND);
        });
    }

    @Test
    void shouldMergeDifferentMessageForSameOperation() {
        // given
        String channelName = "channel";
        String operationId = "operation";
        MessageObject message1 = MessageObject.builder()
                .messageId("message1")
                .name(String.class.getCanonicalName())
                .description("This is a string")
                .build();
        MessageObject message2 = MessageObject.builder()
                .messageId("message2")
                .name(Integer.class.getCanonicalName())
                .description("This is an integer")
                .build();
        MessageObject message3 = MessageObject.builder()
                .messageId("message3")
                .name(Integer.class.getCanonicalName())
                .description("This is also an integer, but in essence the same payload type")
                .build();
        MessageReference messageRef1 = MessageReference.toChannelMessage(channelName, message1);
        MessageReference messageRef2 = MessageReference.toChannelMessage(channelName, message2);
        MessageReference messageRef3 = MessageReference.toChannelMessage(channelName, message3);

        Operation senderOperation1 = Operation.builder()
                .action(OperationAction.SEND)
                .title("sender1")
                .messages(List.of(messageRef1))
                .build();
        Operation senderOperation2 = Operation.builder()
                .action(OperationAction.SEND)
                .title("sender2")
                .messages(List.of(messageRef2))
                .build();
        Operation senderOperation3 = Operation.builder()
                .action(OperationAction.SEND)
                .title("sender3")
                .messages(List.of(messageRef3))
                .build();

        // when
        Map<String, Operation> mergedOperations = OperationMerger.mergeOperations(List.of(
                Map.entry(operationId, senderOperation1),
                Map.entry(operationId, senderOperation2),
                Map.entry(operationId, senderOperation3)));

        // then expectedMessage only includes message1 and message2.
        // Message3 is not included as it is identical in terms of payload type (Message#name) to message 2
        assertThat(mergedOperations).hasSize(1).hasEntrySatisfying(operationId, it -> {
            assertThat(it.getMessages()).containsExactlyInAnyOrder(messageRef1, messageRef2);
        });
    }

    @Test
    void shouldUseOtherMessageIfFirstMessageIsMissingForChannels() {
        // given
        String channelName = "channel";
        MessageObject message2 = MessageObject.builder()
                .messageId(String.class.getCanonicalName())
                .name(String.class.getCanonicalName())
                .description("This is a string")
                .build();
        ChannelObject publisherChannel1 = ChannelObject.builder().build();
        ChannelObject publisherChannel2 = ChannelObject.builder()
                .messages(Map.of(message2.getName(), message2))
                .build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.mergeChannels(
                Arrays.asList(Map.entry(channelName, publisherChannel1), Map.entry(channelName, publisherChannel2)));

        // then expectedMessage message2
        var expectedMessages = Map.of(message2.getName(), message2);

        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            assertThat(it.getMessages()).hasSize(1);
            assertThat(it.getMessages()).containsExactlyInAnyOrderEntriesOf(expectedMessages);
        });
    }

    @Test
    void shouldUseOtherMessageIfFirstMessageIsMissingForOperations() {
        // given
        String channelName = "channel-name";
        MessageObject message2 = MessageObject.builder()
                .messageId(String.class.getCanonicalName())
                .name(String.class.getCanonicalName())
                .description("This is a string")
                .build();
        Operation publishOperation1 = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher1")
                .build();
        Operation publishOperation2 = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher2")
                .messages(List.of(MessageReference.toChannelMessage(channelName, message2)))
                .build();

        // when
        Map<String, Operation> mergedOperations = OperationMerger.mergeOperations(
                Arrays.asList(Map.entry("publisher1", publishOperation1), Map.entry("publisher1", publishOperation2)));
        // then expectedMessage message2
        var expectedMessage = MessageReference.toChannelMessage(channelName, message2);

        assertThat(mergedOperations).hasSize(1).hasEntrySatisfying("publisher1", it -> {
            assertThat(it.getMessages()).hasSize(1);
            assertThat(it.getMessages()).containsExactlyInAnyOrder(expectedMessage);
        });
    }
}
