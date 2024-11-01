// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.grouping;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.Components;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        private final Set<String> markedComponentSchemaIds = new HashSet<>();

        public boolean isChannelMarked(String channelId) {
            return keepEverything || markedChannelIds.contains(channelId);
        }

        public boolean isOperationMarked(String operationId) {
            return keepEverything || markedOperationIds.contains(operationId);
        }

        public boolean isComponentMessageMarked(String messageId) {
            return keepEverything || markedComponentMessageIds.contains(messageId);
        }

        public boolean isComponentSchemaMarked(String schemaId) {
            return keepEverything || markedComponentSchemaIds.contains(schemaId);
        }
    }

    public AsyncAPI groupAPI(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup) {
        boolean keepEverything = asyncApiGroup.getOperationActionsToKeep().isEmpty()
                && asyncApiGroup.getChannelNamesToKeep().isEmpty()
                && asyncApiGroup.getMessageNamesToKeep().isEmpty();
        MarkingContext markingContext = new MarkingContext(keepEverything);

        markOperations(fullAsyncApi, asyncApiGroup, markingContext);
        markChannels(fullAsyncApi, asyncApiGroup, markingContext);
        markMessages(fullAsyncApi, asyncApiGroup, markingContext);
        markSchemasInMessageIds(fullAsyncApi, markingContext, markingContext.markedComponentMessageIds);

        return AsyncAPI.builder()
                .info(fullAsyncApi.getInfo())
                .id(fullAsyncApi.getId())
                .defaultContentType(fullAsyncApi.getDefaultContentType())
                .servers(fullAsyncApi.getServers())
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
                .filter(asyncApiGroup::isMatch)
                .forEach(entry -> {
                    markingContext.markedChannelIds.add(entry.getChannelId());

                    markOperationsInChannel(fullAsyncApi, markingContext, entry);

                    markingContext.markedComponentMessageIds.addAll(
                            entry.getMessages().keySet());
                });
    }

    private void markOperationsInChannel(AsyncAPI fullAsyncApi, MarkingContext markingContext, ChannelObject channel) {
        fullAsyncApi.getOperations().entrySet().stream()
                .filter(operationEntry -> matchesOperationInChannel(channel, operationEntry))
                .forEach(operationEntry -> markingContext.markedOperationIds.add(operationEntry.getKey()));
    }

    private boolean matchesOperationInChannel(ChannelObject channel, Map.Entry<String, Operation> operationEntry) {
        return operationEntry
                .getValue()
                .getChannel()
                .getRef()
                .equals(ChannelReference.fromChannel(channel.getChannelId()).getRef());
    }

    private void markOperations(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup, MarkingContext markingContext) {
        if (fullAsyncApi.getOperations() == null
                || asyncApiGroup.getOperationActionsToKeep().isEmpty()) {
            return;
        }

        fullAsyncApi.getOperations().entrySet().stream()
                .filter(entry -> asyncApiGroup.isMatch(entry.getValue()))
                .forEach(entry -> {
                    markingContext.markedOperationIds.add(entry.getKey());

                    markChannelsForOperation(fullAsyncApi, markingContext, entry.getValue());

                    Set<String> messageIds = entry.getValue().getMessages().stream()
                            .map(MessageReference::getRef)
                            .map(ReferenceUtil::getLastSegment)
                            .collect(Collectors.toSet());
                    markingContext.markedComponentMessageIds.addAll(messageIds);
                });
    }

    private static void markChannelsForOperation(
            AsyncAPI fullAsyncApi, MarkingContext markingContext, Operation operation) {
        String operationChannelId =
                ReferenceUtil.getLastSegment(operation.getChannel().getRef());
        fullAsyncApi.getChannels().values().stream()
                .map(ChannelObject::getChannelId)
                .filter(channelId -> channelId.equals(operationChannelId))
                .forEach(markingContext.markedChannelIds::add);
    }

    private void markMessages(AsyncAPI fullAsyncApi, AsyncApiGroup asyncApiGroup, MarkingContext markingContext) {
        if (fullAsyncApi.getComponents() == null
                || fullAsyncApi.getComponents().getMessages() == null
                || asyncApiGroup.getMessageNamesToKeep().isEmpty()) {
            return;
        }

        fullAsyncApi.getComponents().getMessages().values().stream()
                .map((el) -> (MessageObject) el)
                .filter(asyncApiGroup::isMatch)
                .forEach(entry -> {
                    markingContext.markedComponentMessageIds.add(entry.getMessageId());

                    String messageReference = MessageReference.toComponentMessage(entry.getMessageId())
                            .getRef();
                    fullAsyncApi.getChannels().values().stream()
                            .filter(channelEntry -> matchesMessageForChannel(channelEntry, messageReference))
                            .forEach(channelEntry -> markingContext.markedChannelIds.add(channelEntry.getChannelId()));

                    fullAsyncApi.getOperations().entrySet().stream()
                            .filter(operationEntry -> matchesMessageForOperation(entry, operationEntry))
                            .forEach(operationEntry -> markingContext.markedOperationIds.add(operationEntry.getKey()));
                });
    }

    private boolean matchesMessageForChannel(ChannelObject channel, String messageReference) {
        return channel.getMessages().values().stream()
                .map((el) -> (MessageReference) el)
                .anyMatch(message -> message.getRef().equals(messageReference));
    }

    private boolean matchesMessageForOperation(MessageObject message, Map.Entry<String, Operation> operationEntry) {
        return operationEntry.getValue().getMessages().stream()
                .anyMatch(operationMessage -> operationMessage.getRef().endsWith(message.getMessageId()));
    }

    private void markSchemasInMessageIds(AsyncAPI fullAsyncApi, MarkingContext markingContext, Set<String> messageIds) {
        if (fullAsyncApi.getComponents() == null || fullAsyncApi.getComponents().getMessages() == null) {
            return;
        }

        Set<String> schemaIds = new HashSet<>();

        List<MessageObject> messages = fullAsyncApi.getComponents().getMessages().values().stream()
                .map((el) -> (MessageObject) el)
                .filter(messageEntry -> messageIds.contains(messageEntry.getMessageId()))
                .toList();

        messages.stream()
                .map(MessageObject::getHeaders)
                .filter(Objects::nonNull)
                .map(MessageHeaders::getReference)
                .filter(Objects::nonNull)
                .map(MessageReference::getRef)
                .map(ReferenceUtil::getLastSegment)
                .forEach(schemaIds::add);
        messages.stream()
                .map(MessageObject::getPayload)
                .map(MessagePayload::getMultiFormatSchema)
                .filter(Objects::nonNull)
                .map(MultiFormatSchema::getSchema)
                .map(el -> (MessageReference) el)
                .map(MessageReference::getRef)
                .map(ReferenceUtil::getLastSegment)
                .forEach(schemaIds::add);

        markSchemas(fullAsyncApi, markingContext, schemaIds);
    }

    private void markSchemas(AsyncAPI fullAsyncApi, MarkingContext markingContext, Set<String> schemaIds) {
        if (fullAsyncApi.getComponents() == null || fullAsyncApi.getComponents().getSchemas() == null) {
            return;
        }

        schemaIds.stream()
                .map(schemaId -> Pair.of(
                        schemaId, fullAsyncApi.getComponents().getSchemas().getOrDefault(schemaId, null)))
                .filter(entry -> entry.getValue() != null)
                .forEach(schemaObject -> {
                    markingContext.markedComponentSchemaIds.add(schemaObject.getKey());

                    if (schemaObject.getValue().getProperties() != null) {
                        Set<String> nestedSchemas = schemaObject.getValue().getProperties().values().stream()
                                .filter(el -> el instanceof MessageReference)
                                .map(el -> ((MessageReference) el).getRef())
                                .map(ReferenceUtil::getLastSegment)
                                .filter(schemaId -> !markingContext.markedComponentSchemaIds.contains(schemaId))
                                .collect(Collectors.toSet());
                        if (!nestedSchemas.isEmpty()) {
                            markSchemas(fullAsyncApi, markingContext, nestedSchemas);
                        }
                    }
                });
    }

    private Map<String, ChannelObject> filterChannels(AsyncAPI fullAsyncApi, MarkingContext markingContext) {
        if (fullAsyncApi.getChannels() == null) {
            return null;
        }

        return fullAsyncApi.getChannels().values().stream()
                .filter(channel -> markingContext.isChannelMarked(channel.getChannelId()))
                .map(channel -> {
                    Map<String, Message> filteredMessagesInChannel = channel.getMessages().entrySet().stream()
                            .filter(messageEntry -> markingContext.isComponentMessageMarked(messageEntry.getKey()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                    return channel.toBuilder()
                            .messages(filteredMessagesInChannel)
                            .build();
                })
                .collect(Collectors.toMap(ChannelObject::getChannelId, el -> el));
    }

    private Map<String, Operation> filterOperations(AsyncAPI fullAsyncApi, MarkingContext markingContext) {
        if (fullAsyncApi.getOperations() == null) {
            return null;
        }

        return fullAsyncApi.getOperations().entrySet().stream()
                .filter(entry -> markingContext.isOperationMarked(entry.getKey()))
                .map(entry -> {
                    List<MessageReference> filteredMessagesInOperation = entry.getValue().getMessages().stream()
                            .filter(messageEntry -> markingContext.isComponentMessageMarked(
                                    ReferenceUtil.getLastSegment(messageEntry.getRef())))
                            .collect(Collectors.toList());

                    Operation updatedOperation = entry.getValue().toBuilder()
                            .messages(filteredMessagesInOperation)
                            .build();
                    return Map.entry(entry.getKey(), updatedOperation);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Components filterComponents(AsyncAPI fullAsyncApi, MarkingContext markingContext) {
        if (fullAsyncApi.getComponents() == null) {
            return null;
        }

        return Components.builder()
                .messages(filterComponentMessages(fullAsyncApi.getComponents(), markingContext))
                .schemas(filterComponentSchemas(fullAsyncApi.getComponents(), markingContext))
                .build();
    }

    private Map<String, Message> filterComponentMessages(Components components, MarkingContext markingContext) {
        if (components.getMessages() == null) {
            return null;
        }

        return components.getMessages().entrySet().stream()
                .filter(entry -> markingContext.isComponentMessageMarked(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<String, SchemaObject> filterComponentSchemas(Components components, MarkingContext markingContext) {
        if (components.getSchemas() == null) {
            return null;
        }

        return components.getSchemas().entrySet().stream()
                .filter(entry -> markingContext.isComponentSchemaMarked(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
