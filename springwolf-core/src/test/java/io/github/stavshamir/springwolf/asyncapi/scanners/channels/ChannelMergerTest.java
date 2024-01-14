// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.MessageHelper;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.OperationAction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ChannelMergerTest {

    @Test
    void shouldNotMergeDifferentChannelNames() {
        // given
        String channelName1 = "channel1";
        String channelName2 = "channel2";
        ChannelObject publisherChannel = ChannelObject.builder().build();
        ChannelObject subscriberChannel = ChannelObject.builder().build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.mergeChannels(
                Arrays.asList(Map.entry(channelName1, publisherChannel), Map.entry(channelName2, subscriberChannel)));

        // then
        assertThat(mergedChannels).hasSize(2);
    }

    @Test
    void shouldNotMergeDifferentOperationNames() {
        // given
        String operationName1 = "operation1";
        String operationName2 = "operation2";
        Operation publisherOperation = Operation.builder().build();
        Operation subscriberOperation = Operation.builder().build();

        // when
        Map<String, Operation> mergedOperations = ChannelMerger.mergeOperations(Arrays.asList(
                Map.entry(operationName1, publisherOperation), Map.entry(operationName2, subscriberOperation)));

        // then
        assertThat(mergedOperations).hasSize(2);
    }

    @Test
    void shouldMergeEqualChannelNamesIntoOneChannel() {
        // given
        String channelName = "channel";
        ChannelObject publisherChannel = ChannelObject.builder().build();
        ChannelObject subscriberChannel = ChannelObject.builder().build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.mergeChannels(
                Arrays.asList(Map.entry(channelName, publisherChannel), Map.entry(channelName, subscriberChannel)));

        // then
        assertThat(mergedChannels).hasSize(1);
    }

    @Test
    void shouldMergeEqualOperationNamesIntoOneOperation() {
        // given
        String operationName = "operation";
        Operation publishOperation = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher")
                .build();
        Operation subscribeOperation = Operation.builder()
                .action(OperationAction.RECEIVE)
                .title("subscribe")
                .build();

        // when
        Map<String, Operation> mergedOperations = ChannelMerger.mergeOperations(Arrays.asList(
                Map.entry(operationName, publishOperation), Map.entry(operationName, subscribeOperation)));

        // then
        assertThat(mergedOperations).hasSize(1);
    }

    @Test
    void shouldUseFirstChannelFound() {
        // given
        String channelName = "channel";
        ChannelObject publisherChannel1 =
                ChannelObject.builder().channelId("channel1").build();
        ChannelObject publisherChannel2 =
                ChannelObject.builder().channelId("channel2").build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.mergeChannels(
                Arrays.asList(Map.entry(channelName, publisherChannel1), Map.entry(channelName, publisherChannel2)));

        // then
        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            assertThat(it.getChannelId()).isEqualTo("channel1");
        });
    }

    @Test
    void shouldUseFirstOperationFound() {
        // given
        String operationName = "operation";
        Operation senderOperation =
                Operation.builder().action(OperationAction.SEND).build();
        Operation receiverOperation =
                Operation.builder().action(OperationAction.RECEIVE).build();

        // when
        Map<String, Operation> mergedOperations = ChannelMerger.mergeOperations(
                Arrays.asList(Map.entry(operationName, senderOperation), Map.entry(operationName, receiverOperation)));

        // then
        assertThat(mergedOperations).hasSize(1).hasEntrySatisfying(operationName, it -> {
            assertThat(it.getAction()).isEqualTo(OperationAction.SEND);
        });
    }

    @Test
    void shouldMergeDifferentMessagesForSameChannel() {
        // given
        String channelName = "channel";
        MessageObject message1 = MessageObject.builder()
                .messageId("messageString")
                .name(String.class.getCanonicalName())
                .description("This is a string")
                .build();
        MessageObject message2 = MessageObject.builder()
                .messageId("messageInteger")
                .name(Integer.class.getCanonicalName())
                .description("This is an integer")
                .build();
        MessageObject message3 = MessageObject.builder()
                .messageId("messageInteger")
                .name(Integer.class.getCanonicalName())
                .description("This is also an integer, but in essence the same payload type")
                .build();
        ChannelObject publisherChannel1 = ChannelObject.builder()
                .messages(Map.of(message1.getMessageId(), MessageReference.fromMessage(message1)))
                .build();
        ChannelObject publisherChannel2 = ChannelObject.builder()
                .messages(Map.of(message2.getMessageId(), MessageReference.fromMessage(message2)))
                .build();
        ChannelObject publisherChannel3 = ChannelObject.builder()
                .messages(Map.of(message3.getMessageId(), MessageReference.fromMessage(message3)))
                .build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.mergeChannels(Arrays.asList(
                Map.entry(channelName, publisherChannel1),
                Map.entry(channelName, publisherChannel2),
                Map.entry(channelName, publisherChannel3)));

        // then expectedMessage only includes message1 and message2.
        // Message3 is not included as it is identical in terms of payload type (Message#name) to message 2
        var expectedMessages = MessageHelper.toMessagesMap(Set.of(message1, message2));
        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            assertThat(it.getMessages()).containsExactlyEntriesOf(expectedMessages);
        });
    }

    @Test
    void shouldMergeDifferentMessageForSameOperation() {
        // given
        String operationName = "operation";
        MessageObject message1 = MessageObject.builder()
                .messageId("messageString")
                .name(String.class.getCanonicalName())
                .description("This is a string")
                .build();
        MessageObject message2 = MessageObject.builder()
                .messageId("messageInteger")
                .name(Integer.class.getCanonicalName())
                .description("This is an integer")
                .build();
        MessageObject message3 = MessageObject.builder()
                .messageId("messageInteger")
                .name(Integer.class.getCanonicalName())
                .description("This is also an integer, but in essence the same payload type")
                .build();
        MessageReference messageRef1 = MessageReference.fromMessage(message1);
        MessageReference messageRef2 = MessageReference.fromMessage(message2);
        MessageReference messageRef3 = MessageReference.fromMessage(message3);

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
        Map<String, Operation> mergedOperations = ChannelMerger.mergeOperations(List.of(
                Map.entry(operationName, senderOperation1),
                Map.entry(operationName, senderOperation2),
                Map.entry(operationName, senderOperation3)));

        // then expectedMessage only includes message1 and message2.
        // Message3 is not included as it is identical in terms of payload type (Message#name) to message 2
        assertThat(mergedOperations).hasSize(1).hasEntrySatisfying(operationName, it -> {
            assertThat(it.getMessages()).containsExactlyInAnyOrder(messageRef1, messageRef2);
        });
    }

    @Test
    void shouldUseOtherMessageIfFirstMessageIsMissing() {
        // given
        String channelName = "channel";
        MessageObject message2 = MessageObject.builder()
                .messageId("message2")
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
                .messages(List.of(MessageReference.fromMessage(message2)))
                .build();
        ChannelObject publisherChannel1 =
                ChannelObject.builder() /*.publish(publishOperation1)FIXME*/.build();
        ChannelObject publisherChannel2 =
                ChannelObject.builder() /*.publish(publishOperation2)FIXME*/.build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.mergeChannels(
                Arrays.asList(Map.entry(channelName, publisherChannel1), Map.entry(channelName, publisherChannel2)));

        // then expectedMessage message2
        var expectedMessages = MessageHelper.toMessagesMap(Set.of(message2));
        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            //            assertThat(it.getPublish()) FIXME
            //                    .isEqualTo(Operation.builder()
            //                            .title("publisher1")
            //                            .message(expectedMessages) FIXME
            //                            .build());
            //            assertThat(it.getSubscribe()).isNull(); FIXME
        });
    }
}
