// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;

import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX;

@Slf4j
@RequiredArgsConstructor
public class DefaultAsyncApiDocketService implements AsyncApiDocketService {
    private final SpringwolfConfigProperties configProperties;

    /**
     * lazily initialized AsyncApiDocket instance.
     */
    @Nullable
    private AsyncApiDocket docket;

    @Override
    public AsyncApiDocket getAsyncApiDocket() {
        if (docket == null) {
            docket = createDocket();
        }
        return docket;
    }

    private AsyncApiDocket createDocket() {
        if (configProperties.getDocket() == null
                || !StringUtils.hasText(configProperties.getDocket().getBasePackage())) {
            throw new IllegalArgumentException("One or more required fields (docket, basePackage) "
                    + "in application.properties with path prefix " + SPRINGWOLF_CONFIG_PREFIX + " is not set.");
        }

        AsyncApiDocket.AsyncApiDocketBuilder builder = AsyncApiDocket.builder()
                .basePackage(configProperties.getDocket().getBasePackage())
                .info(buildInfo(configProperties.getDocket().getInfo()))
                .servers(buildServers(configProperties.getDocket().getServers()));

        if (configProperties.getDocket().getId() != null) {
            builder.id(configProperties.getDocket().getId());
        }
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
                    + "in application.properties with path prefix " + SPRINGWOLF_CONFIG_PREFIX
                    + " is not set.");
        }

        Info asyncapiInfo = mapInfo(configDocketInfo);

        return asyncapiInfo;
    }

    public static Info mapInfo(SpringwolfConfigProperties.ConfigDocket.Info configDocketInfo) {
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

    private static Map<String, Server> buildServers(Map<String, Server> servers) {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalArgumentException("No server has been defined in application.properties "
                    + "with path prefix " + SPRINGWOLF_CONFIG_PREFIX);
        } else {
            servers.forEach((serverName, server) -> {
                if (!StringUtils.hasText(server.getProtocol()) || !StringUtils.hasText(server.getHost())) {
                    throw new IllegalArgumentException(
                            "One or more required fields (protocol, host) " + "of the server object (name="
                                    + serverName + ") " + "has not been defined in application.properties "
                                    + "with path prefix " + SPRINGWOLF_CONFIG_PREFIX);
                }
            });
        }

        return servers;
    }
}
