// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.operation;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AsyncAnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.channel.inferrer.ChannelNameResolver;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.TextUtils;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationIdHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class AsyncAnnotationOperationService<Annotation extends java.lang.annotation.Annotation> {

    private final AsyncAnnotationProvider<Annotation> asyncAnnotationProvider;
    private final List<OperationBindingProcessor> operationBindingProcessors;
    private final AsyncAnnotationMessageService asyncAnnotationMessageService;
    private final StringValueResolver stringValueResolver;
    private final ChannelNameResolver channelNameResolver;

    public Operation buildOperation(AsyncOperation asyncOperation, Set<Method> methods) {
        Method method = methods.stream().findFirst().orElseThrow();
        String channelName = channelNameResolver.resolve(asyncOperation, method);
        String channelId = ReferenceUtil.toValidId(channelName);

        List<MessageReference> messages = methods.stream()
                .map(m -> asyncAnnotationMessageService.buildMessage(asyncOperation, m))
                .map(m -> MessageReference.toChannelMessage(channelId, m))
                .collect(Collectors.toList());

        return buildOperation(asyncOperation, method, channelId, messages);
    }

    private Operation buildOperation(
            AsyncOperation asyncOperation, Method method, String channelId, List<MessageReference> messages) {
        String operationId = OperationIdHelper.buildOperationId(
                channelId, this.asyncAnnotationProvider.getOperationType(), method.getName());

        String description = this.stringValueResolver.resolveStringValue(asyncOperation.description());
        if (StringUtils.isBlank(description)) {
            description = "Auto-generated description";
        } else {
            description = TextUtils.trimIndent(description);
        }

        String operationTitle =
                StringUtils.joinWith("_", channelId, this.asyncAnnotationProvider.getOperationType().type);

        Map<String, OperationBinding> operationBinding =
                AsyncAnnotationUtil.processOperationBindingFromAnnotation(method, operationBindingProcessors);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;

        return Operation.builder()
                .operationId(operationId)
                .channel(ChannelReference.fromChannel(channelId))
                .action(this.asyncAnnotationProvider.getOperationType())
                .description(description)
                .title(operationTitle)
                .messages(messages)
                .bindings(opBinding)
                .build();
    }
}
