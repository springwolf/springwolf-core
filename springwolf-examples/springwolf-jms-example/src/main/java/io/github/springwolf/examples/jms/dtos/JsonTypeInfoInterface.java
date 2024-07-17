// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonTypeInfoExampleOne.class, name = "exampleOne"),
    @JsonSubTypes.Type(value = JsonTypeInfoExampleTwo.class, name = "exampleTwo")
})
@Schema(oneOf = {JsonTypeInfoExampleOne.class, JsonTypeInfoExampleTwo.class})
public interface JsonTypeInfoInterface {}
