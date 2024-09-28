// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.listener;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ListenerApplication {
    @Bean
    public Listener listener() {
        return new Listener();
    }

    @Bean
    public ClassListener classListener() {
        return new ClassListener();
    }

    static class Listener {
        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen(String payload) {}

        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen2(List<String> payload) {}

        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen3(Foo payload) {}

        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen4(List<Foo> payload) {}
    }

    public record Foo(Bar aFieldInFooRecord) {}

    public record Bar(String aFieldInBarRecord) {}

    @AsyncListener(operation = @AsyncOperation(channelName = "listener-class-channel"))
    static class ClassListener {
        public void listen(Integer payload) {}
    }
}
