// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.components.postprocessors.SchemasPostProcessor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class SwaggerSchemaServiceTest {

    private static final ObjectMapper objectMapper =
            Json.mapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    private final SchemasPostProcessor schemasPostProcessor = mock();
    private final SchemasPostProcessor schemasPostProcessor2 = mock();

    private SwaggerSchemaService schemaService;

    @BeforeEach
    void setUp() {
        schemaService = new SwaggerSchemaService(
                List.of(new ModelConverterNativeClass.Converter()),
                List.of(schemasPostProcessor, schemasPostProcessor2),
                new SwaggerSchemaUtil(),
                new SpringwolfConfigProperties());
    }

    @Test
    void classWithSchemaAnnotation() {
        ComponentSchema schema = schemaService
                .resolveSchema(ClassWithSchemaAnnotation.class, "content-type-not-relevant")
                .rootSchema();

        assertThat(schema.getReference().getRef()).isEqualTo("#/components/schemas/DifferentName");
    }

    @Test
    void getDefinitionWithoutFqnClassName() throws IOException {
        // given
        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setUseFqn(false);

        SwaggerSchemaService schemaServiceWithFqn =
                new SwaggerSchemaService(List.of(), List.of(), new SwaggerSchemaUtil(), properties);

        // when
        Class<?> clazz =
                OneFieldFooWithoutFqn.class; // swagger seems to cache results. Therefore, a new class must be used.
        Map<String, SchemaObject> schemas = schemaServiceWithFqn
                .resolveSchema(clazz, "content-type-not-relevant")
                .referencedSchemas();
        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(schemas);

        // then
        System.out.println("Got: " + actualDefinitions);
        assertThat(actualDefinitions).contains(clazz.getSimpleName());
        assertThat(actualDefinitions).doesNotContain(clazz.getCanonicalName());
    }

    @Test
    void postProcessorsAreCalled() {
        schemaService.resolveSchema(ClassWithSchemaAnnotation.class, "some-content-type");

        verify(schemasPostProcessor).process(any(), any(), eq("some-content-type"));
        verify(schemasPostProcessor2).process(any(), any(), eq("some-content-type"));
    }

    @Test
    void postProcessorIsSkippedWhenSchemaWasRemoved() {
        doAnswer(invocationOnMock -> {
                    Map<String, Schema> schemas = invocationOnMock.getArgument(1);
                    schemas.clear();
                    return null;
                })
                .when(schemasPostProcessor)
                .process(any(), any(), any());

        schemaService.resolveSchema(ClassWithSchemaAnnotation.class, "content-type-not-relevant");

        verifyNoInteractions(schemasPostProcessor2);
    }

    @Test
    void modelConvertersRefsAreResolved() {
        SwaggerSchemaService.ExtractedSchemas schema =
                schemaService.resolveSchema(ModelConverterNativeClass.class, "content-type-not-relevant");

        assertThat(schema.rootSchema().getReference().getRef()).contains(ModelConverterNativeClass.class.getName());
        assertThat(schema.referencedSchemas()).hasSize(1).containsKey(ModelConverterNativeClass.class.getName());
        assertThat(schema.referencedSchemas().get(ModelConverterNativeClass.class.getName()))
                .satisfies((el) -> assertThat(el.getType()).isEqualTo(SchemaType.OBJECT))
                .satisfies((el) -> assertThat(el.getProperties()).containsOnlyKeys("actual"));
    }

    @Data
    @NoArgsConstructor
    @Schema(name = "DifferentName")
    private static class ClassWithSchemaAnnotation {
        private String s;
        private boolean b;
    }

    @Data
    @NoArgsConstructor
    private static class OneFieldFooWithoutFqn {
        private String s;
    }

    /**
     * When a model converter is used, all usage of {@link ModelConverterNativeClass} should be replaced with {@link ModelConverterActualClass}
     */
    @Data
    @NoArgsConstructor
    private static class ModelConverterNativeClass {
        private ModelConverterActualClass target;

        @Data
        @NoArgsConstructor
        private static class ModelConverterActualClass {
            private String actual;
        }

        /**
         * Copied from SimpleClassModelConverter.java
         */
        public static class Converter implements ModelConverter {

            @Override
            public io.swagger.v3.oas.models.media.Schema resolve(
                    AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
                Class<?> rawClass = getRawClass(type);
                boolean isNativeType = isNativeType(rawClass);

                if (isNativeType) {
                    type = new AnnotatedType(ModelConverterActualClass.class).resolveAsRef(true);
                }

                io.swagger.v3.oas.models.media.Schema<?> schema = proceedWithChain(type, context, chain);

                if (isNativeType && schema != null && rawClass != null) {
                    schema.name(rawClass.getName());
                }

                return schema;
            }

            private Class<?> getRawClass(AnnotatedType type) {
                JavaType javaType = Json.mapper().constructType(type.getType());
                return javaType != null ? javaType.getRawClass() : null;
            }

            private io.swagger.v3.oas.models.media.Schema proceedWithChain(
                    AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
                return (chain.hasNext()) ? chain.next().resolve(type, context, chain) : null;
            }

            private boolean isNativeType(Class<?> rawClass) {
                return ModelConverterNativeClass.class.isAssignableFrom(rawClass);
            }
        }
    }
}
