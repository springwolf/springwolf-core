// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.jackson.datatype.protobuf.ProtobufJacksonConfig;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.core.standalone.plugin.StandalonePlugin;
import io.github.springwolf.examples.kafka.configuration.ObjectMapperConfiguration;
import io.github.springwolf.examples.kafka.configuration.ProtobufPropertiesModule;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * Protobuf ModelConverter must be loaded before other modelConverters
 */
@Order(0)
@StandaloneConfiguration
public class StandaloneProtobufPlugin implements StandalonePlugin {
    private static final ModelResolver protobufModelResolver;

    static {
        ObjectMapper protobufObjectMapper = new ObjectMapper();
        protobufObjectMapper.registerModule(new ProtobufModule(
                ProtobufJacksonConfig.builder().acceptLiteralFieldnames(true).build()));
        protobufObjectMapper.registerModule(new ProtobufPropertiesModule());
        protobufModelResolver = new ObjectMapperConfiguration().modelResolver(protobufObjectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public ModelConverter protobufModelResolver() {
        return protobufModelResolver;
    }

    @Override
    public List<ModelConverter> getModelConverters() {
        return List.of(protobufModelResolver);
    }
}
