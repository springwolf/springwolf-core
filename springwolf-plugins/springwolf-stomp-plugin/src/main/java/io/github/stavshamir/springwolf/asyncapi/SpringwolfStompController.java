package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.producer.SpringwolfStompProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/springwolf/stomp")
@RequiredArgsConstructor
public class SpringwolfStompController {

    private final SpringwolfStompProducer stompProducer;

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        if (!stompProducer.isEnabled()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stomp producer is not enabled");
        }
        stompProducer.send(topic, payload);
    }

}
