package io.github.stavshamir.springwolf.example.stomp;

import io.github.stavshamir.springwolf.example.stomp.consumers.ExampleConsumer;
import io.github.stavshamir.springwolf.example.stomp.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.stomp.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.stomp.util.BaseStompUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static io.github.stavshamir.springwolf.example.stomp.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context.
 */
@SpringBootTest(
        classes = {SpringwolfStompExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerIntegrationWithDockerIntegrationTest {

    //    @Autowired
    //    SpringwolfStompProducer springwolfStompProducer; // TODO:

    @SpyBean
    ExampleConsumer exampleConsumer;

    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"));

    @LocalServerPort
    private int port;

    private String wsPath;
    private WebSocketStompClient webSocketStompClient;

    private final ExamplePayloadDto payload = new ExamplePayloadDto();

    @BeforeEach
    void setup() {
        wsPath = String.format("ws://localhost:%d/myendpoint", port);
        webSocketStompClient = new WebSocketStompClient(
                new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));

        payload.setSomeString("foo");
        payload.setSomeLong(5);
        payload.setSomeEnum(FOO1);
    }

    @Test
    void producerCanUseSpringwolfConfigurationToSendMessage()
            throws ExecutionException, InterruptedException, TimeoutException {
        // given
        BaseStompUtil<AnotherPayloadDto> stompTestUtil = new BaseStompUtil<>(
                webSocketStompClient, wsPath, List.of("/queue/another-queue"), AnotherPayloadDto.class);

        // when
        //        springwolfStompProducer.send("example-queue", payload); // TODO:

        // then
        AnotherPayloadDto response = stompTestUtil.getMessage();
        assertEquals(response.getExample(), payload);
    }
}
