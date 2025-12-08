// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.yaml;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.dataformat.yaml.YAMLMapper;

public class DefaultExampleYamlValueSerializer implements ExampleYamlValueSerializer {

    private final YAMLMapper yamlMapper = YAMLMapper.builder().build();

    @Override
    public String writeDocumentAsYamlString(JsonNode node) throws JacksonException {
        return yamlMapper.writeValueAsString(node);
    }
}
