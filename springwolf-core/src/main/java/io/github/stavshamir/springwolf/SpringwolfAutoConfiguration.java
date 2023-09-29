// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Spring Boot auto-configuration which loads all spring-beans for springwolf core and eventually loaded plugins.
 * <p>
 * This configuration uses the spring @{@link ComponentScan} mechanism to detect and load the necessary beans. Both
 * the core components as well as all plugin components are located underneath the base package 'io.github.stavshamir.springwolf'
 * so existing springwolf plugins are automatically loaded together with the springwolf core beans.
 * <p>
 * To disable this auto-configuration, set the environment property {@code springwolf.enabled=false}.
 */
@AutoConfiguration
@ComponentScan(
        basePackages = {"io.github.stavshamir.springwolf"},
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
            @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)
        })
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, matchIfMissing = true)
public class SpringwolfAutoConfiguration {}
