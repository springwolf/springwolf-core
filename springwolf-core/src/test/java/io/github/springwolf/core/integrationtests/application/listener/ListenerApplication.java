// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.listener;

import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ListenerApplication {
    @Bean
    public Listener listener() {
        return new Listener();
    }

    class Listener {
        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen(String payload) {}
    }
}
