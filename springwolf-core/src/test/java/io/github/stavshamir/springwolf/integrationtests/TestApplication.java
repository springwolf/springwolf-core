// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.integrationtests;

import io.github.stavshamir.springwolf.asyncapi.v3.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
class TestApplication {
    @TestConfiguration
    @ConditionalOnProperty(name = "test.springwolf.asyncapidocket", havingValue = "true", matchIfMissing = true)
    public static class TestApplicationConfiguration {
        @Bean
        public AsyncApiDocket docket() {
            return AsyncApiDocket.builder()
                    .info(Info.builder()
                            .title("AsyncApiDocketConfiguration-title")
                            .version("AsyncApiDocketConfiguration-version")
                            .build())
                    .build();
        }
    }
}
