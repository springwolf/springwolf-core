// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Yaml;
import jakarta.annotation.PostConstruct;

// FIXME: Replace this class by the AsyncAPI 'DefaultAsyncApiSerializerService'
public class DefaultAsyncApiSerializerService implements AsyncApiSerializerService {

    private ObjectMapper jsonMapper = Json.mapper();
    private ObjectMapper yamlMapper = Yaml.mapper();
    private PrettyPrinter printer = new CustomPrettyPrinter();

    @PostConstruct
    void postConstruct() {
        jsonMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        yamlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        ((YAMLFactory) yamlMapper.getFactory()).enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR);
    }

    @Override
    public String toJsonString(AsyncAPI asyncAPI) throws JsonProcessingException {
        return jsonMapper.writer(printer).writeValueAsString(asyncAPI);
    }

    @Override
    public String toYaml(AsyncAPI asyncAPI) throws JsonProcessingException {
        return yamlMapper.writer(printer).writeValueAsString(asyncAPI);
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

    /**
     * Allows to customize the used objectMapper
     * <p>
     * Use {@link #getJsonObjectMapper()} as a starting point
     */
    public void setJsonObjectMapper(ObjectMapper mapper) {
        jsonMapper = mapper;
    }

    /**
     * Allows to customize the used objectMapper
     * <p>
     * Use {@link #getJsonObjectMapper()} as a starting point
     */
    public void setYamlObjectMapper(ObjectMapper mapper) {
        yamlMapper = mapper;
    }

    /**
     * Allows to override the used PrettyPrinter
     */
    public void setPrettyPrinter(PrettyPrinter prettyPrinter) {
        printer = prettyPrinter;
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
