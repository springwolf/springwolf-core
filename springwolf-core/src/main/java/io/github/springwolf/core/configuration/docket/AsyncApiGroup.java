// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
@Builder
public class AsyncApiGroup {
    private final String groupName;

    private final List<OperationAction> operationActionsToKeep;
    private final List<Pattern> channelNamesToKeep;
    private final List<Pattern> messageNamesToKeep; // TODO: use this

    // Implementation Roadmap
    // first draft/beta:
    // * option to group by a single aspect i.e. only by Operation Option OR by ChannelName OR Message/Payload-Prefix
    // (v1.Message, v2.Message)
    // * different groups selectable via ui
    // * Options are exclusive -> validation/undefined behavior
    // * configuration via properties no code/no beans

    // private final String title;

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

    public boolean matchesChannel(ChannelObject value) {
        return channelNamesToKeep.stream()
                .anyMatch(pattern -> pattern.matcher(value.getAddress()).matches());
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
