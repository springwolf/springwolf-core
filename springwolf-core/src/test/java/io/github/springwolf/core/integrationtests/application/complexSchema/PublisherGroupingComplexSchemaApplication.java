// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.complexSchema;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class PublisherGroupingComplexSchemaApplication {
    @Bean
    public ComplexPublisher complexPublisher() {
        return new ComplexPublisher();
    }

    static class ComplexPublisher {
        @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-channel"))
        public void publish1(User payload) {}

        @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-channel"))
        public void publish2(List<User> payload) {}

        public record User(String name, Integer age, Collection<Address> addresses) {}

        public record Address(String streetName, String addition, City city) {}

        public record City(String zipCode, String name) {}
    }

    @AsyncPublisher(operation = @AsyncOperation(channelName = "publisher-channel2"))
    public void publish2(List<String> payload) {}
}
