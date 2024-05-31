// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.core.asyncapi.AsyncApiService;
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

    @GetMapping(
            path = {
                "${springwolf.paths.docs:/springwolf/docs}",
                "${springwolf.paths.docs:/springwolf/docs}.json",
                "${springwolf.path.base:/springwolf}/${springwolf.path.docs:/docs}",
                "${springwolf.path.base:/springwolf}/${springwolf.path.docs:/docs}.json"
            },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String asyncApiJson() throws JsonProcessingException {
        log.debug("Returning AsyncApi.json document");

        Object asyncAPI = asyncApiService.getAsyncAPI();
        return serializer.toJsonString(asyncAPI);
    }

    @GetMapping(
            path = {
                "${springwolf.paths.docs:/springwolf/docs}.yaml",
                "${springwolf.path.base:/springwolf}/${springwolf.path.docs:/docs}.yaml"
            },
            produces = "application/yaml")
    public String asyncApiYaml() throws JsonProcessingException {
        log.debug("Returning AsyncApi.yaml document");

        Object asyncAPI = asyncApiService.getAsyncAPI();
        return serializer.toYaml(asyncAPI);
    }
}
