// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.channel;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.Tag;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageExample;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageTrait;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class MessageTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeMessage() throws IOException {
        MessageObject message = MessageObject.builder()
                .name("UserSignup")
                .title("User signup")
                .summary("Action to sign a user up.")
                .description("A longer description")
                .contentType("application/json")
                .tags(List.of(
                        Tag.builder().name("user").build(),
                        Tag.builder().name("signup").build(),
                        Tag.builder().name("register").build()))
                .headers(MessageHeaders.of(SchemaObject.builder()
                        .type("object")
                        .properties(Map.of(
                                "correlationId",
                                        SchemaObject.builder()
                                                .description("Correlation ID set by application")
                                                .type("string")
                                                .build(),
                                "applicationInstanceId",
                                        SchemaObject.builder()
                                                .description(
                                                        "Unique identifier for a given instance of the publishing application")
                                                .type("string")
                                                .build()))
                        .build()))
                .payload(MessagePayload.of(SchemaObject.builder()
                        .type("object")
                        .properties(Map.of(
                                "user", SchemaReference.fromSchema("userCreate"),
                                "signup", SchemaReference.fromSchema("signup")))
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
