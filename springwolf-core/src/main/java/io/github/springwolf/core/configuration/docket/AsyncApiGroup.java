// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class AsyncApiGroup {
    private final String groupName;

    @Builder.Default
    private final List<OperationAction> operationActionsToKeep = Collections.emptyList();

    @Builder.Default
    private final List<Pattern> channelNamesToKeep = Collections.emptyList();

    @Builder.Default
    private final List<Pattern> messageNamesToKeep = Collections.emptyList();

    // Implementation Roadmap
    // first draft/beta:
    // * option to group by a single aspect i.e. only by Operation Option OR by ChannelName OR Message/Payload-Prefix
    // (v1.Message, v2.Message)
    // * different groups selectable via ui
    // * Options are exclusive -> validation/undefined behavior
    // * configuration via properties no code/no beans

    // UseCases
    // action -> consumer or producer
    // binding -> type (kafka, sqs)
    // channelName (topicName) -> adminEndpunkt , hidden
    // schemaName -> Payload.v1 vs Payload.v2
    // (server, contentType, header)
}
