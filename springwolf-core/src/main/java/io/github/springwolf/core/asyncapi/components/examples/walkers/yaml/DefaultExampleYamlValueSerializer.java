// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.yaml;

import io.swagger.v3.core.util.ObjectMapperFactory;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class DefaultExampleYamlValueSerializer implements ExampleYamlValueSerializer {

    private final ObjectMapper yamlMapper = ObjectMapperFactory.createYaml31();

    @Override
    public String writeDocumentAsYamlString(JsonNode node) throws JacksonException {
        return yamlMapper.writeValueAsString(node);
    }
}
