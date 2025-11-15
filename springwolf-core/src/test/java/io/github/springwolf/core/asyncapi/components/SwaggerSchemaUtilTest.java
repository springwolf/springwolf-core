// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaFormat;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.configuration.properties.PayloadSchemaFormat;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.media.Discriminator;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerSchemaUtilTest {
    private final SwaggerSchemaUtil swaggerSchemaUtil = new SwaggerSchemaUtil();

    @Nested
    class MapSchemaOrRef {

        @Test
        void mapToOpenApiV3Schema() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setType(SchemaType.STRING);
            ExternalDocumentation externalDocs = new ExternalDocumentation();
            externalDocs.setDescription("description");
            externalDocs.setUrl("url");
            schema.setExternalDocs(externalDocs);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchemaOrRef(schema, PayloadSchemaFormat.OPENAPI_V3);

            // then
            // componentSchema should contain a MultiFormatSchema with the original openapi schema
            MultiFormatSchema multiFormatSchema = componentSchema.getMultiFormatSchema();
            assertThat(multiFormatSchema).isNotNull();
            assertThat(multiFormatSchema.getSchema()).isSameAs(schema);
            assertThat(multiFormatSchema.getSchemaFormat()).isEqualTo(SchemaFormat.OPENAPI_V3.value);
        }

        @Test
        void mapToOpenApiV31Schema() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setType(SchemaType.STRING);
            ExternalDocumentation externalDocs = new ExternalDocumentation();
            externalDocs.setDescription("description");
            externalDocs.setUrl("url");
            schema.setExternalDocs(externalDocs);

            // when
            ComponentSchema componentSchema =
                    swaggerSchemaUtil.mapSchemaOrRef(schema, PayloadSchemaFormat.OPENAPI_V3_1);

            // then
            // componentSchema should contain a MultiFormatSchema with the original openapi schema
            MultiFormatSchema multiFormatSchema = componentSchema.getMultiFormatSchema();
            assertThat(multiFormatSchema).isNotNull();
            assertThat(multiFormatSchema.getSchema()).isSameAs(schema);
            assertThat(multiFormatSchema.getSchemaFormat()).isEqualTo(SchemaFormat.OPENAPI_V3_1.value);
        }

        @Test
        void mapReference() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.set$ref("#/components/schemas/MySchema");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchemaOrRef(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getReference()).isEqualTo(new SchemaReference("#/components/schemas/MySchema"));
        }

        @Test
        void mapSchema() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setType(SchemaType.STRING);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchemaOrRef(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getType()).containsExactly(SchemaType.STRING);
        }
    }

    @Nested
    class MapSchema {

        @Test
        void mapToOpenApiV3Schema() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setType(SchemaType.STRING);
            ExternalDocumentation externalDocs = new ExternalDocumentation();
            externalDocs.setDescription("description");
            externalDocs.setUrl("url");
            schema.setExternalDocs(externalDocs);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.OPENAPI_V3);

            // then
            // componentSchema should contain a MultiFormatSchema with the original openapi schema
            MultiFormatSchema multiFormatSchema = componentSchema.getMultiFormatSchema();
            assertThat(multiFormatSchema).isNotNull();
            assertThat(multiFormatSchema.getSchema()).isSameAs(schema);
            assertThat(multiFormatSchema.getSchemaFormat()).isEqualTo(SchemaFormat.OPENAPI_V3.value);
        }

        @Test
        void mapToOpenApiV31Schema() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setType(SchemaType.STRING);
            ExternalDocumentation externalDocs = new ExternalDocumentation();
            externalDocs.setDescription("description");
            externalDocs.setUrl("url");
            schema.setExternalDocs(externalDocs);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.OPENAPI_V3_1);

            // then
            // componentSchema should contain a MultiFormatSchema with the original openapi schema
            MultiFormatSchema multiFormatSchema = componentSchema.getMultiFormatSchema();
            assertThat(multiFormatSchema).isNotNull();
            assertThat(multiFormatSchema.getSchema()).isSameAs(schema);
            assertThat(multiFormatSchema.getSchemaFormat()).isEqualTo(SchemaFormat.OPENAPI_V3_1.value);
        }

        @Test
        void mapExternalDocs() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ExternalDocumentation externalDocs = new ExternalDocumentation();
            externalDocs.setDescription("description");
            externalDocs.setUrl("url");
            schema.setExternalDocs(externalDocs);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getExternalDocs().getDescription())
                    .isEqualTo(externalDocs.getDescription());
            assertThat(componentSchema.getSchema().getExternalDocs().getUrl()).isEqualTo(externalDocs.getUrl());
        }

        @Test
        void mapDeprecated() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setDeprecated(true);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getDeprecated()).isEqualTo(true);
        }

        @Test
        void mapTitle() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setTitle("title");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getTitle()).isEqualTo(schema.getTitle());
        }

        @Test
        void mapType() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setType(SchemaType.STRING);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getType()).containsExactly(schema.getType());
        }

        @Test
        void mapProperties() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema property = new ObjectSchema();
            property.setType(SchemaType.STRING);
            schema.addProperty("property", property);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(((ComponentSchema)
                                    componentSchema.getSchema().getProperties().get("property"))
                            .getSchema()
                            .getType())
                    .containsExactly(property.getType());
        }

        @Test
        void mapDescription() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setDescription("description");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getDescription()).isEqualTo(schema.getDescription());
        }

        @Test
        void mapFormat() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setFormat("format");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getFormat()).isEqualTo(schema.getFormat());
        }

        @Test
        void mapPattern() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setPattern("pattern");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getPattern()).isEqualTo(schema.getPattern());
        }

        @Test
        void mapExclusiveMinimum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMinimum(BigDecimal.ONE);
            schema.setExclusiveMinimum(true);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getExclusiveMinimum()).isEqualTo(schema.getMinimum());
            assertThat(componentSchema.getSchema().getMinimum()).isNull();
        }

        @Test
        void mapExclusiveMinimumValue() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setExclusiveMinimumValue(BigDecimal.ONE);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getExclusiveMinimum()).isEqualTo(schema.getExclusiveMinimumValue());
            assertThat(componentSchema.getSchema().getMinimum()).isNull();
        }

        @Test
        void mapExclusiveMaximum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMaximum(BigDecimal.ONE);
            schema.setExclusiveMaximum(true);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getExclusiveMaximum()).isEqualTo(schema.getMaximum());
            assertThat(componentSchema.getSchema().getMaximum()).isNull();
        }

        @Test
        void mapExclusiveMaximumValue() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setExclusiveMaximumValue(BigDecimal.ONE);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getExclusiveMaximum()).isEqualTo(schema.getExclusiveMaximumValue());
            assertThat(componentSchema.getSchema().getMaximum()).isNull();
        }

        @Test
        void mapMinimum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMinimum(BigDecimal.ONE);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getMinimum()).isEqualTo(schema.getMinimum());
            assertThat(componentSchema.getSchema().getExclusiveMinimum()).isNull();
        }

        @Test
        void mapMaximum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMaximum(BigDecimal.ONE);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getMaximum()).isEqualTo(schema.getMaximum());
            assertThat(componentSchema.getSchema().getExclusiveMaximum()).isNull();
        }

        @Test
        void mapMultipleOf() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMultipleOf(BigDecimal.ONE);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getMultipleOf()).isEqualTo(schema.getMultipleOf());
        }

        @Test
        void mapMinLength() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMinLength(1);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getMinLength()).isEqualTo(schema.getMinLength());
        }

        @Test
        void mapMaxLength() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMaxLength(1);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getMaxLength()).isEqualTo(schema.getMaxLength());
        }

        @Test
        void mapEnum() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setEnum(List.of("enum1", "enum2"));

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getEnumValues()).isEqualTo(schema.getEnum());
        }

        @Test
        void mapExample() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setExample("example");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getExamples()).isEqualTo(List.of(schema.getExample()));
        }

        @Test
        void mapAdditionalProperties() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema additionalProperties = new ObjectSchema();
            additionalProperties.setType(SchemaType.STRING);
            schema.setAdditionalProperties(additionalProperties);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema
                            .getSchema()
                            .getAdditionalProperties()
                            .getSchema()
                            .getType())
                    .containsExactly(additionalProperties.getType());
        }

        @Test
        void mapRequired() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setRequired(List.of("required"));

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            assertThat(componentSchema.getSchema().getRequired()).isEqualTo(schema.getRequired());
        }

        @Test
        void mapDiscriminator() {
            // given
            ObjectSchema schema = new ObjectSchema();
            Discriminator discriminator = new Discriminator();
            discriminator.setPropertyName("name");
            schema.setDiscriminator(discriminator);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();

            assertThat(componentSchema.getSchema().getDiscriminator()).isEqualTo("name");
        }

        @Test
        void mapAllOf() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema allOf = new ObjectSchema();
            allOf.setType(SchemaType.STRING);
            schema.addAllOfItem(allOf);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();

            assertThat((componentSchema.getSchema().getAllOf().get(0).getSchema()).getType())
                    .containsExactly(allOf.getType());
        }

        @Test
        void mapOneOf() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema oneOf = new ObjectSchema();
            oneOf.setType(SchemaType.STRING);
            schema.addOneOfItem(oneOf);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();

            assertThat((componentSchema.getSchema().getOneOf().get(0).getSchema()).getType())
                    .containsExactly(oneOf.getType());
        }

        @Test
        void mapAnyOf() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema anyOf = new ObjectSchema();
            anyOf.setType(SchemaType.STRING);
            schema.addAnyOfItem(anyOf);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();
            assertThat((componentSchema.getSchema().getAnyOf().get(0).getSchema()).getType())
                    .containsExactly(anyOf.getType());
        }

        @Test
        void mapConst() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setConst("const");

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();
            assertThat(componentSchema.getSchema().getConstValue()).isEqualTo(schema.getConst());
        }

        @Test
        void mapNot() {
            // given
            ObjectSchema schema = new ObjectSchema();
            ObjectSchema not = new ObjectSchema();
            not.setType(SchemaType.STRING);
            schema.setNot(not);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();
            assertThat((componentSchema.getSchema().getNot().getSchema()).getType())
                    .containsExactly(not.getType());
        }

        @Test
        void mapItems() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.type(SchemaType.ARRAY);
            ObjectSchema item = new ObjectSchema();
            item.setType(SchemaType.STRING);
            schema.setItems(item);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();
            assertThat((componentSchema.getSchema().getItems().getSchema()).getType())
                    .containsExactly(item.getType());
        }

        @Test
        void mapUniqueItems() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setUniqueItems(false);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();
            assertThat(componentSchema.getSchema().getUniqueItems()).isEqualTo(schema.getUniqueItems());
        }

        @Test
        void mapMinItems() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMinItems(1);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();
            assertThat(componentSchema.getSchema().getMinItems()).isEqualTo(schema.getMinItems());
        }

        @Test
        void mapMaxItems() {
            // given
            ObjectSchema schema = new ObjectSchema();
            schema.setMaxItems(10);

            // when
            ComponentSchema componentSchema = swaggerSchemaUtil.mapSchema(schema, PayloadSchemaFormat.ASYNCAPI_V3);

            // then
            // ensure that componentSchema contains an AsnycApi SchemaObjekt.
            assertThat(componentSchema.getSchema()).isNotNull();
            assertThat(componentSchema.getSchema().getMaxItems()).isEqualTo(schema.getMaxItems());
        }
    }

    @Nested
    class MapToSwagger {
        @Test
        void mapDescription() {
            // given
            SchemaObject schema = new SchemaObject();
            schema.setDescription("description");

            // when
            Schema<?> swaggerSchema = swaggerSchemaUtil.mapToSwagger(schema);

            // then
            assertThat(swaggerSchema.getDescription()).isEqualTo(schema.getDescription());
        }

        @Test
        void mapExamples() {
            // given
            SchemaObject schema = new SchemaObject();
            schema.setExamples(List.of("example1", "example2"));

            // when
            Schema<?> swaggerSchema = swaggerSchemaUtil.mapToSwagger(schema);

            // then
            assertThat(swaggerSchema.getExamples()).isEqualTo(schema.getExamples());
        }

        @Test
        void mapEnum() {
            // given
            SchemaObject schema = new SchemaObject();
            schema.setEnumValues(List.of("enum1", "enum2"));

            // when
            Schema<?> swaggerSchema = swaggerSchemaUtil.mapToSwagger(schema);

            // then
            assertThat(swaggerSchema.getEnum()).isEqualTo(schema.getEnumValues());
        }

        @Test
        void mapNullableEnum() {
            // given
            SchemaObject schema = new SchemaObject();
            schema.setEnumValues(Stream.of("enum1", "enum2", null).toList());
            schema.setTypes(Set.of(SchemaType.STRING, SchemaType.NULL)); // nullable

            // when
            Schema<?> swaggerSchema = swaggerSchemaUtil.mapToSwagger(schema);

            // then
            assertThat(swaggerSchema.getEnum()).isEqualTo(schema.getEnumValues());
            assertThat(swaggerSchema.getTypes()).isEqualTo(schema.getType());
        }

        @Test
        void mapType() {
            // given
            SchemaObject schema = new SchemaObject();
            schema.setType(SchemaType.STRING);

            // when
            Schema<?> swaggerSchema = swaggerSchemaUtil.mapToSwagger(schema);

            // then
            assertThat(swaggerSchema.getType()).isEqualTo(SchemaType.STRING);
        }
    }
}
