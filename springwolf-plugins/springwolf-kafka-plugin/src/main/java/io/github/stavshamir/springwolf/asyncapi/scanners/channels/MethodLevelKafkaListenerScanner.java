package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SpringPayloadAnnotationTypeExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class MethodLevelKafkaListenerScanner extends AbstractMethodLevelListenerScanner<KafkaListener>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected Class<KafkaListener> getListenerAnnotationClass() {
        return KafkaListener.class;
    }

    @Override
    protected String getChannelName(KafkaListener annotation) {
        List<String> resolvedTopics = Arrays.stream(annotation.topics())
                .map(resolver::resolveStringValue)
                .collect(toList());

        log.debug("Found topics: {}", String.join(", ", resolvedTopics));
        return resolvedTopics.get(0);
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(KafkaListener annotation) {
        return ImmutableMap.of("kafka", new KafkaChannelBinding());
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(KafkaListener annotation) {
        String groupId = resolver.resolveStringValue(annotation.groupId());
        if (groupId == null || groupId.isEmpty()) {
            log.debug("No group ID found for this listener");
            groupId = null;
        } else {
            log.debug("Found group id: {}", groupId);
        }

        KafkaOperationBinding binding = new KafkaOperationBinding();
        binding.setGroupId(groupId);
        return ImmutableMap.of("kafka", binding);

    }

    @Override
    protected Class<?> getPayloadType(Method method) {
        return SpringPayloadAnnotationTypeExtractor.getPayloadType(method);
    }
}
