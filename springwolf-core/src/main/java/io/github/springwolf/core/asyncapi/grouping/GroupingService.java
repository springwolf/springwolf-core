// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.grouping;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.Components;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GroupingService {

    @RequiredArgsConstructor
    private static class MarkingContext {
        public final boolean keepEverything;

        public final Set<String> markedOperationIds = new HashSet<>();
        public final Set<String> markedChannelIds = new HashSet<>();
        public final Set<String> markedComponentMessageIds = new HashSet<>();
        //    private final Map<String, Boolean> markedComponentSchemaIds;
        //    private final Map<String, Boolean> markedServerIds;
    }

    public AsyncAPI groupAPI(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup) {
        boolean keepEverything = asyncApiGroup.getOperationActionsToKeep().isEmpty()
                && asyncApiGroup.getChannelNamesToKeep().isEmpty()
                && asyncApiGroup.getMessageNamesToKeep().isEmpty();
        MarkingContext markingContext = new MarkingContext(keepEverything);

        markOperations(fullAsyncApi, asyncApiGroup, markingContext);
        markChannels(fullAsyncApi, asyncApiGroup, markingContext);
        markMessages(fullAsyncApi, asyncApiGroup, markingContext);

        return AsyncAPI.builder()
                .info(fullAsyncApi.getInfo())
                .id(fullAsyncApi.getId())
                .defaultContentType(fullAsyncApi.getDefaultContentType())
                //                        .servers(docket.getServers())
                .channels(filterChannels(fullAsyncApi, markingContext))
                .operations(filterOperations(fullAsyncApi, markingContext))
                .components(filterComponents(fullAsyncApi, markingContext))
                .build();
    }

    private void markChannels(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup, MarkingContext markingContext) {
        if (fullAsyncApi.getChannels() == null
                || asyncApiGroup.getChannelNamesToKeep().isEmpty()) {
            return;
        }

        fullAsyncApi.getChannels().values().stream()
                .filter(channelObject ->
                        // TODO: differentiate between messageId (wrong) and messageName (should be)
                        asyncApiGroup.getChannelNamesToKeep().stream()
                                .anyMatch(pattern -> pattern.matcher(channelObject.getAddress())
                                        .matches()))
                .forEach(entry -> {
                    markingContext.markedChannelIds.add(entry.getChannelId());

                    fullAsyncApi.getOperations().entrySet().stream()
                            .filter(operationEntry -> {
                                String channelId = entry.getChannelId();
                                String channelReference =
                                        ChannelReference.fromChannel(channelId).getRef();
                                return operationEntry
                                        .getValue()
                                        .getChannel()
                                        .getRef()
                                        .equals(channelReference);
                            })
                            .forEach(operationEntry -> markingContext.markedOperationIds.add(operationEntry.getKey()));

                    markingContext.markedComponentMessageIds.addAll(
                            entry.getMessages().keySet());
                });
    }

    private void markOperations(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup, MarkingContext markingContext) {
        if (fullAsyncApi.getOperations() == null
                || asyncApiGroup.getOperationActionsToKeep().isEmpty()) {
            return;
        }

        fullAsyncApi.getOperations().entrySet().stream()
                .filter(entry -> asyncApiGroup
                        .getOperationActionsToKeep()
                        .contains(entry.getValue().getAction()))
                .forEach(entry -> {
                    markingContext.markedOperationIds.add(entry.getKey());

                    String operationChannelId = ReferenceUtil.getLastSegment(
                            entry.getValue().getChannel().getRef());
                    fullAsyncApi.getChannels().values().stream()
                            .map(ChannelObject::getChannelId)
                            .filter(channelId -> channelId.equals(operationChannelId))
                            .forEach(markingContext.markedChannelIds::add);

                    entry.getValue().getMessages().stream()
                            .map(MessageReference::getRef)
                            .map(ReferenceUtil::getLastSegment)
                            .forEach(markingContext.markedComponentMessageIds::add);
                });
    }

    private void markMessages(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup, MarkingContext markingContext) {
        if (fullAsyncApi.getComponents() == null
                || fullAsyncApi.getComponents().getMessages() == null
                || asyncApiGroup.getMessageNamesToKeep().isEmpty()) {
            return;
        }

        fullAsyncApi.getComponents().getMessages().values().stream()
                .map((el) -> (MessageObject) el)
                .filter(messageObject ->
                        // TODO: differentiate between messageId (wrong) and messageName (should be)

                        asyncApiGroup.getMessageNamesToKeep().stream()
                                .anyMatch(pattern -> pattern.matcher(messageObject.getMessageId())
                                        .matches()))
                .forEach(entry -> {
                    markingContext.markedComponentMessageIds.add(entry.getMessageId());

                    String messageReference = MessageReference.toComponentMessage(entry.getMessageId())
                            .getRef();
                    fullAsyncApi.getChannels().values().stream()
                            .filter(channelEntry -> channelEntry.getMessages().values().stream()
                                    .map((el) -> (MessageReference) el)
                                    .anyMatch(message -> message.getRef().equals(messageReference)))
                            .forEach(channelEntry -> markingContext.markedChannelIds.add(channelEntry.getChannelId()));

                    fullAsyncApi.getOperations().entrySet().stream()
                            .filter(operationEntry -> operationEntry.getValue().getMessages().stream()
                                    .anyMatch(message -> message.getRef().equals(messageReference)))
                            .forEach(operationEntry -> markingContext.markedOperationIds.add(operationEntry.getKey()));
                });
    }

    private Map<String, ChannelObject> filterChannels(AsyncAPI fullAsyncApi, MarkingContext markingContext) {
        if (fullAsyncApi.getChannels() == null) {
            return null;
        }

        return fullAsyncApi.getChannels().values().stream()
                .filter(channel -> markingContext.keepEverything
                        || markingContext.markedChannelIds.contains(channel.getChannelId()))
                .map(channel -> channel.toBuilder()
                        .messages(channel.getMessages().entrySet().stream()
                                .filter(messageEntry -> markingContext.keepEverything
                                        || markingContext.markedComponentMessageIds.contains(messageEntry.getKey()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                        .build())
                .collect(Collectors.toMap(ChannelObject::getChannelId, el -> el));
    }

    private Map<String, Operation> filterOperations(AsyncAPI fullAsyncApi, MarkingContext markingContext) {
        if (fullAsyncApi.getOperations() == null) {
            return null;
        }

        return fullAsyncApi.getOperations().entrySet().stream()
                .filter(entry ->
                        markingContext.keepEverything || markingContext.markedOperationIds.contains(entry.getKey()))
                .map(entry -> Map.entry(
                        entry.getKey(),
                        entry.getValue().toBuilder()
                                .messages(entry.getValue().getMessages().stream()
                                        .filter(messageEntry -> markingContext.keepEverything
                                                || markingContext.markedComponentMessageIds.contains(
                                                        ReferenceUtil.getLastSegment(messageEntry.getRef())))
                                        .collect(Collectors.toList()))
                                .build()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Components filterComponents(AsyncAPI fullAsyncApi, MarkingContext markingContext) {
        if (fullAsyncApi.getComponents() == null) {
            return null;
        }

        // TODO: handle schemas

        return Components.builder()
                .messages(filterComponentMessages(fullAsyncApi.getComponents(), markingContext))
                .schemas(fullAsyncApi.getComponents().getSchemas())
                .build();
    }

    private Map<String, Message> filterComponentMessages(Components components, MarkingContext markingContext) {
        if (components.getMessages() == null) {
            return null;
        }

        return components.getMessages().entrySet().stream()
                .filter(entry -> markingContext.keepEverything
                        || markingContext.markedComponentMessageIds.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
