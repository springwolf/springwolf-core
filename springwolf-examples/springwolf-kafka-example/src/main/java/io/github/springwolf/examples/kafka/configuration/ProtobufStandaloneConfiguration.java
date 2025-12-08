// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.configuration;

import tools.jackson.databind.ObjectMapper;
import com.hubspot.jackson.datatype.protobuf.ProtobufJacksonConfig;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * Protobuf ModelConverter must be loaded before other modelConverters
 */
@Order(0)
@StandaloneConfiguration
public class ProtobufStandaloneConfiguration {
    private static final ModelResolver protobufModelResolver;

    static {
        ObjectMapper protobufObjectMapper = new ObjectMapper();
        protobufObjectMapper.registerModule(new ProtobufModule(
                ProtobufJacksonConfig.builder().acceptLiteralFieldnames(true).build()));
        protobufObjectMapper.registerModule(new ProtobufPropertiesModule());
        protobufModelResolver = new ProtobufConfiguration().modelResolver(protobufObjectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public ModelConverter protobufModelResolver() {
        return protobufModelResolver;
    }
}
