// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration.properties;

import com.asyncapi.v2._6_0.model.info.Contact;
import com.asyncapi.v2._6_0.model.info.License;
import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Springwolf configuration loaded from Spring <code>application.properties</code> file.
 */
@ConfigurationProperties(prefix = SpringwolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX)
@Getter
@Setter
public class SpringwolfConfigProperties {

    public enum InitMode {
        /**
         * Default option.
         * AsyncAPI detection on application startup. Exceptions interrupt the application start.
         *
         * Use for immediate feedback in case of misconfiguration.
         */
        FAIL_FAST,

        /**
         * AsyncAPI detection after application startup in background thread (via Spring TaskExecutor).
         *
         * Use when your application context is large and initialization should be deferred to reduce start-up time.
         */
        BACKGROUND
    }

    private boolean enabled = true;

    /**
     * Init mode for building AsyncAPI.
     */
    private InitMode initMode = InitMode.FAIL_FAST;

    /**
     * Use fully qualified names for the schema classes
     *
     * Example:
     * useFqn = true  -> java.lang.String
     * useFqn = false -> String
     */
    private boolean useFqn = false;

    @Nullable
    private Endpoint endpoint;

    @Nullable
    private ConfigDocket docket;

    @Nullable
    private Scanner scanner;

    @Nullable
    private Payload payload = new Payload();

    @Getter
    @Setter
    public static class ConfigDocket {

        /**
         * The base package to scan for listeners which are declared inside a class annotated with @Component or @Service.
         *
         * @see AsyncApiDocket.AsyncApiDocketBuilder#basePackage(String)
         */
        @Nullable
        private String basePackage;

        /**
         * Identifier of the application the AsyncAPI document is defining.
         *
         * @see com.asyncapi.v2._6_0.model.AsyncAPI#id
         */
        @Nullable
        private String id;

        /**
         * A string representing the default content type to use when encoding/decoding a message's payload.
         *
         * @see com.asyncapi.v2._6_0.model.AsyncAPI#getdefaultContentType
         */
        @Nullable
        private String defaultContentType;

        @Nullable
        private Map<String, Server> servers;

        /**
         * The object provides metadata about the API. The metadata can be used by the clients if needed.
         *
         * @see com.asyncapi.v2._6_0.model.info.Info
         */
        @Nullable
        private Info info;

        @Getter
        @Setter
        public static class Info {

            /**
             * The title of the application
             *
             * @see com.asyncapi.v2._6_0.model.info.Info#getTitle()
             */
            @Nullable
            private String title;

            /**
             * Required. Provides the version of the application API (not to be confused with the specification version).
             *
             * @see com.asyncapi.v2._6_0.model.info.Info#getVersion()
             */
            @Nullable
            private String version;

            /**
             * A short description of the application. CommonMark syntax can be used for rich text representation.
             *
             * @see com.asyncapi.v2._6_0.model.info.Info#getDescription()
             */
            @Nullable
            private String description;

            /**
             * A URL to the Terms of Service for the API. MUST be in the format of a URL.
             * {@link com.asyncapi.v2._6_0.model.info.Info#getTermsOfService()}
             */
            @Nullable
            private String termsOfService;

            @NestedConfigurationProperty
            @Nullable
            private Contact contact;

            @NestedConfigurationProperty
            @Nullable
            private License license;

            /**
             * Extension properties for the Info block.
             */
            @Nullable
            private Map<String, String> extensionFields = Map.of("x-generator", "springwolf");
        }
    }

    @Getter
    @Setter
    public static class Scanner {

        @Nullable
        private static AsyncListener asyncListener;

        @Nullable
        private static AsyncPublisher asyncPublisher;

        @Getter
        @Setter
        public static class AsyncListener {

            /**
             * This mirrors the ConfigConstant {@see SpringwolfConfigConstants#SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED}
             */
            private boolean enabled = true;
        }

        @Getter
        @Setter
        public static class AsyncPublisher {

            /**
             * This mirrors the ConfigConstant {@see SpringwolfConfigConstants#SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED}
             */
            private boolean enabled = true;
        }
    }

    @Getter
    @Setter
    public static class Endpoint {

        @Nullable
        private Actuator actuator;

        @Getter
        @Setter
        private static class Actuator {

            /**
             * Flag to move the endpoint that exposes the AsyncAPI document beneath Spring Boot's actuator endpoint.
             */
            private boolean enabled = false;
        }
    }

    @Getter
    public static class Payload {
        /**
         * In case the payload is wrapped, Springwolf will try to unwrap the specified generic classes.
         *
         * The format is: canonicalClassName=generic-argument-index
         */
        private Map<String, Integer> extractableClasses = Map.of(
                "java.util.function.Consumer",
                0,
                "java.util.function.Supplier",
                0,
                "org.springframework.messaging.Message",
                0,
                "org.apache.kafka.streams.kstream.KStream",
                1,
                "java.util.List",
                0,
                "java.util.Optional",
                0);

        public void setExtractableClasses(Map<String, Integer> extractableClasses) {
            this.extractableClasses = extractableClasses.entrySet().stream()
                    .filter(entry -> entry.getValue() >= 0)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }
}
