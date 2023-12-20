// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.operation;

import io.github.stavshamir.springwolf.asyncapi.core.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.core.v3.jackson.DefaultAsyncApiSerializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class OperationReplyAddressTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeOperationReplyAddress() throws IOException {
        var operationReplyAddress = OperationReplyAddress.builder()
                .description("Consumer inbox")
                .location("$message.header#/replyTo")
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/server/operation-reply-address.json");
        assertThatJson(serializer.toJsonString(operationReplyAddress)).isEqualTo(example);
    }
}
