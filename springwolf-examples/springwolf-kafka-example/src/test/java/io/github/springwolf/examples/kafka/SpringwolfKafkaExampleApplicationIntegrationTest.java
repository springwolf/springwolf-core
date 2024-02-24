// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        classes = {SpringwolfKafkaExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@DirtiesContext
class SpringwolfKafkaExampleApplicationIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testContext() {
        assertNotNull(context);
    }
}
