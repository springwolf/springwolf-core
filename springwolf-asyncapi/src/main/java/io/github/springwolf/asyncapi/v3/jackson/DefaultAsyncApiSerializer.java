// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import io.swagger.v3.core.util.ObjectMapperFactory;

public class DefaultAsyncApiSerializer implements AsyncApiSerializerService {

    private final ObjectMapper jsonMapper = ObjectMapperFactory.createJson31();
    private final ObjectMapper yamlMapper = ObjectMapperFactory.createYaml31();
    private final PrettyPrinter printer = new CustomPrettyPrinter();

    public DefaultAsyncApiSerializer() {
        jsonMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        yamlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        ((YAMLFactory) yamlMapper.getFactory()).enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR);
    }

    public String toJsonString(Object object) throws JsonProcessingException {
        return jsonMapper.writer(printer).writeValueAsString(object);
    }

    public String toYaml(Object object) throws JsonProcessingException {
        return yamlMapper.writer(printer).writeValueAsString(object);
    }

    /**
     * Get the current JSON object mapper configuration.
     */
    public ObjectMapper getJsonObjectMapper() {
        return jsonMapper;
    }

    /**
     * Get the current YAML object mapper configuration.
     */
    public ObjectMapper getYamlObjectMapper() {
        return yamlMapper;
    }

    private static class CustomPrettyPrinter extends DefaultPrettyPrinter {
        public CustomPrettyPrinter() {
            super._arrayIndenter = new DefaultIndenter();
            super._objectFieldValueSeparatorWithSpaces = _separators.getObjectFieldValueSeparator() + " ";
        }

        @Override
        public CustomPrettyPrinter createInstance() {
            return new CustomPrettyPrinter();
        }
    }
}
