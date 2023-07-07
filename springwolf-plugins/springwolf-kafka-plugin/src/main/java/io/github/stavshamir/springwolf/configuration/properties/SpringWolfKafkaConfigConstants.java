package io.github.stavshamir.springwolf.configuration.properties;

import static io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigConstants.ENABLED;
import static io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigConstants.SCANNER;
import static io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigConstants.SPRINGWOLF_PLUGIN_CONFIG_PREFIX;

public class SpringWolfKafkaConfigConstants {

    public static final String SPRINGWOLF_KAFKA_CONFIG_PREFIX = SPRINGWOLF_PLUGIN_CONFIG_PREFIX + ".kafka";

    public static final String SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED = "publishing.enabled";

    public static final String SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED =
            SPRINGWOLF_KAFKA_CONFIG_PREFIX + SCANNER + ".kafka-listener" + ENABLED;


}
