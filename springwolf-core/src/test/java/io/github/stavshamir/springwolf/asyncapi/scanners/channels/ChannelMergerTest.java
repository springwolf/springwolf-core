package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.MessageHelper;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ChannelMergerTest {

    @Test
    void shouldNotMergeDifferentChannelNames() {
        // given
        String channelName1 = "channel1";
        String channelName2 = "channel2";
        Operation publishOperation =
                Operation.builder().operationId("publisher").build();
        Operation subscribeOperation =
                Operation.builder().operationId("subscribe").build();
        ChannelItem publisherChannel =
                ChannelItem.builder().publish(publishOperation).build();
        ChannelItem subscriberChannel =
                ChannelItem.builder().subscribe(subscribeOperation).build();

        // when
        Map<String, ChannelItem> mergedChannels = ChannelMerger.merge(
                Arrays.asList(Map.entry(channelName1, publisherChannel), Map.entry(channelName2, subscriberChannel)));

        // then
        assertThat(mergedChannels)
                .hasSize(2)
                .hasEntrySatisfying(channelName1, it -> {
                    assertThat(it.getPublish()).isEqualTo(publishOperation);
                    assertThat(it.getSubscribe()).isNull();
                })
                .hasEntrySatisfying(channelName2, it -> {
                    assertThat(it.getPublish()).isNull();
                    assertThat(it.getSubscribe()).isEqualTo(subscribeOperation);
                });
    }

    @Test
    void shouldMergePublisherAndSubscriberIntoOneChannel() {
        // given
        String channelName = "channel";
        Operation publishOperation =
                Operation.builder().operationId("publisher").build();
        Operation subscribeOperation =
                Operation.builder().operationId("subscribe").build();
        ChannelItem publisherChannel =
                ChannelItem.builder().publish(publishOperation).build();
        ChannelItem subscriberChannel =
                ChannelItem.builder().subscribe(subscribeOperation).build();

        // when
        Map<String, ChannelItem> mergedChannels = ChannelMerger.merge(
                Arrays.asList(Map.entry(channelName, publisherChannel), Map.entry(channelName, subscriberChannel)));

        // then
        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            assertThat(it.getPublish()).isEqualTo(publishOperation);
            assertThat(it.getSubscribe()).isEqualTo(subscribeOperation);
        });
    }

    @Test
    void shouldUseFirstOperationFound() {
        // given
        String channelName = "channel";
        Operation publishOperation1 =
                Operation.builder().operationId("publisher1").build();
        Operation publishOperation2 =
                Operation.builder().operationId("publisher2").build();
        ChannelItem publisherChannel1 =
                ChannelItem.builder().publish(publishOperation1).build();
        ChannelItem publisherChannel2 =
                ChannelItem.builder().publish(publishOperation2).build();

        // when
        Map<String, ChannelItem> mergedChannels = ChannelMerger.merge(
                Arrays.asList(Map.entry(channelName, publisherChannel1), Map.entry(channelName, publisherChannel2)));

        // then
        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            assertThat(it.getPublish()).isEqualTo(publishOperation1);
            assertThat(it.getSubscribe()).isNull();
        });
    }

    @Test
    void shouldMergeDifferentMessageForSameOperation() {
        // given
        String channelName = "channel";
        Message message1 = Message.builder()
                .name(String.class.getCanonicalName())
                .description("This is a string")
                .build();
        Message message2 = Message.builder()
                .name(Integer.class.getCanonicalName())
                .description("This is an integer")
                .build();
        Message message3 = Message.builder()
                .name(Integer.class.getCanonicalName())
                .description("This is also an integer, but in essence the same payload type")
                .build();
        Operation publishOperation1 =
                Operation.builder().operationId("publisher1").message(message1).build();
        Operation publishOperation2 =
                Operation.builder().operationId("publisher2").message(message2).build();
        Operation publishOperation3 =
                Operation.builder().operationId("publisher3").message(message3).build();
        ChannelItem publisherChannel1 =
                ChannelItem.builder().publish(publishOperation1).build();
        ChannelItem publisherChannel2 =
                ChannelItem.builder().publish(publishOperation2).build();
        ChannelItem publisherChannel3 =
                ChannelItem.builder().publish(publishOperation3).build();

        // when
        Map<String, ChannelItem> mergedChannels = ChannelMerger.merge(Arrays.asList(
                Map.entry(channelName, publisherChannel1),
                Map.entry(channelName, publisherChannel2),
                Map.entry(channelName, publisherChannel3)));

        // then expectedMessage only includes message1 and message2.
        // Message3 is not included as it is identical in terms of payload type (Message#name) to message 2
        Object expectedMessages = MessageHelper.toMessageObjectOrComposition(Set.of(message1, message2));
        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            assertThat(it.getPublish())
                    .isEqualTo(Operation.builder()
                            .operationId("publisher1")
                            .message(expectedMessages)
                            .build());
            assertThat(it.getSubscribe()).isNull();
        });
    }

    @Test
    void shouldUseOtherMessageIfFirstMessageIsMissing() {
        // given
        String channelName = "channel";
        Message message2 = Message.builder()
                .name(String.class.getCanonicalName())
                .description("This is a string")
                .build();
        Operation publishOperation1 =
                Operation.builder().operationId("publisher1").build();
        Operation publishOperation2 =
                Operation.builder().operationId("publisher2").message(message2).build();
        ChannelItem publisherChannel1 =
                ChannelItem.builder().publish(publishOperation1).build();
        ChannelItem publisherChannel2 =
                ChannelItem.builder().publish(publishOperation2).build();

        // when
        Map<String, ChannelItem> mergedChannels = ChannelMerger.merge(
                Arrays.asList(Map.entry(channelName, publisherChannel1), Map.entry(channelName, publisherChannel2)));

        // then expectedMessage message2
        Object expectedMessages = MessageHelper.toMessageObjectOrComposition(Set.of(message2));
        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            assertThat(it.getPublish())
                    .isEqualTo(Operation.builder()
                            .operationId("publisher1")
                            .message(expectedMessages)
                            .build());
            assertThat(it.getSubscribe()).isNull();
        });
    }
}
