package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

class SwaggerInflectorJsonGeneratorTest {
    private final SwaggerInflectorJsonGenerator generator = new SwaggerInflectorJsonGenerator();

    @Nested
    class FromSchema {

        @Test
        void build() {
            StringSchema schema = new StringSchema();

            Object actual = generator.fromSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("string");
        }

        @Test
        void failWhenMissingDefinition() {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");
            compositeSchema.addProperty("f", referenceSchema);

            Object result = generator.fromSchema(compositeSchema, emptyMap());
            assertThat(result.toString()).isEqualTo("{}");
        }
    }

    @Nested
    class BuildSchema {
        @Test
        void type_boolean() throws JsonProcessingException {
            BooleanSchema schema = new BooleanSchema();

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("true");
        }

        @Test
        void type_boolean_example_set() throws JsonProcessingException {
            BooleanSchema schema = new BooleanSchema();
            schema.setExample(Boolean.FALSE);

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("false");
        }

        @Test
        void type_integer() throws JsonProcessingException {
            IntegerSchema schema = new IntegerSchema();

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("0");
        }

        @Test
        void type_integer_example_set() throws JsonProcessingException {
            IntegerSchema schema = new IntegerSchema();
            schema.setExample(Integer.parseInt("123"));

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("123");
        }

        @Test
        void type_integer_format_long() throws JsonProcessingException {
            IntegerSchema schema = new IntegerSchema();
            schema.setFormat("int64");

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("0");
        }

        @Test
        void type_number_format_float() throws JsonProcessingException {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("float");

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("1.1");
        }

        @Test
        void type_number_format_double() throws JsonProcessingException {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setFormat("double");

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("1.100000023841858");
        }

        @Test
        void type_number_example_set() throws JsonProcessingException {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setExample(new BigDecimal("123.45"));

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("123.45");
        }

        @Test
        void type_string() throws JsonProcessingException {
            StringSchema schema = new StringSchema();

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"string\"");
        }

        @Test
        void type_string_example_set() throws JsonProcessingException {
            StringSchema schema = new StringSchema();
            schema.setExample("custom-example-value");

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"custom-example-value\"");
        }

        @Test
        void type_string_from_enum() throws JsonProcessingException {
            StringSchema schema = new StringSchema();
            schema.addEnumItem("EnumItem1");
            schema.addEnumItem("EnumItem2");

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"EnumItem1\"");
        }

        @Test
        void type_string_format_byte() throws JsonProcessingException {
            StringSchema schema = new StringSchema();
            schema.setFormat("byte");

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"string\"");
        }

        @Test
        void type_string_format_binary() throws JsonProcessingException {
            BinarySchema schema = new BinarySchema();

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("null");
        }

        @Test
        void type_string_format_date() throws JsonProcessingException {
            DateSchema schema = new DateSchema();

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"2015-07-20\"");
        }

        @Test
        void type_string_format_datetime() throws JsonProcessingException {
            DateTimeSchema schema = new DateTimeSchema();

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"2015-07-20T15:49:04-07:00\"");
        }

        @Test
        void type_string_format_password() throws JsonProcessingException {
            PasswordSchema schema = new PasswordSchema();

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("\"string\"");
        }

        @Test
        void type_primitive_array() throws JsonProcessingException {
            ArraySchema schema = new ArraySchema();
            schema.setItems(new StringSchema());

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("[\"string\"]");
        }

        @Test
        void type_object_array() throws JsonProcessingException {
            ObjectSchema itemSchema = new ObjectSchema();
            itemSchema.addProperty("s", new StringSchema());
            itemSchema.addProperty("b", new BooleanSchema());

            ArraySchema schema = new ArraySchema();
            schema.setItems(itemSchema);

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("[{\"s\":\"string\",\"b\":true}]");
        }

        @Test
        void composite_object_without_references() throws JsonProcessingException {
            ObjectSchema schema = new ObjectSchema();
            schema.addProperty("s", new StringSchema());
            schema.addProperty("b", new BooleanSchema());

            String actual = generator.buildSchema(schema, emptyMap());

            assertThat(actual).isEqualTo("{\"s\":\"string\",\"b\":true}");
        }

        @Test
        void composite_object_with_references() throws JsonProcessingException {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.addProperty("s", new StringSchema());

            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");
            compositeSchema.addProperty("f", referenceSchema);

            ObjectSchema nestedSchema = new ObjectSchema();
            nestedSchema.addProperty("s", new StringSchema());
            nestedSchema.addProperty("b", new BooleanSchema());
            String actual = generator.buildSchema(compositeSchema, Map.of("Nested", nestedSchema));

            assertThat(actual).isEqualTo("{\"f\": {\"b\": true,\"s\": \"string\"},\"s\": \"string\"}");
        }

        @Test
        void object_with_anyOf() {
            ObjectSchema compositeSchema = new ObjectSchema();

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAnyOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("anyOfField", propertySchema);

            String actual = ExampleJsonGenerator.buildSchema(compositeSchema, Map.of("Nested", propertySchema));

            assertThat(actual).isEqualTo("{\"anyOfField\": {}}");
        }

        @Test
        void schema_with_problematic_object_toString_example() throws JsonProcessingException {
            ObjectSchema schema = new ObjectSchema();
            schema.setExample(new ClassWithToString());

            String actual = generator.buildSchema(schema, Map.of());
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
