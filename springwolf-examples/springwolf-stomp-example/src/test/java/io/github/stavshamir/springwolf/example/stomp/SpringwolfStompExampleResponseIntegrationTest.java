package io.github.stavshamir.springwolf.example.stomp;

import io.github.stavshamir.springwolf.example.stomp.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.stomp.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.stomp.util.BaseStompUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static io.github.stavshamir.springwolf.example.stomp.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringwolfStompExampleResponseIntegrationTest {

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
    void publishWithAnotherProducerTest() throws ExecutionException, InterruptedException, TimeoutException {
        // given
        BaseStompUtil<AnotherPayloadDto> stompTestUtil = new BaseStompUtil<>(
                webSocketStompClient, wsPath, List.of("/queue/another-queue"), AnotherPayloadDto.class);

        // when
        stompTestUtil.send("/app/queue/example-queue", payload);

        // then
        AnotherPayloadDto response = stompTestUtil.getMessage();
        assertEquals(response.getExample(), payload);
    }

    @Test
    void publishWithSendTo() throws ExecutionException, InterruptedException, TimeoutException {
        // given
        BaseStompUtil<ExamplePayloadDto> stompTestUtil = new BaseStompUtil<>(
                webSocketStompClient, wsPath, List.of("/topic/sendto-response-queue"), ExamplePayloadDto.class);

        // when
        stompTestUtil.send("/app/queue/sendto-queue", payload);

        // then
        ExamplePayloadDto response = stompTestUtil.getMessage();
        assertEquals(response, payload);
    }

    @Test
    void publishWithSendToUser() throws ExecutionException, InterruptedException, TimeoutException {
        // given
        BaseStompUtil<ExamplePayloadDto> stompTestUtil = new BaseStompUtil<>(
                webSocketStompClient,
                wsPath,
                List.of("/user/queue/sendtouser-response-queue"),
                ExamplePayloadDto.class);

        // when
        stompTestUtil.send("/app/queue/sendtouser-queue", payload);

        // then
        ExamplePayloadDto response = stompTestUtil.getMessage();
        assertEquals(response, payload);
    }
}
