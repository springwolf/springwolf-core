// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.jackson.datatype.protobuf.ProtobufJacksonConfig;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import io.github.springwolf.core.asyncapi.schemas.ModelConvertersProvider;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class ProtobufConfiguration {

    public ProtobufConfiguration(ModelConvertersProvider modelConvertersProvider) {
        ObjectMapper objectMapper = modelConvertersProvider.getObjectMapper();

        Module protobufModule = new ProtobufModule(
                ProtobufJacksonConfig.builder().acceptLiteralFieldnames(true).build());
        objectMapper.registerModule(protobufModule);

        Module protobufPropertiesModule = new ProtobufPropertiesModule();
        objectMapper.registerModule(protobufPropertiesModule);
    }
}
