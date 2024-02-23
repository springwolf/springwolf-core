// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.core.asyncapi.scanners.channels.OperationsScanner;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.OperationAction;
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
            DefaultOperationsService.class,
            DefaultOperationsServiceIntegrationTest.SimpleOperationScanner.class,
            DefaultOperationsServiceIntegrationTest.SameTopic.ReceiveOperationScanner.class,
            DefaultOperationsServiceIntegrationTest.SameTopic.SendOperationScanner.class
        })
class DefaultOperationsServiceIntegrationTest {

    @Autowired
    private DefaultOperationsService defaultOperationsService;

    @Autowired
    private SimpleOperationScanner simpleOperationsScanner;

    @Test
    void getOperations() {
        Map<String, Operation> actualChannels = defaultOperationsService.findOperations();

        assertThat(actualChannels)
                .containsAllEntriesOf(simpleOperationsScanner.scan())
                .containsEntry("receive", SameTopic.ReceiveOperationScanner.receiveOperation)
                .containsEntry("send", SameTopic.SendOperationScanner.sentOperation);
    }

    @Component
    static class SimpleOperationScanner implements OperationsScanner {
        @Override
        public Map<String, Operation> scan() {
            return Map.of(
                    "foo",
                    Operation.builder()
                            .channel(ChannelReference.fromChannel("foo"))
                            .action(OperationAction.RECEIVE)
                            .build());
        }
    }

    static class SameTopic {
        static final String topicName = "receiveSendTopic";

        @Component
        static class SendOperationScanner implements OperationsScanner {
            static final Operation sentOperation = Operation.builder()
                    .channel(ChannelReference.fromChannel(topicName))
                    .action(OperationAction.SEND)
                    .build();

            @Override
            public Map<String, Operation> scan() {
                return Map.of("send", sentOperation);
            }
        }

        @Component
        static class ReceiveOperationScanner implements OperationsScanner {
            static final Operation receiveOperation = Operation.builder()
                    .channel(ChannelReference.fromChannel(topicName))
                    .action(OperationAction.RECEIVE)
                    .build();

            @Override
            public Map<String, Operation> scan() {
                return Map.of("receive", receiveOperation);
            }
        }
    }
}
