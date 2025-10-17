// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringwolfStompExampleApplicationIntegrationTest {
    @Autowired
    private ApplicationContext context;

    @Test
    void context() {
        assertThat(context).isNotNull();
    }
}
