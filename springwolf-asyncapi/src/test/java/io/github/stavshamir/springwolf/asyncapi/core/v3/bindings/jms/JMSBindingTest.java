// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.jms;

import io.github.stavshamir.springwolf.asyncapi.core.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.core.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.schema.Schema;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.server.Server;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class JMSBindingTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeJMSChannel() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "user.signup",
                        Channel.builder()
                                .description(
                                        "This application receives command messages from this channel about users to sign up.")
                                .bindings(Map.of(
                                        "jms",
                                        JMSChannelBinding.builder()
                                                .destination("user-sign-up")
                                                .destinationType(JMSChannelBindingDestinationType.FIFO_QUEUE)
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/jms/README.md#examples-1
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/jms/jms-channel.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "channels.user.signup.publish")
                .isEqualTo(example);
    }

    // FIXME: See https://github.com/asyncapi/bindings/issues/232
    @Test
    void shouldSerializeJMSServerBinding() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .servers(Map.of(
                        "production",
                        Server.builder()
                                .host("jms://my-activemq-broker:61616")
                                .protocol("jms")
                                .protocolVersion("1.1")
                                .description("The production ActiveMQ broker accessed via JMS.")
                                .bindings(Map.of(
                                        "jms",
                                        JMSServerBinding.builder()
                                                .jmsConnectionFactory("org.apache.activemq.ActiveMQConnectionFactory")
                                                .properties(List.of(
                                                        Map.of("name", "disableTimeStampsByDefault", "value", false)))
                                                .clientID("my-application-1")
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/jms/README.md#examples
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/jms/jms-server.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "servers.production.bindings.jms.bindingVersion")
                .isEqualTo(example);
    }

    @Test
    @Disabled("https://github.com/asyncapi/bindings/issues/232 is even more broken")
    void shouldSerializeJMSMessageBinding() throws IOException {
        var message = Map.of(
                "message",
                Message.builder()
                        .bindings(Map.of(
                                "jms",
                                JMSMessageBinding.builder()
                                        .headers(Schema.builder().build())
                                        .build()))
                        .headers(MessageHeaders.of(Schema.builder().build()))
                        .build());

        // https://github.com/asyncapi/bindings/blob/master/jms/README.md#examples
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/jms/jms-message.yaml");
        assertThatJson(serializer.toJsonString(message)).isEqualTo(example);
    }
}
