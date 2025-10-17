// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith({JmsTestContainerExtension.class})
class SpringwolfJmsExampleApplicationIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void context() {
        assertThat(context).isNotNull();
    }
}
