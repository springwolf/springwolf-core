package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.channel.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import com.asyncapi.v2.schema.Schema;
import com.asyncapi.v2.schema.Type;
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
        return Map.of("kafka", new KafkaChannelBinding());
    }

    public static Map<String, ? extends OperationBinding> buildOperationBinding(KafkaListener annotation, StringValueResolver resolver) {
        String groupId = resolver.resolveStringValue(annotation.groupId());
        Schema groupIdSchema = buildKafkaGroupIdSchema(groupId);

        KafkaOperationBinding binding = new KafkaOperationBinding();
        binding.setGroupId(groupIdSchema);
        return Map.of("kafka", binding);
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
            schema.setEnumValue(List.of(value));
            schema.setType(Type.STRING);
            return schema;
        }
        return null;
    }

    public static Map<String, ? extends MessageBinding> buildMessageBinding() {
        return Map.of("kafka", new KafkaMessageBinding());
    }
}
