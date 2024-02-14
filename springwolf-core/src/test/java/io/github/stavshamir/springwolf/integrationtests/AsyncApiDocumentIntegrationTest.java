// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.integrationtests;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.stavshamir.springwolf.fixtures.MinimalIntegrationTestContextConfiguration;
import io.github.stavshamir.springwolf.integrationtests.application.listener.ListenerApplication;
import io.github.stavshamir.springwolf.integrationtests.application.polymorphic.PolymorphicPayloadApplication;
import io.github.stavshamir.springwolf.integrationtests.application.publisher.PublisherApplication;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AsyncApiDocumentIntegrationTest {

    @Nested
    @SpringBootTest(classes = ListenerApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.integrationtests.application.listener",
            })
    class ListenerAnnotationTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void asyncListenerAnnotationIsFound() {
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
            assertThat(asyncAPI).isNotNull();

            assertThat(asyncAPI.getChannels()).containsOnlyKeys("listener-channel");
            assertThat(asyncAPI.getOperations()).containsOnlyKeys("listener-channel_receive_listen");
            assertThat(asyncAPI.getComponents().getMessages()).containsOnlyKeys("java.lang.String");
            assertThat(asyncAPI.getComponents().getSchemas()).containsOnlyKeys("HeadersNotDocumented", "String");
        }
    }

    @Nested
    @SpringBootTest(classes = PublisherApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.integrationtests.application.publisher",
            })
    class PublisherAnnotationTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void asyncPublisherAnnotationIsFound() {
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
            assertThat(asyncAPI).isNotNull();

            assertThat(asyncAPI.getChannels()).containsOnlyKeys("publisher-channel");
            assertThat(asyncAPI.getOperations()).containsOnlyKeys("publisher-channel_send_publish");
            assertThat(asyncAPI.getComponents().getMessages()).containsOnlyKeys("java.lang.String");
            assertThat(asyncAPI.getComponents().getSchemas()).containsOnlyKeys("HeadersNotDocumented", "String");
        }
    }

    @Nested
    @SpringBootTest(classes = PolymorphicPayloadApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                    "springwolf.docket.base-package=io.github.stavshamir.springwolf.integrationtests.application.polymorphic",
            })
    class PolymorphicPayloadTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void polymorphicDiscriminatorFieldIsHandled() {
            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            Map<String, Message> messages = asyncAPI.getComponents().getMessages();
            assertThat(messages)
                    .containsOnlyKeys(
                            "io.github.stavshamir.springwolf.integrationtests.application.polymorphic.PolymorphicApplication$Payload");
            Map<String, SchemaObject> schemas = asyncAPI.getComponents().getSchemas();
            assertThat(schemas).containsOnlyKeys("HeadersNotDocumented", "Payload", "Pet", "Cat", "Dog");

            assertThat(schemas.get("Pet").getDiscriminator()).isEqualTo("type");
            assertThat(schemas.get("Cat").getAllOf().get(0).getReference().getRef())
                    .isEqualTo("#/components/schemas/Pet");
            assertThat(schemas.get("Cat").getAllOf().get(1).getSchema().getProperties())
                    .containsOnlyKeys("catSpecificField");
            assertThat(schemas.get("Dog").getAllOf().get(0).getReference().getRef())
                    .isEqualTo("#/components/schemas/Pet");
            assertThat(schemas.get("Dog").getAllOf().get(1).getSchema().getProperties())
                    .containsOnlyKeys("dogSpecificField");
        }
    }
}
