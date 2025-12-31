// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.schema;

import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class SchemaTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializePrimitiveSample() throws Exception {
        var schema = SchemaObject.builder()
                .type(Set.of(SchemaType.STRING))
                .format("email")
                .build();

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
    void shouldSerializeSimpleModel() throws Exception {
        var schema = SchemaObject.builder()
                .type(Set.of(SchemaType.OBJECT))
                .required(List.of("name"))
                .properties(Map.of(
                        "name",
                                SchemaObject.builder()
                                        .type(Set.of(SchemaType.STRING))
                                        .build(),
                        "address", SchemaReference.toSchema("Address"),
                        "age",
                                SchemaObject.builder()
                                        .type(Set.of(SchemaType.INTEGER))
                                        .format("int32")
                                        .minimum(BigDecimal.ZERO)
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
    void shouldSerializeStringToStringMapping() throws Exception {
        var schema = SchemaObject.builder()
                .type(Set.of(SchemaType.OBJECT))
                .additionalProperties(ComponentSchema.of(
                        SchemaObject.builder().type(Set.of(SchemaType.STRING)).build()))
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
    void shouldSerializeModelMapping() throws Exception {
        var schema = SchemaObject.builder()
                .type(Set.of(SchemaType.OBJECT))
                .additionalProperties(ComponentSchema.of(SchemaReference.toSchema("ComplexModel")))
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
    void shouldSerializeModelWithExample() throws Exception {
        var schema = SchemaObject.builder()
                .type(Set.of(SchemaType.OBJECT))
                .properties(Map.of(
                        "id",
                                SchemaObject.builder()
                                        .type(Set.of(SchemaType.INTEGER))
                                        .format("int64")
                                        .build(),
                        "name",
                                SchemaObject.builder()
                                        .type(Set.of(SchemaType.STRING))
                                        .build()))
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
    void shouldSerializeModelWithBooleans() throws Exception {
        var schema = SchemaObject.builder()
                .type(Set.of(SchemaType.OBJECT))
                .properties(Map.of(
                        "anySchema", true,
                        "cannotBeDefined", false))
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
    void shouldSerializeModelsWithComposition() throws Exception {
        var schemas = Map.of(
                "schemas",
                Map.of(
                        "ErrorModel",
                        SchemaObject.builder()
                                .type(Set.of(SchemaType.OBJECT))
                                .required(List.of("message", "code"))
                                .properties(Map.of(
                                        "message",
                                                SchemaObject.builder()
                                                        .type(Set.of(SchemaType.STRING))
                                                        .build(),
                                        "code",
                                                SchemaObject.builder()
                                                        .type(Set.of(SchemaType.INTEGER))
                                                        .minimum(new BigDecimal("100"))
                                                        .maximum(new BigDecimal("600"))
                                                        .build()))
                                .build(),
                        "ExtendedErrorModel",
                        SchemaObject.builder()
                                .allOf(List.of(
                                        ComponentSchema.of(SchemaReference.toSchema("ErrorModel")),
                                        ComponentSchema.of(SchemaObject.builder()
                                                .type(Set.of(SchemaType.OBJECT))
                                                .required(List.of("rootCause"))
                                                .properties(Map.of(
                                                        "rootCause",
                                                        SchemaObject.builder()
                                                                .type(Set.of(SchemaType.STRING))
                                                                .build()))
                                                .build())))
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
    void shouldSerializeModelsWithPolimorphismSupport() throws Exception {
        var schemas = Map.of(
                "schemas",
                Map.of(
                        "Pet",
                        SchemaObject.builder()
                                .type(Set.of(SchemaType.OBJECT))
                                .discriminator("petType")
                                .properties(Map.of(
                                        "name",
                                        SchemaObject.builder()
                                                .type(Set.of(SchemaType.STRING))
                                                .build(),
                                        "petType",
                                        SchemaObject.builder()
                                                .type(Set.of(SchemaType.STRING))
                                                .build()))
                                .required(List.of("name", "petType"))
                                .build(),
                        "Cat",
                        SchemaObject.builder()
                                .description(
                                        "A representation of a cat. Note that `Cat` will be used as the discriminator value.")
                                .allOf(List.of(
                                        ComponentSchema.of(SchemaReference.toSchema("Pet")),
                                        ComponentSchema.of(SchemaObject.builder()
                                                .type(Set.of(SchemaType.OBJECT))
                                                .properties(Map.of(
                                                        "huntingSkill",
                                                        SchemaObject.builder()
                                                                .type(Set.of(SchemaType.STRING))
                                                                .description("The measured skill for hunting")
                                                                .enumValues(
                                                                        List.of(
                                                                                "clueless",
                                                                                "lazy",
                                                                                "adventurous",
                                                                                "aggressive"))
                                                                .build()))
                                                .required(List.of("huntingSkill"))
                                                .build())))
                                .build(),
                        "Dog",
                        SchemaObject.builder()
                                .description(
                                        "A representation of a dog. Note that `Dog` will be used as the discriminator value.")
                                .allOf(List.of(
                                        ComponentSchema.of(SchemaReference.toSchema("Pet")),
                                        ComponentSchema.of(SchemaObject.builder()
                                                .type(Set.of(SchemaType.OBJECT))
                                                .properties(Map.of(
                                                        "packSize",
                                                        SchemaObject.builder()
                                                                .type(Set.of(SchemaType.INTEGER))
                                                                .format("int32")
                                                                .description("the size of the pack the dog is from")
                                                                .minimum(BigDecimal.ZERO)
                                                                .build()))
                                                .required(List.of("packSize"))
                                                .build())))
                                .build(),
                        "StickInsect",
                        SchemaObject.builder()
                                .description(
                                        "A representation of an Australian walking stick. Note that `StickBug` will be used as the discriminator value.")
                                .allOf(List.of(
                                        ComponentSchema.of(SchemaReference.toSchema("Pet")),
                                        ComponentSchema.of(SchemaObject.builder()
                                                .type(Set.of(SchemaType.OBJECT))
                                                .properties(Map.of(
                                                        "petType",
                                                        SchemaObject.builder()
                                                                .constValue("StickBug")
                                                                .build(),
                                                        "color",
                                                        SchemaObject.builder()
                                                                .type(Set.of(SchemaType.STRING))
                                                                .build()))
                                                .required(List.of("color"))
                                                .build())))
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
