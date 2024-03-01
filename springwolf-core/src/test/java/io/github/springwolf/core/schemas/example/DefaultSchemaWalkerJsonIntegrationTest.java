// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.core.asyncapi.components.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.EmailSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultSchemaWalkerJsonIntegrationTest {

    private final ExampleJsonValueGenerator exampleJsonValueGenerator = new ExampleJsonValueGenerator();
    private final DefaultSchemaWalker<JsonNode, JsonNode> jsonSchemaWalker =
            new DefaultSchemaWalker<>(exampleJsonValueGenerator);

    private final ObjectMapper jsonMapper = Json.mapper();

    @Nested
    class CanHandle {
        @Test
        void shouldHandleApplicationJsonContentType() {
            // when
            boolean canHandle = jsonSchemaWalker.canHandle("application/json");

            // then
            assertThat(canHandle).isTrue();
        }

        @Test
        void shouldNotHandleOtherContentType() {
            // when
            boolean canHandle = jsonSchemaWalker.canHandle("some-content-type");

            // then
            assertThat(canHandle).isFalse();
        }
    }

    @Nested
    class FromSchema {

        @Test
        void build() throws JsonProcessingException {
            StringSchema schema = new StringSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"string\"");
        }

        @Test
        void failWhenMissingDefinition() {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");
            compositeSchema.addProperty("f", referenceSchema);

            Object result = jsonSchemaWalker.fromSchema(compositeSchema, emptyMap());
            assertThat(result).isNull();
        }
    }

    @Nested
    class TestSimpleSchema {
        @Test
        void type_boolean() throws JsonProcessingException {
            BooleanSchema schema = new BooleanSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("true");
        }

        @Test
        void type_boolean_example_set() throws JsonProcessingException {
            BooleanSchema schema = new BooleanSchema();
            schema.setExample(Boolean.FALSE);

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("false");
        }

        @Test
        void type_integer() throws JsonProcessingException {
            IntegerSchema schema = new IntegerSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("0");
        }

        @Test
        void type_integer_example_set() throws JsonProcessingException {
            IntegerSchema schema = new IntegerSchema();
            schema.setExample(Integer.parseInt("123"));

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("123");
        }

        @Test
        void type_integer_format_long() throws JsonProcessingException {
            IntegerSchema schema = new IntegerSchema();
            schema.setFormat("int64");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("0");
        }

        @Test
        void type_number_format_float() throws JsonProcessingException {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("float");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("1.1");
        }

        @Test
        void type_number_format_double() throws JsonProcessingException {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("double");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("1.1");
        }

        @Test
        void type_number_example_set() throws JsonProcessingException {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setExample(new BigDecimal("123.45"));

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("123.45");
        }

        @Test
        void type_string() throws JsonProcessingException {
            StringSchema schema = new StringSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"string\"");
        }

        @Test
        void type_string_example_set() throws JsonProcessingException {
            StringSchema schema = new StringSchema();
            schema.setExample("custom-example-value");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"custom-example-value\"");
        }

        @Test
        void type_string_from_enum() throws JsonProcessingException {
            StringSchema schema = new StringSchema();
            schema.addEnumItem("EnumItem1");
            schema.addEnumItem("EnumItem2");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"EnumItem1\"");
        }

        @Test
        void type_string_format_byte() throws JsonProcessingException {
            StringSchema schema = new StringSchema();
            schema.setFormat("byte");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"YmFzZTY0LWV4YW1wbGU=\"");
        }

        @Test
        void type_string_format_binary() throws JsonProcessingException {
            BinarySchema schema = new BinarySchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString)
                    .isEqualTo(
                            "\"0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001\"");
        }

        @Test
        void type_string_format_date() throws JsonProcessingException {
            DateSchema schema = new DateSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"2015-07-20\"");
        }

        @Test
        void type_string_format_datetime() throws JsonProcessingException {
            DateTimeSchema schema = new DateTimeSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"2015-07-20T15:49:04-07:00\"");
        }

        @Test
        void type_string_format_email() throws JsonProcessingException {
            EmailSchema schema = new EmailSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"example@example.com\"");
        }

        @Test
        void type_string_format_password() throws JsonProcessingException {
            PasswordSchema schema = new PasswordSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"string-password\"");
        }

        @Test
        void type_string_format_uuid() throws JsonProcessingException {
            UUIDSchema schema = new UUIDSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"");
        }

        @Test
        void type_string_format_unknown() throws JsonProcessingException {
            StringSchema schema = new StringSchema();
            schema.setFormat("unknown");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"unknown string schema format: unknown\"");
        }

        @Test
        void type_unknown_schema() throws JsonProcessingException {
            class TestSchema extends Schema<StringSchema> {
                TestSchema() {
                    super("test-schema", (String) null);
                }
            }

            TestSchema schema = new TestSchema();

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"unknown schema type: test-schema\"");
        }

        @Test
        void type_primitive_array() throws JsonProcessingException {
            ArraySchema schema = new ArraySchema();
            schema.setItems(new StringSchema());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("[\"string\"]");
        }
    }

    @Nested
    class TestObjectSchema {
        @Test
        void type_object_array() throws JsonProcessingException {
            ObjectSchema itemSchema = new ObjectSchema();
            itemSchema.addProperty("s", new StringSchema());
            itemSchema.addProperty("b", new BooleanSchema());

            ArraySchema schema = new ArraySchema();
            schema.setItems(itemSchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("[{\"b\":true,\"s\":\"string\"}]");
        }

        @Test
        void composite_object_without_references() throws JsonProcessingException {
            ObjectSchema schema = new ObjectSchema();
            schema.addProperty("s", new StringSchema());
            schema.addProperty("b", new BooleanSchema());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"b\":true,\"s\":\"string\"}");
        }

        @Test
        void composite_object_with_references() throws JsonProcessingException {
            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");

            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.addProperty("s", new StringSchema());
            compositeSchema.addProperty("f", referenceSchema);

            ObjectSchema nestedSchema = new ObjectSchema();
            nestedSchema.addProperty("s", new StringSchema());
            nestedSchema.addProperty("b", new BooleanSchema());
            JsonNode actual = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", nestedSchema));
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"f\":{\"b\":true,\"s\":\"string\"},\"s\":\"string\"}");
        }

        @Test
        void object_with_anyOf() throws JsonProcessingException {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAnyOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("anyOfField", propertySchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"anyOfField\":\"string\"}");
        }

        @Test
        void object_with_oneOf() throws JsonProcessingException {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema propertySchema = new ObjectSchema();
            propertySchema.setOneOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("oneOfField", propertySchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"oneOfField\":\"string\"}");
        }

        @Test
        void object_with_allOf() throws JsonProcessingException {
            ObjectSchema compositeSchema = new ObjectSchema();

            ObjectSchema schema1 = new ObjectSchema();
            schema1.setProperties(Map.of("field1", new StringSchema()));
            ObjectSchema schema2 = new ObjectSchema();
            schema2.setProperties(Map.of("field2", new NumberSchema()));
            StringSchema skippedSchemaSinceObjectIsRequired = new StringSchema();

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAllOf(List.of(schema1, schema2, skippedSchemaSinceObjectIsRequired));
            compositeSchema.addProperty("allOfField", propertySchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"allOfField\":{\"field1\":\"string\",\"field2\":1.1}}");
        }

        @Test
        void schema_with_problematic_object_toString_example() throws JsonProcessingException {
            ObjectSchema schema = new ObjectSchema();
            schema.setExample(new ClassWithToString());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, Map.of());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString)
                    .isEqualTo("\"Text with special character /\\\\\\\"\\\\'\\\\b\\\\f\\\\t\\\\r\\\\n.\"");
        }

        class ClassWithToString {
            @Override
            public String toString() {
                return "Text with special character /\\\"\\'\\b\\f\\t\\r\\n.";
            }
        }
    }
}
