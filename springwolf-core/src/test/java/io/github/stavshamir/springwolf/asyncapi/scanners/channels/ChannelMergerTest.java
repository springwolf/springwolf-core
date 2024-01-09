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
        //        Operation publishOperation =
        //                Operation.builder().action(OperationAction.SEND).build();
        //        Operation subscribeOperation =
        //                Operation.builder().action(OperationAction.RECEIVE).build();
        ChannelObject publisherChannel = ChannelObject.builder().build();
        ChannelObject subscriberChannel = ChannelObject.builder().build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.merge(
                Arrays.asList(Map.entry(channelName1, publisherChannel), Map.entry(channelName2, subscriberChannel)));

        // then
        assertThat(mergedChannels).hasSize(2);
        //                .hasEntrySatisfying(channelName1, it -> {
        //                    assertThat(it.getPublish()).isEqualTo(publishOperation);
        //                    assertThat(it.getSubscribe()).isNull();
        //                })
        //                .hasEntrySatisfying(channelName2, it -> {
        //                    assertThat(it.getPublish()).isNull();
        //                    assertThat(it.getSubscribe()).isEqualTo(subscribeOperation);
        //                });
    }

    @Test
    void shouldMergePublisherAndSubscriberIntoOneChannel() {
        // given
        String channelName = "channel";
        Operation publishOperation = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher")
                .build();
        Operation subscribeOperation = Operation.builder()
                .action(OperationAction.RECEIVE)
                .title("subscribe")
                .build();
        ChannelObject publisherChannel = ChannelObject.builder().build();
        ChannelObject subscriberChannel = ChannelObject.builder().build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.merge(
                Arrays.asList(Map.entry(channelName, publisherChannel), Map.entry(channelName, subscriberChannel)));

        // then
        assertThat(mergedChannels).hasSize(1);
        //        FIXME
        //                .hasEntrySatisfying(channelName, it -> {
        //            assertThat(it.getPublish()).isEqualTo(publishOperation);
        //            assertThat(it.getSubscribe()).isEqualTo(subscribeOperation);
        //        });
    }

    @Test
    void shouldUseFirstOperationFound() {
        // given
        String channelName = "channel";
        Operation publishOperation1 = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher1")
                .build();
        Operation publishOperation2 = Operation.builder()
                .action(OperationAction.RECEIVE)
                .title("publisher2")
                .build();
        ChannelObject publisherChannel1 = ChannelObject.builder().build();
        ChannelObject publisherChannel2 = ChannelObject.builder().build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.merge(
                Arrays.asList(Map.entry(channelName, publisherChannel1), Map.entry(channelName, publisherChannel2)));

        // then
        assertThat(mergedChannels).hasSize(1);
        //        FIXME
        //                .hasEntrySatisfying(channelName, it -> {
        //            assertThat(it.getPublish()).isEqualTo(publishOperation1);
        //            assertThat(it.getSubscribe()).isNull();
        //        });
    }

    @Test
    void shouldMergeDifferentMessageForSameOperation() {
        // given
        String channelName = "channel";
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
        Operation publishOperation1 = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher1")
                .messages(List.of(MessageReference.fromMessage(message1)))
                .build();
        Operation publishOperation2 = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher2")
                .messages(List.of(MessageReference.fromMessage(message2)))
                .build();
        Operation publishOperation3 = Operation.builder()
                .action(OperationAction.SEND)
                .title("publisher3")
                .messages(List.of(MessageReference.fromMessage(message3)))
                .build();
        ChannelObject publisherChannel1 = ChannelObject.builder().build();
        ChannelObject publisherChannel2 = ChannelObject.builder().build();
        ChannelObject publisherChannel3 = ChannelObject.builder().build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.merge(Arrays.asList(
                Map.entry(channelName, publisherChannel1),
                Map.entry(channelName, publisherChannel2),
                Map.entry(channelName, publisherChannel3)));

        // then expectedMessage only includes message1 and message2.
        // Message3 is not included as it is identical in terms of payload type (Message#name) to message 2
        Object expectedMessages = MessageHelper.toMessageObjectOrComposition(Set.of(message1, message2));
        assertThat(mergedChannels).hasSize(1);
        //                .hasEntrySatisfying(channelName, it -> {
        //            assertThat(it.getPublish())
        //                    .isEqualTo(Operation.builder()
        //                            .operationId("publisher1")
        //                            .message(expectedMessages)
        //                            .build());
        //            assertThat(it.getSubscribe()).isNull();
        //        });
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
        Map<String, ChannelObject> mergedChannels = ChannelMerger.merge(
                Arrays.asList(Map.entry(channelName, publisherChannel1), Map.entry(channelName, publisherChannel2)));

        // then expectedMessage message2
        Object expectedMessages = MessageHelper.toMessageObjectOrComposition(Set.of(message2));
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
