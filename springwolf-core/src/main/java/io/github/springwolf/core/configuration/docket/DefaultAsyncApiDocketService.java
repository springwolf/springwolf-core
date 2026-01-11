// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

import static io.github.springwolf.core.configuration.docket.AsyncApiInfoMapper.mapInfo;
import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX;

@Slf4j
@RequiredArgsConstructor
public class DefaultAsyncApiDocketService implements AsyncApiDocketService {
    private final SpringwolfConfigProperties configProperties;
    private final ApplicationContext applicationContext;

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
        AsyncApiDocket.AsyncApiDocketBuilder builder = AsyncApiDocket.builder()
                .basePackage(resolveBasePackage(configProperties.getDocket()))
                .info(buildInfo(configProperties.getDocket().getInfo()));
        if (configProperties.getDocket().getServers() != null) {
            builder.servers(buildServers(configProperties.getDocket().getServers()));
        }
        if (configProperties.getDocket().getId() != null) {
            builder.id(configProperties.getDocket().getId());
        }
        if (configProperties.getDocket().getDefaultContentType() != null) {
            builder.defaultContentType(configProperties.getDocket().getDefaultContentType());
        }

        return builder.build();
    }

    private Info buildInfo(SpringwolfConfigProperties.ConfigDocket.Info configDocketInfo) {
        Info info = mapInfo(configDocketInfo);

        if (!StringUtils.hasText(info.getTitle())) {
            info.setTitle(applicationContext.getEnvironment().getProperty("spring.application.name"));
        }

        if (!StringUtils.hasText(info.getVersion()) || !StringUtils.hasText(info.getTitle())) {
            throw new IllegalArgumentException("One or more required fields of the info object (title, version) "
                    + "in application.properties with path prefix " + SPRINGWOLF_CONFIG_PREFIX
                    + " is not set.");
        }

        return info;
    }

    private Map<String, Server> buildServers(Map<String, Server> servers) {
        if (servers != null) {
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

    public String resolveBasePackage(SpringwolfConfigProperties.ConfigDocket docket) {
        if (docket != null && StringUtils.hasText(docket.getBasePackage())) {
            return docket.getBasePackage();
        }

        Collection<Object> applications = applicationContext
                .getBeansWithAnnotation(SpringBootApplication.class)
                .values();
        if (applications.size() == 1) {
            return applications.iterator().next().getClass().getPackageName();
        }

        throw new IllegalArgumentException("One or more required fields (docket, basePackage) "
                + "in application.properties with path prefix " + SPRINGWOLF_CONFIG_PREFIX + " is not set.");
    }
}
