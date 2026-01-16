// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.fixtures.SpringwolfIntegrationTest;
import io.github.springwolf.core.integrationtests.application.listener.ListenerApplication;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@SpringBootTest(classes = ListenerApplication.class)
@SpringwolfIntegrationTest
@TestPropertySource(
        properties = {
            "springwolf.docket.group-configs[0].group=FooMessage",
            "springwolf.docket.group-configs[0].action-to-match=",
            "springwolf.docket.group-configs[0].channel-name-to-match=",
            "springwolf.docket.group-configs[0].message-name-to-match=.*Foo",
            "springwolf.docket.group-configs[1].group=all & everything",
            "springwolf.docket.group-configs[1].action-to-match=",
            "springwolf.docket.group-configs[1].channel-name-to-match=.*",
            "springwolf.docket.group-configs[1].message-name-to-match=",
        })
class GroupingIntegrationTest {
    @Autowired
    private AsyncApiService asyncApiService;

    @Test
    void shouldFindOnlyForGroupFoo() {
        AsyncAPI asyncAPI = asyncApiService.getForGroupName("FooMessage").get();

        assertThat(asyncAPI.getChannels().keySet()).containsExactlyInAnyOrder("listener-channel");
        assertThat(asyncAPI.getChannels().get("listener-channel").getMessages())
                .containsOnlyKeys(
                        "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
        assertThat(asyncAPI.getOperations())
                .containsOnlyKeys("listener-channel_receive_listen3", "listener-channel_receive_listen4");
        assertThat(asyncAPI.getComponents().getMessages())
                .containsOnlyKeys(
                        "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
        assertThat(asyncAPI.getComponents().getSchemas())
                .containsOnlyKeys(
                        "HeadersNotDocumented",
                        "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Bar",
                        "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");

        MessageObject fooMessage = (MessageObject) asyncAPI.getComponents()
                .getMessages()
                .get("io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
        assertThat(fooMessage.getPayload().getMultiFormatSchema().getSchema()).isInstanceOf(SchemaReference.class);
        SchemaReference fooSchemaRef =
                (SchemaReference) fooMessage.getPayload().getMultiFormatSchema().getSchema();
        assertThat(fooSchemaRef.getRef())
                .isEqualTo(
                        "#/components/schemas/io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
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
