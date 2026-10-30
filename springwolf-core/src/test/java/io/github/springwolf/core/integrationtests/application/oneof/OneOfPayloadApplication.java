// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.oneof;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OneOfPayloadApplication {
    @Bean
    public Listener listener() {
        return new Listener();
    }

    static class Listener {
        @AsyncListener(operation = @AsyncOperation(channelName = "oneof-channel"))
        public void listen(Payload payload) {}
    }

    public record Payload(
            @Schema(oneOf = {OperationA.class, OperationB.class})
            Object operation) {}

    public record OperationA(String valueA) {}

    public record OperationB(String valueB) {}
}
