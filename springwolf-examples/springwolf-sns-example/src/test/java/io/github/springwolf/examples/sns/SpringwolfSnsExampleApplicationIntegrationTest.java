// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sns;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith({SnsTestContainerExtension.class})
class SpringwolfSnsExampleApplicationIntegrationTest {
    @DynamicPropertySource
    static void setUpTestContainers(DynamicPropertyRegistry registry) {
        SnsTestContainerExtension.overrideConfiguration(registry);
    }

    @Autowired
    private ApplicationContext context;

    @Test
    void testContext() {
        assertNotNull(context);
    }
}
