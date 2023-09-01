package io.github.stavshamir.springwolf.example.sqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith({SqsTestContainerExtension.class})
class SpringwolfSqsExampleApplicationIntegrationTest {
    @DynamicPropertySource
    static void setUpTestContainers(DynamicPropertyRegistry registry) {
        SqsTestContainerExtension.overrideConfiguration(registry);
    }

    @Autowired
    private ApplicationContext context;

    @Test
    void testContext() {
        assertNotNull(context);
    }
}
