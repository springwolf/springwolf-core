// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration.properties;

public class SpringwolfConfigConstants {
    public static final String ENABLED = ".enabled";

    public static final String SCANNER = ".scanner";

    public static final String SPRINGWOLF_CONFIG_PREFIX = "springwolf";

    public static final String SPRINGWOLF_ENABLED = SPRINGWOLF_CONFIG_PREFIX + ENABLED;

    public static final String ENDPOINT_ACTUATOR = ".endpoint.actuator";

    public static final String SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED =
            SPRINGWOLF_CONFIG_PREFIX + ENDPOINT_ACTUATOR + ENABLED;

    public static final String SPRINGWOLF_PLUGIN_CONFIG_PREFIX = SPRINGWOLF_CONFIG_PREFIX + ".plugin";

    public static final String SPRINGWOLF_SCANNER_PREFIX = SPRINGWOLF_CONFIG_PREFIX + SCANNER;
    public static final String SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED =
            SPRINGWOLF_SCANNER_PREFIX + ".async-listener" + ENABLED;
    public static final String SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED =
            SPRINGWOLF_SCANNER_PREFIX + ".async-publisher" + ENABLED;
    public static final String SPRINGWOLF_SCANNER_CONSUMER_DATA_ENABLED =
            SPRINGWOLF_SCANNER_PREFIX + ".consumer-data" + ENABLED;
    public static final String SPRINGWOLF_SCANNER_PRODUCER_DATA_ENABLED =
            SPRINGWOLF_SCANNER_PREFIX + ".producer-data" + ENABLED;

    public static final String SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR = SPRINGWOLF_CONFIG_PREFIX + ".example-generator";
}
