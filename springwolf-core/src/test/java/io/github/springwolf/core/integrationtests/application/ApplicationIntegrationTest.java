// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application;

import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.fixtures.SpringwolfIntegrationTest;
import io.github.springwolf.core.integrationtests.application.fqn.FqnApplication;
import io.github.springwolf.core.integrationtests.application.listener.ListenerApplication;
import io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication;
import io.github.springwolf.core.integrationtests.application.publisher.PublisherApplication;
import io.github.springwolf.core.integrationtests.application.ref.SchemaEnumAsRefApplication;
import io.github.springwolf.core.standalone.DefaultStandaloneApplication;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.TestPropertySource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationIntegrationTest {

    @Nested
    @SpringBootTest(classes = ListenerApplication.class)
    @SpringwolfIntegrationTest
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.listener",
            })
    class ListenerAnnotationTest {
        @Autowired
        private ConfigurableEnvironment environment;

        @Autowired
        private AsyncApiService asyncApiService;

        @Autowired
        private AsyncApiSerializerService asyncApiSerializerService;

        @Test
        void asyncListenerAnnotationIsFound() throws Exception {
            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            String actual = asyncApiSerializerService.toJsonString(asyncAPI);
            Files.writeString(
                    Path.of("src", "test", "resources", "application", "asyncapi.listener.actual.json"), actual);

            String expected =
                    Files.readString(Path.of("src", "test", "resources", "application", "asyncapi.listener.json"));
            assertThat(expected).isEqualToIgnoringWhitespace(actual);
        }

        @Test
        void ensureThatStandaloneResultIsIdentical() {
            // given
            AsyncApiService asyncApiService =
                    createStandaloneAsyncApiService(environment, ListenerApplication.class.getPackageName());

            // when
            AsyncAPI asyncApi = asyncApiService.getAsyncAPI();

            // then
            assertThat(asyncApi).isEqualTo(asyncApiService.getAsyncAPI());
        }
    }

    @Nested
    @SpringBootTest(classes = PublisherApplication.class)
    @SpringwolfIntegrationTest
    @TestPropertySource(
            properties = {
                "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.publisher",
            })
    class PublisherAnnotationTest {
        @Autowired
        private ConfigurableEnvironment environment;

        @Autowired
        private AsyncApiService asyncApiService;

        @Autowired
        private AsyncApiSerializerService asyncApiSerializerService;

        @Test
        void asyncPublisherAnnotationIsFound() throws Exception {
            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            String actual = asyncApiSerializerService.toJsonString(asyncAPI);
            Files.writeString(
                    Path.of("src", "test", "resources", "application", "asyncapi.publisher.actual.json"), actual);

            String expected =
                    Files.readString(Path.of("src", "test", "resources", "application", "asyncapi.publisher.json"));
            assertThat(expected).isEqualToIgnoringWhitespace(actual);
        }

        @Test
        void ensureThatStandaloneResultIsIdentical() {
            // given
            AsyncApiService asyncApiService =
                    createStandaloneAsyncApiService(environment, PublisherApplication.class.getPackageName());

            // when
            AsyncAPI asyncApi = asyncApiService.getAsyncAPI();

            // then
            assertThat(asyncApi).isEqualTo(asyncApiService.getAsyncAPI());
        }
    }

    @Nested
    @SpringBootTest(classes = FqnApplication.class)
    @SpringwolfIntegrationTest
    @TestPropertySource(
            properties = {
                "springwolf.use-fqn=false",
            })
    class FqnTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Autowired
        private AsyncApiSerializerService asyncApiSerializerService;

        @Test
        void matchesCheckedInArtifact() throws Exception {
            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            String actual = asyncApiSerializerService.toJsonString(asyncAPI);
            Files.writeString(Path.of("src", "test", "resources", "application", "asyncapi.fqn.actual.json"), actual);

            String expected = Files.readString(Path.of("src", "test", "resources", "application", "asyncapi.fqn.json"));
            assertThat(expected).isEqualToIgnoringWhitespace(actual);
        }

        @Test
        void allClassesHaveSimpleNameNotFullQualifiedTest() throws Exception {
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
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
    @SpringwolfIntegrationTest
    class PolymorphicPayloadTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Autowired
        private AsyncApiSerializerService asyncApiSerializerService;

        @Test
        void matchesCheckedInArtifact() throws Exception {
            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            String actual = asyncApiSerializerService.toJsonString(asyncAPI);
            Files.writeString(
                    Path.of("src", "test", "resources", "application", "asyncapi.polymorphic.actual.json"), actual);

            String expected =
                    Files.readString(Path.of("src", "test", "resources", "application", "asyncapi.polymorphic.json"));
            assertThat(expected).isEqualToIgnoringWhitespace(actual);
        }

        @Test
        void polymorphicDiscriminatorFieldIsHandled() {
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

            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Pet")
                            .getSchema()
                            .getDiscriminator())
                    .isEqualTo("type");
            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Cat")
                            .getSchema()
                            .getAllOf()
                            .get(0)
                            .getReference()
                            .getRef())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Pet");
            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Cat")
                            .getSchema()
                            .getAllOf()
                            .get(1)
                            .getSchema()
                            .getProperties())
                    .containsOnlyKeys("catSpecificField");
            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Dog")
                            .getSchema()
                            .getAllOf()
                            .get(0)
                            .getReference()
                            .getRef())
                    .isEqualTo(
                            "#/components/schemas/io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Pet");
            assertThat(schemas.get(
                                    "io.github.springwolf.core.integrationtests.application.polymorphic.PolymorphicPayloadApplication.Dog")
                            .getSchema()
                            .getAllOf()
                            .get(1)
                            .getSchema()
                            .getProperties())
                    .containsOnlyKeys("dogSpecificField");
        }
    }

    @Nested
    @SpringBootTest(classes = SchemaEnumAsRefApplication.class)
    @SpringwolfIntegrationTest
    class SchemaAsRefTest {
        @Autowired
        private AsyncApiService asyncApiService;

        @Autowired
        private AsyncApiSerializerService asyncApiSerializerService;

        @Test
        void matchesCheckedInArtifact() throws Exception {
            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            String actual = asyncApiSerializerService.toJsonString(asyncAPI);
            Files.writeString(Path.of("src", "test", "resources", "application", "asyncapi.ref.actual.json"), actual);

            String expected = Files.readString(Path.of("src", "test", "resources", "application", "asyncapi.ref.json"));
            assertThat(expected).isEqualToIgnoringWhitespace(actual);
        }

        @Test
        void enumAsRefIsHandled() {
            // given
            String myEnumRootSchemaName =
                    "io.github.springwolf.core.integrationtests.application.ref.SchemaEnumAsRefApplication.Schemas.MyEnumRoot";
            String myEnumObjectSchemaName =
                    "io.github.springwolf.core.integrationtests.application.ref.SchemaEnumAsRefApplication.Schemas.MyEnumObject";

            // when
            AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();

            // then
            Map<String, Message> messages = asyncAPI.getComponents().getMessages();
            assertThat(messages).containsOnlyKeys(myEnumRootSchemaName);
            Map<String, ComponentSchema> schemas = asyncAPI.getComponents().getSchemas();
            assertThat(schemas).containsOnlyKeys("HeadersNotDocumented", myEnumRootSchemaName, myEnumObjectSchemaName);

            assertThat(schemas.get(myEnumRootSchemaName)
                            .getSchema()
                            .getExamples()
                            .get(0)
                            .toString())
                    .isEqualTo("{\"myEnumObjectField\":\"\\\"DOG\\\"\"}");
            assertThat(schemas.get(myEnumObjectSchemaName)
                            .getSchema()
                            .getExamples()
                            .get(0)
                            .toString())
                    .isEqualTo("\"DOG\"");
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
