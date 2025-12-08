// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.fixtures;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.json.JsonMapper;

@TestConfiguration
public class JsonMapperTestConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public JsonMapper jsonMapper() {
        return JsonMapper.builder().build();
    }
}
