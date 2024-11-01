// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.grouping;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class AsyncApiGroupService {
    private final SpringwolfConfigProperties springwolfConfigProperties;
    private final GroupingService groupingService;

    public Map<String, AsyncAPI> group(AsyncAPI asyncAPI) {
        return getAsyncApiGroups()
                .map(group -> Map.entry(group.getGroupName(), groupingService.groupAPI(asyncAPI, group)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Stream<AsyncApiGroup> getAsyncApiGroups() {
        return springwolfConfigProperties.getDocket().getGroupConfigs().stream()
                .map(AsyncApiGroupService::toGroupConfigAndValidate);
    }

    private static AsyncApiGroup toGroupConfigAndValidate(SpringwolfConfigProperties.ConfigDocket.Group group) {
        String groupName = group.getGroup();
        List<Pattern> channelNameToMatch =
                group.getChannelNameToMatch().stream().map(Pattern::compile).toList();
        List<Pattern> messageNameToMatch =
                group.getMessageNameToMatch().stream().map(Pattern::compile).toList();

        if (!StringUtils.hasText(groupName)) {
            throw new IllegalArgumentException("AsyncApiGroup must have a name set in configuration");
        }

        int allItemCount = group.getActionToMatch().size()
                + group.getChannelNameToMatch().size()
                + group.getMessageNameToMatch().size();
        if (allItemCount != 0
                && group.getActionToMatch().size() != allItemCount
                && channelNameToMatch.size() != allItemCount
                && messageNameToMatch.size() != allItemCount) {
            throw new IllegalArgumentException(
                    "AsyncApiGroup %s must specify at most one filter criteria".formatted(groupName));
        }

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .groupName(groupName)
                .operationActionsToKeep(group.getActionToMatch())
                .channelNamesToKeep(channelNameToMatch)
                .messageNamesToKeep(messageNameToMatch)
                .build();
        log.debug("Loaded AsyncApiGroup from configuration: {}", asyncApiGroup);
        return asyncApiGroup;
    }
}
