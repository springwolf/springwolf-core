package io.github.stavshamir.springwolf;

import com.asyncapi.v2.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CustomBeanAsyncApiDocketConfiguration {
    @Bean
    public AsyncApiDocket docket() {
        return AsyncApiDocket.builder()
                .info(
                        Info.builder()
                                .title("AsyncApiDocketConfiguration-title")
                                .version("AsyncApiDocketConfiguration-version")
                                .build())
                .build();
    }
}
