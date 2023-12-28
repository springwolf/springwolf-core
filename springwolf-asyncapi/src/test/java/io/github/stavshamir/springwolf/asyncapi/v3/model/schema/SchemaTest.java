// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class SchemaTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializePrimitiveSample() throws JsonProcessingException {
        var schema = Schema.builder().type("string").format("email").build();

        String example =
                """
                {
                  "type": "string",
                  "format": "email"
                }
                """;
        assertThatJson(serializer.toJsonString(schema)).isEqualTo(example);
    }

    @Test
    void shouldSerializeSimpleModel() throws JsonProcessingException {
        var schema = Schema.builder()
                .type("object")
                .required(List.of("name"))
                .properties(Map.of(
                        "name", Schema.builder().type("string").build(),
                        "address",
                                Schema.builder()
                                        .ref("#/components/schemas/Address")
                                        .build(),
                        "age",
                                Schema.builder()
                                        .type("integer")
                                        .format("int32")
                                        .minimum(0)
                                        .build()))
                .build();

        String example =
                """
                {
                  "type": "object",
                  "required": [
                    "name"
                  ],
                  "properties": {
                    "name": {
                      "type": "string"
                    },
                    "address": {
                      "$ref": "#/components/schemas/Address"
                    },
                    "age": {
                      "type": "integer",
                      "format": "int32",
                      "minimum": 0
                    }
                  }
                }
                """;
        assertThatJson(serializer.toJsonString(schema)).isEqualTo(example);
    }

    @Test
    void shouldSerializeStringToStringMapping() throws JsonProcessingException {
        var schema = Schema.builder()
                .type("object")
                .additionalProperties(Schema.builder().type("string").build())
                .build();

        var example =
                """
                {
                  "type": "object",
                  "additionalProperties": {
                    "type": "string"
                  }
                }
                """;
        assertThatJson(serializer.toJsonString(schema)).isEqualTo(example);
    }

    @Test
    void shouldSerializeModelMapping() throws JsonProcessingException {
        var schema = Schema.builder()
                .type("object")
                .additionalProperties(Schema.builder()
                        .ref("#/components/schemas/ComplexModel")
                        .build())
                .build();

        var example =
                """
                {
                  "type": "object",
                  "additionalProperties": {
                    "$ref": "#/components/schemas/ComplexModel"
                  }
                }
                """;
        assertThatJson(serializer.toJsonString(schema)).isEqualTo(example);
    }

    @Test
    void shouldSerializeModelWithExample() throws JsonProcessingException {
        var schema = Schema.builder()
                .type("object")
                .properties(Map.of(
                        "id", Schema.builder().type("integer").format("int64").build(),
                        "name", Schema.builder().type("string").build()))
                .required(List.of("name"))
                .examples(List.of(Map.of("name", "Puma", "id", 1)))
                .build();

        var example =
                """
                {
                  "type": "object",
                  "properties": {
                    "id": {
                      "type": "integer",
                      "format": "int64"
                    },
                    "name": {
                      "type": "string"
                    }
                  },
                  "required": [
                    "name"
                  ],
                  "examples": [
                    {
                      "name": "Puma",
                      "id": 1
                    }
                  ]
                }
                """;
        assertThatJson(serializer.toJsonString(schema)).isEqualTo(example);
    }

    @Test
    @Disabled("MODEL WITH BOOLEAN SCHEMAS is not supported yet")
    // FIXME: See https://www.asyncapi.com/docs/reference/specification/v3.0.0#schemaObject
    void shouldSerializeModelWithBooleans() throws JsonProcessingException {
        var schema = Schema.builder()
                .type("object")
                //                .properties(Map.of(
                //                        "anySchema", true,
                //                        "cannotBeDefined", false))
                .required(List.of("anySchema"))
                .build();

        var example =
                """
                {
                  "type": "object",
                  "required": [
                    "anySchema"
                  ],
                  "properties": {
                    "anySchema": true,
                    "cannotBeDefined": false
                  }
                }
                """;
        assertThatJson(serializer.toJsonString(schema)).isEqualTo(example);
    }

    @Test
    void shouldSerializeModelsWithComposition() throws JsonProcessingException {
        var schemas = Map.of(
                "schemas",
                Map.of(
                        "ErrorModel",
                        Schema.builder()
                                .type("object")
                                .required(List.of("message", "code"))
                                .properties(Map.of(
                                        "message",
                                                Schema.builder().type("string").build(),
                                        "code",
                                                Schema.builder()
                                                        .type("integer")
                                                        .minimum(100)
                                                        .maximum(600)
                                                        .build()))
                                .build(),
                        "ExtendedErrorModel",
                        Schema.builder()
                                .allOf(List.of(
                                        Schema.builder()
                                                .ref("#/components/schemas/ErrorModel")
                                                .build(),
                                        Schema.builder()
                                                .type("object")
                                                .required(List.of("rootCause"))
                                                .properties(Map.of(
                                                        "rootCause",
                                                        Schema.builder()
                                                                .type("string")
                                                                .build()))
                                                .build()))
                                .build()));

        var example =
                """
                {
                  "schemas": {
                    "ErrorModel": {
                      "type": "object",
                      "required": [
                        "message",
                        "code"
                      ],
                      "properties": {
                        "message": {
                          "type": "string"
                        },
                        "code": {
                          "type": "integer",
                          "minimum": 100,
                          "maximum": 600
                        }
                      }
                    },
                    "ExtendedErrorModel": {
                      "allOf": [
                        {
                          "$ref": "#/components/schemas/ErrorModel"
                        },
                        {
                          "type": "object",
                          "required": [
                            "rootCause"
                          ],
                          "properties": {
                            "rootCause": {
                              "type": "string"
                            }
                          }
                        }
                      ]
                    }
                  }
                }
                """;
        assertThatJson(serializer.toJsonString(schemas)).isEqualTo(example);
    }

    @Test
    void shouldSerializeModelsWithPolimorphismSupport() throws JsonProcessingException {
        var schemas = Map.of(
                "schemas",
                Map.of(
                        "Pet",
                        Schema.builder()
                                .type("object")
                                .discriminator("petType")
                                .properties(Map.of(
                                        "name",
                                        Schema.builder().type("string").build(),
                                        "petType",
                                        Schema.builder().type("string").build()))
                                .required(List.of("name", "petType"))
                                .build(),
                        "Cat",
                        Schema.builder()
                                .description(
                                        "A representation of a cat. Note that `Cat` will be used as the discriminator value.")
                                .allOf(List.of(
                                        Schema.builder()
                                                .ref("#/components/schemas/Pet")
                                                .build(),
                                        Schema.builder()
                                                .type("object")
                                                .properties(Map.of(
                                                        "huntingSkill",
                                                        Schema.builder()
                                                                .type("string")
                                                                .description("The measured skill for hunting")
                                                                .enumValues(
                                                                        List.of(
                                                                                "clueless",
                                                                                "lazy",
                                                                                "adventurous",
                                                                                "aggressive"))
                                                                .build()))
                                                .required(List.of("huntingSkill"))
                                                .build()))
                                .build(),
                        "Dog",
                        Schema.builder()
                                .description(
                                        "A representation of a dog. Note that `Dog` will be used as the discriminator value.")
                                .allOf(List.of(
                                        Schema.builder()
                                                .ref("#/components/schemas/Pet")
                                                .build(),
                                        Schema.builder()
                                                .type("object")
                                                .properties(Map.of(
                                                        "packSize",
                                                        Schema.builder()
                                                                .type("integer")
                                                                .format("int32")
                                                                .description("the size of the pack the dog is from")
                                                                .minimum(0)
                                                                .build()))
                                                .required(List.of("packSize"))
                                                .build()))
                                .build(),
                        "StickInsect",
                        Schema.builder()
                                .description(
                                        "A representation of an Australian walking stick. Note that `StickBug` will be used as the discriminator value.")
                                .allOf(List.of(
                                        Schema.builder()
                                                .ref("#/components/schemas/Pet")
                                                .build(),
                                        Schema.builder()
                                                .type("object")
                                                .properties(Map.of(
                                                        "petType",
                                                        Schema.builder()
                                                                .constValue("StickBug")
                                                                .build(),
                                                        "color",
                                                        Schema.builder()
                                                                .type("string")
                                                                .build()))
                                                .required(List.of("color"))
                                                .build()))
                                .build()));
        var example =
                """
                {
                  "schemas": {
                    "Pet": {
                      "type": "object",
                      "discriminator": "petType",
                      "properties": {
                        "name": {
                          "type": "string"
                        },
                        "petType": {
                          "type": "string"
                        }
                      },
                      "required": [
                        "name",
                        "petType"
                      ]
                    },
                    "Cat": {
                      "description": "A representation of a cat. Note that `Cat` will be used as the discriminator value.",
                      "allOf": [
                        {
                          "$ref": "#/components/schemas/Pet"
                        },
                        {
                          "type": "object",
                          "properties": {
                            "huntingSkill": {
                              "type": "string",
                              "description": "The measured skill for hunting",
                              "enum": [
                                "clueless",
                                "lazy",
                                "adventurous",
                                "aggressive"
                              ]
                            }
                          },
                          "required": [
                            "huntingSkill"
                          ]
                        }
                      ]
                    },
                    "Dog": {
                      "description": "A representation of a dog. Note that `Dog` will be used as the discriminator value.",
                      "allOf": [
                        {
                          "$ref": "#/components/schemas/Pet"
                        },
                        {
                          "type": "object",
                          "properties": {
                            "packSize": {
                              "type": "integer",
                              "format": "int32",
                              "description": "the size of the pack the dog is from",
                              "minimum": 0
                            }
                          },
                          "required": [
                            "packSize"
                          ]
                        }
                      ]
                    },
                    "StickInsect": {
                      "description": "A representation of an Australian walking stick. Note that `StickBug` will be used as the discriminator value.",
                      "allOf": [
                        {
                          "$ref": "#/components/schemas/Pet"
                        },
                        {
                          "type": "object",
                          "properties": {
                            "petType": {
                              "const": "StickBug"
                            },
                            "color": {
                              "type": "string"
                            }
                          },
                          "required": [
                            "color"
                          ]
                        }
                      ]
                    }
                  }
                }
                """;
        assertThatJson(serializer.toJsonString(schemas)).isEqualTo(example);
    }
}
