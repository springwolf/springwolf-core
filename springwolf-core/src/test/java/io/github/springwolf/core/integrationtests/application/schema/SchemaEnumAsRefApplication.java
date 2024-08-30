// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.schema;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchemaEnumAsRefApplication {
    @Bean
    public Listener listener() {
        return new Listener();
    }

    static class Listener {
        @AsyncListener(operation = @AsyncOperation(channelName = "enum-channel"))
        public void listen(Schemas.MyEnumRoot payload) {}
    }

    public class Schemas {
        public record MyEnumRoot(MyEnumObject myEnumObjectField) {}

        @Schema(enumAsRef = true)
        public enum MyEnumObject {
            DOG,
            CAT;
        }
    }
}
