package com.stavshamir.swagger4kafka;

import com.google.common.collect.ImmutableMap;
import com.stavshamir.swagger4kafka.configuration.Docket;
import com.stavshamir.swagger4kafka.configuration.Info;
import com.stavshamir.swagger4kafka.endpoints.KafkaEndpoint;
import com.stavshamir.swagger4kafka.endpoints.KafkaEndpointsService;
import com.stavshamir.swagger4kafka.model.Models;
import com.stavshamir.swagger4kafka.model.PayloadValidator;
import com.stavshamir.swagger4kafka.producer.KafkaProducer;
import io.swagger.models.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/kafka-api")
@CrossOrigin
public class KafkaController {

    private final Docket docket;
    private final KafkaEndpointsService endpointsService;
    private final KafkaProducer kafkaProducer;
    private final Models models;

    @Autowired
    public KafkaController(Docket docket, KafkaEndpointsService endpointsService, KafkaProducer kafkaProducer, Models models) {
        this.docket = docket;
        this.endpointsService = endpointsService;
        this.kafkaProducer = kafkaProducer;
        this.models = models;
    }

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Info info() {
        return Info.builder()
                .serviceName(docket.getServiceName())
                .bootstrapServers(docket.getBootstrapServers())
                .build();
    }

    @GetMapping(value = "/endpoints", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<KafkaEndpoint> endpoints() {
        return endpointsService.getEndpoints();
    }

    @GetMapping(value = "/models", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Model> models() {
        return models.getDefinitions();
    }

    @PostMapping("/validate")
    public Map<String, String> validate(@RequestBody Payload payload) throws ClassNotFoundException {
        try {
            PayloadValidator.validate(payload.getObject(), payload.getClassName());
        } catch (IllegalArgumentException ex) {
            return ImmutableMap.of("message", ex.getMessage());
        }

        return ImmutableMap.of("message", "valid");
    }

    @PostMapping("/publish")
    public void send(@RequestParam String topic, @RequestBody Payload payload) throws ClassNotFoundException {
        PayloadValidator.validate(payload.getObject(), payload.getClassName());
        kafkaProducer.send(topic, payload.getObject());
    }

    @Data
    @NoArgsConstructor
    private static class Payload {
        private String className;
        private Map<String, Object> object;
    }

}

