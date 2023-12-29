// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.protobuf.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.jackson.datatype.protobuf.ProtobufJacksonConfig;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfiguration {
    @Autowired
    private Jackson2ObjectMapperBuilder builder;

    /**
     * Creates and returns an ObjectMapper instance with the necessary modules registered.
     * @return ObjectMapper instance.
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = builder.build();
        objectMapper.registerModules(
                new ProtobufModule(ProtobufJacksonConfig.builder()
                        .acceptLiteralFieldnames(true)
                        .build()),
                new ProtobufPropertiesModule());
        return objectMapper;
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }
}
