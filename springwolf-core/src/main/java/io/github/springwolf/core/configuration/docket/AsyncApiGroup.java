// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AsyncApiGroup {
    private final String group;
    private final String title;

    //    public boolean match(Operation operation,
    //                                  AsyncAPI fullApi,
    //                                  RefResolver refResolver
    //    ) {
    //        // low-level API
    //        operation.getAction();
    //        operation.getMessages();
    //        operation.getMessages().get(0).title().startsWith()
    //
    //        return true;
    //    }

    // action -> consumer or producer
    // binding -> type (kafka, sqs)
    // channelName (topicName) -> adminEndpunkt , hidden
    // schemaName -> Payload.v1 vs Payload.v2
    // (server, contentType, header)

    public boolean matchOperation(OperationMatcher op) {
        //        return op.match(this);
        return false;
    }

    class OperationMatcher {
        public OperationMatcher matchMessage(MessageMatcher message) {
            return this;
        }

        class MessageMatcher {
            public MessageMatcher matchMessageTitle(String title) {
                return this;
            }
        }
    }
}
