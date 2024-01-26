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

class DefaultSchemaWalkerXmlIntegrationTest {

    private ExampleXmlValueGenerator exampleJsonValueGenerator = new ExampleXmlValueGenerator();
    private DefaultSchemaWalker defaultSchemaWalker = new DefaultSchemaWalker(exampleJsonValueGenerator);


    @Nested
    class FromSchema {

        @Test
        void build() {
            StringSchema schema = new StringSchema();

            Object actual = defaultSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("string");
        }

        @Test
        void failWhenMissingDefinition() {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");
            compositeSchema.addProperty("f", referenceSchema);

            Object result = defaultSchemaWalker.fromSchema(compositeSchema, emptyMap());
            assertThat(result).isNull();
        }
    }

    @Nested
    class BuildSchema {
        @Test
        void type_boolean() {
            BooleanSchema schema = new BooleanSchema();
            schema.name("type_boolean");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_boolean>true</type_boolean>");
        }

        @Test
        void type_boolean_example_set() {
            BooleanSchema schema = new BooleanSchema();
            schema.setExample(Boolean.FALSE);
            schema.setName("type_boolean_example_set");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_boolean_example_set>false</type_boolean_example_set>");
        }

        @Test
        void type_integer() {
            IntegerSchema schema = new IntegerSchema();
            schema.name("type_integer");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_integer>0</type_integer>");
        }

        @Test
        void type_integer_example_set() {
            IntegerSchema schema = new IntegerSchema();
            schema.setExample(Integer.parseInt("123"));
            schema.setName("type_integer_example_set");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_integer_example_set>123</type_integer_example_set>");
        }

        @Test
        void type_integer_format_long() {
            IntegerSchema schema = new IntegerSchema();
            schema.setFormat("int64");
            schema.setName("type_integer_format_long");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_integer_format_long>0</type_integer_format_long>");
        }

        @Test
        void type_number_format_float() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("float");
            schema.name("type_number_format_float");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_number_format_float>1.1</type_number_format_float>");
        }

