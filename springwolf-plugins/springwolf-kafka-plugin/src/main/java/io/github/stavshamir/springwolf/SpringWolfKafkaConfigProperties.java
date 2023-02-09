package io.github.stavshamir.springwolf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;

import static io.github.stavshamir.springwolf.SpringWolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;

/**
 * This class is used to create metadata for auto-completion in spring configuration properties/yaml by using
 * the spring-boot-configuration-processor.
 */
@Configuration
@ConfigurationProperties(prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX)
@ConditionalOnProperty(name = SpringWolfConfigConstants.SPRINGWOLF_ENABLED, matchIfMissing = true)
@Getter
@Setter
public class SpringWolfKafkaConfigProperties {

    @Nullable
    private Publishing publishing;

    @Nullable
    private Scanner scanner;

    @Getter
    @Setter
    public static class Publishing {

        /**
         * Enables/Disables the possibility to publish messages through springwolf on the configured kafka instance.
         */
        private boolean enabled = false;

        @NestedConfigurationProperty
        private KafkaProperties.Producer producer;
    }

    @Getter
    @Setter
    public static class Scanner {

        private static KafkaListener kafkaListener;

        @Getter
        @Setter
        public static class KafkaListener {

            /**
             * This mirrors the ConfigConstant {@see SpringWolfKafkaConfigConstants#SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED}
             */
            private boolean enabled = true;
        }
    }
}
