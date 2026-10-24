// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.configuration;

import com.hubspot.jackson3.datatype.protobuf.ProtobufJacksonConfig;
import com.hubspot.jackson3.datatype.protobuf.ProtobufModule;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.swagger.v3.core.converter.ModelConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.JacksonModule;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class ProtobufConfiguration {

    @Bean
    public JacksonModule protobufJacksonModule() {
        return new ProtobufModule(
                ProtobufJacksonConfig.builder().acceptLiteralFieldnames(true).build());
    }

    @Bean
    public ModelConverter protobufModelConverter(SpringwolfConfigProperties properties) {
        return new ProtobufModelConverter(properties.isUseFqn());
    }
}
