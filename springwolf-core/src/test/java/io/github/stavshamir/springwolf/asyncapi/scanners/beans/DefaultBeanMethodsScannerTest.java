package io.github.stavshamir.springwolf.asyncapi.scanners.beans;

import com.google.common.collect.ImmutableSet;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ConfigurationClassScanner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {DefaultBeanMethodsScanner.class})
public class DefaultBeanMethodsScannerTest {

    @Autowired
    private DefaultBeanMethodsScanner beanMethodsScanner;

    @MockBean
    private ConfigurationClassScanner configurationClassScanner;

    @Test
    public void name() {
        when(configurationClassScanner.scan())
                .thenReturn(ImmutableSet.of(ConfigurationClass.class));

        Set<String> beanMethods = beanMethodsScanner.getBeanMethods().stream()
                .map(Method::getName)
                .collect(Collectors.toSet());


        assertThat(beanMethods)
                .hasSize(2)
                .containsExactlyInAnyOrder("stringBean", "consumerBean");
    }

    private static class ConfigurationClass {

        private void notABean() {
        }

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