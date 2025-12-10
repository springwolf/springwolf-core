// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.util;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class BaseStompUtil<R> {
    private final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    private final StompSession session;
    private final BlockingQueue<R> blockingQueue = new ArrayBlockingQueue<>(1);

    public BaseStompUtil(
            WebSocketStompClient webSocketStompClient, String wsPath, List<String> subscriptions, Type responseType)
            throws ExecutionException, InterruptedException, TimeoutException {
        webSocketStompClient.setMessageConverter(converter);

        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(@NonNull StompSession session, @NonNull StompHeaders connectedHeaders) {
                log.info("Connected to Stomp session");
            }

            @Override
            public void handleException(
                    @NonNull StompSession session,
                    StompCommand command,
                    @NonNull StompHeaders headers,
                    @NonNull byte[] payload,
                    @NonNull Throwable exception) {
                log.error("handleException", exception);
                super.handleException(session, command, headers, payload, exception);
            }

            @Override
            public void handleTransportError(@NonNull StompSession session, @NonNull Throwable exception) {
                log.error("handleTransportError", exception);
                super.handleTransportError(session, exception);
            }
        };

        session = webSocketStompClient.connectAsync(wsPath, sessionHandler).get(1, TimeUnit.SECONDS);

        subscriptions.forEach(subscription -> session.subscribe(subscription, new StompFrameHandler() {
            @Override
            public Type getPayloadType(@NonNull StompHeaders headers) {
                return responseType;
            }

            @Override
            @SuppressWarnings("unchecked")
            public void handleFrame(@NonNull StompHeaders headers, Object payload) {
                log.info("handleFrame {} with headers {}", payload, headers);
                blockingQueue.add((R) payload);
            }
        }));
    }

    @Nullable
    public R getMessage() throws InterruptedException {
        R message = blockingQueue.poll(1, TimeUnit.SECONDS);
        if (message == null) {
            log.warn("No message received");
        }
        return message;
    }

    public void send(String destination, Object payload) {
        log.info("Sending message to {} with payload {}", destination, payload);
        session.send(destination, payload);
    }
}
