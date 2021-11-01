package io.github.stavshamir.springwolf.asyncapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/springwolf")
@RequiredArgsConstructor
public class AsyncApiController {

    private final AsyncApiService asyncApiService;
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

}
