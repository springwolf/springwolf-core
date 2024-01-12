// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.configuration;

import io.github.stavshamir.springwolf.producer.SpringwolfKafkaTemplateFromProperties;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaTemplateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Primary
@RequiredArgsConstructor
public class CustomSpringwolfKafkaProducer implements SpringwolfKafkaTemplateProvider {
    private final SpringwolfKafkaTemplateFromProperties springwolfKafkaTemplateFromProperties;

    @Value("${KAFKA_SCHEMA_REGISTRY_URL:http://localhost:8081}")
    private String kafkaSchemaRegistryUrl;

    @Override
    public boolean isPresent() {
        return springwolfKafkaTemplateFromProperties.isPresent();
    }

    @Override
    public Optional<KafkaTemplate<Object, Object>> get(String topic) {
        return springwolfKafkaTemplateFromProperties
                .get(topic)
                .map(objectObjectKafkaTemplate -> customize(objectObjectKafkaTemplate, topic));
    }

    private KafkaTemplate<Object, Object> customize(KafkaTemplate<Object, Object> kafkaTemplate, String topic) {
        if (topic.contains("avro")) {
            return createAvroKafkaTemplate(kafkaTemplate);
        } else if (topic.contains("protobuf")) {
            return createProtobufKafkaTemplate(kafkaTemplate);
        }
        return kafkaTemplate;
    }

    private KafkaTemplate<Object, Object> createAvroKafkaTemplate(KafkaTemplate<Object, Object> kafkaTemplate) {
        Map<String, Object> producerProperties =
                new HashMap<>(kafkaTemplate.getProducerFactory().getConfigurationProperties());

        // configure the producerProperties to use avro serializer/deserializer
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        producerProperties.put("schema.registry.url", kafkaSchemaRegistryUrl);

        DefaultKafkaProducerFactory<Object, Object> producerFactory =
                new DefaultKafkaProducerFactory<>(producerProperties);
        return new KafkaTemplate<>(producerFactory);
    }

    private KafkaTemplate<Object, Object> createProtobufKafkaTemplate(KafkaTemplate<Object, Object> kafkaTemplate) {
        Map<String, Object> producerProperties =
                new HashMap<>(kafkaTemplate.getProducerFactory().getConfigurationProperties());

        // configure the producerProperties to use protobuf serializer/deserializer
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer");
        producerProperties.put("schema.registry.url", kafkaSchemaRegistryUrl);

        DefaultKafkaProducerFactory<Object, Object> producerFactory =
                new DefaultKafkaProducerFactory<>(producerProperties);
        return new KafkaTemplate<>(producerFactory);
    }
}
