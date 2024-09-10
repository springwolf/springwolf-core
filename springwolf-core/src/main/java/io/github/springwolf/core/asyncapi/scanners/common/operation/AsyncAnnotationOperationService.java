// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.operation;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AsyncAnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.TextUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AsyncAnnotationOperationService<MethodAnnotation extends Annotation> {

    private final AsyncAnnotationProvider<MethodAnnotation> asyncAnnotationProvider;
    private final List<OperationBindingProcessor> operationBindingProcessors;
    private final StringValueResolver resolver;
    private final AsyncAnnotationMessageService asyncAnnotationMessageService;

    public Operation buildOperation(AsyncOperation asyncOperation, Method method, String channelId) {
        String description = this.resolver.resolveStringValue(asyncOperation.description());
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
        MessageObject message = asyncAnnotationMessageService.buildMessage(asyncOperation, method);

        return Operation.builder()
                .channel(ChannelReference.fromChannel(channelId))
                .action(this.asyncAnnotationProvider.getOperationType())
                .description(description)
                .title(operationTitle)
                .messages(List.of(MessageReference.toChannelMessage(channelId, message)))
                .bindings(opBinding)
                .build();
    }
}
