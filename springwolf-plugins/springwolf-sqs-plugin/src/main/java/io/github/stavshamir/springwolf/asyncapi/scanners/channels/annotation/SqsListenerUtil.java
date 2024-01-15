// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBindingQueue;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSOperationBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class SqsListenerUtil {

    public static String getChannelName(SqsListener annotation, StringValueResolver resolver) {
        return Stream.concat(Arrays.stream(annotation.value()), Arrays.stream(annotation.queueNames()))
                .findFirst()
                .map(resolver::resolveStringValue)
                .orElseThrow(() -> new IllegalArgumentException("No queue name was found in @SqsListener annotation"));
    }

    public static Map<String, ChannelBinding> buildChannelBinding(
            SqsListener annotation, StringValueResolver resolver) {
        // FIXME: Read the values from the annotation
        var queue = SQSChannelBindingQueue.builder()
                .name("queue-name")
                .fifoQueue(true)
                .build();
        var channelBinding = SQSChannelBinding.builder()
                .queue(queue)
                .build();
        return Map.of("sqs", channelBinding);
    }

    public static Map<String, OperationBinding> buildOperationBinding(
            SqsListener annotation, StringValueResolver resolver) {
        // FIXME: Read the values from the annotation
        var queue = SQSChannelBindingQueue.builder()
                .name("queue-name")
                .fifoQueue(true)
                .build();
        var operationBinding = SQSOperationBinding.builder()
                .queues(List.of(queue))
                .build();
        return Map.of("sqs", operationBinding);
    }

    public static Map<String, MessageBinding> buildMessageBinding() {
        return Map.of("sqs", new SQSMessageBinding());
    }
}
