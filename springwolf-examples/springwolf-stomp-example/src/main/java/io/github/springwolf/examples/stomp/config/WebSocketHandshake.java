// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.config;

import com.sun.security.auth.UserPrincipal;
import org.jspecify.annotations.NonNull;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class WebSocketHandshake extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(
            ServerHttpRequest request, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {
        Principal principal = request.getPrincipal();
        if (principal == null) {
            final var uniqueName = UUID.randomUUID().toString();

            principal = new UserPrincipal(uniqueName);
            logger.debug("New user " + principal);
        }
        return principal;
    }
}
