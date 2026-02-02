// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.DefaultComponentsService;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.schemas.ModelConvertersProvider;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaMapper;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.converters.SchemaTitleModelConverter;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

class AvroPostProcessorIntegrationTest {

    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    private final SpringwolfConfigProperties springwolfConfigProperties = new SpringwolfConfigProperties();

    private final SwaggerSchemaService schemaService = new SwaggerSchemaService(
            springwolfConfigProperties,
            List.of(
                    new AvroSchemaPostProcessor(),
                    new ExampleGeneratorPostProcessor(new SchemaWalkerProvider(
                            List.of(new DefaultSchemaWalker<>(new ExampleJsonValueGenerator()))))),
            new SwaggerSchemaMapper(springwolfConfigProperties),
            new ModelConvertersProvider(springwolfConfigProperties, List.of(new SchemaTitleModelConverter())));

    private final ComponentsService componentsService = new DefaultComponentsService(schemaService);

    @Test
    void happyPath_AvroGamma_having_multiple_AvroDelta() {
        componentsService.resolvePayloadSchema(AvroGamma.class, CONTENT_TYPE_APPLICATION_JSON);

        var examplesBySchema =
                componentsService.getSchemas().entrySet().stream().collect(toMap(Map.Entry::getKey, this::getExamples));

        assertThat(examplesBySchema)
                .containsEntry(
                        AvroGamma.class.getName(), "[{\"avroDeltas\":[{\"name\":\"string\"}],\"name\":\"string\"}]")
                .containsEntry(AvroDelta.class.getName(), "[{\"name\":\"string\"}]");
    }

    @Test
    void unhappyPath_AvroAlpha_having_multiple_AvroBeta() {
        componentsService.resolvePayloadSchema(AvroAlpha.class, CONTENT_TYPE_APPLICATION_JSON);

        var examplesBySchema =
                componentsService.getSchemas().entrySet().stream().collect(toMap(Map.Entry::getKey, this::getExamples));

        assertThat(examplesBySchema)
                //  .containsEntry(AvroAlpha.class.getName(), "null")
                .containsEntry(
                        AvroAlpha.class.getName(), "[{\"avroBetas\":[{\"name\":\"string\"}],\"name\":\"string\"}]")
                .containsEntry(AvroBeta.class.getName(), "[{\"name\":\"string\"}]");
    }

    private String getExamples(Map.Entry<String, ComponentSchema> it) {
        return String.valueOf(it.getValue().getSchema().getExamples());
    }
}
