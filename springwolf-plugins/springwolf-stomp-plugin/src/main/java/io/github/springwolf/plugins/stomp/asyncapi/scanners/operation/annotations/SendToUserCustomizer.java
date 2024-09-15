// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.asyncapi.scanners.operation.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationReply;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodReturnService;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingSendToUserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.annotation.SendToUser;

import java.lang.reflect.Method;
import java.util.List;

@RequiredArgsConstructor
public class SendToUserCustomizer implements OperationCustomizer {
    private final StompBindingSendToUserFactory bindingFactory;
    private final PayloadMethodReturnService payloadService;

    @Override
    public void customize(Operation operation, Method method) {
        SendToUser annotation = AnnotationUtil.findFirstAnnotation(SendToUser.class, method);
        if (annotation != null) {
            String channelId = ReferenceUtil.toValidId(bindingFactory.getChannelName(annotation));
            String payloadName = payloadService.extractSchema(method).name();

            operation.setReply(OperationReply.builder()
                    .channel(ChannelReference.fromChannel(channelId))
                    .messages(List.of(MessageReference.toChannelMessage(channelId, payloadName)))
                    .build());
        }
    }
}
