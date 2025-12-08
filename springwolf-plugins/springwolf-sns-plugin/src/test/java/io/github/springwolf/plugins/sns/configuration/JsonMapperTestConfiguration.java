// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.configuration;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class JsonMapperTestConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public JsonMapper jsonMapper() {
        return JsonMapper.builder().build();
    }
}
