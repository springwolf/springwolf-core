package io.github.stavshamir.swagger4kafka.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.swagger4kafka.test.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelsServiceTest {

    private ModelsService modelsService = new ModelsService();

    private static final String EXAMPLES_PATH = "/models/examples";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void simpleObject() {
        // Given a registered simple object
        // When register is called
        String modelName = modelsService.register(SimpleFoo.class);

        // Then the returned value is the class name
        assertThat(modelName)
                .isEqualTo("SimpleFoo");
    }

    @Test
    public void getExample_simpleObject() throws IOException {
        // Given a registered simple object
        String modelName = modelsService.register(SimpleFoo.class);

        // When getExample is called
        Map<String, Object> example = modelsService.getExample(modelName);
        Map expectedExample = jsonResourceAsMap(EXAMPLES_PATH + "/simple-foo.json");

        // Then it returns the correct example object as json
        assertThat(example)
                .isEqualTo(expectedExample);
    }

    @Test
    public void getExample_compositeObject() throws IOException {
        // Given a registered composite object
        String modelName = modelsService.register(CompositeFoo.class);

        // When getExample is called
        Map<String, Object> example = modelsService.getExample(modelName);
        Map expectedExample = jsonResourceAsMap(EXAMPLES_PATH + "/composite-foo.json");

        // Then it returns the correct example object as json
        assertThat(example)
                .isEqualTo(expectedExample);
    }

    @Test
    public void getDefinitions() throws IOException {
        Map expectedDefinitions = jsonResourceAsMap("/models/definitions.json");

        // Given registered classes
        modelsService.register(CompositeFoo.class);
        modelsService.register(FooWithEnum.class);

        // When getModelsAsJson is called
        String actualDefinitionsJson = objectMapper.writeValueAsString(modelsService.getDefinitions());
        Map actualDefinitions = objectMapper.readValue(actualDefinitionsJson, Map.class);

        // Then it contains the correctly serialized modelsService
        assertThat(actualDefinitions)
                .isEqualTo(expectedDefinitions);
    }

    private Map jsonResourceAsMap(String path) throws IOException {
        return Utils.jsonResourceAsMap(this.getClass(), path);
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    @Data
    @NoArgsConstructor
    private static class CompositeFoo {
        private String s;
        private SimpleFoo f;
    }

    @Data
    @NoArgsConstructor
    private static class FooWithEnum {
        private String s;
        private Bar b;

        private enum Bar {
            BAR1, BAR2
        }
    }

}