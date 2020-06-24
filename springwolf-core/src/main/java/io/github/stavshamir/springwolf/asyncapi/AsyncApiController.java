package io.github.stavshamir.springwolf.asyncapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/asyncapi")
@RequiredArgsConstructor
public class AsyncApiController {

    private final AsyncApiService asyncApiService;
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @PostConstruct
    void postConstruct() {
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @GetMapping(value = "/docs", produces = MediaType.APPLICATION_JSON_VALUE)
    public String asyncApi() throws JsonProcessingException {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
        return jsonMapper.writeValueAsString(asyncAPI);
    }

    @PostMapping("/kafka/publish")
    public void publish(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        log.info("Publishing to kafka topic {}: {}", topic, payload);
        kafkaProducer.send(topic, payload);
    }

}
