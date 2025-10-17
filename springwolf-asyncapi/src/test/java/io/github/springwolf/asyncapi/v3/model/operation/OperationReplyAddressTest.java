// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.operation;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class OperationReplyAddressTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeOperationReplyAddress() throws Exception {
        var operationReplyAddress = OperationReplyAddress.builder()
                .description("Consumer inbox")
                .location("$message.header#/replyTo")
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/server/operation-reply-address.json");
        assertThatJson(serializer.toJsonString(operationReplyAddress)).isEqualTo(example);
    }
}
