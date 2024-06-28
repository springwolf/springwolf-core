// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.configuration.properties;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class is used to create metadata for auto-completion in spring configuration properties/yaml by using
 * the spring-boot-configuration-processor.
 */
@ConfigurationProperties(prefix = SpringwolfSnsConfigConstants.SPRINGWOLF_SNS_CONFIG_PREFIX)
@Getter
@Setter
public class SpringwolfSnsConfigProperties {

    @Nullable
    private Publishing publishing;

    @Nullable
    private Scanner scanner;

    @Getter
    @Setter
    public static class Publishing {

        /**
         * Enables/Disables the possibility to publish messages through springwolf on the configured sns instance.
         */
        private boolean enabled = false;
    }

    @Getter
    @Setter
    public static class Scanner {

        private static SnsListener snsListener;

        @Getter
        @Setter
        public static class SnsListener {

            /**
             * This mirrors the ConfigConstant {@see SpringwolfSnsConfigConstants#SPRINGWOLF_SCANNER_SNS_LISTENER_ENABLED}
             */
            private boolean enabled = true;
        }
    }
}
