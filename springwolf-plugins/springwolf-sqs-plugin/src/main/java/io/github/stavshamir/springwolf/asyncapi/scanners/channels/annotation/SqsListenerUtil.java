package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.channel.sqs.SQSChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.sqs.SQSMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.sqs.SQSOperationBinding;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;
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

    public static Map<String, ? extends ChannelBinding> buildChannelBinding(
            SqsListener annotation, StringValueResolver resolver) {
        return Map.of("sqs", new SQSChannelBinding());
    }

    public static Map<String, ? extends OperationBinding> buildOperationBinding(
            SqsListener annotation, StringValueResolver resolver) {
        return Map.of("sqs", new SQSOperationBinding());
    }

    public static Map<String, ? extends MessageBinding> buildMessageBinding() {
        return Map.of("sqs", new SQSMessageBinding());
    }
}
