// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsScanner;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
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
            DefaultChannelsServiceIntegrationTest.SimpleChannelScanner.class,
            DefaultChannelsServiceIntegrationTest.SameTopic.ReceiveChannelScanner.class,
            DefaultChannelsServiceIntegrationTest.SameTopic.SendChannelScanner.class
        })
class DefaultChannelsServiceIntegrationTest {

    @Autowired
    private DefaultChannelsService defaultChannelsService;

    @Autowired
    private SimpleChannelScanner simpleChannelScanner;

    @Test
    void getChannels() {
        Map<String, ChannelObject> actualChannels = defaultChannelsService.findChannels();

        assertThat(actualChannels)
                .containsAllEntriesOf(simpleChannelScanner.scan())
                .containsEntry(SameTopic.topicName, SameTopic.expectedMergedChannel);
    }

    @Component
    static class SimpleChannelScanner implements ChannelsScanner {
        @Override
        public Map<String, ChannelObject> scan() {
            return Map.of("foo", new ChannelObject());
        }
    }

    static class SameTopic {
        static final String topicName = "receiveSendTopic";
        static final ChannelObject expectedMergedChannel = ChannelObject.builder()
                .messages(Map.of(
                        "receiveMessage",
                        MessageReference.toComponentMessage("receiveMessage"),
                        "sendMessage",
                        MessageReference.toComponentMessage("sendMessage")))
                .build();

        @Component
        static class SendChannelScanner implements ChannelsScanner {
            static final Operation sentOperation = Operation.builder()
                    .channel(ChannelReference.fromChannel(topicName))
                    .action(OperationAction.SEND)
                    .build();

            @Override
            public Map<String, ChannelObject> scan() {
                return Map.of(
                        topicName,
                        ChannelObject.builder()
                                .messages(Map.of("sendMessage", MessageReference.toComponentMessage("sendMessage")))
                                .build());
            }
        }

        @Component
        static class ReceiveChannelScanner implements ChannelsScanner {
            static final Operation receiveOperation = Operation.builder()
                    .channel(ChannelReference.fromChannel(topicName))
                    .action(OperationAction.RECEIVE)
                    .build();

            @Override
            public Map<String, ChannelObject> scan() {
                return Map.of(
                        topicName,
                        ChannelObject.builder()
                                .messages(
                                        Map.of("receiveMessage", MessageReference.toComponentMessage("receiveMessage")))
                                .build());
            }
        }
    }
}
