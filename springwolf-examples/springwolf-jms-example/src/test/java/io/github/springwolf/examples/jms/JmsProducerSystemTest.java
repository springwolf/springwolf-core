// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms;

import io.github.springwolf.examples.jms.consumers.ExampleConsumer;
import io.github.springwolf.examples.jms.dtos.ExamplePayloadDto;
import io.github.springwolf.plugins.jms.producer.SpringwolfJmsProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.github.springwolf.examples.jms.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real jms instance.
 */
@SpringBootTest(
        classes = {SpringwolfJmsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class JmsProducerSystemTest {
    private static final String APP_JMS = "activemq";

    @Autowired
    SpringwolfJmsProducer springwolfJmsProducer;

    @SpyBean
    ExampleConsumer exampleConsumer;

    @Value("${spring.activemq.broker-url}")
    String brokerUrl;

    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"))
            .withCopyFilesInContainer(".env") // do not copy all files in the directory
            .withServices(APP_JMS)
            .withExposedService(APP_JMS, 61616)
            .withLogConsumer(APP_JMS, l -> log.debug("jms: {}", l.getUtf8StringWithoutLineEnding()))
            .waitingFor(APP_JMS, Wait.forLogMessage(".*Artemis Console available.*", 1));

    @DynamicPropertySource
    static void registerActiveMqBroker(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.activemq.broker-url",
                () -> String.format(
                        "tcp://%s:%s",
                        environment.getServiceHost(APP_JMS, 61616), environment.getServicePort(APP_JMS, 61616)));
    }

    @Test
    void producerCanUseSpringwolfConfigurationToSendMessage() {
        // given
        ExamplePayloadDto payload = new ExamplePayloadDto();
        payload.setSomeString("foo");
        payload.setSomeLong(5);
        payload.setSomeEnum(FOO1);

        // Awaitility is used, because message sent before amqp is ready are lost
        Awaitility.await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            // when
            log.info("Waiting for message in {} on {}", exampleConsumer, brokerUrl);
            springwolfJmsProducer.send("example-queue", Map.of(), payload);

            // then
            verify(exampleConsumer, atLeastOnce()).receiveExamplePayload(payload);
        });
    }
}
