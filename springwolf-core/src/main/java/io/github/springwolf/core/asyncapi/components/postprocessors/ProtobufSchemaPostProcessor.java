// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.util.StringUtils;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Removes internal protobuf fields and classes from the schema.
 * <br/>
 * For now, this class is located in springwolf-core as it provides value for the cloud-stream and kafka plugin.
 * To avoid to many (one-class) add-ons, this class was not moved to yet another artifact - as also no new dependencies are required.
 * This may change in the future.
 */
public class ProtobufSchemaPostProcessor implements SchemasPostProcessor {
    private static final String PROTOBUF_SCHEMA_PREFIX = "com.google.protobuf.";
    private static final String PROTOBUF_SCHEMA_REF_PREFIX =
            MessageReference.toSchema(PROTOBUF_SCHEMA_PREFIX).getRef();
    static final List<String> PROTOBUF_FIELDS = List.of(
            "allFields",
            "defaultInstanceForType",
            "descriptorForType",
            "initializationErrorString",
            "initialized",
            "memoizedSerializedSize",
            "parserForType",
            "serializedSize",
            "unknownFields");

    @Override
    public void process(Schema schema, Map<String, Schema> definitions, String contentType) {
        removeSchemas(definitions);

        Deque<Schema> queue = new LinkedList<>(List.of(schema));
        HashSet<Schema> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Schema currentSchema = queue.pop();
            if (visited.contains(currentSchema)) {
                continue;
            }
            visited.add(currentSchema);

            processRefSchema(currentSchema, queue, definitions);
            processProperties(currentSchema, queue);
        }
    }

    private void processRefSchema(Schema schema, Deque<Schema> queue, Map<String, Schema> definitions) {
        if (schema.get$ref() != null) {
            String schemaName = MessageReference.extractRefName(schema.get$ref());
            Schema refedSchema = definitions.get(schemaName);
            if (refedSchema != null) {
                queue.add(refedSchema);
            }
        }
    }

    private void processProperties(Schema schema, Deque<Schema> queue) {
        Map<String, Schema> properties = schema.getProperties();
        if (properties != null) {
            if (properties.keySet().containsAll(PROTOBUF_FIELDS)) {
                properties
                        .entrySet()
                        .removeIf(entry -> PROTOBUF_FIELDS.contains(entry.getKey())
                                || StringUtils.startsWithIgnoreCase(
                                        entry.getValue().get$ref(), PROTOBUF_SCHEMA_REF_PREFIX));
            }

            properties.forEach((key, value) -> queue.add(value));
        }
    }

    private void removeSchemas(Map<String, Schema> definitions) {
        definitions
                .entrySet()
                .removeIf(entry -> StringUtils.startsWithIgnoreCase(entry.getKey(), PROTOBUF_SCHEMA_PREFIX));
    }
}
