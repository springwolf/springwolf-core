// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.yaml.YAMLMapper;

@RequiredArgsConstructor
public class DefaultAsyncApiSerializerService implements AsyncApiSerializerService {

    private final CustomPrettyPrinter prettyPrinter = new CustomPrettyPrinter();
    /**
     * -- GETTER --
     *  Get the current JSON object mapper configuration.
     */
    @Getter
    private final JsonMapper jsonMapper = JsonMapper.builder()
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            .changeDefaultPropertyInclusion(handler ->
                    JsonInclude.Value.construct(JsonInclude.Include.NON_ABSENT, JsonInclude.Include.NON_ABSENT))
            .defaultPrettyPrinter(prettyPrinter)
            .build();
    /**
     * -- GETTER --
     *  Get the current YAML object mapper configuration.
     */
    @Getter
    private final YAMLMapper yamlMapper = YAMLMapper.builder()
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            .changeDefaultPropertyInclusion(handler ->
                    JsonInclude.Value.construct(JsonInclude.Include.NON_ABSENT, JsonInclude.Include.NON_ABSENT))
            .defaultPrettyPrinter(prettyPrinter)
            .build();

    public String toJsonString(Object object) throws JacksonException {
        return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    public String toYaml(Object object) throws JacksonException {
        return yamlMapper.writeValueAsString(object);
    }

    private static class CustomPrettyPrinter extends DefaultPrettyPrinter {
        public CustomPrettyPrinter() {
            super._arrayIndenter = new DefaultIndenter();
            //   TODO:         super._objectFieldValueSeparatorWithSpaces = _separators.getObjectFieldValueSeparator() +
            // " ";
        }

        @Override
        public CustomPrettyPrinter createInstance() {
            return new CustomPrettyPrinter();
        }
    }
}
