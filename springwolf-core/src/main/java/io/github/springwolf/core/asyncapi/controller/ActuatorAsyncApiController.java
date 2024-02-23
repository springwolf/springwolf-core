// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.stavshamir.springwolf.asyncapi.v3.model.AsyncAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestControllerEndpoint(id = "springwolf")
@RequiredArgsConstructor
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
