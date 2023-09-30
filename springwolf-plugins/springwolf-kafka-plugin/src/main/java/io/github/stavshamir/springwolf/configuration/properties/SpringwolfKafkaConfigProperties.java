// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.lang.Nullable;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;

/**
 * This class is used to create metadata for auto-completion in spring configuration properties/yaml by using
 * the spring-boot-configuration-processor.
 */
@ConfigurationProperties(prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX)
@Getter
@Setter
public class SpringwolfKafkaConfigProperties {

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
             * This mirrors the ConfigConstant {@see SpringwolfKafkaConfigConstants#SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED}
             */
            private boolean enabled = true;
        }
    }
}
