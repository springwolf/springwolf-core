package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2._0_0.model.channel.ChannelItem;
import com.asyncapi.v2._0_0.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static java.util.stream.Collectors.*;

@Slf4j
@Order(value = ChannelPriority.MANUAL_DEFINED)
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
                && operationData.getOperationBinding() != null;

        if (!allNonNull) {
            log.warn("Some data fields are null - this producer will not be documented: {}", operationData);
        }

        return allNonNull;
    }

    private ChannelItem buildChannel(List<OperationData> operationDataList) {
        // All bindings in the group are assumed to be the same
        // AsyncApi does not support multiple bindings on a single channel
        Map<String, ? extends ChannelBinding> channelBinding = operationDataList.get(0).getChannelBinding();
        Map<String, ? extends OperationBinding> operationBinding = operationDataList.get(0).getOperationBinding();
        String operationId = operationDataList.get(0).getChannelName() + "_" + this.getOperationType().operationName;
        String description = operationDataList.get(0).getDescription();

        if (description.isEmpty()) {
            description = "Auto-generated description";
        }

        Operation operation = Operation.builder()
                .description(description)
                .operationId(operationId)
                .message(getMessageObject(operationDataList))
                .bindings(operationBinding)
                .build();

        ChannelItem.ChannelItemBuilder channelBuilder = ChannelItem.builder()
                .bindings(channelBinding);
        channelBuilder = switch (getOperationType()) {
            case PUBLISH -> channelBuilder.publish(operation);
            case SUBSCRIBE -> channelBuilder.subscribe(operation);
        };
        return channelBuilder.build();
    }

    private Object getMessageObject(List<OperationData> operationDataList) {
        Set<Message> messages = operationDataList.stream()
                .map(this::buildMessage)
                .collect(toSet());

        return toMessageObjectOrComposition(messages);
    }

    private Message buildMessage(OperationData operationData) {
        Class<?> payloadType = operationData.getPayloadType();
        String modelName = this.getSchemaService().register(payloadType);
        String headerModelName = this.getSchemaService().register(operationData.getHeaders());

        return Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                // FIXME: Add support for Message Description
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .bindings(operationData.getMessageBinding())
                .build();
    }

}
