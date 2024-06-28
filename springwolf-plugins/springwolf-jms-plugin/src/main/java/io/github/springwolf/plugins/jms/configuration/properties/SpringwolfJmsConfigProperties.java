// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.configuration.properties;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class is used to create metadata for auto-completion in spring configuration properties/yaml by using
 * the spring-boot-configuration-processor.
 */
@ConfigurationProperties(prefix = SpringwolfJmsConfigConstants.SPRINGWOLF_JMS_CONFIG_PREFIX)
@Getter
@Setter
public class SpringwolfJmsConfigProperties {

    @Nullable
    private Publishing publishing;

    @Nullable
    private Scanner scanner;

    @Getter
    @Setter
    public static class Publishing {

        /**
         * Enables/Disables the possibility to publish messages through springwolf on the configured jms instance.
         */
        private boolean enabled = false;
    }

    @Getter
    @Setter
    public static class Scanner {

        private static JmsListener jmsListener;

        @Getter
        @Setter
        public static class JmsListener {

            /**
             * This mirrors the ConfigConstant {@see SpringwolfJmsConfigConstants#SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED}
             */
            private boolean enabled = true;
        }
    }
}
