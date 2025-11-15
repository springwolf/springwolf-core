// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaFormat;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.fixtures.MinimalIntegrationTestContextConfiguration;
import io.github.springwolf.core.integrationtests.application.fqn.FqnApplication;
import io.github.springwolf.core.integrationtests.application.listener.ListenerApplication;
import io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication;
import io.github.springwolf.core.integrationtests.application.publisher.PublisherApplication;
import io.github.springwolf.core.integrationtests.application.schema.SchemaEnumAsRefApplication;
import io.github.springwolf.core.standalone.DefaultStandaloneApplication;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AsyncApiDocumentWithOpenApiV3SchemaFormatIntegrationTest {

    @Nested
    @SpringBootTest(classes = ListenerApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.listener",
                "springwolf.docket.payload-schema-format=openapi_v3"
            })
    class ListenerAnnotationTest {
        @Value("${springwolf.docket.base-package}")
        private String basePackage;

        @Autowired
        private ConfigurableEnvironment environment;

        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void asyncListenerAnnotationIsFound() {
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
            assertThat(asyncAPI).isNotNull();

            assertThat(asyncAPI.getChannels().keySet())
                    .containsExactlyInAnyOrder("listener-channel", "listener-class-channel");
            assertThat(asyncAPI.getChannels().get("listener-channel").getMessages())
                    .containsOnlyKeys(
                            "java.lang.String",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
            assertThat(asyncAPI.getChannels().get("listener-class-channel").getMessages())
                    .containsOnlyKeys("java.lang.Integer");
            assertThat(asyncAPI.getOperations())
                    .containsOnlyKeys(
                            "listener-channel_receive_listen",
                            "listener-channel_receive_listen2",
                            "listener-channel_receive_listen3",
                            "listener-channel_receive_listen4",
                            "listener-class-channel_receive_listen");
            assertThat(asyncAPI.getComponents().getMessages())
                    .containsOnlyKeys(
                            "java.lang.String",
                            "java.lang.Integer",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
            assertThat(asyncAPI.getComponents().getSchemas())
                    .containsOnlyKeys(
                            "HeadersNotDocumented",
                            "java.lang.String",
                            "java.lang.Integer",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Bar",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");

            MessageObject stringMessage =
                    (MessageObject) asyncAPI.getComponents().getMessages().get("java.lang.String");
            assertThat(stringMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(Schema.class);
            Schema inlineStringSchema =
                    (Schema) stringMessage.getPayload().getMultiFormatSchema().getSchema();
            assertThat(inlineStringSchema.getType()).isEqualTo("string");
            assertThat(inlineStringSchema.getTypes()).containsExactly("string");

            MessageObject fooMessage = (MessageObject) asyncAPI.getComponents()
                    .getMessages()
                    .get("io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
            assertThat(fooMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(SchemaReference.class);
            SchemaReference fooSchemaRef = (SchemaReference)
                    fooMessage.getPayload().getMultiFormatSchema().getSchema();
            assertThat(fooSchemaRef.getRef())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
        }

        @Test
        void ensureThatStandaloneResultIsIdentical() {
            // given
            AsyncApiService asyncApiService = createStandaloneAsyncApiService(environment, basePackage);

            // when
            AsyncAPI asyncApi = asyncApiService.getAsyncAPI();

            // then
            assertThat(asyncApi).isEqualTo(asyncApiService.getAsyncAPI());
        }
    }

    @Nested
    @SpringBootTest(classes = PublisherApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.publisher",
                "springwolf.docket.payload-schema-format=openapi_v3"
            })
    class PublisherAnnotationTest {
        @Value("${springwolf.docket.base-package}")
        private String basePackage;

        @Autowired
        private ConfigurableEnvironment environment;

        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void asyncPublisherAnnotationIsFound() {
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
            assertThat(asyncAPI).isNotNull();

            assertThat(asyncAPI.getChannels().keySet())
                    .containsExactlyInAnyOrder("publisher-channel", "publisher-class-channel");
            assertThat(asyncAPI.getChannels().get("publisher-channel").getMessages())
                    .containsOnlyKeys(
                            "java.lang.String",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
            assertThat(asyncAPI.getChannels().get("publisher-class-channel").getMessages())
                    .containsOnlyKeys("java.lang.Integer");
            assertThat(asyncAPI.getOperations())
                    .containsOnlyKeys(
                            "publisher-channel_send_publish",
                            "publisher-channel_send_publish2",
                            "publisher-channel_send_publish3",
                            "publisher-channel_send_publish4",
                            "publisher-class-channel_send_publish");
            assertThat(asyncAPI.getComponents().getMessages())
                    .containsOnlyKeys(
                            "java.lang.String",
                            "java.lang.Integer",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
            assertThat(asyncAPI.getComponents().getSchemas())
                    .containsOnlyKeys(
                            "HeadersNotDocumented",
                            "java.lang.String",
                            "java.lang.Integer",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Bar",
                            "io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");

            MessageObject stringMessage =
                    (MessageObject) asyncAPI.getComponents().getMessages().get("java.lang.String");
            assertThat(stringMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(Schema.class); // Swagger Schema

            Schema inlineStringSchema =
                    (Schema) stringMessage.getPayload().getMultiFormatSchema().getSchema();
            assertThat(inlineStringSchema.getType()).isEqualTo("string");
            assertThat(inlineStringSchema.getTypes()).containsExactly("string");

            MessageObject fooMessage = (MessageObject) asyncAPI.getComponents()
                    .getMessages()
                    .get("io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
            assertThat(fooMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(SchemaReference.class);
            SchemaReference fooSchemaRef = (SchemaReference)
                    fooMessage.getPayload().getMultiFormatSchema().getSchema();
            assertThat(fooSchemaRef.getRef())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.listener.ListenerApplication.Foo");
        }

        @Test
        void ensureThatStandaloneResultIsIdentical() {
            // given
            AsyncApiService asyncApiService = createStandaloneAsyncApiService(environment, basePackage);

            // when
            AsyncAPI asyncApi = asyncApiService.getAsyncAPI();

            // then
            assertThat(asyncApi).isEqualTo(asyncApiService.getAsyncAPI());
        }
    }

    @Nested
    @SpringBootTest(classes = FqnApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.fqn",
                "springwolf.use-fqn=false",
                "springwolf.docket.payload-schema-format=openapi_v3"
            })
    class FqnTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Autowired
        private AsyncApiSerializerService asyncApiSerializerService;

        @Test
        void allClassesHaveSimpleNameNotFullQualifiedTest() throws Exception {
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
            assertThat(asyncAPI).isNotNull();
            String serialized = asyncApiSerializerService.toJsonString(asyncAPI);

            assertThat(serialized)
                    .contains(
                            "string",
                            "integer",
                            FqnApplication.Foo.class.getSimpleName(),
                            FqnApplication.Bar.class.getSimpleName(),
                            AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())
                    .doesNotContain(
                            String.class.getName(),
                            Integer.class.getName(),
                            FqnApplication.Foo.class.getName(),
                            FqnApplication.Bar.class.getName());
        }
    }

    @Nested
    @SpringBootTest(classes = PolymorphicPayloadApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.polymorphic",
                "springwolf.docket.payload-schema-format=openapi_v3"
            })
    class PolymorphicPayloadTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void polymorphicDiscriminatorFieldIsHandled() throws JsonProcessingException {
            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            Map<String, Message> messages = asyncAPI.getComponents().getMessages();
            assertThat(messages)
                    .containsOnlyKeys(
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Payload");
            Map<String, ComponentSchema> schemas = asyncAPI.getComponents().getSchemas();
            assertThat(schemas)
                    .containsOnlyKeys(
                            "HeadersNotDocumented",
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Payload",
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Pet",
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Cat",
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Dog");

            Schema petSchema = (Schema) schemas.get(
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Pet")
                    .getMultiFormatSchema()
                    .getSchema();

            assertThat(petSchema.getDiscriminator().getPropertyName()).isEqualTo("type");
            assertThat(petSchema.getDiscriminator().getMapping())
                    .containsAllEntriesOf(
                            Map.of(
                                    "dog",
                                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Dog",
                                    "cat",
                                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Cat"));

            Schema<?> catSchema = (Schema) schemas.get(
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Cat")
                    .getMultiFormatSchema()
                    .getSchema();

            assertThat(catSchema.getAllOf().get(0).get$ref())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Pet");

            assertThat(catSchema.getAllOf().get(1).getProperties()).containsOnlyKeys("catSpecificField");

            Schema<?> dogSchema = (Schema) schemas.get(
                            "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Dog")
                    .getMultiFormatSchema()
                    .getSchema();

            assertThat(dogSchema.getAllOf().get(0).get$ref())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Pet");
            assertThat(dogSchema.getAllOf().get(1).getProperties()).containsOnlyKeys("dogSpecificField");
        }
    }

    @Nested
    @SpringBootTest(classes = SchemaEnumAsRefApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.schema",
                "springwolf.docket.payload-schema-format=openapi_v3"
            })
    class SchemaAsRefTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void enumAsRefIsHandled() {
            // given
            String myEnumRootSchemaName =
                    "io.github.springwolf.core.integrationtests.application.schema.SchemaEnumAsRefApplication.Schemas.MyEnumRoot";
            String myEnumObjectSchemaName =
                    "io.github.springwolf.core.integrationtests.application.schema.SchemaEnumAsRefApplication.Schemas.MyEnumObject";

            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            Map<String, Message> messages = asyncAPI.getComponents().getMessages();
            assertThat(messages).containsOnlyKeys(myEnumRootSchemaName);
            Map<String, ComponentSchema> schemas = asyncAPI.getComponents().getSchemas();
            assertThat(schemas).containsOnlyKeys("HeadersNotDocumented", myEnumRootSchemaName, myEnumObjectSchemaName);

            assertThat(schemas.get(myEnumRootSchemaName).getMultiFormatSchema().getSchemaFormat())
                    .isEqualTo(SchemaFormat.OPENAPI_V3.value);
            Schema openapiSchema1 = (Schema)
                    schemas.get(myEnumRootSchemaName).getMultiFormatSchema().getSchema();

            assertThat(openapiSchema1.getExample().toString()).isEqualTo("{\"myEnumObjectField\":\"\\\"DOG\\\"\"}");

            assertThat(schemas.get(myEnumObjectSchemaName)
                            .getMultiFormatSchema()
                            .getSchemaFormat())
                    .isEqualTo(SchemaFormat.OPENAPI_V3.value);
            Schema openapiSchema2 = (Schema)
                    schemas.get(myEnumObjectSchemaName).getMultiFormatSchema().getSchema();

            assertThat(openapiSchema2.getExample().toString()).isEqualTo("\"DOG\"");
        }
    }

    @Nested
    @SpringBootTest(classes = ListenerApplication.class)
    @MinimalIntegrationTestContextConfiguration
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.listener",
                "springwolf.docket.payload-schema-format=openapi_v3",
                "springwolf.docket.group-configs[0].group=FooMessage",
                "springwolf.docket.group-configs[0].action-to-match=",
                "springwolf.docket.group-configs[0].channel-name-to-match=",
                "springwolf.docket.group-configs[0].message-name-to-match=.*Foo",
                "springwolf.docket.group-configs[1].group=all & everything",
                "springwolf.docket.group-configs[1].action-to-match=",
                "springwolf.docket.group-configs[1].channel-name-to-match=.*",
                "springwolf.docket.group-configs[1].message-name-to-match=",
            })
    class GroupingTest {
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
            assertThat(fooMessage.getPayload().getMultiFormatSchema().getSchema())
                    .isInstanceOf(SchemaReference.class);
            SchemaReference fooSchemaRef = (SchemaReference)
                    fooMessage.getPayload().getMultiFormatSchema().getSchema();
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

    private AsyncApiService createStandaloneAsyncApiService(ConfigurableEnvironment environment, String basePackage) {
        DefaultStandaloneApplication standaloneApplication = DefaultStandaloneApplication.builder()
                .addScanPackage(basePackage)
                .setEnvironment(environment)
                .buildAndStart();
        return standaloneApplication.getAsyncApiService();
    }
}
