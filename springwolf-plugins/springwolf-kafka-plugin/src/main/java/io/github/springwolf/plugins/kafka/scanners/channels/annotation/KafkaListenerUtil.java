// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.scanners.channels.annotation;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.Schema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.lang.Nullable;
import org.springframework.util.StringValueResolver;

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

    public static Map<String, ChannelBinding> buildChannelBinding() {
        return Map.of("kafka", new KafkaChannelBinding());
    }

    public static Map<String, OperationBinding> buildOperationBinding(
            KafkaListener annotation, StringValueResolver resolver) {
        String groupId = resolver.resolveStringValue(annotation.groupId());
        Schema groupIdSchema = buildKafkaGroupIdSchema(groupId);

        KafkaOperationBinding binding = new KafkaOperationBinding();
        binding.setGroupId(groupIdSchema);
        return Map.of("kafka", binding);
    }

    @Nullable
    public static SchemaObject buildKafkaClientIdSchema(String clientId) {
        SchemaObject schema = createStringSchema(clientId);

        if (schema != null) {
            log.debug("Found client id: {}", clientId);
        } else {
            log.debug("No client id found for this listener");
        }

        return schema;
    }

    @Nullable
    public static SchemaObject buildKafkaGroupIdSchema(String groupId) {
        SchemaObject schema = createStringSchema(groupId);

        if (schema != null) {
            log.debug("Found group id: {}", groupId);
        } else {
            log.debug("No group id found for this listener");
        }

        return schema;
    }

    @Nullable
    private static SchemaObject createStringSchema(String value) {
        if (value != null && !value.isEmpty()) {
            SchemaObject schema = new SchemaObject();
            schema.setEnumValues(List.of(value));
            schema.setType(SchemaType.STRING);
            return schema;
        }
        return null;
    }

    public static Map<String, MessageBinding> buildMessageBinding() {
        return Map.of("kafka", new KafkaMessageBinding());
    }
}
