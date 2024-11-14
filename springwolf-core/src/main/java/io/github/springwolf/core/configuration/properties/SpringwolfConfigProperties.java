// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.properties;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.info.Contact;
import io.github.springwolf.asyncapi.v3.model.info.License;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import jakarta.annotation.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Collections;
import java.util.List;
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
         * <p>
         * Use for immediate feedback in case of misconfiguration.
         */
        FAIL_FAST,

        /**
         * AsyncAPI detection after application startup in background thread (via Spring TaskExecutor).
         * <p>
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
     * <p>
     * Example:
     * useFqn = true  -> java.lang.String
     * useFqn = false -> String
     */
    private boolean useFqn = true;

    /**
     * Feature toggle to enable/disable all workarounds for AsyncApi Studio
     * to ensure a best-effort out-of-the-box experience.
     * @see https://studio.asyncapi.com/
     */
    private boolean studioCompatibility = true;

    @Deprecated(forRemoval = true)
    private Paths paths = new Paths();

    private Path path = new Path();

    @Nullable
    private Endpoint endpoint;

    private ConfigDocket docket = new ConfigDocket();

    /**
     * The UI configuration properties.
     *
     * @see UI
     */
    private UI ui = new UI();

    @Nullable
    private Scanner scanner;

    @Nullable
    private Payload payload = new Payload();

    @Getter
    @Setter
    public static class Paths {

        /**
         * Deprecated in favor of springwolf.path.docs to control
         * only the sub path for the docs endpoint
         */
        @Deprecated(forRemoval = true)
        private String docs = "/springwolf/docs";
    }

    @Getter
    @Setter
    public static class Path {
        private String base = "/springwolf";
        private String docs = "/docs";
    }

    @Getter
    @Setter
    public static class ConfigDocket {

        public static final String DEFAULT_CONTENT_TYPE = "application/json";

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
         * @see AsyncAPI#getId()
         */
        @Nullable
        private String id;

        /**
         * A string representing the default content type to use when encoding/decoding a message's payload.
         *
         * @see AsyncAPI#getDefaultContentType()
         */
        private String defaultContentType = DEFAULT_CONTENT_TYPE;

        @Nullable
        private Map<String, Server> servers;

        /**
         * The object provides metadata about the API. The metadata can be used by the clients if needed.
         *
         * @see Info
         */
        @Nullable
        private Info info;

        @Getter
        @Setter
        public static class Info {

            /**
             * The title of the application
             *
             * @see io.github.springwolf.asyncapi.v3.model.info.Info#getTitle()
             */
            @Nullable
            private String title;

            /**
             * Required. Provides the version of the application API (not to be confused with the specification version).
             *
             * @see io.github.springwolf.asyncapi.v3.model.info.Info#getVersion()
             */
            @Nullable
            private String version;

            /**
             * A short description of the application. CommonMark syntax can be used for rich text representation.
             *
             * @see io.github.springwolf.asyncapi.v3.model.info.Info#getDescription()
             */
            @Nullable
            private String description;

            /**
             * A URL to the Terms of Service for the API. MUST be in the format of a URL.
             * {@link io.github.springwolf.asyncapi.v3.model.info.Info#getTermsOfService()}
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

        private List<Group> groupConfigs = List.of();

        @Getter
        @Setter
        @EqualsAndHashCode
        @ToString
        public static class Group {
            /**
             * The name of the Group
             */
            private String group = "";

            /**
             * The action to match for the group
             */
            private List<OperationAction> actionToMatch = Collections.emptyList();

            /**
             * The channel names to match
             */
            private List<String> channelNameToMatch = Collections.emptyList();

            /**
             * The message names to match
             */
            private List<String> messageNameToMatch = Collections.emptyList();
        }
    }

    @Getter
    @Setter
    public static class UI {
        /**
         * Allows to show or hide the bindings in the UI when opening the UI.
         */
        private boolean initiallyShowBindings = true;

        /**
         * Allows to show or hide the headers in the UI when opening the UI.
         */
        private boolean initiallyShowHeaders = true;
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
         * <p>
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
