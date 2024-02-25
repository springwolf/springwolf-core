// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface ExampleYamlValueSerializer {

    String writeDocumentAsYamlString(JsonNode node) throws JsonProcessingException;
}
