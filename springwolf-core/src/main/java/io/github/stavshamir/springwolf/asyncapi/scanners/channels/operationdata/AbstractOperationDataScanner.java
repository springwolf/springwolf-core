// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
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
public abstract class AbstractOperationDataScanner implements ChannelsScanner {

    protected abstract SchemasService getSchemaService();

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
                && operationData.getOperationBinding() != null
                && operationData.getServers() != null;

        if (!allNonNull) {
            log.warn("Some data fields are null - this producer will not be documented: {}", operationData);
        }

        return allNonNull;
    }

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
        if (!servers.isEmpty()) {
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
                .title(modelName)
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
}
