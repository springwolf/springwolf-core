// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sqs;

import io.github.springwolf.examples.sqs.consumers.ExampleConsumer;
import io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto;
import io.github.springwolf.plugins.sqs.producer.SpringwolfSqsProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Arrays;

import static io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real sqs instance.
 */
@SpringBootTest(
        classes = {SpringwolfSqsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class SqsProducerSystemTest {
    private static final String LOCALSTACK_NAME = "localstack";

    @Autowired
    SpringwolfSqsProducer springwolfSqsProducer;

    @SpyBean
    ExampleConsumer exampleConsumer;

    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"))
            .withCopyFilesInContainer(".env") // do not copy all files in the directory
            .withServices(LOCALSTACK_NAME)
            .withLogConsumer(LOCALSTACK_NAME, l -> Arrays.stream(
                            l.getUtf8StringWithoutLineEnding().split("(\n|\r\n)"))
                    .forEach(m -> log.debug("LOCALSTACK: {}", m)))
            .waitingFor(LOCALSTACK_NAME, Wait.forLogMessage(".*Ready.*", 1));

    @Test
    void producerCanUseSpringwolfConfigurationToSendMessage() {
        // given
        ExamplePayloadDto payload = new ExamplePayloadDto();
        payload.setSomeString("foo");
        payload.setSomeLong(5);
        payload.setSomeEnum(FOO1);

        // when
        springwolfSqsProducer.send("example-queue", payload);

        // then
        verify(exampleConsumer, timeout(10000)).receiveExamplePayload(payload);
    }
}
