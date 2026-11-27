// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.web.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(WebMvcConfigurer.class)
@ConditionalOnBean({WebProperties.class})
@Import(SpringwolfUiResourceConfigurer.class)
public class SpringwolfUiResourceConfiguration {}
