// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel;

import io.github.stavshamir.springwolf.asyncapi.core.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.core.v3.jackson.DefaultAsyncApiSerializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class CorrelationIDTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeCorrelationID() throws IOException {
        CorrelationID correlationId = CorrelationID.builder()
                .description("Default Correlation ID")
                .location("$message.header#/correlationId")
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/channel/correlationid.json");
        assertThatJson(serializer.toJsonString(correlationId)).isEqualTo(example);
    }
}
