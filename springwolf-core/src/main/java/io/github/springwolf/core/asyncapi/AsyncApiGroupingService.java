// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AsyncApiGroupingService {

    //    private final String group = "admin";

    // if empty, keep all


    // TODO: Context class/object
    //    private final Map<String, Boolean> markedChannelIds;
    private final Set<String> markedOperationIds = new HashSet<>();
    private final Set<String> markedChannelIds = new HashSet<>();
    //    private final Map<String, Boolean> markedComponentIds;

    public AsyncAPI groupAPI(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup) {
        Boolean markEverything = asyncApiGroup.getOperationActionsToKeep().isEmpty() && asyncApiGroup.getChannelNamesToKeep().isEmpty();

        markOperations(fullAsyncApi, asyncApiGroup, markEverything);
        markChannels(fullAsyncApi, asyncApiGroup);

        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(fullAsyncApi.getInfo())
                .id(fullAsyncApi.getId())
                .defaultContentType(fullAsyncApi.getDefaultContentType())
                //                        .servers(docket.getServers())
                .channels(filterChannels(fullAsyncApi))
                .operations(filterOperations(fullAsyncApi))
                //                        .components(components)
                .build();

        return asyncAPI;
    }

    private Map<String, ChannelObject> filterChannels(AsyncAPI fullAsyncApi) {
        if (fullAsyncApi.getChannels() == null) {
            return null;
        }

        return fullAsyncApi.getChannels().entrySet().stream()
                .filter(entry -> markedChannelIds.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void markChannels(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup) {
        if (fullAsyncApi.getChannels() == null) {
            return;
        }

        fullAsyncApi.getChannels().entrySet().stream()
                .filter(entry -> asyncApiGroup.getChannelNamesToKeep().contains(entry.getKey()))
                .forEach(entry -> {
                    markedChannelIds.add(entry.getKey());

                    fullAsyncApi.getOperations().entrySet().stream()
                            .filter(operationEntry -> {
                                String channelId = entry.getValue().getChannelId();
                                String channelReference =
                                        ChannelReference.fromChannel(channelId).getRef();
                                return operationEntry
                                        .getValue()
                                        .getChannel()
                                        .getRef()
                                        .equals(channelReference);
                            })
                            .forEach(operationEntry -> markedOperationIds.add(operationEntry.getKey()));
                });
    }

    private void markOperations(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup, Boolean markEverything) {
        if (fullAsyncApi.getOperations() == null) {
            return;
        }

        fullAsyncApi.getOperations().entrySet().stream()
                .filter(entry ->
                        markEverything || asyncApiGroup.getOperationActionsToKeep().contains(entry.getValue().getAction()))
                .forEach(entry -> {
                    markedOperationIds.add(entry.getKey());

                    String refFromOperationToChannel =
                            entry.getValue().getChannel().getRef(); // "#/channels/{channelId}"
                    fullAsyncApi.getChannels().entrySet().stream()
                            .filter(channelEntry -> {
                                val channelReference = ChannelReference.fromChannel(channelEntry.getValue())
                                        .getRef();
                                return channelReference.equals(refFromOperationToChannel);
                            })
                            .forEach(channelEntry ->
                                    markedChannelIds.add(channelEntry.getValue().getChannelId()));
                });
    }

    private Map<String, Operation> filterOperations(AsyncAPI fullAsyncApi) {
        if (fullAsyncApi.getOperations() == null) {
            return null;
        }

        return fullAsyncApi.getOperations().entrySet().stream()
                .filter(entry -> markedOperationIds.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //    private Map<String, ChannelObject> filterChannels(AsyncAPI fullAsyncApi) {
    //        Map<String, ChannelObject> allChannels = fullAsyncApi.getChannels();
    //        if (allChannels == null) {
    //            return null;
    //        }
    //
    //        Map<String, ChannelObject> asdf = filterChannelsByOperation(allChannels, fullAsyncApi);
    //        if (asdf == null) {
    //            return null;
    //        }
    //
    //        return asdf.entrySet().stream()
    //                .filter((channel) -> channelNamesToKeep.isEmpty() ||
    // channelNamesToKeep.contains(channel.getKey()))
    //                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    //    }
    //
    //    private Map<String, ChannelObject> filterChannelsByOperation(
    //            Map<String, ChannelObject> channels, AsyncAPI fullAsyncApi) {
    //        Map<String, Operation> operations = filterOperations(fullAsyncApi);
    //        if (fullAsyncApi.getChannels() == null || operations == null) {
    //            return null;
    //        }
    //
    //        Set<String> channelReferences = operations.values().stream()
    //                .map((operation) -> operation.getChannel().getRef())
    //                .collect(Collectors.toSet());
    //
    //        return channels.entrySet().stream()
    //                .filter((channel) -> {
    //                    String channelId = channel.getValue().getChannelId();
    //                    String channelReference =
    //                            ChannelReference.fromChannel(channelId).getRef();
    //                    return channelReferences.contains(channelReference);
    //                })
    //                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    //    }
    //
    //    public Map<String, Operation> filterOperations(AsyncAPI fullAsyncApi) {
    //        if (fullAsyncApi.getOperations() == null) {
    //            return null;
    //        }
    //
    //        // filterOperationsByChannel(fullAsyncApi);
    //
    //        return fullAsyncApi.getOperations().entrySet().stream()
    //                .filter(entry -> operationActionsToKeep.isEmpty()
    //                        || operationActionsToKeep.contains(entry.getValue().getAction()))
    //                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    //    }
    //     for group -> {
    //        service.filterForGroup(group, asyncAPI);

    // info - same
    // id - same
    // contentType - same

    // 4 kinds
    // servers - filter
    // - map entries - same
    // channels - filter
    // - map entries - filter (contains messages)
    // operations - filter
    // - map entries - same
    // components - filter
    // - schemas - filter
    //   - map entries - same
    // - messages - filter
    //   - map entries - same

    // AsyncAPI
    //        Map<ServerId, Boolean>
    //        Map<ChannelId, Boolean>

    // graph problem
    // - nodes:
    //   - kind: servers, channels, operations, components
    //   - marked: true / false

    // 1. pass -> mark by matcher
    // 2. pass -> mark by transitive per type (channels, operations, components)
    // 3. fixup channels#messages (builder / clone)

    // trade-off marking (state, memory) vs simple filter (duplicate processing)
    // -> assumption: easier to filter (no state management)
}
