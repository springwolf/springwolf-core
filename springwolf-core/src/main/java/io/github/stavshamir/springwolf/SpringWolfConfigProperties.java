package io.github.stavshamir.springwolf;

import com.asyncapi.v2.model.info.Contact;
import com.asyncapi.v2.model.info.License;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = SpringWolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX)
@ConditionalOnProperty(name = SpringWolfConfigConstants.SPRINGWOLF_ENABLED, matchIfMissing = true)
@Getter
@Setter
public class SpringWolfConfigProperties {

    private boolean enabled = true;

    @Nullable
    private ConfigDocket docket;

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

        @Nullable
        private Map<String, Server> servers;

        /**
         * The object provides metadata about the API. The metadata can be used by the clients if needed.
         *
         * @see com.asyncapi.v2.model.info.Info
         */
        @Nullable
        private Info info;

        @Getter
        @Setter
        public static class Info {

            /**
             * The title of the application
             *
             * @see com.asyncapi.v2.model.info.Info#getTitle()
             */
            @Nullable
            private String title;

            /**
             * Required. Provides the version of the application API (not to be confused with the specification version).
             *
             * @see com.asyncapi.v2.model.info.Info#getVersion()
             */
            @Nullable
            private String version;

            /**
             * A short description of the application. CommonMark syntax can be used for rich text representation.
             *
             * @see com.asyncapi.v2.model.info.Info#getDescription()
             */
            @Nullable
            private String description;

            /**
             * A URL to the Terms of Service for the API. MUST be in the format of a URL.
             * {@link com.asyncapi.v2.model.info.Info#getTermsOfService()}
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


}
