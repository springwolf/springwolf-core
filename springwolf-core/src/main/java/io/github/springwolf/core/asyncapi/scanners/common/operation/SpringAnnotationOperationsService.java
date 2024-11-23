// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.operation;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessagesService;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class SpringAnnotationOperationsService<ClassAnnotation extends Annotation> {

    private final BindingFactory<ClassAnnotation> bindingFactory;
    private final SpringAnnotationMessagesService<ClassAnnotation> springAnnotationMessagesService;

    public Operation buildOperation(ClassAnnotation classAnnotation, Set<Method> methods) {
        var messages = springAnnotationMessagesService.buildMessages(
                classAnnotation, methods, SpringAnnotationMessagesService.MessageType.OPERATION);
        return buildOperation(classAnnotation, messages);
    }

    private Operation buildOperation(ClassAnnotation classAnnotation, Map<String, MessageReference> messages) {
        Map<String, OperationBinding> operationBinding = bindingFactory.buildOperationBinding(classAnnotation);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        String channelName = bindingFactory.getChannelName(classAnnotation);
        String channelId = ReferenceUtil.toValidId(channelName);

        return Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channelId))
                .messages(messages.values().stream().toList())
                .bindings(opBinding)
                .build();
    }
}
