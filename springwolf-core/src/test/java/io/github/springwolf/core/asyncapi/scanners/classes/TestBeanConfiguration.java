// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfiguration {

    @Bean
    public TestBean testBean() {
        return new TestBean("foo");
    }

    public record TestBean(String value) {}
}
