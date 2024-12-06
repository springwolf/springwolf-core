// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.EmptyMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.EmptyOperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationIdHelper;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationMerger;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanBuilder;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.BindingServiceProperties;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class CloudStreamFunctionOperationsScanner implements OperationsScanner {

    private final AsyncApiDocketService asyncApiDocketService;
    private final BeanMethodsScanner beanMethodsScanner;
    private final ComponentClassScanner componentClassScanner;
    private final ComponentsService componentsService;
    private final PayloadService payloadService;
    private final BindingServiceProperties cloudStreamBindingsProperties;
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder;

    @Override
    public Map<String, Operation> scan() {
        Set<AnnotatedElement> elements = new HashSet<>();
        elements.addAll(componentClassScanner.scan());
        elements.addAll(beanMethodsScanner.getBeanMethods());

        List<Operation> operations = elements.stream()
                .map(functionalChannelBeanBuilder::build)
                .flatMap(Set::stream)
                .filter(this::isChannelBean)
                .map(this::buildOperation)
                .toList();

        return OperationMerger.mergeOperations(operations);
    }

    private boolean isChannelBean(FunctionalChannelBeanData beanData) {
        return cloudStreamBindingsProperties.getBindings().containsKey(beanData.cloudStreamBinding());
    }

    private Operation buildOperation(FunctionalChannelBeanData beanData) {
        String channelName = cloudStreamBindingsProperties
                .getBindings()
                .get(beanData.cloudStreamBinding())
                .getDestination();
        String channelId = ReferenceUtil.toValidId(channelName);
        String operationId = OperationIdHelper.buildOperationId(
                channelId, beanData.beanType().toOperationAction(), beanData.elementName());

        Type payloadType = beanData.payloadType();
        PayloadSchemaObject payloadSchema = payloadService.buildSchema(payloadType);
        MessagePayload payload = MessagePayload.of(
                MultiFormatSchema.builder().schema(payloadSchema.payload()).build());

        String headerModelName = componentsService.registerSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED);
        MessageHeaders messageHeaders = MessageHeaders.of(MessageReference.toSchema(headerModelName));

        MessageObject message = MessageObject.builder()
                .name(payloadSchema.name())
                .title(payloadSchema.title())
                .payload(payload)
                .headers(messageHeaders)
                .bindings(buildMessageBinding())
                .build();

        return Operation.builder()
                .operationId(operationId)
                .action(beanData.beanType().toOperationAction())
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(channelId))
                .messages(List.of(MessageReference.toChannelMessage(channelId, message)))
                .bindings(buildOperationBinding())
                .build();
    }

    private Map<String, MessageBinding> buildMessageBinding() {
        // FIXME: handle messageBindings from annotations as for the channel
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyMessageBinding());
    }

    private Map<String, OperationBinding> buildOperationBinding() {
        // FIXME: handle operationBindings from annotations as for the channel
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyOperationBinding());
    }

    private String getProtocolName() {
        AsyncApiDocket docket = asyncApiDocketService.getAsyncApiDocket();
        if (docket.getServers().size() > 1) {
            log.warn(
                    "More than one server has been defined - the channels protocol will be determined by the first one");
        }

        return docket.getServers().entrySet().stream()
                .findFirst()
                .map(Map.Entry::getValue)
                .map(Server::getProtocol)
                .orElseThrow(() ->
                        new IllegalStateException("There must be at least one server define in the AsyncApiDocker"));
    }
}
