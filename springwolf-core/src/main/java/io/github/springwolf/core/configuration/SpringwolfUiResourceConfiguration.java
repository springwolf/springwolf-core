// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(WebMvcConfigurer.class)
@ConditionalOnBean({WebMvcProperties.class, WebProperties.class})
@Import(SpringwolfUiResourceConfigurer.class)
public class SpringwolfUiResourceConfiguration {}
