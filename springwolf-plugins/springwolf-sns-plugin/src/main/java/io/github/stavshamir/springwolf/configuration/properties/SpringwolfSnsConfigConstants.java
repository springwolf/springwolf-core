// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration.properties;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.ENABLED;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SCANNER;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_PLUGIN_CONFIG_PREFIX;

public class SpringwolfSnsConfigConstants {

    public static final String SPRINGWOLF_SNS_CONFIG_PREFIX = SPRINGWOLF_PLUGIN_CONFIG_PREFIX + ".sns";

    public static final String SPRINGWOLF_SNS_PLUGIN_PUBLISHING_ENABLED = "publishing.enabled";

    public static final String SPRINGWOLF_SCANNER_SNS_LISTENER_ENABLED =
            SPRINGWOLF_SNS_CONFIG_PREFIX + SCANNER + ".sns-listener" + ENABLED;
}
