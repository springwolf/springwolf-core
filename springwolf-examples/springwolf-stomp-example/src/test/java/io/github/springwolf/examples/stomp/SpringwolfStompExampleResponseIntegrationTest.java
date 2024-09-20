// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp;

import io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.stomp.util.BaseStompUtil;
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

import static io.github.springwolf.examples.stomp.config.Constants.ANOTHER_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.EXAMPLE_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.SENDTOUSER_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.SENDTOUSER_RESPONSE_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.SENDTO_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.SENDTO_RESPONSE_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.WEBSOCKET_ENDPOINT;
import static io.github.springwolf.examples.stomp.config.Constants.WEBSOCKET_PREFIX_APP;
import static io.github.springwolf.examples.stomp.config.Constants.WEBSOCKET_PREFIX_USER;
import static io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
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
        wsPath = String.format("ws://localhost:%d/%s", port, WEBSOCKET_ENDPOINT);
        webSocketStompClient = new WebSocketStompClient(
                new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));

        payload.setSomeString("foo");
        payload.setSomeLong(5);
        payload.setSomeEnum(FOO1);
    }

    @Test
    void publishWithAnotherProducerTest() throws ExecutionException, InterruptedException, TimeoutException {
        // given
        BaseStompUtil<AnotherPayloadDto> stompTestUtil =
                new BaseStompUtil<>(webSocketStompClient, wsPath, List.of(ANOTHER_QUEUE), AnotherPayloadDto.class);

        // when
        stompTestUtil.send(WEBSOCKET_PREFIX_APP + EXAMPLE_QUEUE, payload);

        // then
        AnotherPayloadDto response = stompTestUtil.getMessage();
        assertEquals(response.getExample(), payload);
    }

    @Test
    void publishWithSendTo() throws ExecutionException, InterruptedException, TimeoutException {
        // given
        BaseStompUtil<ExamplePayloadDto> stompTestUtil = new BaseStompUtil<>(
                webSocketStompClient, wsPath, List.of(SENDTO_RESPONSE_QUEUE), ExamplePayloadDto.class);

        // when
        stompTestUtil.send(WEBSOCKET_PREFIX_APP + SENDTO_QUEUE, payload);

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
                List.of(WEBSOCKET_PREFIX_USER + SENDTOUSER_RESPONSE_QUEUE),
                ExamplePayloadDto.class);

        // when
        stompTestUtil.send(WEBSOCKET_PREFIX_APP + SENDTOUSER_QUEUE, payload);

        // then
        ExamplePayloadDto response = stompTestUtil.getMessage();
        assertEquals(payload, response);
    }
}
