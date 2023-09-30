// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigProperties;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaTemplateFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SpringwolfKafkaTemplateFactoryTest {

    @Test
    void kafkaTemplateShouldNotBeCreatedForEmptyProperties() {
        SpringwolfKafkaConfigProperties configProperties = new SpringwolfKafkaConfigProperties();

        SpringwolfKafkaTemplateFactory templateFactory = new SpringwolfKafkaTemplateFactory(configProperties);

        Optional<KafkaTemplate<Object, Object>> kafkaTemplate = templateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isNotPresent();
    }

    @Test
    void kafkaTemplateShouldNotBeCreatedForEmptyProducerConfiguration() {
        SpringwolfKafkaConfigProperties configProperties = new SpringwolfKafkaConfigProperties();
        configProperties.setPublishing(new SpringwolfKafkaConfigProperties.Publishing());

        SpringwolfKafkaTemplateFactory templateFactory = new SpringwolfKafkaTemplateFactory(configProperties);

        Optional<KafkaTemplate<Object, Object>> kafkaTemplate = templateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isNotPresent();
    }

    @Test
    void kafkaTemplateShouldBeCreatedWithProducerConfiguration() {
        SpringwolfKafkaConfigProperties configProperties = new SpringwolfKafkaConfigProperties();
        SpringwolfKafkaConfigProperties.Publishing publishing = new SpringwolfKafkaConfigProperties.Publishing();
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
