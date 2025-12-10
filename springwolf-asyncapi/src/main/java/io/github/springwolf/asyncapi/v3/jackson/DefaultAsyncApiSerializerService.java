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
import lombok.Getter;

public class DefaultAsyncApiSerializerService implements AsyncApiSerializerService {

    @Getter
    private final ObjectMapper jsonMapper = ObjectMapperFactory.createJson31();

    @Getter
    private final ObjectMapper yamlMapper = ObjectMapperFactory.createYaml31();

    private final PrettyPrinter printer = new CustomPrettyPrinter();

    //     /**
    //     *  Get the current JSON object mapper configuration.
    //     */
    //    @Getter
    //    private final JsonMapper jsonMapper = JsonMapper.builder()
    //            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
    //            .serializationInclusion(JsonInclude.Include.NON_ABSENT)
    //            .defaultPrettyPrinter(prettyPrinter)
    //            .build();
    //    /**
    //     *  Get the current YAML object mapper configuration.
    //     */
    //    @Getter
    //    private final YAMLMapper yamlMapper = YAMLMapper.builder()
    //            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
    //            .serializationInclusion(JsonInclude.Include.NON_ABSENT)
    //            .defaultPrettyPrinter(prettyPrinter)
    //            .build();

    public DefaultAsyncApiSerializerService() {
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
