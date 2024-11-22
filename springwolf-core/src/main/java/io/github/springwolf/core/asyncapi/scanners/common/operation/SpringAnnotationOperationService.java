// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.operation;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationIdHelper;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SpringAnnotationOperationService<MethodAnnotation extends Annotation> {

    private final BindingFactory<MethodAnnotation> bindingFactory;
    private final SpringAnnotationMessageService<MethodAnnotation> springAnnotationMessageService;

    public Operation buildOperation(
            MethodAndAnnotation<MethodAnnotation> annotation,
            PayloadSchemaObject payloadType,
            SchemaObject headerSchema) {
        String channelId = bindingFactory.getChannelId(annotation.annotation());
        String operationId = OperationIdHelper.buildOperationId(
                channelId, OperationAction.RECEIVE, annotation.method().getName());

        MessageObject message =
                springAnnotationMessageService.buildMessage(annotation.annotation(), payloadType, headerSchema);
        Map<String, OperationBinding> operationBinding = bindingFactory.buildOperationBinding(annotation.annotation());
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;

        return Operation.builder()
                .operationId(operationId)
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channelId))
                .messages(List.of(MessageReference.toChannelMessage(channelId, message)))
                .bindings(opBinding)
                .build();
    }
}
