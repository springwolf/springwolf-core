package io.github.stavshamir.springwolf.example.stomp.config;

import com.sun.security.auth.UserPrincipal;
import lombok.val;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class WebSocketHandshake extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(
            ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Principal principal = request.getPrincipal();
        if (principal == null) {
            val uniqueName = UUID.randomUUID().toString();

            principal = new UserPrincipal(uniqueName);
            logger.debug("New user " + principal);
        }
        return principal;
    }
}
