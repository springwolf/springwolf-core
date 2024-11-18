// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.fqn;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FqnApplication {
    @Bean
    public Listener listener() {
        return new Listener();
    }

    static class Listener {
        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen(String payload) {}

        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen2(List<String> payload) {}

        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen3(Foo payload) {}

        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen4(Integer payload) {}
    }

    public record Foo(Bar aFieldInFooRecord) {}

    public record Bar(String aFieldInBarRecord) {}
}
