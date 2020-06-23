package io.github.stavshamir.swagger4kafka.asyncapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.swagger4kafka.asyncapi.types.AsyncAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@CrossOrigin
@RestController
@RequestMapping("/asyncapi-docs")
@RequiredArgsConstructor
public class AsyncApiController {

    private final AsyncApiService asyncApiService;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @PostConstruct
    void postConstruct() {
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String asyncApi() throws JsonProcessingException {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
        return jsonMapper.writeValueAsString(asyncAPI);
    }

}
