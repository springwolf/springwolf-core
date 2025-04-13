package io.github.springwolf.plugins.asb.asyncapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SpringwolfAsbConfigurationProperties.
 */

@ConfigurationProperties(prefix = SpringwolfAsbConfigConstants.SPRINGWOLF_AMQP_CONFIG_PREFIX)
@Getter
@Setter
public class SpringwolfAsbConfigurationProperties {

    private Publishing publishing;

    @Getter
    @Setter
    public static class Publishing {

        /**
         * Enables/Disables the possibility to publish messages through springwolf on the configured amqp instance.
         */
        private boolean enabled = false;
    }
}
