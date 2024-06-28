// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.util.Yaml;

public class DefaultExampleYamlValueSerializer implements ExampleYamlValueSerializer {

    private final ObjectMapper yamlMapper;

    public DefaultExampleYamlValueSerializer(SpringwolfConfigProperties springwolfConfigProperties) {
        this.yamlMapper = Yaml.mapper();

        if (springwolfConfigProperties.isStudioCompatibility()) {
            // AsyncApi Studio has problems with missing quotes on dates, so we add quotes on every example
            // see https://github.com/springwolf/springwolf-core/issues/820
            ((YAMLFactory) yamlMapper.getFactory()).disable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
        }
    }

    @Override
    public String writeDocumentAsYamlString(JsonNode node) throws JsonProcessingException {
        return yamlMapper.writeValueAsString(node);
    }
}
