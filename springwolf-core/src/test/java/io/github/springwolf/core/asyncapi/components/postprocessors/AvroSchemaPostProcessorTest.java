// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

class AvroSchemaPostProcessorTest {
    SchemasPostProcessor processor = new AvroSchemaPostProcessor();

    @Test
    void avroSchemasAreRemovedTest() {
        // given
        var avroSchema = new Schema();
        avroSchema.set$ref("#/components/schemas/org.apache.avro.Schema");

        var avroSpecificData = new Schema();
        avroSpecificData.set$ref("#/components/schemas/org.apache.avro.specific.SpecificData");

        var schema = new Schema();
        schema.setProperties(new HashMap<>(
                Map.of("foo", new StringSchema(), "schema", avroSchema, "specificData", avroSpecificData)));

        var definitions = new HashMap<String, Schema>();
        definitions.put("schema", schema);
        definitions.put("customClassRefUnusedInThisTest", new StringSchema());
        definitions.put("org.apache.avro.Schema", new Schema());
        definitions.put("org.apache.avro.ConversionJava.lang.Object", new Schema());

        // when
        processor.process(schema, definitions, "content-type-ignored");

        // then
        assertThat(schema.getProperties()).isEqualTo(Map.of("foo", new StringSchema()));
        assertThat(definitions)
                .isEqualTo(Map.of("schema", schema, "customClassRefUnusedInThisTest", new StringSchema()));
    }

    @Test
    void avroSchemasAreRemovedInRefsTest() {
        // given
        var avroSchema = new Schema();
        avroSchema.set$ref("#/components/schemas/org.apache.avro.Schema");

        var avroSpecificData = new Schema();
        avroSpecificData.set$ref("#/components/schemas/org.apache.avro.specific.SpecificData");

        var refSchema = new Schema();
        refSchema.setProperties(new HashMap<>(
                Map.of("foo", new StringSchema(), "schema", avroSchema, "specificData", avroSpecificData)));

        var refProperty = new Schema();
        refProperty.set$ref("#/components/schemas/refSchema");
        var schema = new Schema();
        schema.setProperties(new HashMap<>(Map.of("ref", refProperty)));

        var definitions = new HashMap<String, Schema>();
        definitions.put("schema", schema);
        definitions.put("refSchema", refSchema);
        definitions.put("customClassRefUnusedInThisTest", new StringSchema());
        definitions.put("org.apache.avro.Schema", new Schema());
        definitions.put("org.apache.avro.ConversionJava.lang.Object", new Schema());

        // when
        processor.process(schema, definitions, "content-type-ignored");

        // then
        assertThat(schema.getProperties()).isEqualTo(Map.of("ref", refProperty));
        assertThat(refSchema.getProperties()).isEqualTo(Map.of("foo", new StringSchema()));
        assertThat(definitions)
                .isEqualTo(Map.of(
                        "schema",
                        schema,
                        "refSchema",
                        refSchema,
                        "customClassRefUnusedInThisTest",
                        new StringSchema()));
    }

    @Test
    void handleRecursiveSchemasTest() {
        var schema = new Schema();
        schema.set$ref("#/components/schemas/intermediateSchema");

        var intermediateSchema = new Schema();
        intermediateSchema.set$ref("#/components/schemas/schema");

        var definitions = new HashMap<String, Schema>();
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
