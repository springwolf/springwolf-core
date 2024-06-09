// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

/**
 * This class is used to create metadata for auto-completion in spring configuration properties/yaml by using
 * the spring-boot-configuration-processor.
 */
@ConfigurationProperties(prefix = SpringwolfStompConfigConstants.SPRINGWOLF_STOMP_CONFIG_PREFIX)
@Getter
@Setter
public class SpringwolfStompConfigProperties {

    @Nullable
    private Endpoint endpoint = new Endpoint();

    @Nullable
    private Publishing publishing;

    @Nullable
    private Scanner scanner;

    @Getter
    @Setter
    public static class Endpoint {
        private String app = "";

        private String user = "";
    }

    @Getter
    @Setter
    public static class Publishing {

        /**
         * Enables/Disables the possibility to publish messages through springwolf on the configured stomp instance.
         */
        private boolean enabled = false;
    }

    @Getter
    @Setter
    public static class Scanner {

        private static StompMessageMapping stompMessageMapping;

        @Getter
        @Setter
        public static class StompMessageMapping {

            /**
             * This mirrors the ConfigConstant {@see SpringwolfStompConfigConstants#SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED}
             */
            private boolean enabled = true;
        }
    }
}
