// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.schemas.example;

import com.fasterxml.jackson.databind.JsonNode;
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

class DefaultSchemaWalkerYamlIntegrationTest {

    private final ExampleYamlValueGenerator exampleYamlValueGenerator =
            new ExampleYamlValueGenerator(new ExampleJsonValueGenerator(), new DefaultExampleYamlValueSerializer());
    private final DefaultSchemaWalker<JsonNode, String> jsonSchemaWalker =
            new DefaultSchemaWalker<>(exampleYamlValueGenerator);

    @Nested
    class CanHandle {
        @Test
        void shouldHandleApplicationJsonContentType() {
            // when
            boolean canHandle = jsonSchemaWalker.canHandle("application/yaml");

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
        void build() {
            StringSchema schema = new StringSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    string
                    """);
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
        void type_boolean() {
            BooleanSchema schema = new BooleanSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    true
                    """);
        }

        @Test
        void type_boolean_example_set() {
            BooleanSchema schema = new BooleanSchema();
            schema.setExample(Boolean.FALSE);

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    false
                    """);
        }

        @Test
        void type_integer() {
            IntegerSchema schema = new IntegerSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    0
                    """);
        }

        @Test
        void type_integer_example_set() {
            IntegerSchema schema = new IntegerSchema();
            schema.setExample(Integer.parseInt("123"));

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    123
                    """);
        }

        @Test
        void type_integer_format_long() {
            IntegerSchema schema = new IntegerSchema();
            schema.setFormat("int64");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    0
                    """);
        }

        @Test
        void type_number_format_float() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("float");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    1.1
                    """);
        }

        @Test
        void type_number_format_double() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("double");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    1.1
                    """);
        }

        @Test
        void type_number_example_set() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setExample(new BigDecimal("123.45"));

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    123.45
                    """);
        }

        @Test
        void type_string() {
            StringSchema schema = new StringSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    string
                    """);
        }

        @Test
        void type_string_example_set() {
            StringSchema schema = new StringSchema();
            schema.setExample("custom-example-value");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    custom-example-value
                    """);
        }

        @Test
        void type_string_from_enum() {
            StringSchema schema = new StringSchema();
            schema.addEnumItem("EnumItem1");
            schema.addEnumItem("EnumItem2");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    EnumItem1
                    """);
        }

        @Test
        void type_string_format_byte() {
            StringSchema schema = new StringSchema();
            schema.setFormat("byte");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    YmFzZTY0LWV4YW1wbGU=
                    """);
        }

        @Test
        void type_string_format_binary() {
            BinarySchema schema = new BinarySchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo(
                            """
                            "0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001"
                            """);
        }

        @Test
        void type_string_format_date() {
            DateSchema schema = new DateSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    2015-07-20
                    """);
        }

        @Test
        void type_string_format_datetime() {
            DateTimeSchema schema = new DateTimeSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    2015-07-20T15:49:04-07:00
                    """);
        }

        @Test
        void type_string_format_email() {
            EmailSchema schema = new EmailSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    example@example.com
                    """);
        }

        @Test
        void type_string_format_password() {
            PasswordSchema schema = new PasswordSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    string-password
                    """);
        }

        @Test
        void type_string_format_uuid() {
            UUIDSchema schema = new UUIDSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    3fa85f64-5717-4562-b3fc-2c963f66afa6
                    """);
        }

        @Test
        void type_string_format_unknown() {
            StringSchema schema = new StringSchema();
            schema.setFormat("unknown");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    "unknown string schema format: unknown"
                    """);
        }

        @Test
        void type_unknown_schema() {
            class TestSchema extends Schema<StringSchema> {
                TestSchema() {
                    super("test-schema", null);
                }
            }

            TestSchema schema = new TestSchema();

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    "unknown schema type: test-schema"
                    """);
        }

        @Test
        void type_primitive_array() {
            ArraySchema schema = new ArraySchema();
            schema.setItems(new StringSchema());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    - string
                    """);
        }
    }

    @Nested
    class TestObjectSchema {
        @Test
        void type_object_array() {
            ObjectSchema itemSchema = new ObjectSchema();
            itemSchema.addProperty("s", new StringSchema());
            itemSchema.addProperty("b", new BooleanSchema());

            ArraySchema schema = new ArraySchema();
            schema.setItems(itemSchema);

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo(
                            """
                    - b: true
                      s: string
                    """);
        }

        @Test
        void composite_object_without_references() {
            ObjectSchema schema = new ObjectSchema();
            schema.addProperty("s", new StringSchema());
            schema.addProperty("b", new BooleanSchema());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    b: true
                    s: string
                    """);
        }

        @Test
        void composite_object_with_references() {
            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");

            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.addProperty("s", new StringSchema());
            compositeSchema.addProperty("f", referenceSchema);

            ObjectSchema nestedSchema = new ObjectSchema();
            nestedSchema.addProperty("s", new StringSchema());
            nestedSchema.addProperty("b", new BooleanSchema());
            String actualString = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", nestedSchema));

            assertThat(actualString)
                    .isEqualTo(
                            """
                    f:
                      b: true
                      s: string
                    s: string
                    """);
        }

        @Test
        void object_with_anyOf() {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAnyOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("anyOfField", propertySchema);

            String actualString = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));

            assertThat(actualString).isEqualTo("""
                    anyOfField: string
                    """);
        }

        @Test
        void object_with_oneOf() {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema propertySchema = new ObjectSchema();
            propertySchema.setOneOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("oneOfField", propertySchema);

            String actualString = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));

            assertThat(actualString).isEqualTo("""
                    oneOfField: string
                    """);
        }

        @Test
        void object_with_allOf() {
            ObjectSchema compositeSchema = new ObjectSchema();

            ObjectSchema schema1 = new ObjectSchema();
            schema1.setProperties(Map.of("field1", new StringSchema()));
            ObjectSchema schema2 = new ObjectSchema();
            schema2.setProperties(Map.of("field2", new NumberSchema()));
            StringSchema skippedSchemaSinceObjectIsRequired = new StringSchema();

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAllOf(List.of(schema1, schema2, skippedSchemaSinceObjectIsRequired));
            compositeSchema.addProperty("allOfField", propertySchema);

            String actualString = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));

            assertThat(actualString)
                    .isEqualTo(
                            """
                    allOfField:
                      field1: string
                      field2: 1.1
                    """);
        }

        @Test
        void schema_with_problematic_object_toString_example() {
            ObjectSchema schema = new ObjectSchema();
            schema.setExample(new ClassWithToString());

            String actualString = jsonSchemaWalker.fromSchema(schema, Map.of());

            assertThat(actualString)
                    .isEqualTo(
                            """
                            Text with special character /\\\\'\\b\\f\\t\\r\\n.
                            """);
        }

        class ClassWithToString {
            @Override
            public String toString() {
                return "Text with special character /\\\\'\\b\\f\\t\\r\\n.";
            }
        }
    }
}
