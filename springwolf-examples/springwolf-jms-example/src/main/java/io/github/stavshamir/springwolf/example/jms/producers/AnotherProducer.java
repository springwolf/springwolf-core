// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.jms.producers;

import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.addons.generic_binding.annotation.AsyncGenericOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.JmsAsyncOperationBinding;
import io.github.stavshamir.springwolf.example.jms.dtos.AnotherPayloadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnotherProducer {
    private final JmsTemplate template;

    public static final String QUEUE = "another-queue";

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = QUEUE,
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    @JmsAsyncOperationBinding
    @AsyncGenericOperationBinding(
            type = "jms",
            fields = {"internal-field=customValue", "nested.key=nestedValue"})
    public void sendMessage(AnotherPayloadDto msg) {
        template.convertAndSend(QUEUE, msg);
    }
}
