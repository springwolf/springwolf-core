// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.components.postprocessors.AvroSchemaPostProcessor;
import io.github.springwolf.core.asyncapi.components.postprocessors.ExampleGeneratorPostProcessor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificData;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * In GH-1627, it was found that it is important to finish a post-processor on all schemas first,
 * before moving on to the next post-processor to generate examples
 */
class SwaggerSchemaServicePostProcessorExecutionTest {

    private final SpringwolfConfigProperties springwolfConfigProperties = new SpringwolfConfigProperties();

    private final SwaggerSchemaService schemaService = new SwaggerSchemaService(
            springwolfConfigProperties,
            List.of(
                    new AvroSchemaPostProcessor(),
                    new ExampleGeneratorPostProcessor(new SchemaWalkerProvider(
                            List.of(new DefaultSchemaWalker<>(new ExampleJsonValueGenerator()))))),
            new SwaggerSchemaMapper(springwolfConfigProperties),
            new ModelConvertersProvider(springwolfConfigProperties, List.of()));

    @Test
    void happyPath_AvroGamma_having_multiple_AvroDelta() {
        // when
        var extractedSchema =
                schemaService.resolveSchema(AvroTestClasses.AvroGamma.class, MediaType.APPLICATION_JSON_VALUE);

        // then
        var examplesBySchema = extractedSchema.referencedSchemas().entrySet().stream()
                .collect(toMap(Map.Entry::getKey, this::getExamples));
        assertThat(examplesBySchema)
                .containsEntry(
                        AvroTestClasses.AvroGamma.class.getCanonicalName(),
                        "[{\"avroDeltas\":[{\"name\":\"string\"}],\"name\":\"string\"}]")
                .containsEntry(AvroTestClasses.AvroDelta.class.getCanonicalName(), "[{\"name\":\"string\"}]");
    }

    @Test
    void unhappyPath_AvroAlpha_having_multiple_AvroBeta() {
        // when
        var extractedSchema =
                schemaService.resolveSchema(AvroTestClasses.AvroAlpha.class, MediaType.APPLICATION_JSON_VALUE);

        // then
        var examplesBySchema = extractedSchema.referencedSchemas().entrySet().stream()
                .collect(toMap(Map.Entry::getKey, this::getExamples));
        assertThat(examplesBySchema)
                .containsEntry(
                        AvroTestClasses.AvroAlpha.class.getCanonicalName(),
                        "[{\"avroBetas\":[{\"name\":\"string\"}],\"name\":\"string\"}]")
                .containsEntry(AvroTestClasses.AvroBeta.class.getCanonicalName(), "[{\"name\":\"string\"}]");
    }

    private String getExamples(Map.Entry<String, ComponentSchema> it) {
        return String.valueOf(it.getValue().getSchema().getExamples());
    }

    private static class AvroTestClasses {
        static class AvroAlpha {

            public String getName() {
                return "alpha";
            }

            public List<AvroBeta> getAvroBetas() {
                return List.of(new AvroBeta());
            }

            public SpecificData getSpecificData() {
                return new SpecificData();
            }

            public Schema getSchema() {
                return null;
            }
        }

        static class AvroBeta {

            public String getName() {
                return "beta";
            }

            public SpecificData getSpecificData() {
                return new SpecificData();
            }

            public Schema getSchema() {
                return null;
            }
        }

        static class AvroDelta {

            public String getName() {
                return "delta";
            }

            public SpecificData getSpecificData() {
                return new SpecificData();
            }

            public Schema getSchema() {
                return null;
            }
        }

        static class AvroGamma {

            public String getName() {
                return "gamma";
            }

            public List<AvroDelta> getAvroDeltas() {
                return List.of(new AvroDelta());
            }

            public SpecificData getSpecificData() {
                return new SpecificData();
            }

            public Schema getSchema() {
                return null;
            }
        }
    }
}
