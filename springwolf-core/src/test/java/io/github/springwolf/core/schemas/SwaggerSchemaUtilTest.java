// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.schemas;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.media.Discriminator;
import io.swagger.v3.oas.models.media.ObjectSchema;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerSchemaUtilTest {
    private final SwaggerSchemaUtil swaggerSchemaUtil = new SwaggerSchemaUtil();

    @Nested
    class MapSchemaOrRef {

        @Test
        void mapReference() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.set$ref("#/components/schemas/MySchema");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchemaOrRef(schema);

            // then
            assertThat(componentSchema.getReference()).isEqualTo(new MessageReference("#/components/schemas/MySchema"));
        }

        @Test
        void mapSchema() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setType("string");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchemaOrRef(schema);

            // then
            assertThat(componentSchema.getSchema().getType()).isEqualTo(schema.getType());
        }
    }

    @Nested
    class MapSchema {
        @Test
        void mapExternalDocs() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ExternalDocumentation externalDocs = new ExternalDocumentation();
            externalDocs.setDescription("description");
            externalDocs.setUrl("url");
            schema.setExternalDocs(externalDocs);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getExternalDocs().getDescription()).isEqualTo(externalDocs.getDescription());
            assertThat(componentSchema.getExternalDocs().getUrl()).isEqualTo(externalDocs.getUrl());
        }

        @Test
        void mapDeprecated() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setDeprecated(true);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getDeprecated()).isEqualTo(true);
        }

        @Test
        void mapTitle() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setTitle("title");

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getTitle()).isEqualTo(schema.getTitle());
        }

        @Test
        void mapType() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setType("string");

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getType()).isEqualTo(schema.getType());
        }

        @Test
        void mapProperties() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema property = new ObjectSchema();
            property.setType("string");
            schema.addProperty("property", property);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(((ComponentSchema) componentSchema.getProperties().get("property"))
                            .getSchema()
                            .getType())
                    .isEqualTo(property.getType());
        }

        @Test
        void mapDescription() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setDescription("description");

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getDescription()).isEqualTo(schema.getDescription());
        }

        @Test
        void mapFormat() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setFormat("format");

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getFormat()).isEqualTo(schema.getFormat());
        }

        @Test
        void mapPattern() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setPattern("pattern");

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getPattern()).isEqualTo(schema.getPattern());
        }

        @Test
        void mapExclusiveMinimum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMinimum(BigDecimal.ONE);
            schema.setExclusiveMinimum(true);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getExclusiveMinimum()).isEqualTo(schema.getMinimum());
            assertThat(componentSchema.getMinimum()).isNull();
        }

        @Test
        void mapExclusiveMinimumValue() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setExclusiveMinimumValue(BigDecimal.ONE);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getExclusiveMinimum()).isEqualTo(schema.getExclusiveMinimumValue());
            assertThat(componentSchema.getMinimum()).isNull();
        }

        @Test
        void mapExclusiveMaximum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMaximum(BigDecimal.ONE);
            schema.setExclusiveMaximum(true);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getExclusiveMaximum()).isEqualTo(schema.getMaximum());
            assertThat(componentSchema.getMaximum()).isNull();
        }

        @Test
        void mapExclusiveMaximumValue() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setExclusiveMaximumValue(BigDecimal.ONE);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getExclusiveMaximum()).isEqualTo(schema.getExclusiveMaximumValue());
            assertThat(componentSchema.getMaximum()).isNull();
        }

        @Test
        void mapMinimum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMinimum(BigDecimal.ONE);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getMinimum()).isEqualTo(schema.getMinimum());
            assertThat(componentSchema.getExclusiveMinimum()).isNull();
        }

        @Test
        void mapMaximum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMaximum(BigDecimal.ONE);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getMaximum()).isEqualTo(schema.getMaximum());
            assertThat(componentSchema.getExclusiveMaximum()).isNull();
        }

        @Test
        void mapMultipleOf() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMultipleOf(BigDecimal.ONE);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getMultipleOf()).isEqualTo(schema.getMultipleOf());
        }

        @Test
        void mapMinLength() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMinLength(1);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getMinLength()).isEqualTo(schema.getMinLength());
        }

        @Test
        void mapMaxLength() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMaxLength(1);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getMaxLength()).isEqualTo(schema.getMaxLength());
        }

        @Test
        void mapEnum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setEnum(List.of("enum1", "enum2"));

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getEnumValues()).isEqualTo(schema.getEnum());
        }

        @Test
        void mapExample() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setExample("example");

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getExamples()).isEqualTo(List.of(schema.getExample()));
        }

        @Test
        void mapAdditionalProperties() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema additionalProperties = new ObjectSchema();
            additionalProperties.setType("string");
            schema.setAdditionalProperties(additionalProperties);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(((ComponentSchema) componentSchema.getAdditionalProperties())
                            .getSchema()
                            .getType())
                    .isEqualTo(additionalProperties.getType());
        }

        @Test
        void mapRequired() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setRequired(List.of("required"));

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getRequired()).isEqualTo(schema.getRequired());
        }

        @Test
        void mapDiscriminator() {
            // given
            ObjectSchema schema = new ObjectSchema();
            Discriminator discriminator = new Discriminator();
            discriminator.setPropertyName("name");
            schema.setDiscriminator(discriminator);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getDiscriminator()).isEqualTo("name");
        }

        @Test
        void mapAllOf() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema allOf = new ObjectSchema();
            allOf.setType("string");
            schema.addAllOfItem(allOf);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat((componentSchema.getAllOf().get(0).getSchema()).getType())
                    .isEqualTo(allOf.getType());
        }

        @Test
        void mapOneOf() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema oneOf = new ObjectSchema();
            oneOf.setType("string");
            schema.addOneOfItem(oneOf);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat((componentSchema.getOneOf().get(0).getSchema()).getType())
                    .isEqualTo(oneOf.getType());
        }

        @Test
        void mapAnyOf() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema anyOf = new ObjectSchema();
            anyOf.setType("string");
            schema.addAnyOfItem(anyOf);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat((componentSchema.getAnyOf().get(0).getSchema()).getType())
                    .isEqualTo(anyOf.getType());
        }

        @Test
        void mapConst() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setConst("const");

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getConstValue()).isEqualTo(schema.getConst());
        }

        @Test
        void mapNot() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema not = new ObjectSchema();
            not.setType("string");
            schema.setNot(not);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat((componentSchema.getNot().getSchema()).getType()).isEqualTo(not.getType());
        }

        @Test
        void mapItems() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.type("array");
            ObjectSchema item = new ObjectSchema();
            item.setType("string");
            schema.setItems(item);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat((componentSchema.getItems().getSchema()).getType()).isEqualTo(item.getType());
        }

        @Test
        void mapUniqueItems() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setUniqueItems(false);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getUniqueItems()).isEqualTo(schema.getUniqueItems());
        }

        @Test
        void mapMinItems() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMinItems(1);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getMinItems()).isEqualTo(schema.getMinItems());
        }

        @Test
        void mapMaxItems() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMaxItems(10);

            // when
            SchemaObject componentSchema = swaggerSchemaUtil.mapSchema(schema);

            // then
            assertThat(componentSchema.getMaxItems()).isEqualTo(schema.getMaxItems());
        }
    }
}
