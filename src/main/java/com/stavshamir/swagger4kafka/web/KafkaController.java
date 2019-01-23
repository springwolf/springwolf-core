package com.stavshamir.swagger4kafka.web;

import com.stavshamir.swagger4kafka.configuration.Docket;
import com.stavshamir.swagger4kafka.dtos.Info;
import com.stavshamir.swagger4kafka.dtos.KafkaEndpoint;
import com.stavshamir.swagger4kafka.dtos.Payload;
import com.stavshamir.swagger4kafka.dtos.ValidationMessage;
import com.stavshamir.swagger4kafka.services.KafkaEndpointsService;
import com.stavshamir.swagger4kafka.services.ModelsService;
import com.stavshamir.swagger4kafka.validation.PayloadValidator;
import com.stavshamir.swagger4kafka.producer.KafkaProducer;
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

    private final Docket docket;
    private final KafkaEndpointsService endpointsService;
    private final KafkaProducer kafkaProducer;
    private final ModelsService modelsService;

    @Autowired
    public KafkaController(Docket docket, KafkaEndpointsService endpointsService, KafkaProducer kafkaProducer, ModelsService modelsService) {
        this.docket = docket;
        this.endpointsService = endpointsService;
        this.kafkaProducer = kafkaProducer;
        this.modelsService = modelsService;
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
        return modelsService.getDefinitions();
    }

    @PostMapping("/validate")
    public ValidationMessage validate(@RequestBody Payload payload) {
        return PayloadValidator.validate(payload.getObject(), payload.getClassName());
    }

    @PostMapping("/publish")
    public ValidationMessage send(@RequestParam String topic, @RequestBody Payload payload) {
        ValidationMessage validation = PayloadValidator.validate(payload.getObject(), payload.getClassName());
        kafkaProducer.send(topic, payload.getObject());
        return validation;
    }

}

