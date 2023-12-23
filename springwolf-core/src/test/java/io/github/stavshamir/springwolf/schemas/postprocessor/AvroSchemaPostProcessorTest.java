// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AvroSchemaPostProcessorTest {
    SchemasPostProcessor processor = new AvroSchemaPostProcessor();

    @Test
    void avroSchemasAreRemovedTest() {
        // given
        var avroSchema = new io.swagger.v3.oas.models.media.Schema();
        avroSchema.set$ref("#/components/schemas/org.apache.avro.Schema");

        var avroSpecificData = new io.swagger.v3.oas.models.media.Schema();
        avroSpecificData.set$ref("#/components/schemas/org.apache.avro.specific.SpecificData");

        var schema = new io.swagger.v3.oas.models.media.Schema();
        schema.setProperties(new HashMap<>(
                Map.of("foo", new StringSchema(), "schema", avroSchema, "specificData", avroSpecificData)));

        var definitions = new HashMap<String, io.swagger.v3.oas.models.media.Schema>();
        definitions.put("customClassRefUnusedInThisTest", new StringSchema());
        definitions.put("org.apache.avro.Schema", new io.swagger.v3.oas.models.media.Schema());
        definitions.put("org.apache.avro.ConversionJava.lang.Object", new io.swagger.v3.oas.models.media.Schema());

        // when
        processor.process(schema, definitions);

        // then
        assertThat(schema.getProperties()).isEqualTo(Map.of("foo", new StringSchema()));
        assertThat(definitions).isEqualTo(Map.of("customClassRefUnusedInThisTest", new StringSchema()));
    }
}
