package io.github.stavshamir.springwolf;

import com.asyncapi.v2._6_0.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class CustomBeanAsyncApiDocketConfiguration {
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
