// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.components.Components;
import io.github.springwolf.asyncapi.v3.model.info.Contact;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.info.License;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaFormat;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.asyncapi.v3.model.server.ServerReference;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class DefaultAsyncApiSerializerServiceIntegrationTest {

    private static final AsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    private AsyncAPI getAsyncAPITestObject(SchemaFormat schemaFormat) {
        Info info = Info.builder()
                .title("AsyncAPI Sample App")
                .version("1.0.1")
                .description("This is a sample server.")
                .termsOfService("https://asyncapi.org/terms/")
                .contact(Contact.builder()
                        .name("API Support")
                        .url("http://www.asyncapi.org/support")
                        .email("support@asyncapi.org")
                        .build())
                .license(License.builder()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html")
                        .build())
                .build();

        Server productionServer = Server.builder()
                .host("development.gigantic-server.com")
                .description("Development server")
                .protocol("kafka")
                .protocolVersion("1.0.0")
                .build();

        MessageObject message = MessageObject.builder()
                .name("io.github.springwolf.core.ExamplePayload")
                .title("Example Payload")
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.toSchema("ExamplePayload"))
                        .build()))
                .bindings(Map.of(
                        "kafka",
                        KafkaMessageBinding.builder()
                                // FIXME: We should have a SchemaString (Schema<String>)
                                .key(SchemaObject.builder()
                                        .type(Set.of(SchemaType.STRING.getValue()))
                                        .build())
                                .build()))
                .build();
        Map<String, Message> messages = Map.of(message.getMessageId(), message);

        SchemaObject groupId = new SchemaObject();
        groupId.setEnumValues(List.of("myGroupId"));
        groupId.setType(Set.of(SchemaType.STRING.getValue()));

        OperationBinding operationBinding =
                KafkaOperationBinding.builder().groupId(groupId).build();

        Operation newUserOperation = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel("new-user"))
                .messages(List.of(MessageReference.toChannelMessage("new-user", message)))
                .bindings(Map.of("kafka", operationBinding))
                .build();

        ChannelObject newUserChannel = ChannelObject.builder()
                // FIXME: Can we autogenerate the address somehow?
                .address("new-user")
                .description("This channel is used to exchange messages about users signing up")
                .servers(List.of(
                        ServerReference.builder().ref("#/servers/production").build()))
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .build();

        Map<String, ComponentSchema> schemas = createPayloadSchema(schemaFormat);

        return AsyncAPI.builder()
                .info(info)
                .defaultContentType("application/json")
                .servers(Map.of("production", productionServer))
                .channels(Map.of(newUserChannel.getChannelId(), newUserChannel))
                .components(
                        Components.builder().schemas(schemas).messages(messages).build())
                .operations(Map.of("new-user_listenerMethod_subscribe", newUserOperation))
                .build();
    }

    private Map<String, ComponentSchema> createPayloadSchema(SchemaFormat schemaFormat) {
        switch (schemaFormat) {
            case DEFAULT: {
                SchemaObject examplePayloadSchema = new SchemaObject();
                examplePayloadSchema.setType(Set.of(SchemaType.OBJECT.getValue()));
                SchemaObject stringSchema = new SchemaObject();
                stringSchema.setType(Set.of(SchemaType.STRING.getValue()));
                examplePayloadSchema.setProperties(Map.of("s", stringSchema));
                return Map.of("ExamplePayload", ComponentSchema.of(examplePayloadSchema));
            }
            case OPENAPI_V3: {
                ObjectSchema examplePayloadSchema = new ObjectSchema();
                StringSchema stringSchema = new StringSchema();
                examplePayloadSchema.setProperties(Map.of("s", stringSchema));
                return Map.of(
                        "ExamplePayload",
                        ComponentSchema.of(new MultiFormatSchema(SchemaFormat.OPENAPI_V3.value, examplePayloadSchema)));
            }
            default: {
                throw new IllegalArgumentException("Unsupported schema format: " + schemaFormat);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("params")
    void AsyncAPI_should_map_to_a_valid_asyncapi_json(SchemaFormat schemaFormat, String expectedFile) throws Exception {
        AsyncAPI asyncapi = getAsyncAPITestObject(schemaFormat);
        String actual = serializer.toJsonString(asyncapi);
        InputStream s = this.getClass().getResourceAsStream(expectedFile + ".json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8);
        assertThatJson(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("params")
    void AsyncAPI_should_map_to_a_valid_asyncapi_yaml(SchemaFormat schemaFormat, String expectedFile) throws Exception {
        AsyncAPI asyncapi = getAsyncAPITestObject(schemaFormat);
        String actual = serializer.toJsonString(asyncapi);
        JsonNode expected = ClasspathUtil.parseYamlFile(expectedFile + ".yaml");
        assertThatJson(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> params() {
        return Stream.of(
                // types
                Arguments.of(SchemaFormat.DEFAULT, "/v3/asyncapi/asyncapi"),
                Arguments.of(SchemaFormat.OPENAPI_V3, "/v3/asyncapi/asyncapi-openapischema"));
    }
}
