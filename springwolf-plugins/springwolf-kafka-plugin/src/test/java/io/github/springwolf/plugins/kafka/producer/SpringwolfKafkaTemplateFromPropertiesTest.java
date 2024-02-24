// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.producer;

import io.github.springwolf.plugins.kafka.properties.SpringwolfKafkaConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;

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
        SpringwolfKafkaConfigProperties configProperties = new SpringwolfKafkaConfigProperties();
        SpringwolfKafkaConfigProperties.Publishing publishing = new SpringwolfKafkaConfigProperties.Publishing();
        publishing.setEnabled(true);
        publishing.setProducer(new KafkaProperties.Producer());

        configProperties.setPublishing(publishing);

        // when
        SpringwolfKafkaTemplateFromProperties kafkaTemplateProvider =
                new SpringwolfKafkaTemplateFromProperties(configProperties);

        // then
        assertThat(kafkaTemplateProvider.isPresent()).isTrue();

        Map<String, Object> configurationProperties =
                kafkaTemplateProvider.get("topic").get().getProducerFactory().getConfigurationProperties();
        assertThat(configurationProperties)
                .isEqualTo(new KafkaProperties.Producer().buildProperties(new DefaultSslBundleRegistry()));
    }
}
