// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
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
    private final Info groupInfo = Info.builder().build();

    @Builder.Default
    private final List<OperationAction> operationActionsToKeep = Collections.emptyList();

    @Builder.Default
    private final List<Pattern> channelNamesToKeep = Collections.emptyList();

    @Builder.Default
    private final List<Pattern> messageNamesToKeep = Collections.emptyList();

    public boolean isMatch(MessageObject messageObject) {
        return getMessageNamesToKeep().stream().anyMatch(pattern -> pattern.matcher(messageObject.getMessageId())
                .matches());
    }

    public boolean isMatch(Operation operation) {
        return getOperationActionsToKeep().contains(operation.getAction());
    }

    public boolean isMatch(ChannelObject channelObject) {
        return getChannelNamesToKeep().stream()
                .anyMatch(pattern -> pattern.matcher(channelObject.getAddress()).matches());
    }
}
