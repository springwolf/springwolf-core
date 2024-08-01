// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.annotations.AsyncApiPayload;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultComponentsServiceIntegrationTest {
    private final String CONTENT_TYPE = "does-not-matter";

    private final SwaggerSchemaService schemaService =
            new SwaggerSchemaService(List.of(), List.of(), new SwaggerSchemaUtil(), new SpringwolfConfigProperties());

    private final ComponentsService componentsService = new DefaultComponentsService(schemaService);

    @Nested
    class AllSchemaFields {
        @Test
        void getStringSchemas() {
            componentsService.registerSchema(StringFoo.class, CONTENT_TYPE);

            Map<String, SchemaObject> schemas = componentsService.getSchemas();
            assertThat(schemas).hasSize(1);
            SchemaObject objectFoo = schemas.get(this.getClass().getName() + "$StringFoo");

            assertThat(objectFoo)
                    .isEqualTo(SchemaObject.builder()
                            .title("title")
                            .type("object")
                            .properties(Map.of(
                                    "s",
                                    ComponentSchema.of(SchemaObject.builder()
                                            .type("string")
                                            .minLength(1)
                                            .maxLength(10)
                                            .examples(List.of("\"example\""))
                                            .pattern("[a-z]+")
                                            .deprecated(true)
                                            .description("description")
                                            .build())))
                            .required(List.of("s"))
                            .build());
        }

        @Test
        void getArraySchemas() {
            componentsService.registerSchema(ArrayFoo.class, CONTENT_TYPE);

            Map<String, SchemaObject> schemas = componentsService.getSchemas();
            assertThat(schemas).hasSize(1);
            SchemaObject objectFoo = schemas.get(this.getClass().getName() + "$ArrayFoo");

            assertThat(objectFoo)
                    .isEqualTo(SchemaObject.builder()
                            .title("title")
                            .type("object")
                            .properties(Map.of(
                                    "array",
                                    ComponentSchema.of(SchemaObject.builder()
                                            .type("array")
                                            .items(ComponentSchema.of(SchemaObject.builder()
                                                    .type("string")
                                                    .description("items description")
                                                    .build()))
                                            .maxItems(10)
                                            .minItems(1)
                                            .uniqueItems(true)
                                            .description("array description")
                                            .build())))
                            .build());
        }

        @Test
        void getRefSchemas() {
            componentsService.registerSchema(RefFoo.class, CONTENT_TYPE);

            Map<String, SchemaObject> schemas = componentsService.getSchemas();
            assertThat(schemas).hasSize(1);
            SchemaObject objectFoo = schemas.get(this.getClass().getName() + "$RefFoo");

            assertThat(objectFoo)
                    .isEqualTo(SchemaObject.builder()
                            .title("title")
                            .type("object")
                            .properties(Map.of(
                                    "ref",
                                    ComponentSchema.of(SchemaObject.builder()
                                            .type("object")
                                            .not(ComponentSchema.of(SchemaObject.builder()
                                                    .type("object")
                                                    .build()))
                                            .oneOf(List.of(ComponentSchema.of(SchemaObject.builder()
                                                    .type("string")
                                                    .build())))
                                            .anyOf(List.of(ComponentSchema.of(SchemaObject.builder()
                                                    .type("string")
                                                    .build())))
                                            .allOf(List.of(ComponentSchema.of(SchemaObject.builder()
                                                    .type("string")
                                                    .build())))
                                            .build())))
                            .build());
        }

        @Test
        void getNumberSchemas() {
            componentsService.registerSchema(NumberFoo.class, CONTENT_TYPE);

            Map<String, SchemaObject> schemas = componentsService.getSchemas();
            assertThat(schemas).hasSize(1);
            SchemaObject objectFoo = schemas.get(this.getClass().getName() + "$NumberFoo");

            assertThat(objectFoo)
                    .isEqualTo(SchemaObject.builder()
                            .title("title")
                            .type("object")
                            .properties(Map.of(
                                    "number",
                                    ComponentSchema.of(SchemaObject.builder()
                                            .type("number")
                                            .format("double")
                                            .multipleOf(BigDecimal.ONE)
                                            .exclusiveMaximum(BigDecimal.valueOf(5))
                                            .exclusiveMinimum(BigDecimal.ONE)
                                            .enumValues(List.of("2", "3", "4"))
                                            .build())))
                            .build());
        }

        @Test
        void getNotSupportedSchemas() {
            componentsService.registerSchema(NotSupportedFoo.class, CONTENT_TYPE);

            Map<String, SchemaObject> schemas = componentsService.getSchemas();
            assertThat(schemas).hasSize(1);
            SchemaObject objectFoo = schemas.get(this.getClass().getName() + "$NotSupportedFoo");

            assertThat(objectFoo)
                    .isEqualTo(
                            SchemaObject.builder().title("title").type("object").build());
        }

        @Data
        @NoArgsConstructor
        @Schema(title = "title")
        private static class StringFoo {
            @Schema(
                    minLength = 1,
                    maxLength = 10,
                    example = "\"example\"",
                    pattern = "[a-z]+",
                    requiredMode = Schema.RequiredMode.REQUIRED,
                    deprecated = true,
                    description = "description")
            private String s;
        }

        @Data
        @NoArgsConstructor
        @Schema(title = "title")
        private static class ArrayFoo {
            @ArraySchema(
                    schema = @Schema(type = "string", description = "items description"),
                    arraySchema = @Schema(description = "array description"),
                    maxItems = 10,
                    minItems = 1,
                    uniqueItems = true)
            private List<String> array;
        }

        @Data
        @NoArgsConstructor
        @Schema(title = "title")
        private static class RefFoo {
            @Schema(
                    not = String.class,
                    oneOf = {String.class},
                    anyOf = {String.class},
                    allOf = {String.class})
            private Object ref;
        }

        @Data
        @NoArgsConstructor
        @Schema(title = "title")
        private static class NumberFoo {
            @Schema(
                    multipleOf = 1,
                    maximum = "5", // is combined into exclusiveMaximum
                    exclusiveMaximum = true,
                    minimum = "1", // is combined into exclusiveMinimum
                    exclusiveMinimum = true,
                    allowableValues = {"2", "3", "4"})
            private Double number;
        }

        @Data
        @NoArgsConstructor
        @Schema(title = "title")
        private static class NotSupportedFoo {
            @Schema(
                    hidden = true, // currently not supported
                    defaultValue = "default", // currently not supported
                    nullable = true, // currently not supported
                    contains = Void.class, // currently not supported
                    additionalItems = Void.class, // currently not supported
                    additionalProperties = Schema.AdditionalPropertiesValue.TRUE, // currently not supported
                    additionalPropertiesSchema = Void.class, // currently not supported
                    propertyNames = Void.class, // currently not supported
                    maxProperties = 10, // currently not supported
                    minProperties = 1, // currently not supported
                    discriminatorProperty = "discriminator", // currently not supported
                    discriminatorMapping = { // currently not supported
                        @DiscriminatorMapping(value = "String", schema = String.class),
                    })
            private Object notSupported;
        }
    }

    @Nested
    class AsyncApiPayloadTest {
        @Test
        void stringEnvelopTest() {
            componentsService.registerSchema(StringEnvelop.class, CONTENT_TYPE);

            Map<String, SchemaObject> schemas = componentsService.getSchemas();
            assertThat(schemas).hasSize(1);

            SchemaObject schema = schemas.get(this.getClass().getName() + "$StringEnvelop");
            assertThat(schema)
                    .isEqualTo(SchemaObject.builder()
                            .type("string")
                            .description("The payload in the envelop")
                            .maxLength(10)
                            .build());
        }

        @Test
        void illegalEnvelopTest() {
            componentsService.registerSchema(EnvelopWithMultipleAsyncApiPayloadAnnotations.class, CONTENT_TYPE);

            Map<String, SchemaObject> schemas = componentsService.getSchemas();
            assertThat(schemas).hasSize(1);

            // fallback to EnvelopWithMultipleAsyncApiPayloadAnnotations, because it has multiple AsyncApiPayload
            SchemaObject schema =
                    schemas.get(this.getClass().getName() + "$EnvelopWithMultipleAsyncApiPayloadAnnotations");
            assertThat(schema)
                    .isEqualTo(SchemaObject.builder()
                            .type("object")
                            .properties(Map.of(
                                    "otherField",
                                    ComponentSchema.of(SchemaObject.builder()
                                            .type("integer")
                                            .format("int32")
                                            .build()),
                                    "payload",
                                    ComponentSchema.of(SchemaObject.builder()
                                            .type("string")
                                            .description("The payload in the envelop")
                                            .maxLength(10)
                                            .build())))
                            .build());
        }

        @Data
        @NoArgsConstructor
        public class StringEnvelop {
            Integer otherField;

            @AsyncApiPayload
            @Schema(description = "The payload in the envelop", maxLength = 10)
            String payload;
        }

        @Data
        @NoArgsConstructor
        public class EnvelopWithMultipleAsyncApiPayloadAnnotations {
            @AsyncApiPayload
            Integer otherField;

            @AsyncApiPayload
            @Schema(description = "The payload in the envelop", maxLength = 10)
            String payload;
        }
    }
}
