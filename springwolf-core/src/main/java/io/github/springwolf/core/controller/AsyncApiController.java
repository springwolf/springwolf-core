// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.core.JacksonException;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AsyncApiController {

    private final AsyncApiService asyncApiService;
    private final AsyncApiSerializerService serializer;

    @GetMapping(
            path = {
                "${springwolf.paths.docs:/springwolf/docs}", // maintainer note: remove, path.base instead
                "${springwolf.paths.docs:/springwolf/docs}.json", // maintainer note: remove, path.base instead
                "${springwolf.path.base:/springwolf}${springwolf.path.docs:/docs}",
                "${springwolf.path.base:/springwolf}${springwolf.path.docs:/docs}.json", // maintainer note: remove, use
                // accept header instead
                "${springwolf.path.base:/springwolf}${springwolf.path.docs:/docs}/{group}"
            },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String asyncApiJson(@PathVariable(required = false) Optional<String> group) throws JacksonException {
        return serializer.toJsonString(getAsyncAPI(group));
    }

    @GetMapping(
            path = {
                "${springwolf.paths.docs:/springwolf/docs}", // maintainer note: remove, path.base instead
                "${springwolf.paths.docs:/springwolf/docs}.yaml", // maintainer note: remove, path.base instead
                "${springwolf.path.base:/springwolf}${springwolf.path.docs:/docs}",
                "${springwolf.path.base:/springwolf}${springwolf.path.docs:/docs}.yaml", // maintainer note: remove, use
                // accept header instead
                "${springwolf.path.base:/springwolf}${springwolf.path.docs:/docs}/{group}",
            },
            produces = "application/yaml")
    public String asyncApiYaml(@PathVariable(required = false) Optional<String> group) throws JacksonException {
        return serializer.toYaml(getAsyncAPI(group));
    }

    private AsyncAPI getAsyncAPI(Optional<String> group) {
        log.debug("Returning AsyncApi document for group {}", group.orElse("default"));

        return group.map(groupName -> asyncApiService
                        .getForGroupName(groupName)
                        .orElseThrow(() ->
                                new IllegalArgumentException("AsyncAPI group %s was not found".formatted(groupName))))
                .orElseGet(asyncApiService::getAsyncAPI);
    }
}
