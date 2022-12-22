package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.SpringWolfAmqpConfigProperties;
import io.github.stavshamir.springwolf.SpringWolfConfigProperties;
import io.github.stavshamir.springwolf.asyncapi.ChannelsService;
import io.github.stavshamir.springwolf.asyncapi.SpringwolfAmqpController;
import io.github.stavshamir.springwolf.producer.SpringwolfAmqpProducer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
public class SpringwolfAmqpProducerConfigurationIntegrationTest {

    @SpringBootTest
    @ContextConfiguration(classes = {
            DefaultAsyncApiDocketService.class,
            SpringwolfAmqpProducer.class,
            SpringwolfAmqpController.class
    })
    @EnableConfigurationProperties(value = {
            SpringWolfConfigProperties.class,
            SpringWolfAmqpConfigProperties.class
    })
    @TestPropertySource(properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example.consumers",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.url=some-server:1234",
            "springwolf.plugin.amqp.publishing.enabled=true"
    })
    @MockBeans(value = {
            @MockBean(ChannelsService.class),
            @MockBean(RabbitTemplate.class)
    })
    public static class AmqpProducerWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfAmqpProducer> springwolfAmqpProducer;

        @Autowired
        private Optional<SpringwolfAmqpController> springwolfAmqpController;

        @Test
        public void springwolfAmqpProducerShouldBePresentInSpringContext() {
            assertThat(springwolfAmqpProducer).isPresent();
            assertThat(springwolfAmqpController).isPresent();
        }
    }

    @SpringBootTest
    @ContextConfiguration(classes = {
            DefaultAsyncApiDocketService.class,
            SpringwolfAmqpProducer.class,
            SpringwolfAmqpController.class
    })
    @EnableConfigurationProperties(value = {
            SpringWolfConfigProperties.class,
            SpringWolfAmqpConfigProperties.class
    })
    @TestPropertySource(properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example.consumers",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.url=some-server:1234",
            "springwolf.plugin.amqp.publishing.enabled=false"
    })
    @MockBeans(value = {
            @MockBean(ChannelsService.class),
            @MockBean(RabbitTemplate.class)
    })
    public static class AmqpProducerWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfAmqpProducer> springwolfAmqpProducer;

        @Autowired
        private Optional<SpringwolfAmqpController> springwolfAmqpController;

        @Test
        public void springwolfAmqpProducerShouldNotBePresentInSpringContext() {
            assertThat(springwolfAmqpProducer).isNotPresent();
            assertThat(springwolfAmqpController).isNotPresent();
        }
    }

}
