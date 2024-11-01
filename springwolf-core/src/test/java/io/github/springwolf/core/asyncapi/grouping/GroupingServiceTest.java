// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.grouping;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.Components;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GroupingServiceTest {

    private final MessageObject message1 =
            MessageObject.builder().messageId("messageId1").build();
    private final MessageObject message2 =
            MessageObject.builder().messageId("messageId2").build();
    private final MessageObject message3 =
            MessageObject.builder().messageId("messageId3").build();

    private final ChannelObject channel1 = ChannelObject.builder()
            .channelId("channelId1")
            .address("channelId1") // TODO: change value
            .messages(new HashMap<>(Map.of(
                    message1.getMessageId(),
                    MessageReference.toComponentMessage(message1.getMessageId()),
                    message2.getMessageId(),
                    MessageReference.toComponentMessage(message2.getMessageId()))))
            .build();
    private final ChannelObject channel2 = ChannelObject.builder()
            .channelId("channelId2")
            .address("channelId2") // TODO: change value)
            .messages(new HashMap<>(
                    Map.of(message3.getMessageId(), MessageReference.toComponentMessage(message3.getMessageId()))))
            .build();

    private final Operation sendOperation = Operation.builder()
            .action(OperationAction.SEND)
            .channel(ChannelReference.fromChannel(channel1))
            .messages(List.of(
                    MessageReference.toComponentMessage(message1.getMessageId()),
                    MessageReference.toComponentMessage(message2.getMessageId())))
            .build();
    private final Operation receiveOperation = Operation.builder()
            .action(OperationAction.RECEIVE)
            .channel(ChannelReference.fromChannel(channel2))
            .messages(List.of(MessageReference.toComponentMessage(message3.getMessageId())))
            .build();

    @Test
    void shouldCreateNewAsyncApi() {
        // given
        AsyncAPI full = AsyncAPI.builder().build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of())
                .messageNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped).isNotSameAs(full);
    }

    @Test
    void shouldUseIdenticalInfo() {
        // given
        Info info = Info.builder().title("title").build();
        AsyncAPI full = AsyncAPI.builder().info(info).build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of())
                .messageNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getInfo()).isSameAs(info);
    }

    @Test
    void shouldUseIdenticalId() {
        // given
        AsyncAPI full = AsyncAPI.builder().id("id").build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of())
                .messageNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getId()).isEqualTo("id");
    }

    @Test
    void shouldUseIdenticalDefaultContentType() {
        // given
        AsyncAPI full =
                AsyncAPI.builder().defaultContentType("application/json").build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of())
                .messageNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getDefaultContentType()).isEqualTo("application/json");
    }

    @Test
    void shouldFilterOperationByAction() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel1.getChannelId(), channel1))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of(OperationAction.SEND))
                .channelNamesToKeep(List.of())
                .messageNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
    }

    @Test
    // should get all copied for all empty filters
    void shouldGetAllOperationsWhenActionsIsEmpty() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel1.getChannelId(), channel1))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of())
                .messageNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation, "receive", receiveOperation));
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
    }

    @Test
    void shouldFilterOperationByActionAndTransitiveChannels() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel1.getChannelId(), channel1, channel2.getChannelId(), channel2))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of(OperationAction.SEND))
                .channelNamesToKeep(List.of())
                .messageNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
    }

    @Test
    void shouldFilterOperationsByChannelName() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel1.getChannelId(), channel1, channel2.getChannelId(), channel2))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of(Pattern.compile(channel1.getChannelId())))
                .messageNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
    }

    @Test
    void shouldFilterOperationByMessageName() {
        // given
        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel1.getChannelId(), channel1, channel2.getChannelId(), channel2))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .components(Components.builder()
                        .messages(Map.of(message1.getMessageId(), message1, message2.getMessageId(), message2))
                        .build())
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of())
                .messageNamesToKeep(List.of(Pattern.compile(message1.getMessageId())))
                .build();

        // when
        AsyncAPI grouped = new GroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
        channel1.getMessages().remove(message2.getMessageId());
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
        assertThat(grouped.getComponents().getMessages()).isEqualTo(Map.of(message1.getMessageId(), message1));
    }
}
