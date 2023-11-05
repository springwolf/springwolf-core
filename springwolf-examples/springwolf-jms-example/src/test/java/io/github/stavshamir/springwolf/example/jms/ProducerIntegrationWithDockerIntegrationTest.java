// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.jms;

import io.github.stavshamir.springwolf.example.jms.consumers.ExampleConsumer;
import io.github.stavshamir.springwolf.example.jms.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.producer.SpringwolfJmsProducer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static io.github.stavshamir.springwolf.example.jms.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real jms instance.
 */
@SpringBootTest(
        classes = {SpringwolfJmsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
@TestMethodOrder(OrderAnnotation.class)
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerIntegrationWithDockerIntegrationTest {

    @Autowired
    SpringwolfJmsProducer springwolfJmsProducer;

    @SpyBean
    ExampleConsumer exampleConsumer;

    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"));

    @Test
    @Order(2)
    void producerCanUseSpringwolfConfigurationToSendMessage() throws InterruptedException {
        // given
        ExamplePayloadDto payload = new ExamplePayloadDto();
        payload.setSomeString("foo");
        payload.setSomeLong(5);
        payload.setSomeEnum(FOO1);

        // when
        Thread.sleep(30000);
        springwolfJmsProducer.send("example-queue", payload);

        // then
        verify(exampleConsumer, timeout(10000)).receiveExamplePayload(payload);
    }
}
