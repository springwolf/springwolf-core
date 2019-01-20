package com.stavshamir.springaroo;

import com.google.common.collect.ImmutableMap;
import com.stavshamir.springaroo.endpoints.KafkaEndpoint;
import com.stavshamir.springaroo.endpoints.KafkaEndpointsService;
import com.stavshamir.springaroo.model.Models;
import com.stavshamir.springaroo.producer.KafkaProducer;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/kafka-api")
@CrossOrigin
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

    @GetMapping(value = "/endpoints", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<KafkaEndpoint> endpoints() {
        return endpointsService.getEndpoints();
    }

    @GetMapping(value = "/models", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Model> models() {
        return models.getDefinitions();
    }

    @PostMapping("/publish")
    public void send(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        kafkaProducer.send(topic, payload);
    }

}

