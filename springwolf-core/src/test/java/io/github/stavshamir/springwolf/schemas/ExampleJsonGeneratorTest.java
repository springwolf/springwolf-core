package io.github.stavshamir.springwolf.schemas;

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
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

class ExampleJsonGeneratorTest {

    @Test
    void type_boolean() {
        BooleanSchema schema = new BooleanSchema();

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("true");
    }

    @Test
    void type_boolean_example_set() {
        BooleanSchema schema = new BooleanSchema();
        schema.setExample(Boolean.FALSE);

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("false");
    }

    @Test
    void type_integer() {
        IntegerSchema schema = new IntegerSchema();

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("0");
    }

    @Test
    void type_integer_example_set() {
        IntegerSchema schema = new IntegerSchema();
        schema.setExample(Integer.parseInt("123"));

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("123");
    }

    @Test
    void type_integer_format_long() {
        IntegerSchema schema = new IntegerSchema();
        schema.setFormat("int64");

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("0");
    }

    @Test
    void type_number_format_float() {
        Schema<BigDecimal> schema = new NumberSchema();
        schema.setFormat("float");

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("1.1");
    }

    @Test
    void type_number_format_double() {
        Schema<BigDecimal> schema = new NumberSchema();
        schema.setFormat("double");

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("1.1");
    }

    @Test
    void type_number_example_set() {
        Schema<BigDecimal> schema = new NumberSchema();
        schema.setExample(new BigDecimal("123.45"));

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("123.45");
    }

    @Test
    void type_string() {
        StringSchema schema = new StringSchema();

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("\"string\"");
    }

    @Test
    void type_string_example_set() {
        StringSchema schema = new StringSchema();
        schema.setExample("custom-example-value");

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("\"custom-example-value\"");
    }

    @Test
    void type_string_from_enum() {
        StringSchema schema = new StringSchema();
        schema.addEnumItem("EnumItem1");
        schema.addEnumItem("EnumItem2");

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("\"EnumItem1\"");
    }

    @Test
    void type_string_format_byte() {
        StringSchema schema = new StringSchema();
        schema.setFormat("byte");

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("\"YmFzZTY0LWV4YW1wbGU=\"");
    }

    @Test
    void type_string_format_binary() {
        BinarySchema schema = new BinarySchema();

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("\"0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001\"");
    }

    @Test
    void type_string_format_date() {
        DateSchema schema = new DateSchema();

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("\"2006-01-02\"");
    }

    @Test
    void type_string_format_datetime() {
        DateTimeSchema schema = new DateTimeSchema();

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("\"2006-01-02T15:04:05Z07:00\"");
    }

    @Test
    void type_string_format_password() {
        PasswordSchema schema = new PasswordSchema();

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("\"string-password\"");
    }

    @Test
    void type_primitive_array() {
        ArraySchema schema = new ArraySchema();
        schema.setItems(new StringSchema());

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("[\"string\"]");
    }

    @Test
    void type_object_array() {
        ObjectSchema itemSchema = new ObjectSchema();
        itemSchema.addProperty("s", new StringSchema());
        itemSchema.addProperty("b", new BooleanSchema());

        ArraySchema schema = new ArraySchema();
        schema.setItems(itemSchema);

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("[{\"b\": true,\"s\": \"string\"}]");
    }

    @Test
    void composite_object_without_references() {
        ObjectSchema schema = new ObjectSchema();
        schema.addProperty("s", new StringSchema());
        schema.addProperty("b", new BooleanSchema());

        String actual = ExampleJsonGenerator.fromSchema(schema, emptyMap());

        assertThat(actual).isEqualTo("{\"b\": true,\"s\": \"string\"}");
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
        String actual = ExampleJsonGenerator.fromSchema(compositeSchema, Map.of("Nested", nestedSchema));

        assertThat(actual).isEqualTo("{\"f\": {\"b\": true,\"s\": \"string\"},\"s\": \"string\"}");
    }
}
