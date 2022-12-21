package io.github.stavshamir.springwolf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;

import static io.github.stavshamir.springwolf.SpringWolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;

@Configuration
@ConfigurationProperties(prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX)
@ConditionalOnProperty(name = SpringWolfConfigConstants.SPRINGWOLF_ENABLED, matchIfMissing = true)
@Getter
@Setter
public class SpringWolfKafkaConfigProperties {

    @Nullable
    private Publishing publishing;

    @Getter
    @Setter
    public static class Publishing {

        /**
         * Enables/Disables the possibility to publish messages through springwolf on the configured kafka instance.
         */
        private boolean enabled = false;

    }

}
