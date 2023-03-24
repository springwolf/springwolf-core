package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.MessageBinding;
import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.schema.Schema;
import com.asyncapi.v2.model.schema.Type;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Slf4j
public class KafkaListenerUtil {

    public static String getChannelName(KafkaListener annotation, StringValueResolver resolver) {
        List<String> resolvedTopics = Arrays.stream(annotation.topics())
                .map(resolver::resolveStringValue)
                .collect(toList());

        log.debug("Found topics: {}", String.join(", ", resolvedTopics));
        return resolvedTopics.get(0);
    }

    public static Map<String, ? extends ChannelBinding> buildChannelBinding() {
        return ImmutableMap.of("kafka", new KafkaChannelBinding());
    }

    public static Map<String, ? extends OperationBinding> buildOperationBinding(KafkaListener annotation, StringValueResolver resolver) {
        String groupId = resolver.resolveStringValue(annotation.groupId());
        Schema groupIdSchema = buildKafkaGroupIdSchema(groupId);

        KafkaOperationBinding binding = new KafkaOperationBinding();
        binding.setGroupId(groupIdSchema);
        return ImmutableMap.of("kafka", binding);
    }

    @Nullable
    public static Schema buildKafkaClientIdSchema(String clientId) {
        Schema schema = createStringSchema(clientId);

        if (schema != null) {
            log.debug("Found client id: {}", clientId);
        } else {
            log.debug("No client id found for this listener");
        }

        return schema;
    }
    @Nullable
    public static Schema buildKafkaGroupIdSchema(String groupId) {
        Schema schema = createStringSchema(groupId);

        if (schema != null) {
            log.debug("Found group id: {}", groupId);
        } else {
            log.debug("No group id found for this listener");
        }

        return schema;
    }

    @Nullable
    private static Schema createStringSchema(String value) {
        if (value != null && !value.isEmpty()) {
            Schema schema = new Schema();
            schema.setEnumValues(List.of(value));
            schema.setType(Type.STRING);
            return schema;
        }
        return null;
    }

    public static Map<String, ? extends MessageBinding> buildMessageBinding() {
        return ImmutableMap.of("kafka", new KafkaMessageBinding());
    }
}
