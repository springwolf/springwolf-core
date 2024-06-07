// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@Order(value = Ordered.HIGHEST_PRECEDENCE) // Highest so that all others will replace this configuration
public class SpringwolfUiResourceConfigurer implements WebMvcConfigurer {

    private final SpringwolfConfigProperties springwolfConfigProperties;
    private final WebProperties webProperties;
    private final WebMvcProperties webMvcProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String springwolfBasePath = springwolfConfigProperties.getPath().getBase();

        log.debug("Serving Springwolf with base-path: {}", springwolfBasePath);

        registry.addResourceHandler(springwolfBasePath + "/**", webMvcProperties.getStaticPathPattern())
                .addResourceLocations(buildStaticLocation());
    }

    private String[] buildStaticLocation() {
        List<String> staticLocations =
                new ArrayList<>(Arrays.asList(webProperties.getResources().getStaticLocations()));
        staticLocations.add("classpath:/META-INF/resources/springwolf/");

        return staticLocations.toArray(new String[0]);
    }
}
