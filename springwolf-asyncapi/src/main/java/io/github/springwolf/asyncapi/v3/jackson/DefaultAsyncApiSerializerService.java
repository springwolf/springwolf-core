// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.core.util.ObjectMapperFactory;
import lombok.Getter;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.core.util.Separators;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.dataformat.yaml.YAMLWriteFeature;

public class DefaultAsyncApiSerializerService implements AsyncApiSerializerService {

    private final PrettyPrinter printer = new CustomPrettyPrinter();

    @Getter
    private final ObjectMapper jsonMapper = ObjectMapperFactory.createJson31()
            .rebuild()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_ABSENT)
                    .withContentInclusion(JsonInclude.Include.NON_ABSENT))
            .defaultPrettyPrinter(printer)
            .build();

    @Getter
    private final ObjectMapper yamlMapper = ObjectMapperFactory.createYaml31()
            .rebuild()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_ABSENT)
                    .withContentInclusion(JsonInclude.Include.NON_ABSENT))
            .defaultPrettyPrinter(printer)
            .build();

    public String toJsonString(Object object) {
        return jsonMapper.writeValueAsString(object);
    }

    public String toYaml(Object object) {
        return yamlMapper
                .writer()
                .with(YAMLWriteFeature.INDENT_ARRAYS)
                .with(YAMLWriteFeature.INDENT_ARRAYS_WITH_INDICATOR)
                .writeValueAsString(object);
    }

    private static class CustomPrettyPrinter extends DefaultPrettyPrinter {
        public CustomPrettyPrinter() {
            super(Separators.createDefaultInstance().withObjectNameValueSpacing(Separators.Spacing.AFTER));
            super._arrayIndenter = new DefaultIndenter();
        }

        @Override
        public CustomPrettyPrinter createInstance() {
            return new CustomPrettyPrinter();
        }
    }
}
