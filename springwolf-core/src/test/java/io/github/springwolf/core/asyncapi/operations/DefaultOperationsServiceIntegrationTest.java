// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.operations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(
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
        Operation simpleOperation = simpleOperationsScanner.scan().get(0);

        assertThat(actualChannels)
                .containsEntry(simpleOperation.getOperationId(), simpleOperation)
                .containsEntry("receive", SameTopic.ReceiveOperationScanner.receiveOperation)
                .containsEntry("send", SameTopic.SendOperationScanner.sentOperation);
    }

    @Component
    static class SimpleOperationScanner implements OperationsScanner {
        @Override
        public List<Operation> scan() {
            Operation operation = Operation.builder()
                    .operationId("foo")
                    .channel(ChannelReference.fromChannel("foo"))
                    .action(OperationAction.RECEIVE)
                    .build();

            return List.of(operation);
        }
    }

    static class SameTopic {
        static final String topicName = "receiveSendTopic";

        @Component
        static class SendOperationScanner implements OperationsScanner {
            static final Operation sentOperation = Operation.builder()
                    .operationId("send")
                    .channel(ChannelReference.fromChannel(topicName))
                    .action(OperationAction.SEND)
                    .build();

            @Override
            public List<Operation> scan() {
                return List.of(sentOperation);
            }
        }

        @Component
        static class ReceiveOperationScanner implements OperationsScanner {
            static final Operation receiveOperation = Operation.builder()
                    .operationId("receive")
                    .channel(ChannelReference.fromChannel(topicName))
                    .action(OperationAction.RECEIVE)
                    .build();

            @Override
            public List<Operation> scan() {
                return List.of(receiveOperation);
            }
        }
    }
}
