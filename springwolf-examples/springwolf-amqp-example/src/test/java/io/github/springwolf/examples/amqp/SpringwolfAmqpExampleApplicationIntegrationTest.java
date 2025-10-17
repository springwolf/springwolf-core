// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SpringwolfAmqpExampleApplicationIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void context() {
        assertThat(context).isNotNull();
    }
}
