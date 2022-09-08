package io.github.stavshamir.springwolf.example.configuration;

import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    public final static String PRODUCER_TOPIC = "example-producer-topic";
    public final static String CONSUMER_TOPIC = "example-consumer-topic";
    private final String BOOTSTRAP_SERVERS;

    public KafkaConfiguration(@Value("${kafka.bootstrap.servers}") String bootstrapServers) {
        this.BOOTSTRAP_SERVERS = bootstrapServers;
    }

    @Bean
    public Map<String, Object> consumerConfiguration() {
        return ImmutableMap.<String, Object>builder()
                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS)
                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class)
                .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
                .put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true)
                .put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100")
                .put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000")
                .put(ConsumerConfig.GROUP_ID_CONFIG, "example-group-id")
                .build();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ExamplePayloadDto> exampleKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ExamplePayloadDto> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();

        DefaultKafkaConsumerFactory<String, ExamplePayloadDto> consumerFactory =
                new DefaultKafkaConsumerFactory<>(consumerConfiguration(), new StringDeserializer(), new JsonDeserializer<>(ExamplePayloadDto.class));
        containerFactory.setConsumerFactory(consumerFactory);

        return containerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AnotherPayloadDto> anotherKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AnotherPayloadDto> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();

        DefaultKafkaConsumerFactory<String, AnotherPayloadDto> consumerFactory =
                new DefaultKafkaConsumerFactory<>(consumerConfiguration(), new StringDeserializer(), new JsonDeserializer<>(AnotherPayloadDto.class));
        containerFactory.setConsumerFactory(consumerFactory);
        containerFactory.setBatchListener(true);

        return containerFactory;
    }

    private Map<String, Object> producerConfiguration() {
       return ImmutableMap.of(
               ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS,
               ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
               ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
       );
    }

    @Bean
    public KafkaTemplate<String, ExamplePayloadDto> examplePayloadKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfiguration()));
    }

    @Bean
    public KafkaTemplate<String, AnotherPayloadDto> anotherPayloadKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfiguration()));
    }

}
