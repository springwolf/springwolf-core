// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.EmptyMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.EmptyOperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationMerger;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanBuilder;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.BindingServiceProperties;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class CloudStreamFunctionOperationsScanner implements OperationsScanner {

    private final AsyncApiDocketService asyncApiDocketService;
    private final BeanMethodsScanner beanMethodsScanner;
    private final ComponentsService componentsService;
    private final BindingServiceProperties cloudStreamBindingsProperties;
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder;

    @Override
    public Map<String, Operation> scan() {
        Set<Method> beanMethods = beanMethodsScanner.getBeanMethods();
        return OperationMerger.mergeOperations(beanMethods.stream()
                .map(functionalChannelBeanBuilder::fromMethodBean)
                .flatMap(Set::stream)
                .filter(this::isChannelBean)
                .map(this::toOperationEntry)
                .toList());
    }

    private boolean isChannelBean(FunctionalChannelBeanData beanData) {
        return cloudStreamBindingsProperties.getBindings().containsKey(beanData.cloudStreamBinding());
    }

    private Map.Entry<String, Operation> toOperationEntry(FunctionalChannelBeanData beanData) {
        String channelName = cloudStreamBindingsProperties
                .getBindings()
                .get(beanData.cloudStreamBinding())
                .getDestination();

        String operationId = buildOperationId(beanData, channelName);
        Operation operation = buildOperation(beanData, channelName);

        return Map.entry(operationId, operation);
    }

    private Operation buildOperation(FunctionalChannelBeanData beanData, String channelName) {
        Class<?> payloadType = beanData.payloadType();
        String modelName = componentsService.registerSchema(payloadType);
        String headerModelName = componentsService.registerSchema(AsyncHeaders.NOT_DOCUMENTED);

        MessageObject message = MessageObject.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(MessagePayload.of(MessageReference.toSchema(modelName)))
                .headers(MessageHeaders.of(MessageReference.toSchema(headerModelName)))
                .bindings(buildMessageBinding())
                .build();

        var builder = Operation.builder()
                .description("Auto-generated description")
                .channel(ChannelReference.fromChannel(channelName))
                .messages(List.of(MessageReference.toChannelMessage(channelName, message)))
                .bindings(buildOperationBinding());
        if (beanData.beanType() == FunctionalChannelBeanData.BeanType.CONSUMER) {
            builder.action(OperationAction.RECEIVE);
        } else {
            builder.action(OperationAction.SEND);
        }

        return builder.build();
    }

    private Map<String, MessageBinding> buildMessageBinding() {
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyMessageBinding());
    }

    private Map<String, OperationBinding> buildOperationBinding() {
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

    private String buildOperationId(FunctionalChannelBeanData beanData, String channelName) {
        String operationName =
                beanData.beanType() == FunctionalChannelBeanData.BeanType.CONSUMER ? "publish" : "subscribe";

        return String.format("%s_%s_%s", channelName, operationName, beanData.beanName());
    }
}
