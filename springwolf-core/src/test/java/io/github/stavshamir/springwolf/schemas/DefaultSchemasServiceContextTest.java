package io.github.stavshamir.springwolf.schemas;

import io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.example.ExampleGenerator;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import io.github.stavshamir.springwolf.schemas.example.SwaggerInflectorJsonGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultSchemasServiceContextTest {

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(
            classes = {
                DefaultSchemasService.class,
                ExampleJsonGenerator.class,
                SwaggerInflectorJsonGenerator.class,
            })
    class DefaultExampleJsonGenerator {

        @Autowired
        private ExampleGenerator exampleGenerator;

        @Test
        void testContextContainsDefaultExampleGenerator() {
            assertThat(exampleGenerator).isInstanceOf(ExampleJsonGenerator.class);
        }
    }

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(
            classes = {
                DefaultSchemasService.class,
                ExampleJsonGenerator.class,
                SwaggerInflectorJsonGenerator.class,
            })
    @EnableConfigurationProperties(
            value = {
                SpringWolfConfigProperties.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.example-generator=swagger-inflector-json",
            })
    class FallbackExampleJsonGenerator {

        @Autowired
        private ExampleGenerator exampleGenerator;

        @Test
        void testContextHasSwaggerInflectorJsonGenerator() {
            assertThat(exampleGenerator).isInstanceOf(SwaggerInflectorJsonGenerator.class);
        }
    }
}
