package io.github.stavshamir.springwolf.asyncapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


@Service
public class DefaultAsyncApiSerializerService implements AsyncApiSerializerService {

    private ObjectMapper jsonMapper = new ObjectMapper();
    private PrettyPrinter printer = new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @PostConstruct
    void postConstruct() {
        jsonMapper.setConfig(
            jsonMapper.getSerializationConfig()
                .with(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
                .without(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        );
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    @Override
    public String toJsonString(AsyncAPI asyncAPI) throws JsonProcessingException {
        return jsonMapper.writer(printer).writeValueAsString(asyncAPI);
    }

    /**
     * Get the current object mapper configuration.
     */
    public ObjectMapper getObjectMapper() {
        return jsonMapper;
    }
    
    /**
     * Allows to customize the used objectMapper
     * <p>
     * Use {@link #getObjectMapper()} as a starting point
     */
    public void setObjectMapper(ObjectMapper mapper) {
        jsonMapper = mapper;
    }

    /**
     * Allows to override the used PrettyPrinter
     */
    public void setPrettyPrinter(PrettyPrinter prettyPrinter) {
        printer = prettyPrinter;
    }

}
