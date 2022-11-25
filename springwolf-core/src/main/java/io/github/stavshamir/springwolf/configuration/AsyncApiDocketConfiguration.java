package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2.model.info.Info;
import io.github.stavshamir.springwolf.SpringWolfConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncApiDocketConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AsyncApiDocket asyncApiDocket(SpringWolfConfigProperties configProperties) {
        Info info = buildInfo(configProperties.getDocket().getInfo());

        return AsyncApiDocket.builder()
                .basePackage(configProperties.getDocket().getBasePackage())
                .info(info)
                .servers(configProperties.getDocket().getServers())
                .build();
    }

    private static Info buildInfo(SpringWolfConfigProperties.ConfigDocket.Info info) {
        return Info.builder()
                .version(info.getVersion())
                .title(info.getTitle())
                .description(info.getDescription())
                .contact(info.getContact())
                .license(info.getLicense())
                .build();
    }


}
