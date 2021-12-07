package io.github.stavshamir.springwolf.producer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class SpringwolfAmqpProducer {

    private final RabbitTemplate rabbitTemplate;

    @Getter
    private boolean isEnabled = true;

    public SpringwolfAmqpProducer(@Autowired RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String channelName, Map<String, Object> payload) {
        rabbitTemplate.convertAndSend(channelName, payload);
    }

}
