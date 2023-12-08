// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfiguration {

    @Bean
    public TestBean testBean() {
        return new TestBean("foo");
    }

    @Value
    public static class TestBean {
        String value;
    }
}
