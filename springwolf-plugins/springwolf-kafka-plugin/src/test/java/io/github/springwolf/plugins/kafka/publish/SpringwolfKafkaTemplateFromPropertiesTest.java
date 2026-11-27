// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.publish;

import io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigProperties;
import io.github.springwolf.plugins.kafka.producer.SpringwolfKafkaTemplateFromProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SpringwolfKafkaTemplateFromPropertiesTest {

    @Test
    void kafkaTemplateShouldNotBeCreatedForEmptyProperties() {
        // given
        SpringwolfKafkaConfigProperties configProperties = new SpringwolfKafkaConfigProperties();

        // when
        SpringwolfKafkaTemplateFromProperties kafkaTemplateProvider =
                new SpringwolfKafkaTemplateFromProperties(configProperties);

        // then
        assertThat(kafkaTemplateProvider.isPresent()).isFalse();
        assertThat(kafkaTemplateProvider.get("topic")).isNotPresent();
    }

    @Test
    void kafkaTemplateShouldNotBeCreatedForEmptyProducerConfiguration() {
        // given
        SpringwolfKafkaConfigProperties configProperties = new SpringwolfKafkaConfigProperties();
        configProperties.setPublishing(new SpringwolfKafkaConfigProperties.Publishing());

        // when
        SpringwolfKafkaTemplateFromProperties kafkaTemplateProvider =
                new SpringwolfKafkaTemplateFromProperties(configProperties);

        // then
        assertThat(kafkaTemplateProvider.isPresent()).isFalse();
        assertThat(kafkaTemplateProvider.get("topic")).isNotPresent();
    }

    @Test
    void kafkaTemplateShouldBeCreatedWithProducerConfiguration() {
        // given
        SpringwolfKafkaConfigProperties configProperties = configPropertiesWithProducer();

        // when
        SpringwolfKafkaTemplateFromProperties kafkaTemplateProvider =
                new SpringwolfKafkaTemplateFromProperties(configProperties);

        // then
        assertThat(kafkaTemplateProvider.isPresent()).isTrue();

        Map<String, Object> configurationProperties =
                kafkaTemplateProvider.get("topic").get().getProducerFactory().getConfigurationProperties();
        assertThat(configurationProperties)
                .containsEntry("acks", "all")
                .containsEntry("security.protocol", "SSL")
                .containsEntry("ssl.protocol", "TLSv1.3")
                .containsEntry("custom.producer.property", "custom-value");
    }

    private SpringwolfKafkaConfigProperties configPropertiesWithProducer() {
        KafkaProperties.Producer producer = new KafkaProperties.Producer();
        producer.setAcks("all");
        producer.getSecurity().setProtocol("SSL");
        producer.getSsl().setProtocol("TLSv1.3");
        producer.getProperties().put("custom.producer.property", "custom-value");

        SpringwolfKafkaConfigProperties.Publishing publishing = new SpringwolfKafkaConfigProperties.Publishing();
        publishing.setEnabled(true);
        publishing.setProducer(producer);

        SpringwolfKafkaConfigProperties configProperties = new SpringwolfKafkaConfigProperties();
        configProperties.setPublishing(publishing);
        return configProperties;
    }
}
