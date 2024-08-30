// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms;

import io.github.springwolf.examples.jms.consumers.ExampleConsumer;
import io.github.springwolf.examples.jms.dtos.ExamplePayloadDto;
import io.github.springwolf.plugins.jms.producer.SpringwolfJmsProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Map;

import static io.github.springwolf.examples.jms.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
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
@Slf4j
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerSystemTest {
    private static final String APP_JMS = "activemq";

    @Autowired
    SpringwolfJmsProducer springwolfJmsProducer;

    @SpyBean
    ExampleConsumer exampleConsumer;

    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"))
            .withServices(APP_JMS)
            .withLogConsumer(APP_JMS, l -> log.debug("jms: {}", l.getUtf8StringWithoutLineEnding()))
            .waitingFor(APP_JMS, Wait.forLogMessage(".*Artemis Console available.*", 1));

    @Test
    @Order(2)
    void producerCanUseSpringwolfConfigurationToSendMessage() {
        // given
        ExamplePayloadDto payload = new ExamplePayloadDto();
        payload.setSomeString("foo");
        payload.setSomeLong(5);
        payload.setSomeEnum(FOO1);

        // when
        springwolfJmsProducer.send("example-queue", Map.of(), payload);

        // then
        verify(exampleConsumer, timeout(10000)).receiveExamplePayload(payload);
    }
}
