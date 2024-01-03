// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
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
        Map<String, ChannelObject> actualChannels = defaultChannelsService.findChannels();

        assertThat(actualChannels)
                .containsAllEntriesOf(fooChannelScanner.scan())
                .containsAllEntriesOf(barChannelScanner.scan())
                .containsEntry(SameTopic.topicName, SameTopic.expectedMergedChannel);
    }

    @Component
    static class FooChannelScanner implements ChannelsScanner {
        @Override
        public Map<String, ChannelObject> scan() {
            return Map.of("foo", new ChannelObject());
        }
    }

    @Component
    static class BarChannelScanner implements ChannelsScanner {
        @Override
        public Map<String, ChannelObject> scan() {
            return Map.of("bar", new ChannelObject());
        }
    }

    static class SameTopic {
        static final String topicName = "subscribeProduceTopic";
        static final ChannelObject expectedMergedChannel = ChannelObject.builder()
                //                .publish(SameTopic.ProduceChannelScanner.publishOperation) FIXME
                //                .subscribe(SameTopic.SubscribeChannelScanner.subscribeOperation)
                .build();

        @Component
        static class ProduceChannelScanner implements ChannelsScanner {
            static final Operation publishOperation =
                    Operation.builder() /*.message("publish")FIXME*/.build();

            @Override
            public Map<String, ChannelObject> scan() {
                return Map.of(
                        topicName,
                        ChannelObject.builder() /*.publish(publishOperation) FIXME*/
                                .build());
            }
        }

        @Component
        static class SubscribeChannelScanner implements ChannelsScanner {
            static final Operation subscribeOperation =
                    Operation.builder() /*.message("consumer")FIXME*/.build();

            @Override
            public Map<String, ChannelObject> scan() {
                return Map.of(
                        topicName,
                        ChannelObject.builder() /*.subscribe(subscribeOperation)FIXME*/
                                .build());
            }
        }
    }
}
