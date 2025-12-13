// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.beans;

import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = {DefaultBeanMethodsScanner.class})
class DefaultBeanMethodsScannerIntegrationTest {

    @Autowired
    private DefaultBeanMethodsScanner beanMethodsScanner;

    @MockitoBean
    private ComponentClassScanner componentClassScanner;

    @Test
    void name() {
        when(componentClassScanner.scan()).thenReturn(Set.of(ConfigurationClass.class));

        Set<String> beanMethods = beanMethodsScanner.getBeanMethods().stream()
                .map(Method::getName)
                .collect(Collectors.toSet());

        assertThat(beanMethods).hasSize(2).containsExactlyInAnyOrder("stringBean", "consumerBean");
    }

    private static class ConfigurationClass {

        private void notABean() {}

        @Bean
        private String stringBean() {
            return "foo";
        }

        @Bean
        private Consumer<String> consumerBean() {
            return System.out::println;
        }
    }
}