        @Test
        void type_number_format_double() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("double");
            schema.setName("type_number_format_double");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_number_format_double>1.1</type_number_format_double>");
        }

        @Test
        void type_number_example_set() {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setExample(new BigDecimal("123.45"));
            schema.setName("type_number_example_set");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_number_example_set>123.45</type_number_example_set>");
        }

        @Test
        void type_string() {
            StringSchema schema = new StringSchema();
            schema.name("type_string");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string>string</type_string>");
        }

        @Test
        void type_string_example_set() {
            StringSchema schema = new StringSchema();
            schema.setExample("custom-example-value");
            schema.setName("type_string_example_set");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_example_set>custom-example-value</type_string_example_set>");
        }

        @Test
        void type_string_from_enum() {
            StringSchema schema = new StringSchema();
            schema.addEnumItem("EnumItem1");
            schema.addEnumItem("EnumItem2");
            schema.setName("type_string_from_enum");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_from_enum>EnumItem1</type_string_from_enum>");
        }

        @Test
        void type_string_format_byte() {
            StringSchema schema = new StringSchema();
            schema.setFormat("byte");
            schema.setName("type_string_format_byte");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_format_byte>YmFzZTY0LWV4YW1wbGU=</type_string_format_byte>");
        }

        @Test
        void type_string_format_binary() {
            BinarySchema schema = new BinarySchema();
            schema.setName("type_string_format_binary");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_format_binary>0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001</type_string_format_binary>");
        }

        @Test
        void type_string_format_date() {
            DateSchema schema = new DateSchema();
            schema.setName("type_string_format_date");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_format_date>2015-07-20</type_string_format_date>");
        }

        @Test
        void type_string_format_datetime() {
            DateTimeSchema schema = new DateTimeSchema();
            schema.setName("type_string_format_datetime");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_format_datetime>2015-07-20T15:49:04-07:00</type_string_format_datetime>");
        }

        @Test
        void type_string_format_email() {
            EmailSchema schema = new EmailSchema();
            schema.setName("type_string_format_email");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_format_email>example@example.com</type_string_format_email>");
        }

        @Test
        void type_string_format_password() {
            PasswordSchema schema = new PasswordSchema();
            schema.setName("type_string_format_password");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_format_password>string-password</type_string_format_password>");
        }

        @Test
        void type_string_format_uuid() {
            UUIDSchema schema = new UUIDSchema();
            schema.setName("type_string_format_password");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_format_password>3fa85f64-5717-4562-b3fc-2c963f66afa6</type_string_format_password>");
        }

        @Test
        void type_string_format_unknown() {
            StringSchema schema = new StringSchema();
            schema.setFormat("unknown");
            schema.setName("type_string_format_unknown");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_string_format_unknown>unknown string schema format: unknown</type_string_format_unknown>");
        }

        @Test
        void type_unknown_schema() {
            class TestSchema extends Schema<StringSchema> {
                TestSchema() {
                    super("test-schema", (String) null);
                }
            }

            TestSchema schema = new TestSchema();
            schema.setName("type_unknown_schema");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("<type_unknown_schema>unknown schema type: test-schema</type_unknown_schema>");
        }

        @Test
        void type_primitive_array() {
            ArraySchema schema = new ArraySchema();
            schema.setItems(new StringSchema());
            schema.setName("type_primitive_array");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("[\"string\"]");
        }

        @Test
        void type_object_array() {
            ObjectSchema itemSchema = new ObjectSchema();
            itemSchema.addProperty("s", new StringSchema());
            itemSchema.addProperty("b", new BooleanSchema());

            ArraySchema schema = new ArraySchema();
            schema.setItems(itemSchema);
            schema.setName("type_object_array");

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim();

            assertThat(actual).isEqualTo("""
            <type_object_array>
                <b>true</b>
                <s>string</s>
            </type_object_array>
            """.trim().stripIndent());
        }

        @Test
        void composite_object_without_references() {
            ObjectSchema schema = new ObjectSchema();
            schema.setName("CompositeObjectWithoutReferences");
            schema.addProperty("s", new StringSchema());
            schema.addProperty("b", new BooleanSchema());

            String actual = defaultSchemaWalker.buildSchema(schema, emptyMap()).trim().stripIndent();

            assertThat(actual).isEqualTo("""            
            <CompositeObjectWithoutReferences>
                <b>true</b>
                <s>string</s>
            </CompositeObjectWithoutReferences>
            """.trim().stripIndent());
        }

        @Test
        void composite_object_with_references() {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName("composite_object_with_references_root");
            compositeSchema.addProperty("s", new StringSchema());

            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");
            compositeSchema.addProperty("f", referenceSchema);

            ObjectSchema nestedSchema = new ObjectSchema();
            nestedSchema.addProperty("s", new StringSchema());
            nestedSchema.addProperty("b", new BooleanSchema());
            String actual = defaultSchemaWalker.buildSchema(compositeSchema, Map.of("Nested", nestedSchema))
                    .trim().stripIndent();

            assertThat(actual).isEqualTo("""
            <composite_object_with_references_root>
                <f>
                    <b>true</b>
                    <s>string</s>
                </f>
                <s>string</s>
            </composite_object_with_references_root>
            """.trim().stripIndent());
        }

        @Test
        void object_with_anyOf() {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName("object_with_anyOf");

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAnyOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("anyOfField", propertySchema);

            String actual = defaultSchemaWalker.buildSchema(compositeSchema, Map.of("Nested", propertySchema)).trim();

            assertThat(actual).isEqualTo("""
            <object_with_anyOf>
                <anyOfField>string</anyOfField>
            </object_with_anyOf>
            """.trim().stripIndent()
            );
        }

        @Test
        void schema_with_problematic_object_toString_example() {
            ObjectSchema schema = new ObjectSchema();
            schema.setExample(new ClassWithToString());

            String actual = defaultSchemaWalker.buildSchema(schema, Map.of());
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
