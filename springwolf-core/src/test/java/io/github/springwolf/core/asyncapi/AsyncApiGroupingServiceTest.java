// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AsyncApiGroupingServiceTest {

    @Test
    void shouldCreateNewAsyncApi() {
        // given
        AsyncAPI full = AsyncAPI.builder().build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

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
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

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
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

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
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getDefaultContentType()).isEqualTo("application/json");
    }

    @Test
    void shouldFilterOperationByAction() {
        // given
        String channelId = "channelId";
        ChannelObject channel = ChannelObject.builder().channelId(channelId).build();

        Operation sendOperation = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel(channel))
                .build();
        Operation receiveOperation = Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channel))
                .build();

        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel.getChannelId(), channel))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of(OperationAction.SEND))
                .channelNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel.getChannelId(), channel));
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
    }

    @Test
    // should get all copied for all empty filters
    void shouldGetAllOperationsWhenActionsIsEmpty() {
        // given
        String channelId = "channelId";
        ChannelObject channel = ChannelObject.builder().channelId(channelId).build();

        Operation sendOperation = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel(channel))
                .build();
        Operation receiveOperation = Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channel))
                .build();

        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel.getChannelId(), channel))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

        // then
        // TODO: special case: when nothing is marked, mark everything
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation, "receive", receiveOperation));
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel.getChannelId(), channel));
    }

    @Test
    void shouldFilterOperationByActionAndTransitiveChannels() {
        // given
        String channelId1 = "channelId1";
        ChannelObject channel1 = ChannelObject.builder().channelId(channelId1).build();
        String channelId2 = "channelId2";
        ChannelObject channel2 = ChannelObject.builder().channelId(channelId2).build();

        Operation sendOperation = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel(channel1))
                .build();
        Operation receiveOperation = Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channelId2))
                .build();

        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel1.getChannelId(), channel1, channel2.getChannelId(), channel2))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of(OperationAction.SEND))
                .channelNamesToKeep(List.of())
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
    }

    @Test
    void shouldFilterOperationsByChannelName() {
        // given
        String channelId1 = "channelId1";
        ChannelObject channel1 = ChannelObject.builder().channelId(channelId1).build();
        String channelId2 = "channelId2";
        ChannelObject channel2 = ChannelObject.builder().channelId(channelId2).build();

        Operation sendOperation = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel(channel1))
                .build();
        Operation receiveOperation = Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channelId2))
                .build();

        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel1.getChannelId(), channel1, channel2.getChannelId(), channel2))
                .operations(Map.of("send", sendOperation, "receive", receiveOperation))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of())
                .channelNamesToKeep(List.of(channel1.getChannelId()))
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation));
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
    }

    @Test
    @Disabled
    void testOrIsNotEnough() {
        // given
        String channelId1 = "channelId1";
        ChannelObject channel1 = ChannelObject.builder().channelId(channelId1).build();
        String channelId2 = "channelId2";
        ChannelObject channel2 = ChannelObject.builder().channelId(channelId2).build();

        Operation sendOperation1 = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel(channel1))
                .build();
        Operation receiveOperation1 = Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channelId1))
                .build();

        Operation sendOperation2 = Operation.builder()
                .action(OperationAction.SEND)
                .channel(ChannelReference.fromChannel(channel2))
                .build();
        Operation receiveOperation2 = Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channelId2))
                .build();

        AsyncAPI full = AsyncAPI.builder()
                .channels(Map.of(channel1.getChannelId(), channel1, channel2.getChannelId(), channel2))
                .operations(Map.of(
                        "channel1_send", sendOperation1,
                        "channel1_receive", receiveOperation1,
                        "channel2_send", sendOperation2,
                        "channel2_receive", receiveOperation2))
                .build();

        AsyncApiGroup asyncApiGroup = AsyncApiGroup.builder()
                .operationActionsToKeep(List.of(OperationAction.SEND))
                .channelNamesToKeep(List.of(channel1.getChannelId()))
                .build();

        // when
        AsyncAPI grouped = new AsyncApiGroupingService().groupAPI(full, asyncApiGroup);

        // then
        assertThat(grouped.getOperations()).isEqualTo(Map.of("send", sendOperation1));
        assertThat(grouped.getChannels()).isEqualTo(Map.of(channel1.getChannelId(), channel1));
    }
}
