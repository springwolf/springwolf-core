// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

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

class ExampleJsonGeneratorTest {

    @Nested
    class FromSchema {
        private final ExampleGenerator generator = new ExampleJsonGenerator();

        @Test
        void build() {
            StringSchema schema = new StringSchema();

            Object actual = generator.fromSchema(schema, emptyMap());

            assertThat(actual.toString()).isEqualTo("\"string\"");
        }

        @Test
        void failWhenMissingDefinition() {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");
            compositeSchema.addProperty("f", referenceSchema);

            Object result = generator.fromSchema(compositeSchema, emptyMap());
            assertThat(result).isNull();
        }
    }

    @Nested
    class BuildSchema {
        @Test
        void type_boolean() {
            BooleanSchema schema = new BooleanSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("true");
        }

        @Test
        void type_boolean_example_set() {
            BooleanSchema schema = new BooleanSchema();
            schema.setExample(Boolean.FALSE);

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("false");
        }

        @Test
        void type_integer() {
            IntegerSchema schema = new IntegerSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("0");
        }

        @Test
        void type_integer_example_set() {
            IntegerSchema schema = new IntegerSchema();
            schema.setExample(Integer.parseInt("123"));

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("123");
        }

        @Test
        void type_integer_format_long() {
            IntegerSchema schema = new IntegerSchema();
            schema.setFormat("int64");

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("0");
        }

        @Test
        void type_number_format_float() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("float");

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("1.1");
        }

        @Test
        void type_number_format_double() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("double");

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("1.1");
        }

        @Test
        void type_number_example_set() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setExample(new BigDecimal("123.45"));

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("123.45");
        }

        @Test
        void type_string() {
            StringSchema schema = new StringSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"string\"");
        }

        @Test
        void type_string_example_set() {
            StringSchema schema = new StringSchema();
            schema.setExample("custom-example-value");

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"custom-example-value\"");
        }

        @Test
        void type_string_from_enum() {
            StringSchema schema = new StringSchema();
            schema.addEnumItem("EnumItem1");
            schema.addEnumItem("EnumItem2");

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"EnumItem1\"");
        }

        @Test
        void type_string_format_byte() {
            StringSchema schema = new StringSchema();
            schema.setFormat("byte");

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"YmFzZTY0LWV4YW1wbGU=\"");
        }

        @Test
        void type_string_format_binary() {
            BinarySchema schema = new BinarySchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual)
                    .isEqualTo(
                            "\"0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001\"");
        }

        @Test
        void type_string_format_date() {
            DateSchema schema = new DateSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"2015-07-20\"");
        }

        @Test
        void type_string_format_datetime() {
            DateTimeSchema schema = new DateTimeSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"2015-07-20T15:49:04-07:00\"");
        }

        @Test
        void type_string_format_email() {
            EmailSchema schema = new EmailSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"example@example.com\"");
        }

        @Test
        void type_string_format_password() {
            PasswordSchema schema = new PasswordSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"string-password\"");
        }

        @Test
        void type_string_format_uuid() {
            UUIDSchema schema = new UUIDSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"");
        }

        @Test
        void type_string_format_unknown() {
            StringSchema schema = new StringSchema();
            schema.setFormat("unknown");

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"unknown string schema format: unknown\"");
        }

        @Test
        void type_unknown_schema() {
            class TestSchema extends Schema<StringSchema> {
                TestSchema() {
                    super("test-schema", (String) null);
                }
            }

            TestSchema schema = new TestSchema();

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"unknown schema type: test-schema\"");
        }

        @Test
        void type_primitive_array() {
            ArraySchema schema = new ArraySchema();
            schema.setItems(new StringSchema());

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("[\"string\"]");
        }

        @Test
        void type_object_array() {
            ObjectSchema itemSchema = new ObjectSchema();
            itemSchema.addProperty("s", new StringSchema());
            itemSchema.addProperty("b", new BooleanSchema());

            ArraySchema schema = new ArraySchema();
            schema.setItems(itemSchema);

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("[{\"b\":true,\"s\":\"string\"}]");
        }

        @Test
        void composite_object_without_references() {
            ObjectSchema schema = new ObjectSchema();
            schema.addProperty("s", new StringSchema());
            schema.addProperty("b", new BooleanSchema());

            String actual = ExampleJsonGenerator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("{\"b\":true,\"s\":\"string\"}");
        }

        @Test
        void composite_object_with_references() {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.addProperty("s", new StringSchema());

            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");
            compositeSchema.addProperty("f", referenceSchema);

            ObjectSchema nestedSchema = new ObjectSchema();
            nestedSchema.addProperty("s", new StringSchema());
            nestedSchema.addProperty("b", new BooleanSchema());
            String actual = ExampleJsonGenerator.buildSchema(compositeSchema, Map.of("Nested", nestedSchema));

            assertThat(actual).isEqualTo("{\"f\":{\"b\":true,\"s\":\"string\"},\"s\":\"string\"}");
        }

        @Test
        void object_with_anyOf() {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAnyOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("anyOfField", propertySchema);

            String actual = ExampleJsonGenerator.buildSchema(compositeSchema, Map.of("Nested", propertySchema));

            assertThat(actual).isEqualTo("{\"anyOfField\":\"string\"}");
        }

        @Test
        void schema_with_problematic_object_toString_example() {
            ObjectSchema schema = new ObjectSchema();
            schema.setExample(new ClassWithToString());

            String actual = ExampleJsonGenerator.buildSchema(schema, Map.of());
            assertThat(actual).isEqualTo("\"Text with special character /\\\\\\\"\\\\'\\\\b\\\\f\\\\t\\\\r\\\\n.\"");
        }

        class ClassWithToString {
            @Override
            public String toString() {
                return "Text with special character /\\\"\\'\\b\\f\\t\\r\\n.";
            }
        }
    }
}
