package io.github.stavshamir.springwolf.asyncapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiSerializerService;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AsyncApiController {

    private final AsyncApiService asyncApiService;
    private final AsyncApiSerializerService serializer;

    @GetMapping(path ={"${springwolf.paths.docs:/springwolf/docs}","${springwolf.paths.docs:/springwolf/docs.json}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public String asyncApiJson() throws JsonProcessingException {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
        return serializer.toJsonString(asyncAPI);
    }

    @GetMapping(path ="${springwolf.paths.docs:/springwolf/docs}.yaml", produces = "application/yaml")
    public String asyncApiYaml() throws JsonProcessingException {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
        return serializer.toYaml(asyncAPI);
    }
}
