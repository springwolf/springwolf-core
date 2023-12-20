// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model;

import io.github.stavshamir.springwolf.asyncapi.core.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.core.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.ChannelParameter;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message.MessageTrait;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.components.ComponentSchema;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.components.Components;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.info.Info;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.info.License;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.operation.OperationAction;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.operation.OperationTraits;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.schema.Schema;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.security_scheme.SecurityScheme;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.security_scheme.SecurityType;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.server.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class AsyncAPITest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldCreateSimpleAsyncAPI() throws IOException {
        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(Info.builder()
                        .title("Account Service")
                        .version("1.0.0")
                        .description("This service is in charge of processing user signups")
                        .build())
                .channels(Map.of(
                        "userSignedup",
                        Channel.builder()
                                .address("user/signedup")
                                .messages(Map.of(
                                        "UserSignedUp",
                                        Message.builder()
                                                .ref("#/components/messages/UserSignedUp")
                                                .build()))
                                .build()))
                .operations(Map.of(
                        "sendUserSignedup",
                        Operation.builder()
                                .action(OperationAction.SEND)
                                .channel(ChannelReference.builder()
                                        .ref("#/channels/userSignedup")
                                        .build())
                                .messages(List.of(MessageReference.builder()
                                        .ref("#/channels/userSignedup/messages/UserSignedUp")
                                        .build()))
                                .build()))
                .components(Components.builder()
                        .messages(Map.of(
                                "UserSignedUp",
                                Message.builder()
                                        .payload(MessagePayload.of(Schema.builder()
                                                .type("object")
                                                .properties(Map.of(
                                                        "displayName",
                                                        Schema.builder()
                                                                .type("string")
                                                                .description("Name of the user")
                                                                .build(),
                                                        "email",
                                                        Schema.builder()
                                                                .type("string")
                                                                .format("email")
                                                                .description("Email of the user")
                                                                .build()))
                                                .build()))
                                        .build()))
                        .build())
                .build();

        // https://github.com/asyncapi/spec/blob/master/examples/simple-asyncapi.yml
        String example = ClasspathUtil.readAsString("/v3/model/simple-asyncapi.json");
        assertThatJson(serializer.toJsonString(asyncAPI)).isEqualTo(example);
    }

    @Test
    void shouldCreateStreetlightsKafkaAsyncAPI() throws IOException {
        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(Info.builder()
                        .title("Streetlights Kafka API")
                        .version("1.0.0")
                        .description(
                                "The Smartylighting Streetlights API allows you to remotely manage the city lights.\n\n### Check out its awesome features:\n\n* Turn a specific streetlight on/off \\U0001F303\n* Dim a specific streetlight \\U0001F60E\n* Receive real-time information about environmental lighting conditions \\U0001F4C8\n")
                        .license(License.builder()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                                .build())
                        .build())
                .defaultContentType("application/json")
                .servers(Map.of(
                        "scram-connections",
                        Server.builder()
                                .host("test.mykafkacluster.org:18092")
                                .protocol("kafka-secure")
                                .description("Test broker secured with scramSha256")
                                .security(List.of(SecurityScheme.builder()
                                        .ref("#/components/securitySchemes/saslScram")
                                        .build()))
                                .tags(List.of(
                                        Tag.builder()
                                                .name("env:test-scram")
                                                .description(
                                                        "This environment is meant for running internal tests through scramSha256")
                                                .build(),
                                        Tag.builder()
                                                .name("kind:remote")
                                                .description(
                                                        "This server is a remote server. Not exposed by the application")
                                                .build(),
                                        Tag.builder()
                                                .name("visibility:private")
                                                .description(
                                                        "This resource is private and only available to certain users")
                                                .build()))
                                .build(),
                        "mtls-connections",
                        Server.builder()
                                .host("test.mykafkacluster.org:28092")
                                .protocol("kafka-secure")
                                .description("Test broker secured with X509")
                                .security(List.of(SecurityScheme.builder()
                                        .ref("#/components/securitySchemes/certs")
                                        .build()))
                                .tags(List.of(
                                        Tag.builder()
                                                .name("env:test-mtls")
                                                .description(
                                                        "This environment is meant for running internal tests through mtls")
                                                .build(),
                                        Tag.builder()
                                                .name("kind:remote")
                                                .description(
                                                        "This server is a remote server. Not exposed by the application")
                                                .build(),
                                        Tag.builder()
                                                .name("visibility:private")
                                                .description(
                                                        "This resource is private and only available to certain users")
                                                .build()))
                                .build()))
                .channels(Map.of(
                        "lightingMeasured",
                        Channel.builder()
                                .address("smartylighting.streetlights.1.0.event.{streetlightId}.lighting.measured")
                                .messages(Map.of(
                                        "lightMeasured",
                                        Message.builder()
                                                .ref("#/components/messages/lightMeasured")
                                                .build()))
                                .description("The topic on which measured values may be produced and consumed.")
                                .parameters(Map.of(
                                        "streetlightId",
                                        ChannelParameter.builder()
                                                .ref("#/components/parameters/streetlightId")
                                                .build()))
                                .build(),
                        "lightTurnOn",
                        Channel.builder()
                                .address("smartylighting.streetlights.1.0.action.{streetlightId}.turn.on")
                                .messages(Map.of(
                                        "turnOn",
                                        Message.builder()
                                                .ref("#/components/messages/turnOnOff")
                                                .build()))
                                .parameters(Map.of(
                                        "streetlightId",
                                        ChannelParameter.builder()
                                                .ref("#/components/parameters/streetlightId")
                                                .build()))
                                .build(),
                        "lightTurnOff",
                        Channel.builder()
                                .address("smartylighting.streetlights.1.0.action.{streetlightId}.turn.off")
                                .messages(Map.of(
                                        "turnOff",
                                        Message.builder()
                                                .ref("#/components/messages/turnOnOff")
                                                .build()))
                                .parameters(Map.of(
                                        "streetlightId",
                                        ChannelParameter.builder()
                                                .ref("#/components/parameters/streetlightId")
                                                .build()))
                                .build(),
                        "lightsDim",
                        Channel.builder()
                                .address("smartylighting.streetlights.1.0.action.{streetlightId}.dim")
                                .messages(Map.of(
                                        "dimLight",
                                        Message.builder()
                                                .ref("#/components/messages/dimLight")
                                                .build()))
                                .parameters(Map.of(
                                        "streetlightId",
                                        ChannelParameter.builder()
                                                .ref("#/components/parameters/streetlightId")
                                                .build()))
                                .build()))
                .operations(Map.of(
                        "receiveLightMeasurement",
                        Operation.builder()
                                .action(OperationAction.RECEIVE)
                                .channel(ChannelReference.builder()
                                        .ref("#/channels/lightingMeasured")
                                        .build())
                                .summary("Inform about environmental lighting conditions of a particular streetlight.")
                                .traits(List.of(OperationTraits.builder()
                                        .ref("#/components/operationTraits/kafka")
                                        .build()))
                                .messages(List.of(MessageReference.builder()
                                        .ref("#/channels/lightingMeasured/messages/lightMeasured")
                                        .build()))
                                .build(),
                        "turnOn",
                        Operation.builder()
                                .action(OperationAction.SEND)
                                .channel(ChannelReference.builder()
                                        .ref("#/channels/lightTurnOn")
                                        .build())
                                .traits(List.of(OperationTraits.builder()
                                        .ref("#/components/operationTraits/kafka")
                                        .build()))
                                .messages(List.of(MessageReference.builder()
                                        .ref("#/channels/lightTurnOn/messages/turnOn")
                                        .build()))
                                .build(),
                        "turnOff",
                        Operation.builder()
                                .action(OperationAction.SEND)
                                .channel(ChannelReference.builder()
                                        .ref("#/channels/lightTurnOff")
                                        .build())
                                .traits(List.of(OperationTraits.builder()
                                        .ref("#/components/operationTraits/kafka")
                                        .build()))
                                .messages(List.of(MessageReference.builder()
                                        .ref("#/channels/lightTurnOff/messages/turnOff")
                                        .build()))
                                .build(),
                        "dimLight",
                        Operation.builder()
                                .action(OperationAction.SEND)
                                .channel(ChannelReference.builder()
                                        .ref("#/channels/lightsDim")
                                        .build())
                                .traits(List.of(OperationTraits.builder()
                                        .ref("#/components/operationTraits/kafka")
                                        .build()))
                                .messages(List.of(MessageReference.builder()
                                        .ref("#/channels/lightsDim/messages/dimLight")
                                        .build()))
                                .build()))
                .components(Components.builder()
                        .messages(Map.of(
                                "lightMeasured",
                                Message.builder()
                                        .name("lightMeasured")
                                        .title("Light measured")
                                        .summary(
                                                "Inform about environmental lighting conditions of a particular streetlight.")
                                        .contentType("application/json")
                                        .traits(List.of(MessageTrait.builder()
                                                .ref("#/components/messageTraits/commonHeaders")
                                                .build()))
                                        .payload(MessagePayload.of(MessageReference.builder()
                                                .ref("#/components/schemas/lightMeasuredPayload")
                                                .build()))
                                        .build(),
                                "turnOnOff",
                                Message.builder()
                                        .name("turnOnOff")
                                        .title("Turn on/off")
                                        .summary("Command a particular streetlight to turn the lights on or off.")
                                        .traits(List.of(MessageTrait.builder()
                                                .ref("#/components/messageTraits/commonHeaders")
                                                .build()))
                                        .payload(MessagePayload.of(MessageReference.builder()
                                                .ref("#/components/schemas/turnOnOffPayload")
                                                .build()))
                                        .build(),
                                "dimLight",
                                Message.builder()
                                        .name("dimLight")
                                        .title("Dim light")
                                        .summary("Command a particular streetlight to dim the lights.")
                                        .traits(List.of(MessageTrait.builder()
                                                .ref("#/components/messageTraits/commonHeaders")
                                                .build()))
                                        .payload(MessagePayload.of(MessageReference.builder()
                                                .ref("#/components/schemas/dimLightPayload")
                                                .build()))
                                        .build()))
                        .schemas(Map.of(
                                "lightMeasuredPayload",
                                ComponentSchema.of(Schema.builder()
                                        .type("object")
                                        .properties(Map.of(
                                                "lumens",
                                                Schema.builder()
                                                        .type("integer")
                                                        .minimum(0)
                                                        .description("Light intensity measured in lumens.")
                                                        .build(),
                                                "sentAt",
                                                Schema.builder()
                                                        .ref("#/components/schemas/sentAt")
                                                        .build()))
                                        .build()),
                                "turnOnOffPayload",
                                ComponentSchema.of(Schema.builder()
                                        .type("object")
                                        .properties(Map.of(
                                                "command",
                                                Schema.builder()
                                                        .type("string")
                                                        .enumValues(List.of("on", "off"))
                                                        .description("Whether to turn on or off the light.")
                                                        .build(),
                                                "sentAt",
                                                Schema.builder()
                                                        .ref("#/components/schemas/sentAt")
                                                        .build()))
                                        .build()),
                                "dimLightPayload",
                                ComponentSchema.of(Schema.builder()
                                        .type("object")
                                        .properties(Map.of(
                                                "percentage",
                                                Schema.builder()
                                                        .type("integer")
                                                        .description(
                                                                "Percentage to which the light should be dimmed to.")
                                                        .minimum(0)
                                                        .maximum(100)
                                                        .build(),
                                                "sentAt",
                                                Schema.builder()
                                                        .ref("#/components/schemas/sentAt")
                                                        .build()))
                                        .build()),
                                "sentAt",
                                ComponentSchema.of(Schema.builder()
                                        .type("string")
                                        .format("date-time")
                                        .description("Date and time when the message was sent.")
                                        .build())))
                        .securitySchemes(Map.of(
                                "saslScram",
                                SecurityScheme.builder()
                                        .type(SecurityType.SCRAM_SHA256)
                                        .description("Provide your username and password for SASL/SCRAM authentication")
                                        .build(),
                                "certs",
                                SecurityScheme.builder()
                                        .type(SecurityType.X509)
                                        .description("Download the certificate files from service provider")
                                        .build()))
                        .parameters(Map.of(
                                "streetlightId",
                                ChannelParameter.builder()
                                        .description("The ID of the streetlight.")
                                        .build()))
                        .messageTraits(Map.of(
                                "commonHeaders",
                                MessageTrait.builder()
                                        .headers(MessageHeaders.of(Schema.builder()
                                                .type("object")
                                                .properties(Map.of(
                                                        "my-app-header",
                                                        Schema.builder()
                                                                .type("integer")
                                                                .minimum(0)
                                                                .maximum(100)
                                                                .build()))
                                                .build()))
                                        .build()))
                        .operationTraits(Map.of(
                                "kafka",
                                OperationTraits.builder()
                                        .bindings(Map.of(
                                                "kafka",
                                                KafkaOperationBinding.builder()
                                                        .clientId(Schema.builder()
                                                                .type("string")
                                                                .enumValues(List.of("my-app-id"))
                                                                .build())
                                                        .build()))
                                        .build()))
                        .build())
                .build();

        // https://github.com/asyncapi/spec/blob/master/examples/streetlights-kafka-asyncapi.yml
        String example = ClasspathUtil.readAsString("/v3/model/streetlights-kafka-asyncapi.json");
        assertThatJson(serializer.toJsonString(asyncAPI))
                .whenIgnoringPaths("components.operationTraits.kafka.bindings.kafka.bindingVersion")
                .isEqualTo(example);
    }

    // FIXME: We should have the capability to serialize automatically creating the $ref values with the proper keys
    //        Current approach forces the developer to manually create the structure, which is limiting
}
