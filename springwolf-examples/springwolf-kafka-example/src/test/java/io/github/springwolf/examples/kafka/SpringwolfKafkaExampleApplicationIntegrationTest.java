// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {SpringwolfKafkaExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringwolfKafkaExampleApplicationIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void context() {
        assertThat(context).isNotNull();
    }
}
