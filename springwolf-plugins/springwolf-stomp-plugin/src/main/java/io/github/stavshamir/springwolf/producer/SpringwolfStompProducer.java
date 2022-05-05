package io.github.stavshamir.springwolf.producer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class SpringwolfStompProducer {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Getter
    private boolean isEnabled = true;

    public SpringwolfStompProducer(@Autowired SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void send(String topic, Map<String, Object> payload) {
        simpMessagingTemplate.convertAndSend(topic, payload);
    }
}
