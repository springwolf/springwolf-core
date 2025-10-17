// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.channel;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class CorrelationIDTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeCorrelationID() throws Exception {
        CorrelationID correlationId = CorrelationID.builder()
                .description("Default Correlation ID")
                .location("$message.header#/correlationId")
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/channel/correlationid.json");
        assertThatJson(serializer.toJsonString(correlationId)).isEqualTo(example);
    }
}
