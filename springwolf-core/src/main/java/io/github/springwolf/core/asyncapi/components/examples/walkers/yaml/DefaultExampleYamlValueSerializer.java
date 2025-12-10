// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.yaml;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.ObjectMapperFactory;

public class DefaultExampleYamlValueSerializer implements ExampleYamlValueSerializer {

    private final ObjectMapper yamlMapper = ObjectMapperFactory.createYaml31();

    @Override
    public String writeDocumentAsYamlString(JsonNode node) throws JacksonException {
        return yamlMapper.writeValueAsString(node);
    }
}
