// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.asb.asyncapi;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

/**
 * SpringwolfAsbAutoConfiguration for the springwolf asb plugin.
 */

@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
@Import({
        SpringwolfAsbPropertiesConfiguration.class,
        SpringwolfAsbProducerConfiguration.class
})
public class SpringwolfAsbAutoConfiguration {


}
