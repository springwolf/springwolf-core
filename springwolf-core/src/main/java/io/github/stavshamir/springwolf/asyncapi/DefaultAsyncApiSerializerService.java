package io.github.stavshamir.springwolf.asyncapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Yaml;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


@Service
public class DefaultAsyncApiSerializerService implements AsyncApiSerializerService {

    private ObjectMapper jsonMapper = Json.mapper();
    private ObjectMapper yamlMapper = Yaml.mapper();
    private PrettyPrinter printer = new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @PostConstruct
    void postConstruct() {
        jsonMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        yamlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        ((YAMLFactory)yamlMapper.getFactory()).enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR);
    }

    @Override
    public String toJsonString(AsyncAPI asyncAPI) throws JsonProcessingException {
        return jsonMapper.writer(printer).writeValueAsString(asyncAPI);
    }

    @Override
    public String toYaml(AsyncAPI asyncAPI) throws JsonProcessingException {
        return yamlMapper.writer(printer).writeValueAsString(asyncAPI);
    }

    /**
     * Get the current object mapper configuration.
     *
     * @deprecated
     * This method is replaced by {@link DefaultAsyncApiSerializerService#getJsonObjectMapper()}
     */
    @Deprecated(since = "0.11.0", forRemoval = true)
    public ObjectMapper getObjectMapper() {
        return getJsonObjectMapper();
    }

    /**
     * Get the current JSON object mapper configuration.
     */
    public ObjectMapper getJsonObjectMapper() {
        return jsonMapper;
    }

    /**
     * Get the current YAML object mapper configuration.
     */
    public ObjectMapper getYamlObjectMapper() {
        return yamlMapper;
    }

    /**
     * Allows to customize the used objectMapper
     * <p>
     * Use {@link #getJsonObjectMapper()} as a starting point
     * @deprecated
     * This method is replaced by {@link DefaultAsyncApiSerializerService#setJsonObjectMapper(ObjectMapper)}
     */
    @Deprecated(since = "0.11.0", forRemoval = true)
    public void setObjectMapper(ObjectMapper mapper) {
        jsonMapper = mapper;
    }

    /**
     * Allows to customize the used objectMapper
     * <p>
     * Use {@link #getJsonObjectMapper()} as a starting point
     */
    public void setJsonObjectMapper(ObjectMapper mapper) {
        jsonMapper = mapper;
    }

    /**
     * Allows to customize the used objectMapper
     * <p>
     * Use {@link #getJsonObjectMapper()} as a starting point
     */
    public void setYamlObjectMapper(ObjectMapper mapper) {
        yamlMapper = mapper;
    }

    /**
     * Allows to override the used PrettyPrinter
     */
    public void setPrettyPrinter(PrettyPrinter prettyPrinter) {
        printer = prettyPrinter;
    }

}
