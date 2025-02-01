// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.jackson.datatype.protobuf.ProtobufJacksonConfig;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import io.github.springwolf.examples.kafka.configuration.ObjectMapperConfiguration;
import io.github.springwolf.examples.kafka.configuration.ProtobufPropertiesModule;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.core.annotation.Order;

import java.util.Collection;
import java.util.List;

/**
 * Protobuf ModelConverter must be loaded before other modelConverters
 */
@Order(0)
public class StandaloneProtobufPlugin implements StandalonePlugin {

    @Override
    public Collection<ModelConverter> getModelConverters() {
        ObjectMapper protobufObjectMapper = new ObjectMapper();
        protobufObjectMapper.registerModule(new ProtobufModule(
                ProtobufJacksonConfig.builder().acceptLiteralFieldnames(true).build()));
        protobufObjectMapper.registerModule(new ProtobufPropertiesModule());
        ModelResolver protobufModelResolver = new ObjectMapperConfiguration().modelResolver(protobufObjectMapper);

        return List.of(protobufModelResolver);
    }
}
