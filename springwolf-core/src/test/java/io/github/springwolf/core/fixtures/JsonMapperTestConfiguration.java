// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.fixtures;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

@TestConfiguration
public class JsonMapperTestConfiguration {
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    public static final JsonMapper jsonMapper = JsonMapper.builder()
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_ABSENT)
                    .withContentInclusion(JsonInclude.Include.NON_ABSENT))
            .defaultPrettyPrinter(printer)
            .build();

    @ConditionalOnMissingBean
    @Bean
    public JsonMapper jsonMapper() {
        return jsonMapper;
    }
}
