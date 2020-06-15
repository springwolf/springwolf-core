package io.github.stavshamir.swagger4kafka.asyncapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.stavshamir.swagger4kafka.asyncapi.types.AsyncAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

import static com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.WRITE_DOC_START_MARKER;

@CrossOrigin
@RestController
@RequestMapping("/asyncapi-docs")
@RequiredArgsConstructor
public class AsyncApiController {

    private final AsyncApiService asyncApiService;
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory().disable(WRITE_DOC_START_MARKER));

    @PostConstruct
    void postConstruct() {
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        yamlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @GetMapping
    public ResponseEntity<String> asyncApi(@RequestParam(value = "format", required = false) String format)
            throws JsonProcessingException {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

        switch (format.toLowerCase()) {
            case "json":
                String jsonBody = jsonMapper.writeValueAsString(asyncAPI);
                return ResponseEntity.ok()
                        .contentLength(jsonBody.length())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonBody);
            case "yaml":
                String yamlBody = yamlMapper.writeValueAsString(asyncAPI);
                return ResponseEntity.ok()
                        .contentLength(yamlBody.length())
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(yamlBody);
            default:
                throw new UnsupportedOperationException("Supported formats are \"json\" and \"yaml\"");
        }
    }

}
