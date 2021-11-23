package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class MethodLevelRabbitListenerScanner extends AbstractMethodLevelListenerScanner
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    private static class RabbitListenerMethodsNames {
        public static final String QUEUES = "queues";
    }

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
        List<String> resolvedQueues = resolveQueues(queues);

        log.debug("Found queues: {}", String.join(", ", resolvedQueues));
        return resolvedQueues.get(0);
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(Annotation annotation) {
        return ImmutableMap.of("amqp", new AMQPOperationBinding());
    }

    private String[] getQueues(Annotation annotation) {
        return ReflectionUtils
                .getValueOfAnnotationProperty(annotation, RabbitListenerMethodsNames.QUEUES, String[].class)
                .orElse(null);
    }

    private List<String> resolveQueues(String[] queues) {
        return Arrays.stream(queues)
                .map(resolver::resolveStringValue)
                .collect(toList());
    }

}
