// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static io.github.springwolf.examples.stomp.stomp.config.Constants.WEBSOCKET_BROKER_QUEUE;
import static io.github.springwolf.examples.stomp.stomp.config.Constants.WEBSOCKET_BROKER_TOPIC;
import static io.github.springwolf.examples.stomp.stomp.config.Constants.WEBSOCKET_ENDPOINT;
import static io.github.springwolf.examples.stomp.stomp.config.Constants.WEBSOCKET_PREFIX_APP;
import static io.github.springwolf.examples.stomp.stomp.config.Constants.WEBSOCKET_PREFIX_USER;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);

        registry.enableSimpleBroker(WEBSOCKET_BROKER_TOPIC, WEBSOCKET_BROKER_QUEUE);
        registry.setApplicationDestinationPrefixes(WEBSOCKET_PREFIX_APP);
        registry.setUserDestinationPrefix(WEBSOCKET_PREFIX_USER);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);

        registry.addEndpoint(WEBSOCKET_ENDPOINT)
                .setHandshakeHandler(new WebSocketHandshake())
                .setAllowedOriginPatterns("*");

        registry.addEndpoint(WEBSOCKET_ENDPOINT)
                .setHandshakeHandler(new WebSocketHandshake())
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
