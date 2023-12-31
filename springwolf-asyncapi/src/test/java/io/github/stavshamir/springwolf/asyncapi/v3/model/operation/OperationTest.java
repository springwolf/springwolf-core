// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.operation;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Tag;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.security_scheme.SecurityScheme;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class OperationTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeOperation() throws IOException {
        var operation = Operation.builder()
                .title("User sign up")
                .summary("Action to sign a user up.")
                .description("A longer description")
                .channel(ChannelReference.builder().ref("#/channels/userSignup").build())
                .action(OperationAction.SEND)
                .security(List.of(SecurityScheme.builder().build()))
                .tags(List.of(
                        Tag.builder().name("user").build(),
                        Tag.builder().name("signup").build(),
                        Tag.builder().name("register").build()))
                .bindings(
                        Map.of("amqp", AMQPOperationBinding.builder().ack(false).build()))
                .traits(List.of(OperationTraits.builder()
                        .ref("#/components/operationTraits/kafka")
                        .build()))
                .messages(List.of(MessageReference.fromMessage("userSignedUp")))
                .reply(OperationReply.builder()
                        .address(OperationReplyAddress.builder()
                                .location("$message.header#/replyTo")
                                .build())
                        .channel(ChannelReference.builder()
                                .ref("#/channels/userSignupReply")
                                .build())
                        .messages(List.of(MessageReference.fromMessage("userSignedUpReply")))
                        .build())
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/operation/operation.json");
        assertThatJson(serializer.toJsonString(operation))
                // FIXME: https://github.com/asyncapi/spec/issues/1007
                .whenIgnoringPaths("bindings.amqp.bindingVersion", "bindings.amqp.expiration", "security")
                .isEqualTo(example);
    }
}
