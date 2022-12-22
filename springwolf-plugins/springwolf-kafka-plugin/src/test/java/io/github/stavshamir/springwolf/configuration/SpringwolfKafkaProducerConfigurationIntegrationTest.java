package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.SpringWolfConfigProperties;
import io.github.stavshamir.springwolf.SpringWolfKafkaConfigProperties;
import io.github.stavshamir.springwolf.asyncapi.SpringwolfKafkaController;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
public class SpringwolfKafkaProducerConfigurationIntegrationTest {

    @SpringBootTest
    @ContextConfiguration(classes = {
            DefaultAsyncApiDocketService.class,
            SpringwolfKafkaProducerConfiguration.class,
            SpringwolfKafkaTemplateFactory.class,
            SpringwolfKafkaController.class
    })
    @EnableConfigurationProperties(value = {
            SpringWolfConfigProperties.class,
            SpringWolfKafkaConfigProperties.class
    })
    @TestPropertySource(properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example.consumers",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.url=some-server:1234",
            "springwolf.plugin.kafka.publishing.enabled=true"
    })
    public static class KafkaProducerWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfKafkaProducer> springwolfKafkaProducer;

        @Autowired
        private Optional<SpringwolfKafkaTemplateFactory> kafkaTemplateFactory;

        @Autowired
        private Optional<SpringwolfKafkaController> springwolfKafkaController;

        @Test
        public void springwolfKafkaTemplateShouldBePresentInSpringContext() {
            assertThat(kafkaTemplateFactory).isPresent();
            assertThat(springwolfKafkaProducer).isPresent();
            assertThat(springwolfKafkaController).isPresent();
        }
    }

    @SpringBootTest
    @ContextConfiguration(classes = {
            DefaultAsyncApiDocketService.class,
            SpringwolfKafkaProducerConfiguration.class,
            SpringwolfKafkaTemplateFactory.class,
            SpringwolfKafkaController.class
    })
    @EnableConfigurationProperties(value = {
            SpringWolfConfigProperties.class,
            SpringWolfKafkaConfigProperties.class
    })
    @TestPropertySource(properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example.consumers",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.url=some-server:1234",
            "springwolf.plugin.kafka.publishing.enabled=false"
    })
    public static class KafkaProducerWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfKafkaProducer> springwolfKafkaProducer;

        @Autowired
        private Optional<SpringwolfKafkaTemplateFactory> kafkaTemplateFactory;

        @Autowired
        private Optional<SpringwolfKafkaController> springwolfKafkaController;

        @Test
        public void springwolfKafkaTemplateShouldNotBePresentInSpringContext() {
            assertThat(kafkaTemplateFactory).isNotPresent();
            assertThat(springwolfKafkaProducer).isNotPresent();
            assertThat(springwolfKafkaController).isNotPresent();
        }
    }

}
