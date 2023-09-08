package io.github.stavshamir.springwolf.asyncapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiSerializerService;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Slf4j
@RestControllerEndpoint(id = "springwolf")
@RequiredArgsConstructor
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_USE_MANAGEMENT_PORT, havingValue = "true")
public class ActuatorAsyncApiController {

    private final AsyncApiService asyncApiService;
    private final AsyncApiSerializerService serializer;

    @GetMapping(
            path = {"/docs", "/docs.json"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String asyncApiJson() throws JsonProcessingException {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
        return serializer.toJsonString(asyncAPI);
    }

    @GetMapping(path = "/docs.yaml", produces = "application/yaml")
    public String asyncApiYaml() throws JsonProcessingException {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
        return serializer.toYaml(asyncAPI);
    }
}
