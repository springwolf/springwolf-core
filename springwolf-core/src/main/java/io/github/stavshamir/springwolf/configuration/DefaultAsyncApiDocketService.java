// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2._6_0.model.info.Info;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAsyncApiDocketService implements AsyncApiDocketService {

    /**
     * Docket defined by the user as a @Bean
     */
    private final Optional<AsyncApiDocket> customDocket;

    /**
     * Docket definition in application.properties
     */
    private final Optional<SpringwolfConfigProperties> configProperties;

    @Override
    public AsyncApiDocket getAsyncApiDocket() {
        if (customDocket.isPresent()) {
            log.debug("Reading springwolf configuration from custom defined @Bean AsyncApiDocket");
            return customDocket.get();
        } else if (configProperties.isPresent()) {
            log.debug("Reading springwolf configuration from application.properties files");
            return parseApplicationConfigProperties(configProperties.get());
        }
        throw new IllegalArgumentException("No springwolf configuration found. "
                + "Either define the properties in the application.properties under the "
                + SpringwolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX + " prefix "
                + "or add a @Bean AsyncApiDocket to the spring context");
    }

    private AsyncApiDocket parseApplicationConfigProperties(SpringwolfConfigProperties configProperties) {
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

        asyncapiInfo.setExtensionFields(configDocketInfo.getExtensionFields());

        return asyncapiInfo;
    }
}
