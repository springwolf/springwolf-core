// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.fixtures.SpringwolfIntegrationTest;
import io.github.springwolf.core.integrationtests.application.complexSchema.PublisherGroupingComplexSchemaApplication;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@SpringBootTest(classes = PublisherGroupingComplexSchemaApplication.class)
@SpringwolfIntegrationTest
@TestPropertySource(
        properties = {
            "springwolf.docket.group-configs[0].group=UserMessage",
            "springwolf.docket.group-configs[0].action-to-match=",
            "springwolf.docket.group-configs[0].channel-name-to-match=",
            "springwolf.docket.group-configs[0].message-name-to-match=.*User",
            "springwolf.docket.group-configs[1].group=all & everything",
            "springwolf.docket.group-configs[1].action-to-match=",
            "springwolf.docket.group-configs[1].channel-name-to-match=.*",
            "springwolf.docket.group-configs[1].message-name-to-match=",
        })
class GroupingWithComplexSchemaIntegrationTest {
    @Autowired
    private AsyncApiService asyncApiService;

    @Test
    void shouldFindOnlyForGroupFoo() {
        AsyncAPI asyncAPI = asyncApiService.getForGroupName("UserMessage").get();

        assertThat(asyncAPI.getChannels().keySet()).containsExactlyInAnyOrder("publisher-channel");
        assertThat(asyncAPI.getChannels().get("publisher-channel").getMessages())
                .containsOnlyKeys(
                        "io.github.springwolf.core.integrationtests.application.complexSchema.PublisherGroupingComplexSchemaApplication.ComplexPublisher.User");
        assertThat(asyncAPI.getOperations())
                .containsOnlyKeys("publisher-channel_send_publish1", "publisher-channel_send_publish2");
        assertThat(asyncAPI.getComponents().getMessages())
                .containsOnlyKeys(
                        "io.github.springwolf.core.integrationtests.application.complexSchema.PublisherGroupingComplexSchemaApplication.ComplexPublisher.User");
        assertThat(asyncAPI.getComponents().getSchemas())
                .containsOnlyKeys(
                        "HeadersNotDocumented",
                        "io.github.springwolf.core.integrationtests.application.complexSchema.PublisherGroupingComplexSchemaApplication.ComplexPublisher.User",
                        "io.github.springwolf.core.integrationtests.application.complexSchema.PublisherGroupingComplexSchemaApplication.ComplexPublisher.Address",
                        "io.github.springwolf.core.integrationtests.application.complexSchema.PublisherGroupingComplexSchemaApplication.ComplexPublisher.City");

        MessageObject userMessage = (MessageObject)
                asyncAPI.getComponents()
                        .getMessages()
                        .get(
                                "io.github.springwolf.core.integrationtests.application.complexSchema.PublisherGroupingComplexSchemaApplication.ComplexPublisher.User");
        assertThat(userMessage.getPayload().getMultiFormatSchema().getSchema()).isInstanceOf(SchemaReference.class);
        SchemaReference fooSchemaRef = (SchemaReference)
                userMessage.getPayload().getMultiFormatSchema().getSchema();
        assertThat(fooSchemaRef.getRef())
                .isEqualTo(
                        "#/components/schemas/io.github.springwolf.core.integrationtests.application.complexSchema.PublisherGroupingComplexSchemaApplication.ComplexPublisher.User");
    }

    @Test
    void shouldFindAllForGroupAll() {
        // given
        AsyncAPI fullApi = asyncApiService.getAsyncAPI();

        // when
        AsyncAPI asyncAPIOpt =
                asyncApiService.getForGroupName("all & everything").get();

        // then

        // String and Integer get filtered.
        // Question: Why are they in the fullApi in the first place, if not referenced? (inline schema)
        fullApi.getComponents().getSchemas().remove(String.class.getName());
        fullApi.getComponents().getSchemas().remove(Integer.class.getName());

        assertThat(asyncAPIOpt).isEqualTo(fullApi);
    }
}
