// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@Order(value = Ordered.HIGHEST_PRECEDENCE) // Highest so that all others will replace this configuration
public class SpringwolfUiResourceConfigurer implements WebMvcConfigurer {

    private final SpringwolfConfigProperties springwolfConfigProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String springwolfBasePath = springwolfConfigProperties.getPath().getBase();

        log.debug("Serving Springwolf with base-path: {}", springwolfBasePath);
        if (!springwolfBasePath.equals(SpringwolfConfigProperties.Path.DEFAULT_BASE)) {

            registry.addResourceHandler(springwolfBasePath + "/**").addResourceLocations(buildStaticLocation());
            registry.addResourceHandler(SpringwolfConfigProperties.Path.DEFAULT_BASE + "/**")
                    .addResourceLocations("classpath:/non-existent/");
        }
    }

    private String[] buildStaticLocation() {
        List<String> staticLocations = new ArrayList<>();
        staticLocations.add("classpath:/META-INF/resources/springwolf/");

        return staticLocations.toArray(new String[0]);
    }
}
