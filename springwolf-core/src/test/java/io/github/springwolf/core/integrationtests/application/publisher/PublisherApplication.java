// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.publisher;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.core.integrationtests.application.listener.ListenerApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PublisherApplication {
    @Bean
    public Publisher publisher() {
        return new Publisher();
    }

    @Bean
    public ClassPublisher classPublisher() {
        return new ClassPublisher();
    }

    static class Publisher {
        @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-channel"))
        public void publish(String payload) {}

        @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-channel"))
        public void publish2(List<String> payload) {}

        @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-channel"))
        public void publish3(ListenerApplication.Foo payload) {}

        @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-channel"))
        public void publish4(List<ListenerApplication.Foo> payload) {}
    }

    @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-class-channel"))
    static class ClassPublisher {
        public void publish(Integer payload) {}
    }
}
