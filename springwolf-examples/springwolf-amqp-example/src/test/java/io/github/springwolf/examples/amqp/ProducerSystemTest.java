// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import io.github.springwolf.examples.amqp.consumers.ExampleConsumer;
import io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto;
import io.github.springwolf.plugins.amqp.producer.SpringwolfAmqpProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.amqp.AmqpIOException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real sqs instance.
 */
@SpringBootTest(
        classes = {SpringwolfAmqpExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(properties = {"spring.rabbitmq.host=localhost"})
@Slf4j
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerSystemTest {
    private static final String AMQP_NAME = "amqp";

    @Autowired
    SpringwolfAmqpProducer springwolfAmqpProducer;

    @SpyBean
    ExampleConsumer exampleConsumer;

    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"))
            .withCopyFilesInContainer(".env") // do not copy all files in the directory
            .withServices(AMQP_NAME)
            .waitingFor(AMQP_NAME, Wait.forLogMessage(".*Server startup complete.*", 1))
            .withLogConsumer(AMQP_NAME, l -> log.debug("amqp: {}", l.getUtf8StringWithoutLineEnding()));

    @Test
    @Order(1)
    void verifyAmqpIsAvailable() {
        ConnectionFactory factory = new CachingConnectionFactory("localhost");

        await().atMost(60, SECONDS).ignoreException(AmqpIOException.class).untilAsserted(() -> assertThat(
                        factory.createConnection().isOpen())
                .isTrue());
    }

    @Test
    @Order(2)
    void producerCanUseSpringwolfConfigurationToSendMessage() {
        // given
        ExamplePayloadDto payload = new ExamplePayloadDto();
        payload.setSomeString("foo");
        payload.setSomeLong(5);
        payload.setSomeEnum(FOO1);

        // when
        springwolfAmqpProducer.send(AmqpConstants.QUEUE_EXAMPLE_QUEUE, payload);

        // then
        verify(exampleConsumer, timeout(10000)).receiveExamplePayload(payload);
    }
}
