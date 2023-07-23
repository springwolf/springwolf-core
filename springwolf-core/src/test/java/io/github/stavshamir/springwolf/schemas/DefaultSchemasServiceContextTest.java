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
                //                "springwolf.docket.info.title=Info title was loaded from spring properties",
                //                "springwolf.docket.info.version=1.0.0",
                //                "springwolf.docket.id=urn:io:github:stavshamir:springwolf:example",
                //                "springwolf.docket.default-content-type=application/yaml",
                //                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                //                "springwolf.docket.servers.test-protocol.protocol=test",
                //                "springwolf.docket.servers.test-protocol.url=some-server:1234",
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
