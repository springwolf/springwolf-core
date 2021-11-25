package io.github.stavshamir.springwolf.example.producers;

import io.github.stavshamir.springwolf.asyncapi.SpringwolfProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpringwolfAmqpProducer implements SpringwolfProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String channelName, Map<String, Object> payload) {
        rabbitTemplate.convertAndSend(channelName, payload);
    }

}
