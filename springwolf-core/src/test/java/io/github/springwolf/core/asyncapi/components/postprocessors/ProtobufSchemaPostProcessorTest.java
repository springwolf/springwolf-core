// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

class ProtobufSchemaPostProcessorTest {
    private static final Map<String, Schema> protobufSchemas = List.of("UnknownFields").stream()
            .map(schema -> {
                var protobufSchema = new io.swagger.v3.oas.models.media.Schema();
                protobufSchema.setName("com.google.protobuf." + schema);
                return protobufSchema;
            })
            .collect(Collectors.toMap(io.swagger.v3.oas.models.media.Schema::getName, schema -> schema));
    private static final Map<String, io.swagger.v3.oas.models.media.Schema> protobufPropertySchemas =
            ProtobufSchemaPostProcessor.PROTOBUF_FIELDS.stream()
                    .map(field -> {
                        var schema = new io.swagger.v3.oas.models.media.Schema();
                        schema.setName(field);
                        return schema;
                    })
                    .collect(Collectors.toMap(io.swagger.v3.oas.models.media.Schema::getName, schema -> schema));

    ProtobufSchemaPostProcessor processor = new ProtobufSchemaPostProcessor();

    @Test
    void avroSchemasAreRemovedTest() {
        // given
        var schema = new io.swagger.v3.oas.models.media.Schema();
        HashMap<String, StringSchema> properties = new HashMap<>(Map.of("foo", new StringSchema()));
        schema.setProperties(properties);

        var definitions = new HashMap<String, io.swagger.v3.oas.models.media.Schema>();
        definitions.put("schema", schema);
        definitions.putAll(protobufSchemas);

        // when
        processor.process(schema, definitions, "content-type-ignored");

        // then
        assertThat(definitions.keySet()).isEqualTo(Set.of("schema"));
    }

    @Test
    void protobufSchemaPropertyFieldsAreRemovedTest() {
        // given
        var someStringBytesPropertySchema = new io.swagger.v3.oas.models.media.Schema();
        someStringBytesPropertySchema.set$ref(
                MessageReference.toSchema("com.google.protobuf.SomeStringBytes").getRef());

        var schema = new io.swagger.v3.oas.models.media.Schema();
        HashMap<String, Schema> properties = new HashMap<>(Map.of("foo", new StringSchema()));
        properties.put("someStringBytes", someStringBytesPropertySchema);
        properties.putAll(protobufPropertySchemas);
        schema.setProperties(properties);

        var definitions = new HashMap<String, io.swagger.v3.oas.models.media.Schema>();
        definitions.put("schema", schema);
        definitions.putAll(protobufSchemas);

        // when
        processor.process(schema, definitions, "content-type-ignored");

        // then
        assertThat(schema.getProperties().keySet()).isEqualTo(Set.of("foo"));
    }

    @Test
    void handleRecursiveSchemasTest() {
        var schema = new io.swagger.v3.oas.models.media.Schema();
        schema.set$ref("#/components/schemas/intermediateSchema");

        var intermediateSchema = new io.swagger.v3.oas.models.media.Schema();
        intermediateSchema.set$ref("#/components/schemas/schema");

        var definitions = new HashMap<String, io.swagger.v3.oas.models.media.Schema>();
        definitions.put("schema", schema);
        definitions.put("intermediateSchema", new StringSchema());

        // when
        try {
            processor.process(schema, definitions, "content-type-ignored");
        } catch (StackOverflowError ex) {
            // then, no StackOverflowException is thrown
            fail();
        }
    }
}
