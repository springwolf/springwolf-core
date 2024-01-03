// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.asyncapi.v3.model.info.Info;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class DefaultAsyncApiDocketService implements AsyncApiDocketService {

    /**
     * Docket defined by the user as a @Bean
     */
    private final Optional<AsyncApiDocket> customDocket;

    /**
     * Docket definition in application.properties
     */
    private final SpringwolfConfigProperties configProperties;

    /**
     * valid Docket instance, either reference to customDocket (if set) or environment based Docket.
     * Lazy initialized on first invocation of getAsyncApiDocket().
     */
    @Nullable
    private AsyncApiDocket docket;

    @Override
    public AsyncApiDocket getAsyncApiDocket() {
        if (docket == null) {
            createDocket();
        }
        return docket;
    }

    private void createDocket() {
        if (customDocket.isPresent()) {
            log.debug("Reading springwolf configuration from custom defined @Bean AsyncApiDocket");
            log.warn("The usage of the @Bean AsyncApiDocket is deprecated and scheduled to be deleted. "
                    + "Use the spring properties file instead. "
                    + "More details: https://www.springwolf.dev/docs/quickstart");
            docket = customDocket.get();
        } else {
            log.debug("Reading springwolf configuration from application.properties files");
            docket = parseApplicationConfigProperties();
        }
    }

    private AsyncApiDocket parseApplicationConfigProperties() {
        if (configProperties.getDocket() == null || configProperties.getDocket().getBasePackage() == null) {
            throw new IllegalArgumentException(
                    "One or more required fields (docket, basePackage) " + "in application.properties with path prefix "
                            + SpringwolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX + " is not set.");
        }

        Info info = buildInfo(configProperties.getDocket().getInfo());

        AsyncApiDocket.AsyncApiDocketBuilder builder = AsyncApiDocket.builder()
                .basePackage(configProperties.getDocket().getBasePackage())
                .info(info)
                .servers(configProperties.getDocket().getServers())
                .id(configProperties.getDocket().getId());

        if (configProperties.getDocket().getDefaultContentType() != null) {
            builder.defaultContentType(configProperties.getDocket().getDefaultContentType());
        }

        return builder.build();
    }

    private static Info buildInfo(@Nullable SpringwolfConfigProperties.ConfigDocket.Info configDocketInfo) {
        if (configDocketInfo == null
                || !StringUtils.hasText(configDocketInfo.getVersion())
                || !StringUtils.hasText(configDocketInfo.getTitle())) {
            throw new IllegalArgumentException("One or more required fields of the info object (title, version) "
                    + "in application.properties with path prefix " + SpringwolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX
                    + " is not set.");
        }

        Info asyncapiInfo = Info.builder()
                .version(configDocketInfo.getVersion())
                .title(configDocketInfo.getTitle())
                .description(configDocketInfo.getDescription())
                .contact(configDocketInfo.getContact())
                .license(configDocketInfo.getLicense())
                .build();

        // copy extension fields from configDocketInfo to asyncapiInfo.
        if (configDocketInfo.getExtensionFields() != null) {
            Map<String, Object> extFieldsMap = Map.copyOf(configDocketInfo.getExtensionFields());
            asyncapiInfo.setExtensionFields(extFieldsMap);
        }

        return asyncapiInfo;
    }
}
