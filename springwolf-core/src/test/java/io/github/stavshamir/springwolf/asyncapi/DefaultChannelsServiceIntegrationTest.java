// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            DefaultChannelsService.class,
            DefaultChannelsServiceIntegrationTest.FooChannelScanner.class,
            DefaultChannelsServiceIntegrationTest.BarChannelScanner.class,
            DefaultChannelsServiceIntegrationTest.SameTopic.SubscribeChannelScanner.class,
            DefaultChannelsServiceIntegrationTest.SameTopic.ProduceChannelScanner.class
        })
class DefaultChannelsServiceIntegrationTest {

    @Autowired
    private DefaultChannelsService defaultChannelsService;

    @Autowired
    private FooChannelScanner fooChannelScanner;

    @Autowired
    private BarChannelScanner barChannelScanner;

    @Test
    void getChannels() {
        Map<String, ChannelItem> actualChannels = defaultChannelsService.findChannels();

        assertThat(actualChannels)
                .containsAllEntriesOf(fooChannelScanner.scan())
                .containsAllEntriesOf(barChannelScanner.scan())
                .containsEntry(SameTopic.topicName, SameTopic.expectedMergedChannel);
    }

    @Component
    static class FooChannelScanner implements ChannelsScanner {
        @Override
        public Map<String, ChannelItem> scan() {
            return Map.of("foo", new ChannelItem());
        }
    }

    @Component
    static class BarChannelScanner implements ChannelsScanner {
        @Override
        public Map<String, ChannelItem> scan() {
            return Map.of("bar", new ChannelItem());
        }
    }

    static class SameTopic {
        static final String topicName = "subscribeProduceTopic";
        static final ChannelItem expectedMergedChannel = ChannelItem.builder()
                .publish(SameTopic.ProduceChannelScanner.publishOperation)
                .subscribe(SameTopic.SubscribeChannelScanner.subscribeOperation)
                .build();

        @Component
        static class ProduceChannelScanner implements ChannelsScanner {
            static final Operation publishOperation =
                    Operation.builder().message("publish").build();

            @Override
            public Map<String, ChannelItem> scan() {
                return Map.of(
                        topicName,
                        ChannelItem.builder().publish(publishOperation).build());
            }
        }

        @Component
        static class SubscribeChannelScanner implements ChannelsScanner {
            static final Operation subscribeOperation =
                    Operation.builder().message("consumer").build();

            @Override
            public Map<String, ChannelItem> scan() {
                return Map.of(
                        topicName,
                        ChannelItem.builder().subscribe(subscribeOperation).build());
            }
        }
    }
}
