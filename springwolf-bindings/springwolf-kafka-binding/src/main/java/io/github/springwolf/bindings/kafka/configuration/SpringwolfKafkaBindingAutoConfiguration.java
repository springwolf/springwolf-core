// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.kafka.configuration;

import io.github.springwolf.bindings.kafka.scanners.messages.KafkaMessageBindingProcessor;
import io.github.springwolf.bindings.kafka.scanners.operations.KafkaOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringValueResolver;

/**
 * Autoconfiguration for the springwolf Kafka Binding.
 */
@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
@StandaloneConfiguration
public class SpringwolfKafkaBindingAutoConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public KafkaOperationBindingProcessor kafkaOperationBindingProcessor(StringValueResolver stringValueResolver) {
        return new KafkaOperationBindingProcessor(stringValueResolver);
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public KafkaMessageBindingProcessor kafkaMessageBindingProcessor(StringValueResolver stringValueResolver) {
        return new KafkaMessageBindingProcessor(stringValueResolver);
    }
}
