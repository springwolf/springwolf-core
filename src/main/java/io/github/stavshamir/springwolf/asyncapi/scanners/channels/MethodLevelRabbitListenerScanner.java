package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class MethodLevelRabbitListenerScanner extends AbstractMethodLevelListenerScanner
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }


    @Override
    protected String getListenerClassName() {
        return "org.springframework.amqp.rabbit.annotation.RabbitListener";
    }

    @Override
    protected String getChannelName(Annotation annotation) {
        String[] queues = getQueues(annotation);
        List<String> resolvedTopics = Arrays.stream(queues)
                .map(resolver::resolveStringValue)
                .collect(toList());

        log.debug("Found queues: {}", String.join(", ", resolvedTopics));
        return resolvedTopics.get(0);
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(Annotation annotation) {
        return ImmutableMap.of("amqp", new AMQPOperationBinding());
    }

    private String[] getQueues(Annotation annotation) {
        try {
            Object queues = annotation.annotationType()
                    .getDeclaredMethod("queues")
                    .invoke(annotation, (Object[]) null);

            if (queues instanceof String[]) {
                return (String[]) queues;
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Failed to read queues value", e);
        }

        return null;
    }

}
