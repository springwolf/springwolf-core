// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.polymorphic;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PolymorphicPayloadApplication {
    @Bean
    public Listener listener() {
        return new Listener();
    }

    static class Listener {
        @AsyncListener(operation = @AsyncOperation(channelName = "listener-channel"))
        public void listen(Payload payload) {}
    }

    public record Payload(Pet pet) {}

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
    @JsonSubTypes(
            value = {
                @JsonSubTypes.Type(value = Dog.class, name = "dog"),
                @JsonSubTypes.Type(value = Cat.class, name = "cat"),
            })
    @Schema(
            discriminatorProperty = "type",
            discriminatorMapping = {
                @DiscriminatorMapping(value = "dog", schema = Dog.class),
                @DiscriminatorMapping(value = "cat", schema = Cat.class)
            },
            oneOf = {Dog.class, Cat.class},
            subTypes = {Dog.class, Cat.class})
    public interface Pet {
        String getType();
    }

    public record Dog() implements Pet {
        @Override
        public String getType() {
            return "dog";
        }

        public String getDogSpecificField() {
            return "dog-specific-field";
        }
    }

    public record Cat() implements Pet {
        @Override
        public String getType() {
            return "cat";
        }

        public String getCatSpecificField() {
            return "cat-specific-field";
        }
    }
}
