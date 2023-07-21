package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.asyncapi.SpringwolfKafkaController;
import io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigProperties;
import io.github.stavshamir.springwolf.configuration.properties.SpringWolfKafkaConfigProperties;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfKafkaProducerConfigurationIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(
            classes = {
                DefaultAsyncApiDocketService.class,
                SpringwolfKafkaProducerConfiguration.class,
                SpringwolfKafkaTemplateFactory.class,
                SpringwolfKafkaController.class
            })
    @EnableConfigurationProperties(value = {SpringWolfConfigProperties.class, SpringWolfKafkaConfigProperties.class})
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234",
                "springwolf.plugin.kafka.publishing.enabled=true"
            })
    class KafkaProducerWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfKafkaProducer> springwolfKafkaProducer;

        @Autowired
        private Optional<SpringwolfKafkaController> springwolfKafkaController;

        @Test
        void springwolfKafkaTemplateShouldBePresentInSpringContext() {
            assertThat(springwolfKafkaProducer).isPresent();
            assertThat(springwolfKafkaController).isPresent();
        }
    }

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(
            classes = {
                DefaultAsyncApiDocketService.class,
                SpringwolfKafkaProducerConfiguration.class,
                SpringwolfKafkaController.class
            })
    @EnableConfigurationProperties(value = {SpringWolfConfigProperties.class, SpringWolfKafkaConfigProperties.class})
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234",
                "springwolf.plugin.kafka.publishing.enabled=false"
            })
    class KafkaProducerWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfKafkaProducer> springwolfKafkaProducer;

        @Autowired
        private Optional<SpringwolfKafkaController> springwolfKafkaController;

        @Test
        void springwolfKafkaTemplateShouldNotBePresentInSpringContext() {
            assertThat(springwolfKafkaProducer).isNotPresent();
            assertThat(springwolfKafkaController).isNotPresent();
        }
    }
}
