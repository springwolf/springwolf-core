// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.configuration.properties;

import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.ENABLED;
import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SCANNER;
import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_PLUGIN_CONFIG_PREFIX;

public class SpringwolfStompConfigConstants {

    public static final String SPRINGWOLF_STOMP_CONFIG_PREFIX = SPRINGWOLF_PLUGIN_CONFIG_PREFIX + ".stomp";

    public static final String SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED =
            SPRINGWOLF_STOMP_CONFIG_PREFIX + SCANNER + ".stomp-message-mapping" + ENABLED;
}
