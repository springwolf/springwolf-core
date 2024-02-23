// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.addons.json_schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import io.github.springwolf.core.schemas.SwaggerSchemaUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.EmailSchema;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JsonSchemaGeneratorTest {
    private final ObjectMapper mapper = Json.mapper();
    private final SwaggerSchemaUtil swaggerSchemaUtil = new SwaggerSchemaUtil();
    private final JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(mapper);

    @ParameterizedTest
    @MethodSource
    public void validateJsonSchemaTest(String expectedJsonSchema, Supplier<Schema<?>> asyncApiSchema)
            throws IOException {
        // given
        SchemaObject actualSchema = swaggerSchemaUtil.mapSchema(asyncApiSchema.get());

        // when
        verifyValidJsonSchema(expectedJsonSchema);

        // ref cycle ping -> pingField -> pong -> pongField -> ping (repeat)
        SchemaObject pingSchema = new SchemaObject();
        pingSchema.setType("object");
        pingSchema.setProperties(Map.of("pingfield", ComponentSchema.of(MessageReference.toSchema("PongSchema"))));
        SchemaObject pongSchema = new SchemaObject();
        pongSchema.setType("object");
        pongSchema.setProperties(Map.of("pongField", ComponentSchema.of(MessageReference.toSchema("PingSchema"))));

        SchemaObject stringSchema = new SchemaObject();
        stringSchema.setType("string");

        Map<String, SchemaObject> definitions =
                Map.of("StringRef", stringSchema, "PingSchema", pingSchema, "PongSchema", pongSchema);

        // when
        Object jsonSchema = jsonSchemaGenerator.fromSchema(actualSchema, definitions);

        // then
        String jsonSchemaString = mapper.writeValueAsString(jsonSchema);
        verifyValidJsonSchema(jsonSchemaString);

        assertThat(jsonSchemaString).isEqualToIgnoringWhitespace(expectedJsonSchema);
    }

    public static Stream<Arguments> validateJsonSchemaTest() {
        return Stream.of(
                // types
                Arguments.of(
                        "{\"type\":\"number\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) NumberSchema::new),
                Arguments.of(
                        "{\"type\":\"string\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) StringSchema::new),
                Arguments.of(
                        "{\"type\":\"array\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) ArraySchema::new),
                Arguments.of(
                        "{\"type\":\"boolean\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) BooleanSchema::new),
                Arguments.of(
                        "{\"format\":\"email\",\"type\":\"string\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) EmailSchema::new),
                Arguments.of(
                        "{\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) MapSchema::new),
                Arguments.of(
                        "{\"properties\": { \"id\": {\"type\": \"number\"}},\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema schema = new ObjectSchema();
                            schema.setProperties(Map.of("id", new NumberSchema()));
                            return schema;
                        }),
                // fields
                Arguments.of(
                        "{\"anyOf\": [{\"type\": \"number\"}],\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema schema = new ObjectSchema();
                            schema.setAnyOf(List.of(new NumberSchema()));
                            return schema;
                        }),
                Arguments.of(
                        "{\"allOf\": [{\"type\": \"number\"}],\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema schema = new ObjectSchema();
                            schema.setAllOf(List.of(new NumberSchema()));
                            return schema;
                        }),
                Arguments.of(
                        "{\"const\": \"test\",\"type\":\"string\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            StringSchema schema = new StringSchema();
                            schema.setConst("test");
                            return schema;
                        }),
                Arguments.of(
                        "{\"description\": \"test\",\"type\":\"string\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            StringSchema schema = new StringSchema();
                            schema.setDescription("test");
                            return schema;
                        }),
                Arguments.of(
                        "{\"enum\": [\"test\", \"value2\"],\"type\":\"string\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            StringSchema schema = new StringSchema();
                            schema.setEnum(List.of("test", "value2"));
                            return schema;
                        }),
                Arguments.of(
                        "{\"enum\": [\"test\", \"value2\"],\"type\":\"string\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            StringSchema schema = new StringSchema();
                            schema.setEnum(List.of("test", "value2"));
                            schema.setNullable(true);
                            return schema;
                        }),
                Arguments.of(
                        "{\"exclusiveMinimum\": 1,\"exclusiveMaximum\": 10,\"type\":\"number\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            NumberSchema schema = new NumberSchema();
                            schema.setExclusiveMinimumValue(new BigDecimal(1));
                            schema.setExclusiveMaximumValue(new BigDecimal(10));
                            return schema;
                        }),
                Arguments.of(
                        "{\"format\": \"test\",\"type\":\"string\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            StringSchema schema = new StringSchema();
                            schema.setFormat("test");
                            return schema;
                        }),
                Arguments.of(
                        "{\"items\": {\"type\":\"number\"},\"type\":\"array\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ArraySchema schema = new ArraySchema();
                            schema.setItems(new NumberSchema());
                            return schema;
                        }),
                Arguments.of(
                        "{\"maximum\": 10,\"minimum\": 1,\"type\":\"number\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            NumberSchema schema = new NumberSchema();
                            schema.setMaximum(new BigDecimal(10));
                            schema.setMinimum(new BigDecimal(1));
                            return schema;
                        }),
                Arguments.of(
                        "{\"maxItems\": 10,\"minItems\": 1,\"type\":\"array\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ArraySchema schema = new ArraySchema();
                            schema.setMaxItems(10);
                            schema.setMinItems(1);
                            return schema;
                        }),
                Arguments.of(
                        "{\"maxLength\": 10,\"minLength\": 1,\"type\":\"string\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            StringSchema schema = new StringSchema();
                            schema.setMaxLength(10);
                            schema.setMinLength(1);
                            return schema;
                        }),
                Arguments.of(
                        "{\"multipleOf\": 10,\"type\":\"number\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            NumberSchema schema = new NumberSchema();
                            schema.setMultipleOf(new BigDecimal(10));
                            return schema;
                        }),
                Arguments.of(
                        "{\"not\": {\"type\":\"number\"},\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema schema = new ObjectSchema();
                            schema.setNot(new NumberSchema());
                            return schema;
                        }),
                Arguments.of(
                        "{\"oneOf\": [{\"type\": \"number\"}],\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema schema = new ObjectSchema();
                            schema.setOneOf(List.of(new NumberSchema()));
                            return schema;
                        }),
                Arguments.of(
                        "{\"pattern\":\"test\",\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema schema = new ObjectSchema();
                            schema.setPattern("test");
                            return schema;
                        }),
                Arguments.of(
                        "{\"properties\": {\"field1\": {\"type\": \"number\"}, \"field2\": {\"type\": \"string\"}}, \"required\":[\"field1\"],\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema schema = new ObjectSchema();
                            schema.setProperties(
                                    new TreeMap(Map.of("field1", new NumberSchema(), "field2", new StringSchema())));
                            schema.setRequired(List.of("field1"));
                            return schema;
                        }),
                Arguments.of(
                        "{\"title\": \"test\",\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema schema = new ObjectSchema();
                            schema.setTitle("test");
                            return schema;
                        }),
                Arguments.of(
                        "{\"type\":\"array\",\"uniqueItems\": true,\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ArraySchema schema = new ArraySchema();
                            schema.setUniqueItems(true);
                            return schema;
                        }),
                // ref
                Arguments.of(
                        "{\"properties\":{\"field\": {\"type\":\"string\"}}, \"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema refField = new ObjectSchema();
                            refField.set$ref("StringRef");

                            ObjectSchema schema = new ObjectSchema();
                            schema.setProperties(Map.of("field", refField));
                            return schema;
                        }),
                Arguments.of(
                        "{\"properties\":{\"field\":{\"properties\":{\"pingfield\":{\"properties\":{\"pongField\":{}},\"type\":\"object\"}},\"type\":\"object\"}},\"type\":\"object\",\"$schema\":\"https://json-schema.org/draft-04/schema#\"}",
                        (Supplier<Schema>) () -> {
                            ObjectSchema refField = new ObjectSchema();
                            refField.set$ref("PingSchema");

                            ObjectSchema schema = new ObjectSchema();
                            schema.setProperties(Map.of("field", refField));
                            return schema;
                        })
                //
                );
    }

    private void verifyValidJsonSchema(String content) throws JsonProcessingException {
        JsonNode jsonNode = mapper.readTree(content);
        JsonSchemaFactory.getInstance(SpecVersionDetector.detect(jsonNode));
    }
}
