// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import io.github.springwolf.core.asyncapi.MessageHelper;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
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
        ChannelObject publisherChannel = ChannelObject.builder().build();
        ChannelObject subscriberChannel = ChannelObject.builder().build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.mergeChannels(
                Arrays.asList(Map.entry(channelName1, publisherChannel), Map.entry(channelName2, subscriberChannel)));

        // then
        assertThat(mergedChannels).hasSize(2);
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
    void shouldUseFirstChannelFound() {
        // given
        String channelName = "channel";
        ChannelObject publisherChannel1 =
                ChannelObject.builder().title("channel1").build();
        ChannelObject publisherChannel2 =
                ChannelObject.builder().title("channel2").build();

        // when
        Map<String, ChannelObject> mergedChannels = ChannelMerger.mergeChannels(
                Arrays.asList(Map.entry(channelName, publisherChannel1), Map.entry(channelName, publisherChannel2)));

        // then
        assertThat(mergedChannels).hasSize(1).hasEntrySatisfying(channelName, it -> {
            assertThat(it.getTitle()).isEqualTo("channel1");
        });
    }

    @Test
    void shouldMergeDifferentMessagesForSameChannel() {
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
        ChannelObject publisherChannel1 = ChannelObject.builder()
                .messages(Map.of(message1.getName(), MessageReference.toComponentMessage(message1)))
                .build();
        ChannelObject publisherChannel2 = ChannelObject.builder()
                .messages(Map.of(message2.getName(), MessageReference.toComponentMessage(message2)))
                .build();
        ChannelObject publisherChannel3 = ChannelObject.builder()
                .messages(Map.of(message3.getName(), MessageReference.toComponentMessage(message3)))
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
            assertThat(it.getMessages()).containsExactlyInAnyOrderEntriesOf(expectedMessages);
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
}
