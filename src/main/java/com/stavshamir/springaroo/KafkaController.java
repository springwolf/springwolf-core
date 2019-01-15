package com.stavshamir.springaroo;

import com.google.common.collect.ImmutableMap;
import com.stavshamir.springaroo.endpoints.KafkaEndpointsService;
import com.stavshamir.springaroo.model.Models;
import com.stavshamir.springaroo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/kafka-api")
public class KafkaController {

    private final KafkaEndpointsService endpointsService;
    private final KafkaProducer kafkaProducer;
    private final Models models;

    @Autowired
    public KafkaController(KafkaEndpointsService endpointsService, KafkaProducer kafkaProducer, Models models) {
        this.endpointsService = endpointsService;
        this.kafkaProducer = kafkaProducer;
        this.models = models;
    }

    @GetMapping(value = "/documentation", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> documentation() {
        return ImmutableMap.<String, Object>builder()
                .put("endpoints", endpointsService.getEndpoints())
                .put("definitions", models.getDefinitions())
                .build();
    }

    @PostMapping("/publish")
    public void send(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        kafkaProducer.send(topic, payload);
    }

}
