package io.github.stavshamir.springwolf.configuration.properties;

import static io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigConstants.ENABLED;
import static io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigConstants.SCANNER;
import static io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigConstants.SPRINGWOLF_PLUGIN_CONFIG_PREFIX;

public class SpringWolfAmqpConfigConstants {

    public static final String SPRINGWOLF_AMQP_CONFIG_PREFIX = SPRINGWOLF_PLUGIN_CONFIG_PREFIX + ".amqp";

    public static final String SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED = "publishing.enabled";

    public static final String SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED =
            SPRINGWOLF_AMQP_CONFIG_PREFIX + SCANNER + ".rabbit-listener" + ENABLED;
}
