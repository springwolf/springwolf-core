// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.server.Server;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Slf4j
@Deprecated(forRemoval = true)
public abstract class AbstractOperationDataScanner implements ChannelsScanner {

    protected abstract SchemasService getSchemaService();

    protected abstract AsyncApiDocketService getAsyncApiDocketService();

    /**
     * provides a list of all {@link OperationData} items detected by the concrete
     * subclass.
     *
     * @return
     */
    protected abstract List<OperationData> getOperationData();

    protected abstract OperationData.OperationType getOperationType();

    @Override
    public Map<String, ChannelItem> scan() {
        Map<String, List<OperationData>> operationDataGroupedByChannelName = this.getOperationData().stream()
                .filter(this::allFieldsAreNonNull)
                .collect(groupingBy(OperationData::getChannelName));

        return operationDataGroupedByChannelName.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> buildChannel(entry.getValue())));
    }

    private boolean allFieldsAreNonNull(OperationData operationData) {
        boolean allNonNull = operationData.getChannelName() != null
                && operationData.getPayloadType() != null
                && operationData.getOperationBinding() != null;

        if (!allNonNull) {
            log.warn("Some data fields are null - this producer will not be documented: {}", operationData);
        }

        return allNonNull;
    }

    /**
     * Creates an asyncapi {@link ChannelItem} using the given list of {@link OperationData}. Expects, that all {@link OperationData}
     * items belong to the same channel. Most properties of the resulting {@link ChannelItem} are extracted from the
     * first {@link OperationData} item in the list, assuming that all {@link OperationData} contains the same channel
     * informations.
     *
     * @param operationDataList List of all {@link OperationData} items for a single channel.
     * @return the resulting {@link ChannelItem}
     */
    private ChannelItem buildChannel(List<OperationData> operationDataList) {
        // All bindings in the group are assumed to be the same
        // AsyncApi does not support multiple bindings on a single channel
        Map<String, ? extends ChannelBinding> channelBinding =
                operationDataList.get(0).getChannelBinding();
        Map<String, ? extends OperationBinding> operationBinding =
                operationDataList.get(0).getOperationBinding();
        Map<String, Object> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        Map<String, Object> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        String operationId = operationDataList.get(0).getChannelName() + "_" + this.getOperationType().operationName;
        String description = operationDataList.get(0).getDescription();
        List<String> servers = operationDataList.get(0).getServers();

        if (description.isEmpty()) {
            description = "Auto-generated description";
        }

        Operation operation = Operation.builder()
                .description(description)
                .operationId(operationId)
                .message(getMessageObject(operationDataList))
                .bindings(opBinding)
                .build();

        ChannelItem.ChannelItemBuilder channelBuilder = ChannelItem.builder().bindings(chBinding);
        channelBuilder = switch (getOperationType()) {
            case PUBLISH -> channelBuilder.publish(operation);
            case SUBSCRIBE -> channelBuilder.subscribe(operation);};

        // Only set servers if servers are defined. Avoid setting an emtpy list
        // because this would generate empty server entries for each channel in the resulting
        // async api.
        if (servers != null && !servers.isEmpty()) {
            validateServers(servers, operationId);
            channelBuilder.servers(servers);
        }
        return channelBuilder.build();
    }

    private Object getMessageObject(List<OperationData> operationDataList) {
        Set<Message> messages =
                operationDataList.stream().map(this::buildMessage).collect(toSet());

        return toMessageObjectOrComposition(messages);
    }

    private Message buildMessage(OperationData operationData) {
        Class<?> payloadType = operationData.getPayloadType();
        String modelName = this.getSchemaService().register(payloadType);
        String headerModelName = this.getSchemaService().register(operationData.getHeaders());

        /*
         * Message information can be obtained via a @AsyncMessage annotation on the method parameter, the Payload
         * itself or via the Swagger @Schema annotation on the Payload.
         */

        var schema = payloadType.getAnnotation(Schema.class);
        String description = schema != null ? schema.description() : null;

        var builder = Message.builder()
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(description)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .bindings(operationData.getMessageBinding());

        // Retrieve the Message information obtained from the @AsyncMessage annotation. These values have higher
        // priority
        //  so if we find them, we need to override the default values.
        processAsyncMessageAnnotation(operationData.getMessage(), builder);

        return builder.build();
    }

    private void processAsyncMessageAnnotation(Message annotationMessage, Message.MessageBuilder builder) {
        if (annotationMessage != null) {
            builder.messageId(annotationMessage.getMessageId());

            var schemaFormat = annotationMessage.getSchemaFormat() != null
                    ? annotationMessage.getSchemaFormat()
                    : Message.DEFAULT_SCHEMA_FORMAT;
            builder.schemaFormat(schemaFormat);

            var annotationMessageDescription = annotationMessage.getDescription();
            if (StringUtils.hasText(annotationMessageDescription)) {
                builder.description(annotationMessageDescription);
            }

            var name = annotationMessage.getName();
            if (StringUtils.hasText(name)) {
                builder.name(name);
            }

            var title = annotationMessage.getTitle();
            if (StringUtils.hasText(title)) {
                builder.title(title);
            }
        }
    }

    /**
     * validates the given list of server names (for a specific operation) with the servers defined in the 'servers' part of
     * the current AsyncApi.
     *
     * @param serversFromOperation the server names defined for the current operation
     * @param operationId          operationId of the current operation - used for exception messages
     * @throws IllegalArgumentException if server from operation is not present in AsyncApi's servers definition.
     */
    void validateServers(List<String> serversFromOperation, String operationId) {
        if (!serversFromOperation.isEmpty()) {
            Map<String, Server> asyncApiServers =
                    getAsyncApiDocketService().getAsyncApiDocket().getServers();
            if (asyncApiServers == null || asyncApiServers.isEmpty()) {
                throw new IllegalArgumentException(String.format(
                        "Operation '%s' defines server refs (%s) but there are no servers defined in this AsyncAPI.",
                        operationId, serversFromOperation));
            }
            for (String server : serversFromOperation) {
                if (!asyncApiServers.containsKey(server)) {
                    throw new IllegalArgumentException(String.format(
                            "Operation '%s' defines unknown server ref '%s'. This AsyncApi defines these server(s): %s",
                            operationId, server, asyncApiServers.keySet()));
                }
            }
        }
    }
}
