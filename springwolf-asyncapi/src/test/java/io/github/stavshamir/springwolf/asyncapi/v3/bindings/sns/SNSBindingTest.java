// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.Channel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class SNSBindingTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeSNSChannel() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "user-signedup",
                        Channel.builder()
                                .description("A user has signed up to our service")
                                .bindings(Map.of(
                                        "sns",
                                        SNSChannelBinding.builder()
                                                .policy(SNSChannelBindingPolicy.builder()
                                                        .statements(List.of(SNSChannelBindingStatements.builder()
                                                                .effect(SNSChannelBindingEffect.ALLOW)
                                                                .principal("*")
                                                                .action("SNS:Publish")
                                                                .build()))
                                                        .build())
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/kafka/README.md#example-1
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/sns/sns-channel.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "channels.user-signedup.bindings.sns.bindingVersion")
                .isEqualTo(example);
    }

    @Test
    @Disabled("The Spec Binding example needs to be reviewed, doesn't match specification")
    void shouldSerializeSNSSendMessageOperation() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "user-signedup",
                        Channel.builder()
                                .description("A user has signed up to our service")
                                .bindings(Map.of(
                                        "sns", SNSChannelBinding.builder().build()))
                                // subscribe doesn't exist
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/kafka/README.md#example-1
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/sns/sns-sendMessage.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "channels.user-signedup.bindings.sns.bindingVersion")
                .isEqualTo(example);
    }
}
