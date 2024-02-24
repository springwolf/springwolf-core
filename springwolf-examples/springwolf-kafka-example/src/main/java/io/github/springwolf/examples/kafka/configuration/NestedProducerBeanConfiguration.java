// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.configuration;

import io.github.springwolf.examples.kafka.producers.NestedProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NestedProducerBeanConfiguration {

    /**
     * Define Producer via spring bean.
     * Annotations present on that class should also be found
     */
    @Bean
    public NestedProducer nestedProducer() {
        return new NestedProducer();
    }
}
