// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.fixtures.JsonMapperTestConfiguration;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.EmailSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultSchemaWalkerJsonIntegrationTest {

    private final ExampleJsonValueGenerator exampleJsonValueGenerator = new ExampleJsonValueGenerator();
    private final DefaultSchemaWalker<JsonNode, JsonNode> jsonSchemaWalker =
            new DefaultSchemaWalker<>(exampleJsonValueGenerator);

    private static final JsonMapper jsonMapper = JsonMapperTestConfiguration.jsonMapper;

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
        void build(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"string\"");
        }

        @Test
        void failWhenMissingDefinition(TestInfo testInfo) {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName(testInfo.getDisplayName());

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
        void type_boolean(TestInfo testInfo) throws Exception {
            BooleanSchema schema = new BooleanSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("true");
        }

        @Test
        void type_boolean_default_set(TestInfo testInfo) throws Exception {
            BooleanSchema schema = new BooleanSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setDefault(Boolean.FALSE);

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("false");
        }

        @Test
        void type_boolean_example_set(TestInfo testInfo) throws Exception {
            BooleanSchema schema = new BooleanSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample(Boolean.FALSE);

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("false");
        }

        @Test
        void type_integer(TestInfo testInfo) throws Exception {
            IntegerSchema schema = new IntegerSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("0");
        }

        @Test
        void type_integer_default_set(TestInfo testInfo) throws Exception {
            IntegerSchema schema = new IntegerSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setDefault(Integer.parseInt("123"));

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("123");
        }

        @Test
        void type_integer_example_set(TestInfo testInfo) throws Exception {
            IntegerSchema schema = new IntegerSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample(Integer.parseInt("123"));

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("123");
        }

        @Test
        void type_integer_format_long(TestInfo testInfo) throws Exception {
            IntegerSchema schema = new IntegerSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("int64");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("0");
        }

        @Test
        void type_number_format_float(TestInfo testInfo) throws Exception {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("float");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("1.1");
        }

        @Test
        void type_number_format_double(TestInfo testInfo) throws Exception {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("double");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("1.1");
        }

        @Test
        void type_number_default_set(TestInfo testInfo) throws Exception {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setDefault(new BigDecimal("123.45"));

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("123.45");
        }

        @Test
        void type_number_example_set(TestInfo testInfo) throws Exception {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample(new BigDecimal("123.45"));

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("123.45");
        }

        @Test
        void type_string(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"string\"");
        }

        @Test
        void type_string_default_set(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setDefault("custom-example-value");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"custom-example-value\"");
        }

        @Test
        void type_string_example_set(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample("custom-example-value");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"custom-example-value\"");
        }

        @Test
        void type_string_from_enum(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.addEnumItem("EnumItem1");
            schema.addEnumItem("EnumItem2");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"EnumItem1\"");
        }

        @Test
        void type_string_format_byte(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("byte");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"YmFzZTY0LWV4YW1wbGU=\"");
        }

        @Test
        void type_string_format_binary(TestInfo testInfo) throws Exception {
            BinarySchema schema = new BinarySchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString)
                    .isEqualTo(
                            "\"0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001\"");
        }

        @Test
        void type_string_format_date(TestInfo testInfo) throws Exception {
            DateSchema schema = new DateSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"2015-07-20\"");
        }

        @Test
        void type_string_format_datetime(TestInfo testInfo) throws Exception {
            DateTimeSchema schema = new DateTimeSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"2015-07-20T15:49:04-07:00\"");
        }

        @Test
        void type_string_format_email(TestInfo testInfo) throws Exception {
            EmailSchema schema = new EmailSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"example@example.com\"");
        }

        @Test
        void type_string_format_password(TestInfo testInfo) throws Exception {
            PasswordSchema schema = new PasswordSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"string-password\"");
        }

        @Test
        void type_string_format_uuid(TestInfo testInfo) throws Exception {
            UUIDSchema schema = new UUIDSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"");
        }

        @Test
        void type_string_format_int32(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("int32");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"0\"");
        }

        @Test
        void type_string_format_int64(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("int32");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"0\"");
        }

        @Test
        void type_string_format_float(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("float");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"1.1\"");
        }

        @Test
        void type_string_format_double(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("double");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"1.1\"");
        }

        @Test
        void type_string_format_unknown(TestInfo testInfo) throws Exception {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("unknown");

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"unknown string schema format: unknown\"");
        }

        @Test
        void type_unknown_schema(TestInfo testInfo) throws Exception {
            class TestSchema extends Schema<StringSchema> {
                TestSchema() {
                    super("test-schema", null);
                }
            }

            TestSchema schema = new TestSchema();
            schema.setName(testInfo.getDisplayName());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("\"unknown schema type: test-schema\"");
        }

        @Test
        void type_primitive_array(TestInfo testInfo) throws Exception {
            ArraySchema schema = new ArraySchema();
            schema.setName(testInfo.getDisplayName());
            schema.setItems(new StringSchema());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("[\"string\"]");
        }
    }

    @Nested
    class TestObjectSchema {
        @Test
        void type_object_array(TestInfo testInfo) throws Exception {
            ObjectSchema itemSchema = new ObjectSchema();
            itemSchema.addProperty("s", new StringSchema());
            itemSchema.addProperty("b", new BooleanSchema());

            ArraySchema schema = new ArraySchema();
            schema.setName(testInfo.getDisplayName());
            schema.setItems(itemSchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("[{\"b\":true,\"s\":\"string\"}]");
        }

        @Test
        void composite_object_without_references(TestInfo testInfo) throws Exception {
            ObjectSchema schema = new ObjectSchema();
            schema.setName(testInfo.getDisplayName());
            schema.addProperty("s", new StringSchema());
            schema.addProperty("b", new BooleanSchema());

            JsonNode actual = jsonSchemaWalker.fromSchema(schema, emptyMap());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"b\":true,\"s\":\"string\"}");
        }

        @Test
        void composite_object_with_references(TestInfo testInfo) throws Exception {
            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");

            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName(testInfo.getDisplayName());
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
        void object_with_anyOf(TestInfo testInfo) throws Exception {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName(testInfo.getDisplayName());

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAnyOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("anyOfField", propertySchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"anyOfField\":\"string\"}");
        }

        @Test
        void object_with_oneOf(TestInfo testInfo) throws Exception {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName(testInfo.getDisplayName());

            Schema propertySchema = new ObjectSchema();
            propertySchema.setOneOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("oneOfField", propertySchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"oneOfField\":\"string\"}");
        }

        @Test
        void object_with_allOf(TestInfo testInfo) throws Exception {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName(testInfo.getDisplayName());

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
        void object_with_map(TestInfo testInfo) throws Exception {
            MapSchema mapSchema = new MapSchema();
            mapSchema.setName(testInfo.getDisplayName());

            Schema propertySchema = new StringSchema();
            mapSchema.setAdditionalProperties(propertySchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(mapSchema, Map.of());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"key\":\"string\"}");
        }

        @Test
        void object_with_map_and_set(TestInfo testInfo) throws Exception {
            // Example: Map<String, Set<String>>
            Schema<?> objectSchema = new ObjectSchema();
            objectSchema.setName("objectName");
            objectSchema.setProperties(Map.of("field1", new StringSchema()));

            Schema<?> arraySchema = new ArraySchema();
            arraySchema.setItems(objectSchema);

            MapSchema mapSchema = new MapSchema();
            mapSchema.setName(testInfo.getDisplayName());
            mapSchema.setAdditionalProperties(arraySchema);

            JsonNode actual = jsonSchemaWalker.fromSchema(mapSchema, Map.of());
            String actualString = jsonMapper.writeValueAsString(actual);

            assertThat(actualString).isEqualTo("{\"key\":[{\"field1\":\"string\"}]}");
        }

        @Test
        void schema_with_problematic_object_toString_example(TestInfo testInfo) throws Exception {
            ObjectSchema schema = new ObjectSchema();
            schema.setName(testInfo.getDisplayName());
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
