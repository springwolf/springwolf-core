package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
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
public class MethodLevelKafkaListenerScanner extends AbstractMethodLevelListenerScanner
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    private static class KafkaListenerMethodsNames {
        public static final String TOPICS = "topics";
        public static final String GROUP_ID = "groupId";
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected String getListenerClassName() {
        return "org.springframework.kafka.annotation.KafkaListener";
    }

    @Override
    protected String getChannelName(Annotation annotation) {
        String[] topics = getTopics(annotation);
        List<String> resolvedTopics = Arrays.stream(topics)
                .map(resolver::resolveStringValue)
                .collect(toList());

        log.debug("Found topics: {}", String.join(", ", resolvedTopics));
        return resolvedTopics.get(0);
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(Annotation annotation) {
        String groupId = getGroupId(annotation);
        String resolvedGroupId = resolver.resolveStringValue(groupId);

        if (resolvedGroupId == null || resolvedGroupId.isEmpty()) {
            log.debug("No group ID found for this listener");
            resolvedGroupId = null;
        } else {
            log.debug("Found group id: {}", resolvedGroupId);
        }

        KafkaOperationBinding binding = new KafkaOperationBinding();
        binding.setGroupId(resolvedGroupId);
        return ImmutableMap.of("kafka", binding);
    }

    private String[] getTopics(Annotation annotation) {
        return ReflectionUtils
                .getValueOfAnnotationProperty(annotation, KafkaListenerMethodsNames.TOPICS, String[].class)
                .orElse(null);
    }

    private String getGroupId(Annotation annotation) {
        return ReflectionUtils
                .getValueOfAnnotationProperty(annotation, KafkaListenerMethodsNames.GROUP_ID, String.class)
                .orElse("");
    }

}
