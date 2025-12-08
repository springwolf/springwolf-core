// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.yaml;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;

public interface ExampleYamlValueSerializer {

    String writeDocumentAsYamlString(JsonNode node) throws JacksonException;
}
