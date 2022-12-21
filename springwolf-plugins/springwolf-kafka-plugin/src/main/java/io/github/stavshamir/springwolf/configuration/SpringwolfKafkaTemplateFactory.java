package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2.model.server.Server;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@ConditionalOnBean(value = SpringwolfKafkaProducerConfiguration.class)
public class SpringwolfKafkaTemplateFactory {

    private final AsyncApiDocketService asyncApiDocketService;

    public Optional<KafkaTemplate<Object, Map<String, ?>>> buildKafkaTemplate() {
        return getBootstrapServers(asyncApiDocketService.getAsyncApiDocket())
                .map(this::buildProducerConfiguration)
                .map(producerConfiguration -> new DefaultKafkaProducerFactory<Object, Map<String, ?>>(producerConfiguration))
                .map(KafkaTemplate::new);
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
