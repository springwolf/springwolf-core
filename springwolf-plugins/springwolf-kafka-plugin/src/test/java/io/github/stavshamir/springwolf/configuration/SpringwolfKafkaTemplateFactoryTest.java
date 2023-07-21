package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.configuration.properties.SpringWolfKafkaConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SpringwolfKafkaTemplateFactoryTest {

    @Test
    void kafkaTemplateShouldNotBeCreatedForEmptyProperties() {
        SpringWolfKafkaConfigProperties configProperties = new SpringWolfKafkaConfigProperties();

        SpringwolfKafkaTemplateFactory templateFactory = new SpringwolfKafkaTemplateFactory(configProperties);

        Optional<KafkaTemplate<Object, Object>> kafkaTemplate = templateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isNotPresent();
    }

    @Test
    void kafkaTemplateShouldNotBeCreatedForEmptyProducerConfiguration() {
        SpringWolfKafkaConfigProperties configProperties = new SpringWolfKafkaConfigProperties();
        configProperties.setPublishing(new SpringWolfKafkaConfigProperties.Publishing());

        SpringwolfKafkaTemplateFactory templateFactory = new SpringwolfKafkaTemplateFactory(configProperties);

        Optional<KafkaTemplate<Object, Object>> kafkaTemplate = templateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isNotPresent();
    }

    @Test
    void kafkaTemplateShouldBeCreatedWithProducerConfiguration() {
        SpringWolfKafkaConfigProperties configProperties = new SpringWolfKafkaConfigProperties();
        SpringWolfKafkaConfigProperties.Publishing publishing = new SpringWolfKafkaConfigProperties.Publishing();
        publishing.setEnabled(true);
        publishing.setProducer(new KafkaProperties.Producer());

        configProperties.setPublishing(publishing);

        SpringwolfKafkaTemplateFactory templateFactory = new SpringwolfKafkaTemplateFactory(configProperties);

        Optional<KafkaTemplate<Object, Object>> kafkaTemplate = templateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isPresent();
        Map<String, Object> configurationProperties =
                kafkaTemplate.get().getProducerFactory().getConfigurationProperties();

        assertThat(configurationProperties).isEqualTo(new KafkaProperties.Producer().buildProperties());
    }
}
