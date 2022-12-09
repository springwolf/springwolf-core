package io.github.stavshamir.springwolf.asyncapi.scanners.channels;


import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChannelMergerTest {


    @Test
    public void shouldNotMergeDifferentChannelNames() {
        // given
        String channelName1 = "channel1";
        String channelName2 = "channel2";
        Operation publishOperation = Operation.builder().operationId("publisher").build();
        Operation subscribeOperation = Operation.builder().operationId("subscribe").build();
        ChannelItem publisherChannel = ChannelItem.builder().publish(publishOperation).build();
        ChannelItem subscriberChannel = ChannelItem.builder().subscribe(subscribeOperation).build();

        // when
        Map<String, ChannelItem> mergedChannels = ChannelMerger.merge(Arrays.asList(
                Maps.immutableEntry(channelName1, publisherChannel),
                Maps.immutableEntry(channelName2, subscriberChannel)));

        // then
        assertThat(mergedChannels).hasSize(2)
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
    public void shouldMergePublisherAndSubscriberIntoOneChannel() {
        // given
        String channelName = "channel";
        Operation publishOperation = Operation.builder().operationId("publisher").build();
        Operation subscribeOperation = Operation.builder().operationId("subscribe").build();
        ChannelItem publisherChannel = ChannelItem.builder().publish(publishOperation).build();
        ChannelItem subscriberChannel = ChannelItem.builder().subscribe(subscribeOperation).build();

        // when
        Map<String, ChannelItem> mergedChannels = ChannelMerger.merge(Arrays.asList(
                Maps.immutableEntry(channelName, publisherChannel),
                Maps.immutableEntry(channelName, subscriberChannel)));

        // then
        assertThat(mergedChannels).hasSize(1)
                .hasEntrySatisfying(channelName, it -> {
                    assertThat(it.getPublish()).isEqualTo(publishOperation);
                    assertThat(it.getSubscribe()).isEqualTo(subscribeOperation);
                });
    }

    @Test
    public void shouldUseFirstOperationFound() {
        // given
        String channelName = "channel";
        Operation publishOperation1 = Operation.builder().operationId("publisher1").build();
        Operation publishOperation2 = Operation.builder().operationId("publisher2").build();
        ChannelItem publisherChannel1 = ChannelItem.builder().publish(publishOperation1).build();
        ChannelItem publisherChannel2 = ChannelItem.builder().publish(publishOperation2).build();

        // when
        Map<String, ChannelItem> mergedChannels = ChannelMerger.merge(Arrays.asList(
                Maps.immutableEntry(channelName, publisherChannel1),
                Maps.immutableEntry(channelName, publisherChannel2)));

        // then
        assertThat(mergedChannels).hasSize(1)
                .hasEntrySatisfying(channelName, it -> {
                    assertThat(it.getPublish()).isEqualTo(publishOperation1);
                    assertThat(it.getSubscribe()).isNull();
                });
    }
}