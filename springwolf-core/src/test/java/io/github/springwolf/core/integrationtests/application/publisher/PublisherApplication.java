// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.publisher;

import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PublisherApplication {
    @Bean
    public Publisher publisher() {
        return new Publisher();
    }

    static class Publisher {
        @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-channel"))
        public void publish(String payload) {}
    }
}
