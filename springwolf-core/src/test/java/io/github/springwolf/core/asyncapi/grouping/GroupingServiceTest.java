// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.grouping;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.components.Components;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class GroupingServiceTest {

    private final SchemaObject schema1 = SchemaObject.builder().title("Schema1").build();
    private final SchemaObject schema2inlined =
            SchemaObject.builder().title("Schema2").build();
    private final SchemaObject schema3 = SchemaObject.builder().title("Schema3").build();
    private final SchemaObject header1Schema =
            SchemaObject.builder().title("Schema1Header").build();

    private final MessageObject message1 = MessageObject.builder()
            .messageId("messageId1")
            .payload(MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.toSchema(schema1.getTitle()))
                    .build()))
            .headers(MessageHeaders.of(SchemaReference.toSchema(header1Schema.getTitle())))
            .build();
    private final MessageObject message2 = MessageObject.builder()
            .messageId("messageId2")
            .payload(MessagePayload.of(schema2inlined))
            .build();
    private final MessageObject message3 = MessageObject.builder()
            .messageId("messageId3")
            .payload(MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.toSchema(schema3.getTitle()))
                    .build()))
            .build();

    private final ChannelObject channel1 = ChannelObject.builder()
            .channelId("channelId1")
            .address("channelId1-address")
            .messages(new HashMap<>(Map.of(
                    message1.getMessageId(),
                    MessageReference.toComponentMessage(message1.getMessageId()),
                    message2.getMessageId(),
                    MessageReference.toComponentMessage(message2.getMessageId()))))
            .build();
    private final ChannelObject channel2 = ChannelObject.builder()
            .channelId("channelId2")
            .address("channelId2-address")
            .messages(new HashMap<>(
                    Map.of(message3.getMessageId(), MessageReference.toComponentMessage(message3.getMessageId()))))
            .build();

    private final Operation sendOperation = Operation.builder()
            .operationId("send")
            .action(OperationAction.SEND)
            .channel(ChannelReference.fromChannel(channel1))
            .messages(List.of(
                    MessageReference.toChannelMessage(channel1.getChannelId(), message1.getMessageId()),
                    MessageReference.toChannelMessage(channel1.getChannelId(), message2.getMessageId())))
            .build();
    private final Operation receiveOperation = Operation.builder()
            .operationId("receive")
            .action(OperationAction.RECEIVE)
            .channel(ChannelReference.fromChannel(channel2))
            .messages(List.of(MessageReference.toChannelMessage(channel2.getChannelId(), message3.getMessageId())))
            .build();

    private final AsyncAPI simpleApi = AsyncAPI.builder()
            .channels(Map.of(channel2.getChannelId(), channel2))
            .operations(Map.of("receive", receiveOperation))
            .components(Components.builder()
                    .messages(Map.of(message3.getMessageId(), message3))
                    .schemas(Map.of(schema3.getTitle(), schema3))
                    .build())
            .build();
    private final AsyncAPI fullApi = AsyncAPI.builder()
            .channels(Map.of(channel1.getChannelId(), channel1, channel2.getChannelId(), channel2))
            .operations(Map.of("send", sendOperation, "receive", receiveOperation))
            .components(Components.builder()
                    .messages(Map.of(
                            message1.getMessageId(),
                            message1,
                            message2.getMessageId(),
                            message2,
                            message3.getMessageId(),
                            message3))
                    .schemas(Map.of(
                            schema1.getTitle(), schema1,
                            header1Schema.getTitle(), header1Schema,
                            schema3.getTitle(), schema3))
                    .build())
            .build();

    private final AsyncApiGroup noFilterGroup = AsyncApiGroup.builder()
            .operationActionsToKeep(List.of())
            .channelNamesToKeep(List.of())
            .messageNamesToKeep(List.of())
            .build();

    private final GroupingService groupingService = new GroupingService();

    @Test
    void shouldCreateNewAsyncApi() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .channels(simpleApi.getChannels())
                .operations(simpleApi.getOperations())
                .components(simpleApi.getComponents())
                .build();

        // when
        AsyncAPI grouped = groupingService.groupAPI(full, noFilterGroup);

        // then
        assertThat(grouped).isNotSameAs(full);
    }

    @Test
    void shouldUseIdenticalInfo() {
        // given
        Info info = Info.builder().title("title").build();
        AsyncAPI full = AsyncAPI.builder()
                .info(info)
                .channels(simpleApi.getChannels())
                .operations(simpleApi.getOperations())
                .components(simpleApi.getComponents())
                .build();

        // when
        AsyncAPI grouped = groupingService.groupAPI(full, noFilterGroup);

        // then
        assertThat(grouped.getInfo()).isSameAs(info);
    }

    @Test
    void shouldUseIdenticalId() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .id("id")
                .channels(simpleApi.getChannels())
                .operations(simpleApi.getOperations())
                .components(simpleApi.getComponents())
                .build();

        // when
        AsyncAPI grouped = groupingService.groupAPI(full, noFilterGroup);

        // then
        assertThat(grouped.getId()).isEqualTo("id");
    }

    @Test
    void shouldUseIdenticalDefaultContentType() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .defaultContentType("application/json")
                .channels(simpleApi.getChannels())
                .operations(simpleApi.getOperations())
                .components(simpleApi.getComponents())
                .build();

        // when
        AsyncAPI grouped = groupingService.groupAPI(full, noFilterGroup);

        // then
        assertThat(grouped.getDefaultContentType()).isEqualTo("application/json");
    }

    @Test
    void shouldUseIdenticalServers() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .servers(Map.of("server", Server.builder().build()))
                .channels(simpleApi.getChannels())
                .operations(simpleApi.getOperations())
                .components(simpleApi.getComponents())
                .build();

        // when
        AsyncAPI grouped = groupingService.groupAPI(full, noFilterGroup);

        // then
        assertThat(grouped.getServers()).isEqualTo(full.getServers());
    }

    @Test
    void shouldCopyEverythingForEmptyFilter() {
        // when
        AsyncAPI grouped = groupingService.groupAPI(fullApi, noFilterGroup);

        // then
        assertThat(grouped).isEqualTo(fullApi);
    }

    @Test
    void shouldResolveReferencedSchemas1FromSchema4() {
        SchemaObject schema4 = SchemaObject.builder()
                .title("Schema4")
                .oneOf(List.of(ComponentSchema.of(SchemaReference.toSchema(schema1.getTitle()))))
                .build();
        MessageObject message = MessageObject.builder()
                .messageId("messageId1")
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.toSchema(schema4.getTitle()))
                        .build()))
                .build();

        AsyncAPI api = AsyncAPI.builder()
                .channels(Map.of())
                .operations(Map.of())
                .components(Components.builder()
                        .messages(Map.of(message.getMessageId(), message))
                        .schemas(Map.of(schema1.getTitle(), schema1, schema4.getTitle(), schema4))
                        .build())
                .build();

        AsyncApiGroup messageFilterGroup = AsyncApiGroup.builder()
                .messageNamesToKeep(List.of(Pattern.compile("message.*")))
                .build();

        // when
        AsyncAPI grouped = groupingService.groupAPI(api, messageFilterGroup);

        // then
        assertThat(grouped.getComponents().getSchemas()).containsKey(schema1.getTitle());
        assertThat(grouped.getComponents().getSchemas()).containsKey(schema4.getTitle());
    }

    @Nested
    class ActionFiltering {

        @Test
        void shouldFilterSimple() {
            // given
            AsyncApiGroup actionFilterGroup = AsyncApiGroup.builder()
                    .operationActionsToKeep(List.of(OperationAction.RECEIVE))
                    .channelNamesToKeep(List.of())
                    .messageNamesToKeep(List.of())
                    .build();
            // when
            AsyncAPI grouped = groupingService.groupAPI(simpleApi, actionFilterGroup);

            // then
            assertThat(grouped.getChannels()).isEqualTo(Map.of(channel2.getChannelId(), channel2));
            assertThat(grouped.getOperations()).isEqualTo(Map.of("receive", receiveOperation));
            assertThat(grouped.getComponents().getMessages()).isEqualTo(Map.of(message3.getMessageId(), message3));
            assertThat(grouped.getComponents().getSchemas()).isEqualTo(Map.of(schema3.getTitle(), schema3));
        }

        @Test
        void shouldFilterFull() {
            // given
            AsyncApiGroup actionFilterGroup = AsyncApiGroup.builder()
                    .operationActionsToKeep(List.of(OperationAction.SEND))
                    .channelNamesToKeep(List.of())
                    .messageNamesToKeep(List.of())
                    .build();

            // when
            AsyncAPI grouped = groupingService.groupAPI(fullApi, actionFilterGroup);

            // then
            assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
            assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
            assertThat(grouped.getComponents().getMessages())
                    .isEqualTo(Map.of(
                            message1.getMessageId(), message1,
                            message2.getMessageId(), message2));
            assertThat(grouped.getComponents().getSchemas())
                    .isEqualTo(Map.of(schema1.getTitle(), schema1, header1Schema.getTitle(), header1Schema));
        }
    }

    @Nested
    class ChannelFiltering {

        @Test
        void shouldFilterEverythingWhenNoMatch() {
            // given
            AsyncApiGroup channelFilterGroup = AsyncApiGroup.builder()
                    .operationActionsToKeep(List.of())
                    .channelNamesToKeep(List.of(Pattern.compile("this-channel-name-does-not-exist")))
                    .messageNamesToKeep(List.of())
                    .build();

            // when
            AsyncAPI grouped = groupingService.groupAPI(fullApi, channelFilterGroup);

            // then
            assertThat(grouped.getChannels()).isEmpty();
            assertThat(grouped.getOperations()).isEmpty();
            assertThat(grouped.getComponents().getMessages()).isEmpty();
            assertThat(grouped.getComponents().getSchemas()).isEmpty();
        }

        @Test
        void shouldFilterSimple() {
            // given
            AsyncApiGroup channelFilterGroup = AsyncApiGroup.builder()
                    .operationActionsToKeep(List.of())
                    .channelNamesToKeep(List.of(Pattern.compile(channel2.getAddress())))
                    .messageNamesToKeep(List.of())
                    .build();

            // when
            AsyncAPI grouped = groupingService.groupAPI(simpleApi, channelFilterGroup);

            // then
            assertThat(grouped.getChannels()).isEqualTo(Map.of(channel2.getChannelId(), channel2));
            assertThat(grouped.getOperations()).isEqualTo(Map.of("receive", receiveOperation));
            assertThat(grouped.getComponents().getMessages()).isEqualTo(Map.of(message3.getMessageId(), message3));
            assertThat(grouped.getComponents().getSchemas()).isEqualTo(Map.of(schema3.getTitle(), schema3));
        }

        @Test
        void shouldFilterFull() {
            // given
            AsyncApiGroup channelFilterGroup = AsyncApiGroup.builder()
                    .operationActionsToKeep(List.of())
                    .channelNamesToKeep(List.of(Pattern.compile(channel1.getAddress())))
                    .messageNamesToKeep(List.of())
                    .build();

            // when
            AsyncAPI grouped = groupingService.groupAPI(fullApi, channelFilterGroup);

            // then
            assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
            assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
            assertThat(grouped.getComponents().getMessages())
                    .isEqualTo(Map.of(
                            message1.getMessageId(), message1,
                            message2.getMessageId(), message2));
            assertThat(grouped.getComponents().getSchemas())
                    .isEqualTo(Map.of(schema1.getTitle(), schema1, header1Schema.getTitle(), header1Schema));
        }
    }

    @Nested
    class MessageFiltering {
        @Test
        void shouldFilterEverythingWhenNoMatch() {
            // given
            AsyncApiGroup messageFilterGroup = AsyncApiGroup.builder()
                    .operationActionsToKeep(List.of())
                    .channelNamesToKeep(List.of())
                    .messageNamesToKeep(List.of(Pattern.compile("this-channel-name-does-not-exist")))
                    .build();

            // when
            AsyncAPI grouped = groupingService.groupAPI(fullApi, messageFilterGroup);

            // then
            assertThat(grouped.getChannels()).isEmpty();
            assertThat(grouped.getOperations()).isEmpty();
            assertThat(grouped.getComponents().getMessages()).isEmpty();
            assertThat(grouped.getComponents().getSchemas()).isEmpty();
        }

        @Test
        void shouldFilterSimple() {
            // given
            AsyncApiGroup messageFilterGroup = AsyncApiGroup.builder()
                    .operationActionsToKeep(List.of())
                    .channelNamesToKeep(List.of())
                    .messageNamesToKeep(List.of(Pattern.compile(message3.getMessageId())))
                    .build();

            // when
            AsyncAPI grouped = groupingService.groupAPI(simpleApi, messageFilterGroup);

            // then
            assertThat(grouped.getChannels()).isEqualTo(Map.of(channel2.getChannelId(), channel2));
            assertThat(grouped.getOperations()).isEqualTo(Map.of("receive", receiveOperation));
            assertThat(grouped.getComponents().getMessages()).isEqualTo(Map.of(message3.getMessageId(), message3));
            assertThat(grouped.getComponents().getSchemas()).isEqualTo(Map.of(schema3.getTitle(), schema3));
        }

        @Test
        void shouldFilterFull() {
            // given
            AsyncApiGroup messageFilterGroup = AsyncApiGroup.builder()
                    .operationActionsToKeep(List.of())
                    .channelNamesToKeep(List.of())
                    .messageNamesToKeep(List.of(Pattern.compile(message1.getMessageId())))
                    .build();

            // when
            AsyncAPI grouped = groupingService.groupAPI(fullApi, messageFilterGroup);

            // then
            assertThat(grouped.getOperations().keySet()).isEqualTo(Set.of("send"));
            assertThat(grouped.getOperations().get("send"))
                    .usingRecursiveComparison()
                    .ignoringFields("messages")
                    .isEqualTo(sendOperation);
            assertThat(grouped.getOperations().get("send").getMessages())
                    .hasSize(1)
                    .isEqualTo(List.of(
                            MessageReference.toChannelMessage(channel1.getChannelId(), message1.getMessageId())));

            assertThat(grouped.getChannels().keySet()).isEqualTo(Set.of(channel1.getChannelId()));
            assertThat(grouped.getChannels().get(channel1.getChannelId()))
                    .usingRecursiveComparison()
                    .ignoringFields("messages")
                    .isEqualTo(channel1);
            assertThat(grouped.getChannels().get(channel1.getChannelId()).getMessages())
                    .hasSize(1)
                    .isEqualTo(Map.of(
                            message1.getMessageId(), MessageReference.toComponentMessage(message1.getMessageId())));

            assertThat(grouped.getComponents().getMessages()).isEqualTo(Map.of(message1.getMessageId(), message1));

            assertThat(grouped.getComponents().getSchemas())
                    .isEqualTo(Map.of(schema1.getTitle(), schema1, header1Schema.getTitle(), header1Schema));
        }
    }
}
