// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.components.examples.walkers.yaml.DefaultExampleYamlValueSerializer;
import io.github.springwolf.core.asyncapi.components.examples.walkers.yaml.ExampleYamlValueGenerator;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
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

class DefaultSchemaWalkerYamlIntegrationTest {

    private final SpringwolfConfigProperties springwolfConfigProperties = new SpringwolfConfigProperties();
    private final ExampleYamlValueGenerator exampleYamlValueGenerator = new ExampleYamlValueGenerator(
            new ExampleJsonValueGenerator(),
            new DefaultExampleYamlValueSerializer(springwolfConfigProperties),
            springwolfConfigProperties);
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
        void build(TestInfo testInfo) {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "string"
                    """);
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
        void type_boolean(TestInfo testInfo) {
            BooleanSchema schema = new BooleanSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    true
                    """);
        }

        @Test
        void type_boolean_default_set(TestInfo testInfo) {
            BooleanSchema schema = new BooleanSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setDefault(Boolean.FALSE);

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    false
                    """);
        }

        @Test
        void type_boolean_example_set(TestInfo testInfo) {
            BooleanSchema schema = new BooleanSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample(Boolean.FALSE);

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    false
                    """);
        }

        @Test
        void type_integer(TestInfo testInfo) {
            IntegerSchema schema = new IntegerSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    0
                    """);
        }

        @Test
        void type_integer_default_set(TestInfo testInfo) {
            IntegerSchema schema = new IntegerSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setDefault(Integer.parseInt("123"));

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    123
                    """);
        }

        @Test
        void type_integer_example_set(TestInfo testInfo) {
            IntegerSchema schema = new IntegerSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample(Integer.parseInt("123"));

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    123
                    """);
        }

        @Test
        void type_integer_format_long(TestInfo testInfo) {
            IntegerSchema schema = new IntegerSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("int64");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    0
                    """);
        }

        @Test
        void type_number_format_float(TestInfo testInfo) {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("float");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    1.1
                    """);
        }

        @Test
        void type_number_format_double(TestInfo testInfo) {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("double");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    1.1
                    """);
        }

        @Test
        void type_number_default_set(TestInfo testInfo) {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setDefault(new BigDecimal("123.45"));

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    123.45
                    """);
        }

        @Test
        void type_number_example_set(TestInfo testInfo) {
            Schema<BigDecimal> schema = new NumberSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample(new BigDecimal("123.45"));

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    123.45
                    """);
        }

        @Test
        void type_string(TestInfo testInfo) {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "string"
                    """);
        }

        @Test
        void type_string_default_set(TestInfo testInfo) {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setDefault("custom-example-value");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "custom-example-value"
                    """);
        }

        @Test
        void type_string_example_set(TestInfo testInfo) {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample("custom-example-value");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "custom-example-value"
                    """);
        }

        @Test
        void type_string_from_enum(TestInfo testInfo) {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.addEnumItem("EnumItem1");
            schema.addEnumItem("EnumItem2");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "EnumItem1"
                    """);
        }

        @Test
        void type_string_from_enum_as_ref() {
            StringSchema schema = new StringSchema();
            schema.setName(null);
            schema.addEnumItem("EnumItem1");
            schema.addEnumItem("EnumItem2");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "EnumItem1"
                    """);
        }

        @Test
        void type_string_format_byte(TestInfo testInfo) {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("byte");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "YmFzZTY0LWV4YW1wbGU="
                    """);
        }

        @Test
        void type_string_format_binary(TestInfo testInfo) {
            BinarySchema schema = new BinarySchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo(
                            """
                            "0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001"
                            """);
        }

        @Test
        void type_string_format_date(TestInfo testInfo) {
            DateSchema schema = new DateSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "2015-07-20"
                    """);
        }

        @Test
        void type_string_format_datetime(TestInfo testInfo) {
            DateTimeSchema schema = new DateTimeSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    "2015-07-20T15:49:04-07:00"
                    """);
        }

        @Test
        void type_string_format_email(TestInfo testInfo) {
            EmailSchema schema = new EmailSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "example@example.com"
                    """);
        }

        @Test
        void type_string_format_password(TestInfo testInfo) {
            PasswordSchema schema = new PasswordSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    "string-password"
                    """);
        }

        @Test
        void type_string_format_uuid(TestInfo testInfo) {
            UUIDSchema schema = new UUIDSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                    """);
        }

        @Test
        void type_string_format_unknown(TestInfo testInfo) {
            StringSchema schema = new StringSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setFormat("unknown");

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    "unknown string schema format: unknown"
                    """);
        }

        @Test
        void type_unknown_schema(TestInfo testInfo) {
            class TestSchema extends Schema<StringSchema> {
                TestSchema() {
                    super("test-schema", null);
                }
            }

            TestSchema schema = new TestSchema();
            schema.setName(testInfo.getDisplayName());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    "unknown schema type: test-schema"
                    """);
        }

        @Test
        void type_primitive_array(TestInfo testInfo) {
            ArraySchema schema = new ArraySchema();
            schema.setName(testInfo.getDisplayName());
            schema.setItems(new StringSchema());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString).isEqualTo("""
                    - "string"
                    """);
        }
    }

    @Nested
    class TestObjectSchema {
        @Test
        void type_object_array(TestInfo testInfo) {
            ObjectSchema itemSchema = new ObjectSchema();
            itemSchema.addProperty("s", new StringSchema());
            itemSchema.addProperty("b", new BooleanSchema());

            ArraySchema schema = new ArraySchema();
            schema.setName(testInfo.getDisplayName());
            schema.setItems(itemSchema);

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo(
                            """
                    - b: true
                      s: "string"
                    """);
        }

        @Test
        void composite_object_without_references(TestInfo testInfo) {
            ObjectSchema schema = new ObjectSchema();
            schema.setName(testInfo.getDisplayName());
            schema.addProperty("s", new StringSchema());
            schema.addProperty("b", new BooleanSchema());

            String actualString = jsonSchemaWalker.fromSchema(schema, emptyMap());

            assertThat(actualString)
                    .isEqualTo("""
                    b: true
                    s: "string"
                    """);
        }

        @Test
        void composite_object_with_references(TestInfo testInfo) {
            Schema referenceSchema = new Schema();
            referenceSchema.set$ref("#/components/schemas/Nested");

            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName(testInfo.getDisplayName());
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
                      s: "string"
                    s: "string"
                    """);
        }

        @Test
        void object_with_anyOf(TestInfo testInfo) {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName(testInfo.getDisplayName());

            Schema propertySchema = new ObjectSchema();
            propertySchema.setAnyOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("anyOfField", propertySchema);

            String actualString = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));

            assertThat(actualString).isEqualTo("""
                    anyOfField: "string"
                    """);
        }

        @Test
        void object_with_oneOf(TestInfo testInfo) {
            ObjectSchema compositeSchema = new ObjectSchema();
            compositeSchema.setName(testInfo.getDisplayName());

            Schema propertySchema = new ObjectSchema();
            propertySchema.setOneOf(List.of(new StringSchema(), new NumberSchema()));
            compositeSchema.addProperty("oneOfField", propertySchema);

            String actualString = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));

            assertThat(actualString).isEqualTo("""
                    oneOfField: "string"
                    """);
        }

        @Test
        void object_with_allOf(TestInfo testInfo) {
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

            String actualString = jsonSchemaWalker.fromSchema(compositeSchema, Map.of("Nested", propertySchema));

            assertThat(actualString)
                    .isEqualTo(
                            """
                    allOfField:
                      field1: "string"
                      field2: 1.1
                    """);
        }

        @Test
        void object_with_map(TestInfo testInfo) {
            MapSchema mapSchema = new MapSchema();
            mapSchema.setName(testInfo.getDisplayName());

            Schema propertySchema = new StringSchema();
            mapSchema.setAdditionalProperties(propertySchema);

            String actualString = jsonSchemaWalker.fromSchema(mapSchema, Map.of());

            assertThat(actualString).isEqualTo("key: \"string\"\n");
        }

        @Test
        void object_with_map_and_set(TestInfo testInfo) {
            // Example: Map<String, Set<String>>
            Schema<?> objectSchema = new ObjectSchema();
            objectSchema.setName("objectName");
            objectSchema.setProperties(Map.of("field1", new StringSchema()));

            Schema<?> arraySchema = new ArraySchema();
            arraySchema.setItems(objectSchema);

            MapSchema mapSchema = new MapSchema();
            mapSchema.setName(testInfo.getDisplayName());
            mapSchema.setAdditionalProperties(arraySchema);

            String actualString = jsonSchemaWalker.fromSchema(mapSchema, Map.of());

            assertThat(actualString).isEqualTo("key:\n- field1: \"string\"\n");
        }

        @Test
        void schema_with_problematic_object_toString_example(TestInfo testInfo) {
            ObjectSchema schema = new ObjectSchema();
            schema.setName(testInfo.getDisplayName());
            schema.setExample(new ClassWithToString());

            String actualString = jsonSchemaWalker.fromSchema(schema, Map.of());

            assertThat(actualString)
                    .isEqualTo(
                            """
                            "Text with special character /\\\\\\\\'\\\\b\\\\f\\\\t\\\\r\\\\n."
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
