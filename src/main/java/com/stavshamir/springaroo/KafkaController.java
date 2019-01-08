package com.stavshamir.springaroo;

import com.stavshamir.springaroo.endpoints.KafkaEndpoint;
import com.stavshamir.springaroo.endpoints.KafkaEndpointsService;
import com.stavshamir.springaroo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaEndpointsService endpointsService;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaController(KafkaEndpointsService endpointsService, KafkaProducer kafkaProducer) {
        this.endpointsService = endpointsService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/endpoints")
    public Set<KafkaEndpoint> endpoints() {
        return endpointsService.getEndpoints();
    }

    @PostMapping("/publish")
    public void send(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        kafkaProducer.send(topic, payload);
    }

}
