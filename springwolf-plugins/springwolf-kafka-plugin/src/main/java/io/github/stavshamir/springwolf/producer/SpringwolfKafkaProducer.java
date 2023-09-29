// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@RequiredArgsConstructor
public class SpringwolfKafkaProducer {

    private final Optional<KafkaTemplate<Object, Object>> kafkaTemplate;

    public boolean isEnabled() {
        return kafkaTemplate.isPresent();
    }

    public void send(String topic, String key, Map<String, String> headers, Object payload) {
        if (kafkaTemplate.isPresent()) {
            kafkaTemplate
                    .get()
                    .send(buildProducerRecord(topic, key, headers, payload))
                    .toCompletableFuture()
                    .join();
        } else {
            log.warn("Kafka producer is not configured");
        }
    }

    private ProducerRecord<Object, Object> buildProducerRecord(
            String topic, String key, Map<String, String> headers, Object payload) {
        List<Header> recordHeaders = headers != null ? buildHeaders(headers) : Collections.emptyList();

        return new ProducerRecord<>(topic, null, null, key, payload, recordHeaders);
    }

    private List<Header> buildHeaders(Map<String, String> headers) {
        return headers.entrySet().stream()
                .map(header ->
                        new RecordHeader(header.getKey(), header.getValue().getBytes(UTF_8)))
                .collect(Collectors.toList());
    }
}
