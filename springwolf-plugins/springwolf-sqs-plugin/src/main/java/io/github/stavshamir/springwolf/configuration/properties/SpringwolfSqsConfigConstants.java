package io.github.stavshamir.springwolf.configuration.properties;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.ENABLED;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SCANNER;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_PLUGIN_CONFIG_PREFIX;

public class SpringwolfSqsConfigConstants {

    public static final String SPRINGWOLF_SQS_CONFIG_PREFIX = SPRINGWOLF_PLUGIN_CONFIG_PREFIX + ".sqs";

    public static final String SPRINGWOLF_SQS_PLUGIN_PUBLISHING_ENABLED = "publishing.enabled";

    public static final String SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED =
            SPRINGWOLF_SQS_CONFIG_PREFIX + SCANNER + ".sqs-listener" + ENABLED;
}
