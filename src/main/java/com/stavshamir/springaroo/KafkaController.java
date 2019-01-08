package com.stavshamir.springaroo;

import com.stavshamir.springaroo.endpoints.KafkaEndpoint;
import com.stavshamir.springaroo.endpoints.KafkaEndpointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaEndpointsService endpointsService;

    @Autowired
    public KafkaController(KafkaEndpointsService endpointsService) {
        this.endpointsService = endpointsService;
    }

    @GetMapping("/endpoints")
    public Set<KafkaEndpoint> endpoints() {
        return endpointsService.getEndpoints();
    }

}
