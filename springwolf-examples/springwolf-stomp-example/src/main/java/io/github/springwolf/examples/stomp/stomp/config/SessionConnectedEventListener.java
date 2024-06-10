// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.stomp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class SessionConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {
    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectedEvent.getMessage());
        log.debug(
                "Client connected with session id: {} and username: {}",
                headerAccessor.getSessionId(),
                sessionConnectedEvent.getUser().getName());
    }

    @EventListener
    public void onSessionDisconnect(SessionDisconnectEvent sessionDisconnectEvent) {
        log.debug(
                "Client disconnected with username: {}",
                sessionDisconnectEvent.getUser().getName());
    }
}
