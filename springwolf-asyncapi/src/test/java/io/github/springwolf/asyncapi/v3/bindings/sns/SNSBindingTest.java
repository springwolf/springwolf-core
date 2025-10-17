// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sns;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class SNSBindingTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeSNSChannel() throws Exception {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "user-signedup",
                        ChannelObject.builder()
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

        // https://github.com/asyncapi/bindings/tree/master/sns
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/sns/sns-channel.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "channels.user-signedup.bindings.sns.bindingVersion")
                .isEqualTo(example);
    }

    @Test
    @Disabled("The Spec Binding example needs to be reviewed, doesn't match specification")
    void shouldSerializeSNSSendMessageOperation() throws Exception {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "user-signedup",
                        ChannelObject.builder()
                                .description("A user has signed up to our service")
                                .bindings(Map.of(
                                        "sns", SNSChannelBinding.builder().build()))
                                // subscribe doesn't exist
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/tree/master/sns
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/sns/sns-sendMessage.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "channels.user-signedup.bindings.sns.bindingVersion")
                .isEqualTo(example);
    }
}
