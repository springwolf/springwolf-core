// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.configuration.properties;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class is used to create metadata for auto-completion in spring configuration properties/yaml by using
 * the spring-boot-configuration-processor.
 */
@ConfigurationProperties(prefix = SpringwolfStompConfigConstants.SPRINGWOLF_STOMP_CONFIG_PREFIX)
@Getter
@Setter
public class SpringwolfStompConfigProperties {

    private Endpoint endpoint = new Endpoint();

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
    public static class Scanner {

        private static StompMessageMapping stompMessageMapping;
        private static StompSendTo stompSendTo;
        private static StompSendToUser stompSendToUser;

        @Getter
        @Setter
        public static class StompMessageMapping {

            /**
             * This mirrors the ConfigConstant {@see SpringwolfStompConfigConstants#SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED}
             */
            private boolean enabled = true;
        }

        @Getter
        @Setter
        public static class StompSendTo {

            /**
             * This mirrors the ConfigConstant {@see SpringwolfStompConfigConstants#SPRINGWOLF_SCANNER_STOMP_SEND_TO_ENABLED}
             */
            private boolean enabled = true;
        }

        @Getter
        @Setter
        public static class StompSendToUser {

            /**
             * This mirrors the ConfigConstant {@see SpringwolfStompConfigConstants#SPRINGWOLF_SCANNER_STOMP_SEND_TO_USER_ENABLED}
             */
            private boolean enabled = true;
        }
    }
}
