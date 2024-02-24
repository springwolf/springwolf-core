// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import io.github.springwolf.examples.amqp.consumers.ExampleConsumer;
import io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto;
import io.github.springwolf.plugins.amqp.producer.SpringwolfAmqpProducer;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
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
@Testcontainers
@DirtiesContext
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(properties = {"spring.rabbitmq.host=localhost"})
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerSystemTest {

    @Autowired
    SpringwolfAmqpProducer springwolfAmqpProducer;

    @SpyBean
    ExampleConsumer exampleConsumer;

    @Container
    public static DockerComposeContainer<?> environment =
            new DockerComposeContainer<>(new File("docker-compose.yml")).withServices("amqp");

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
        springwolfAmqpProducer.send("example-queue", payload);

        // then
        verify(exampleConsumer, timeout(10000)).receiveExamplePayload(payload);
    }
}
