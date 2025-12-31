// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelParameter;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageTrait;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.components.Components;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.info.License;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.operation.OperationTraits;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.asyncapi.v3.model.security_scheme.SecurityScheme;
import io.github.springwolf.asyncapi.v3.model.security_scheme.SecurityType;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class AsyncAPITest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldCreateSimpleAsyncAPI() throws Exception {
        var userSignUpMessage = MessageObject.builder()
                .messageId("UserSignedUp")
                .payload(MessagePayload.of(SchemaObject.builder()
                        .type(Set.of(SchemaType.OBJECT))
                        .properties(Map.of(
                                "displayName",
                                SchemaObject.builder()
                                        .type(Set.of(SchemaType.STRING))
                                        .description("Name of the user")
                                        .build(),
                                "email",
                                SchemaObject.builder()
                                        .type(Set.of(SchemaType.STRING))
                                        .format("email")
                                        .description("Email of the user")
                                        .build()))
                        .build()))
                .build();

        var channelUserSignedup = ChannelObject.builder()
                .channelId("userSignedup")
                .address("user/signedup")
                .messages(Map.of(userSignUpMessage.getMessageId(), MessageReference.toComponentMessage("UserSignedUp")))
                .build();

        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(Info.builder()
                        .title("Account Service")
                        .version("1.0.0")
                        .description("This service is in charge of processing user signups")
                        .build())
                .channels(Map.of(channelUserSignedup.getChannelId(), channelUserSignedup))
                .operations(Map.of(
                        "sendUserSignedup",
                        Operation.builder()
                                .action(OperationAction.SEND)
                                .channel(ChannelReference.fromChannel("userSignedup"))
                                .messages(List.of(MessageReference.toChannelMessage(
                                        "userSignedup", userSignUpMessage.getMessageId())))
                                .build()))
                .components(Components.builder()
                        .messages(Map.of(userSignUpMessage.getMessageId(), userSignUpMessage))
                        .build())
                .build();

        // https://github.com/asyncapi/spec/blob/master/examples/simple-asyncapi.yml
        String example = ClasspathUtil.readAsString("/v3/model/simple-asyncapi.json");
        assertThatJson(serializer.toJsonString(asyncAPI)).isEqualTo(example);
    }

    @Test
    void shouldCreateStreetlightsKafkaAsyncAPI() throws Exception {
        var lightMeasuredMessage = MessageObject.builder()
                .messageId("lightMeasured")
                .name("lightMeasured")
                .title("Light measured")
                .summary("Inform about environmental lighting conditions of a particular streetlight.")
                .contentType("application/json")
                .traits(List.of(MessageTrait.builder()
                        .ref("#/components/messageTraits/commonHeaders")
                        .build()))
                .payload(MessagePayload.of(SchemaReference.toSchema("lightMeasuredPayload")))
                .build();

        var turnOnOffMessage = MessageObject.builder()
                .messageId("turnOnOff")
                .name("turnOnOff")
                .title("Turn on/off")
                .summary("Command a particular streetlight to turn the lights on or off.")
                .traits(List.of(MessageTrait.builder()
                        .ref("#/components/messageTraits/commonHeaders")
                        .build()))
                .payload(MessagePayload.of(SchemaReference.toSchema("turnOnOffPayload")))
                .build();

        var dimLightMessage = MessageObject.builder()
                .messageId("dimLight")
                .name("dimLight")
                .title("Dim light")
                .summary("Command a particular streetlight to dim the lights.")
                .traits(List.of(MessageTrait.builder()
                        .ref("#/components/messageTraits/commonHeaders")
                        .build()))
                .payload(MessagePayload.of(SchemaReference.toSchema("dimLightPayload")))
                .build();

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
                        ChannelObject.builder()
                                .address("smartylighting.streetlights.1.0.event.{streetlightId}.lighting.measured")
                                .messages(Map.of(
                                        "lightMeasured", MessageReference.toComponentMessage(lightMeasuredMessage)))
                                .description("The topic on which measured values may be produced and consumed.")
                                .parameters(Map.of(
                                        "streetlightId",
                                        ChannelParameter.builder()
                                                .ref("#/components/parameters/streetlightId")
                                                .build()))
                                .build(),
                        "lightTurnOn",
                        ChannelObject.builder()
                                .address("smartylighting.streetlights.1.0.action.{streetlightId}.turn.on")
                                .messages(Map.of("turnOn", MessageReference.toComponentMessage(turnOnOffMessage)))
                                .parameters(Map.of(
                                        "streetlightId",
                                        ChannelParameter.builder()
                                                .ref("#/components/parameters/streetlightId")
                                                .build()))
                                .build(),
                        "lightTurnOff",
                        ChannelObject.builder()
                                .address("smartylighting.streetlights.1.0.action.{streetlightId}.turn.off")
                                .messages(Map.of("turnOff", MessageReference.toComponentMessage(turnOnOffMessage)))
                                .parameters(Map.of(
                                        "streetlightId",
                                        ChannelParameter.builder()
                                                .ref("#/components/parameters/streetlightId")
                                                .build()))
                                .build(),
                        "lightsDim",
                        ChannelObject.builder()
                                .address("smartylighting.streetlights.1.0.action.{streetlightId}.dim")
                                .messages(Map.of("dimLight", MessageReference.toComponentMessage(dimLightMessage)))
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
                                .messages(
                                        List.of(MessageReference.toChannelMessage("lightingMeasured", "lightMeasured")))
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
                                .messages(List.of(MessageReference.toChannelMessage("lightTurnOn", "turnOn")))
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
                                .messages(List.of(MessageReference.toChannelMessage("lightTurnOff", "turnOff")))
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
                                .messages(List.of(MessageReference.toChannelMessage("lightsDim", "dimLight")))
                                .build()))
                .components(Components.builder()
                        .messages(Map.of(
                                lightMeasuredMessage.getMessageId(), lightMeasuredMessage,
                                turnOnOffMessage.getMessageId(), turnOnOffMessage,
                                dimLightMessage.getMessageId(), dimLightMessage))
                        .schemas(Map.of(
                                "lightMeasuredPayload",
                                ComponentSchema.of(SchemaObject.builder()
                                        .type(Set.of(SchemaType.OBJECT))
                                        .properties(Map.of(
                                                "lumens",
                                                SchemaObject.builder()
                                                        .type(Set.of(SchemaType.INTEGER))
                                                        .minimum(BigDecimal.ZERO)
                                                        .description("Light intensity measured in lumens.")
                                                        .build(),
                                                "sentAt",
                                                SchemaReference.toSchema("sentAt")))
                                        .build()),
                                "turnOnOffPayload",
                                ComponentSchema.of(SchemaObject.builder()
                                        .type(Set.of(SchemaType.OBJECT))
                                        .properties(Map.of(
                                                "command",
                                                SchemaObject.builder()
                                                        .type(Set.of(SchemaType.STRING))
                                                        .enumValues(List.of("on", "off"))
                                                        .description("Whether to turn on or off the light.")
                                                        .build(),
                                                "sentAt",
                                                SchemaReference.toSchema("sentAt")))
                                        .build()),
                                "dimLightPayload",
                                ComponentSchema.of(SchemaObject.builder()
                                        .type(Set.of(SchemaType.OBJECT))
                                        .properties(Map.of(
                                                "percentage",
                                                SchemaObject.builder()
                                                        .type(Set.of(SchemaType.INTEGER))
                                                        .description(
                                                                "Percentage to which the light should be dimmed to.")
                                                        .minimum(BigDecimal.ZERO)
                                                        .maximum(new BigDecimal("100"))
                                                        .build(),
                                                "sentAt",
                                                SchemaReference.toSchema("sentAt")))
                                        .build()),
                                "sentAt",
                                ComponentSchema.of(SchemaObject.builder()
                                        .type(Set.of(SchemaType.STRING))
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
                                        .headers(MessageHeaders.of(SchemaObject.builder()
                                                .type(Set.of(SchemaType.OBJECT))
                                                .properties(Map.of(
                                                        "my-app-header",
                                                        SchemaObject.builder()
                                                                .type(Set.of(SchemaType.INTEGER))
                                                                .minimum(BigDecimal.ZERO)
                                                                .maximum(new BigDecimal("100"))
                                                                .build()))
                                                .build()))
                                        .build()))
                        .operationTraits(Map.of(
                                "kafka",
                                OperationTraits.builder()
                                        .bindings(Map.of(
                                                "kafka",
                                                KafkaOperationBinding.builder()
                                                        .clientId(SchemaObject.builder()
                                                                .type(Set.of(SchemaType.STRING))
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
