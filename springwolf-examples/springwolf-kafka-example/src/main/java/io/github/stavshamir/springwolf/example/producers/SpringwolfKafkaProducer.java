package io.github.stavshamir.springwolf.example.producers;

import io.github.stavshamir.springwolf.asyncapi.SpringwolfProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpringwolfKafkaProducer implements SpringwolfProducer {

    @Autowired
    private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    @Override
    public void send(String channelName, Map<String, Object> payload) {
        kafkaTemplate.send(channelName, payload);
    }

}
