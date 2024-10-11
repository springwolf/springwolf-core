// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.fixtures.MinimalIntegrationTestContextConfiguration;
import io.github.springwolf.core.integrationtests.application.listener.ListenerApplication;
import io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication;
import io.github.springwolf.core.integrationtests.application.publisher.PublisherApplication;
import io.github.springwolf.core.integrationtests.application.schema.SchemaEnumAsRefApplication;
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
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.listener",
            })
    class ListenerAnnotationTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void asyncListenerAnnotationIsFound() {
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
            assertThat(asyncAPI).isNotNull();

            assertThat(asyncAPI.getChannels().keySet())
                    .containsExactlyInAnyOrder("listener-channel_id", "listener-class-channel_id");
            assertThat(asyncAPI.getChannels().get("listener-channel_id").getMessages())
                    .containsOnlyKeys(
                            "java.lang.String",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");
            assertThat(asyncAPI.getChannels().get("listener-class-channel_id").getMessages())
                    .containsOnlyKeys("java.lang.Integer");
            assertThat(asyncAPI.getOperations())
                    .containsOnlyKeys(
                            "listener-channel_id_receive_listen",
                            "listener-channel_id_receive_listen2",
                            "listener-channel_id_receive_listen3",
                            "listener-channel_id_receive_listen4",
                            "listener-class-channel_id_receive_ClassListener");
            assertThat(asyncAPI.getComponents().getMessages())
                    .containsOnlyKeys(
                            "java.lang.String",
                            "java.lang.Integer",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");
            assertThat(asyncAPI.getComponents().getSchemas())
                    .containsOnlyKeys(
                            "HeadersNotDocumented",
                            "java.lang.String",
                            "java.lang.Integer",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Bar",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");

            MessageObject stringMessage =
                    (MessageObject) asyncAPI.getComponents().getMessages().get("java.lang.String");
            assertThat(stringMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(SchemaObject.class);
            SchemaObject inlineStringSchema = (SchemaObject)
                    stringMessage.getPayload().getMultiFormatSchema().getSchema();
            assertThat(inlineStringSchema.getType()).isEqualTo("string");

            MessageObject fooMessage = (MessageObject) asyncAPI.getComponents()
                    .getMessages()
                    .get("io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");
            assertThat(fooMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(MessageReference.class);
            MessageReference fooRefMessage = (MessageReference)
                    fooMessage.getPayload().getMultiFormatSchema().getSchema();
            assertThat(fooRefMessage.getRef())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");
        }
    }

    @Nested
    @SpringBootTest(classes = PublisherApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.publisher",
            })
    class PublisherAnnotationTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void asyncPublisherAnnotationIsFound() {
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
            assertThat(asyncAPI).isNotNull();

            assertThat(asyncAPI.getChannels().keySet())
                    .containsExactlyInAnyOrder("publisher-channel_id", "publisher-class-channel_id");
            assertThat(asyncAPI.getChannels().get("publisher-channel_id").getMessages())
                    .containsOnlyKeys(
                            "java.lang.String",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");
            assertThat(asyncAPI.getChannels().get("publisher-class-channel_id").getMessages())
                    .containsOnlyKeys("java.lang.Integer");
            assertThat(asyncAPI.getOperations())
                    .containsOnlyKeys(
                            "publisher-channel_id_send_publish",
                            "publisher-channel_id_send_publish2",
                            "publisher-channel_id_send_publish3",
                            "publisher-channel_id_send_publish4",
                            "publisher-class-channel_id_send_ClassPublisher");
            assertThat(asyncAPI.getComponents().getMessages())
                    .containsOnlyKeys(
                            "java.lang.String",
                            "java.lang.Integer",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");
            assertThat(asyncAPI.getComponents().getSchemas())
                    .containsOnlyKeys(
                            "HeadersNotDocumented",
                            "java.lang.String",
                            "java.lang.Integer",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Bar",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");

            MessageObject stringMessage =
                    (MessageObject) asyncAPI.getComponents().getMessages().get("java.lang.String");
            assertThat(stringMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(SchemaObject.class);
            SchemaObject inlineStringSchema = (SchemaObject)
                    stringMessage.getPayload().getMultiFormatSchema().getSchema();
            assertThat(inlineStringSchema.getType()).isEqualTo("string");

            MessageObject fooMessage = (MessageObject) asyncAPI.getComponents()
                    .getMessages()
                    .get("io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");
            assertThat(fooMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(MessageReference.class);
            MessageReference fooRefMessage = (MessageReference)
                    fooMessage.getPayload().getMultiFormatSchema().getSchema();
            assertThat(fooRefMessage.getRef())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.listener.ListenerApplication$Foo");
        }
    }

    @Nested
    @SpringBootTest(classes = PolymorphicPayloadApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.polymorphic",
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
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Payload");
            Map<String, SchemaObject> schemas = asyncAPI.getComponents().getSchemas();
            assertThat(schemas)
                    .containsOnlyKeys(
                            "HeadersNotDocumented",
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Payload",
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Pet",
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Cat",
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Dog");

            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Pet")
                            .getDiscriminator())
                    .isEqualTo("type");
            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Cat")
                            .getAllOf()
                            .get(0)
                            .getReference()
                            .getRef())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Pet");
            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Cat")
                            .getAllOf()
                            .get(1)
                            .getSchema()
                            .getProperties())
                    .containsOnlyKeys("catSpecificField");
            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Dog")
                            .getAllOf()
                            .get(0)
                            .getReference()
                            .getRef())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Pet");
            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication$Dog")
                            .getAllOf()
                            .get(1)
                            .getSchema()
                            .getProperties())
                    .containsOnlyKeys("dogSpecificField");
        }
    }

    @Nested
    @SpringBootTest(classes = SchemaEnumAsRefApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.schema",
            })
    class SchemaAsRefTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void enumAsRefIsHandled() {
            // given
            String myEnumRootSchemaName =
                    "io.github.springwolf.core.integrationtests.application.schema.SchemaEnumAsRefApplication$Schemas$MyEnumRoot";
            String myEnumObjectSchemaName =
                    "io.github.springwolf.core.integrationtests.application.schema.SchemaEnumAsRefApplication$Schemas$MyEnumObject";

            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            Map<String, Message> messages = asyncAPI.getComponents().getMessages();
            assertThat(messages).containsOnlyKeys(myEnumRootSchemaName);
            Map<String, SchemaObject> schemas = asyncAPI.getComponents().getSchemas();
            assertThat(schemas).containsOnlyKeys("HeadersNotDocumented", myEnumRootSchemaName, myEnumObjectSchemaName);

            assertThat(schemas.get(myEnumRootSchemaName).getExamples().get(0).toString())
                    .isEqualTo("{\"myEnumObjectField\":\"\\\"DOG\\\"\"}");
            assertThat(schemas.get(myEnumObjectSchemaName).getExamples().get(0).toString())
                    .isEqualTo("\"DOG\"");
        }
    }
}
