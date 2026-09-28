// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringwolfKafkaExampleApplication {
    private static final String AVRO_SERIALIZABLE_PACKAGES = "io.github.springwolf.examples.kafka.dto.avro";

    public static void main(String[] args) {
        System.setProperty("org.apache.avro.SERIALIZABLE_PACKAGES", AVRO_SERIALIZABLE_PACKAGES);

        SpringApplication.run(SpringwolfKafkaExampleApplication.class, args);
    }
}
