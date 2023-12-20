// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.sqs;

import io.github.stavshamir.springwolf.asyncapi.core.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.core.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.Channel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class SQSBindingTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    @Disabled("Binding example is not updated to AsyncAPI v3")
    void shouldSerializeSQSConsumer() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "user-signedup",
                        Channel.builder()
                                .description("A user has signed up for our service")
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/sqs/README.md#sns-to-sqs-pub-sub-1
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/sqs/sqs-consumer.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "channels.user-signedup.bindings.sns.bindingVersion")
                .isEqualTo(example);
    }
}
