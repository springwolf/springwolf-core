// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Tag;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageExample;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageTrait;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.Schema;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class MessageTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeMessage() throws IOException {
        Message message = Message.builder()
                .name("UserSignup")
                .title("User signup")
                .summary("Action to sign a user up.")
                .description("A longer description")
                .contentType("application/json")
                .tags(List.of(
                        Tag.builder().name("user").build(),
                        Tag.builder().name("signup").build(),
                        Tag.builder().name("register").build()))
                .headers(MessageHeaders.of(Schema.builder()
                        .type("object")
                        .properties(Map.of(
                                "correlationId",
                                        Schema.builder()
                                                .description("Correlation ID set by application")
                                                .type("string")
                                                .build(),
                                "applicationInstanceId",
                                        Schema.builder()
                                                .description(
                                                        "Unique identifier for a given instance of the publishing application")
                                                .type("string")
                                                .build()))
                        .build()))
                .payload(MessagePayload.of(Schema.builder()
                        .type("object")
                        .properties(Map.of(
                                "user",
                                        Schema.builder()
                                                .ref("#/components/schemas/userCreate")
                                                .build(),
                                "signup",
                                        Schema.builder()
                                                .ref("#/components/schemas/signup")
                                                .build()))
                        .build()))
                .correlationId(CorrelationID.builder()
                        .description("Default Correlation ID")
                        .location("$message.header#/correlationId")
                        .build())
                .traits(List.of(MessageTrait.builder()
                        .ref("#/components/messageTraits/commonHeaders")
                        .build()))
                .examples(List.of(MessageExample.builder()
                        .name("SimpleSignup")
                        .summary("A simple UserSignup example message")
                        .headers(Map.of(
                                "correlationId", "my-correlation-id",
                                "applicationInstanceId", "myInstanceId"))
                        .payload(Map.of(
                                "user", Map.of("someUserKey", "someUserValue"),
                                "signup", Map.of("someSignupKey", "someSignupValue")))
                        .build()))
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/channel/message.json");
        assertThatJson(serializer.toJsonString(message)).isEqualTo(example);
    }
}
