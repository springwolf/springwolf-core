// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;

@Slf4j
@WebEndpoint(id = "springwolf")
@RequiredArgsConstructor
public class ActuatorAsyncApiController {

    private final AsyncApiService asyncApiService;
    private final AsyncApiSerializerService serializer;

    @ReadOperation
    public String asyncApiJson() throws JsonProcessingException {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
        return serializer.toJsonString(asyncAPI);
    }
}
