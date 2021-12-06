package io.github.stavshamir.springwolf.producer;

import com.asyncapi.v2.model.server.Server;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class SpringwolfKafkaProducer {

    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    @Getter
    private boolean isEnabled = true;

    public SpringwolfKafkaProducer(@Autowired AsyncApiDocket docket) {
        Optional<String> bootstrapServers = getBootstrapServers(docket);

        if (bootstrapServers.isPresent()) {
            Map<String, Object> config = buildProducerConfiguration(bootstrapServers.get());
            DefaultKafkaProducerFactory<String, Map<String, Object>> factory = new DefaultKafkaProducerFactory<>(config);
            this.kafkaTemplate = new KafkaTemplate<>(factory);
        } else {
            log.warn("No Kafka server found in the docket - at least one server must be configured with protocol 'kafka'");
            kafkaTemplate = null;
            isEnabled = false;
        }
    }

    public void send(String topic, Map<String, Object> payload) {
        if (!isEnabled) {
            log.debug("Kafka producer is disabled");
            return;
        }

        if (kafkaTemplate == null) {
            log.warn("Kafka producer is not configured");
            return;
        }

        kafkaTemplate.send(topic, payload);
    }

    private Optional<String> getBootstrapServers(AsyncApiDocket docket) {
        return docket.getServers().values().stream()
                .filter(server -> server.getProtocol().equals("kafka"))
                .map(Server::getUrl)
                .findFirst();
    }

    private Map<String, Object> buildProducerConfiguration(String bootstrapServers) {
        return ImmutableMap.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
                JsonSerializer.ADD_TYPE_INFO_HEADERS, false
        );
    }

}
