package io.github.stavshamir.springwolf.configuration.properties;

import com.asyncapi.v2._6_0.model.info.Contact;
import com.asyncapi.v2._6_0.model.info.License;
import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = SpringWolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX)
@ConditionalOnProperty(name = SpringWolfConfigConstants.SPRINGWOLF_ENABLED, matchIfMissing = true)
@Getter
@Setter
public class SpringWolfConfigProperties {

    public enum LoadingMode {
        /**
         * AsyncAPI detection on application startup. Exceptions interrupt the application start.
         */
        FAIL_FAST,
        /**
         * AsyncAPI detection lazy on first request.
         */
        LAZY,
        /**
         * AsyncAPI detection after application startup in background thread (via Spring TaskExecutor).
         */
        BACKGROUND
    }

    private boolean enabled = true;

    /**
     * Loading mode for AsyncAPI detection.
     */
    private LoadingMode loadingMode = LoadingMode.LAZY;

    @Nullable
    private ConfigDocket docket;

    @Nullable
    private Scanner scanner;

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
        }
    }

    @Getter
    @Setter
    public static class Scanner {

        @Nullable
        private static AsyncListener asyncListener;

        @Nullable
        private static AsyncPublisher asyncPublisher;

        @Nullable
        private static ConsumerData consumerData;

        @Nullable
        private static ProducerData producerData;

        @Getter
        @Setter
        public static class AsyncListener {

            /**
             * This mirrors the ConfigConstant {@see SpringWolfConfigConstants#SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED}
             */
            private boolean enabled = true;
        }

        @Getter
        @Setter
        public static class AsyncPublisher {

            /**
             * This mirrors the ConfigConstant {@see SpringWolfConfigConstants#SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED}
             */
            private boolean enabled = true;
        }

        @Getter
        @Setter
        public static class ConsumerData {

            /**
             * This mirrors the ConfigConstant {@see SpringWolfConfigConstants#SPRINGWOLF_SCANNER_PRODUCER_DATA_ENABLED}
             */
            private boolean enabled = true;
        }

        @Getter
        @Setter
        public static class ProducerData {

            /**
             * This mirrors the ConfigConstant {@see SpringWolfConfigConstants#SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED}
             */
            private boolean enabled = true;
        }
    }
}
