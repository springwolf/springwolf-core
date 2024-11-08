// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.grouping;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AsyncApiGroupServiceTest {

    private final SpringwolfConfigProperties.ConfigDocket configDocket = mock();
    private final SpringwolfConfigProperties springwolfConfigProperties = mock();
    private final GroupingService groupingService = mock();
    private final AsyncApiGroupService asyncApiGroupService =
            new AsyncApiGroupService(springwolfConfigProperties, groupingService);

    private final AsyncAPI asyncAPI = mock(AsyncAPI.class);
    private final AsyncAPI groupedAsyncApi = mock(AsyncAPI.class);

    @BeforeEach
    void setUp() {
        when(springwolfConfigProperties.getDocket()).thenReturn(configDocket);
        when(groupingService.groupAPI(eq(asyncAPI), any())).thenReturn(groupedAsyncApi);
    }

    @Test
    void shouldReturnEmptyWhenNoGroupDefined() {
        // given
        when(configDocket.getGroupConfigs()).thenReturn(List.of());

        // when
        Map<String, AsyncAPI> result = asyncApiGroupService.group(asyncAPI);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldCreateGroup() {
        // given
        SpringwolfConfigProperties.ConfigDocket.Group group = new SpringwolfConfigProperties.ConfigDocket.Group();
        group.setGroup("group1");
        when(configDocket.getGroupConfigs()).thenReturn(List.of(group));

        // when
        Map<String, AsyncAPI> result = asyncApiGroupService.group(asyncAPI);

        // then
        assertThat(result).hasSize(1).containsEntry("group1", groupedAsyncApi);
    }

    @Test
    void shouldRequireNonBlankGroupName() {
        // given
        SpringwolfConfigProperties.ConfigDocket.Group group = new SpringwolfConfigProperties.ConfigDocket.Group();
        group.setGroup("");
        when(configDocket.getGroupConfigs()).thenReturn(List.of(group));

        // when
        try {
            asyncApiGroupService.group(asyncAPI);
            fail();
        } catch (Exception e) {
            // then
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("AsyncApiGroup must have a name set in configuration");
        }
    }

    @Test
    void shouldOnlyAllowGroupingByOneCriteria() {
        // given
        SpringwolfConfigProperties.ConfigDocket.Group group = new SpringwolfConfigProperties.ConfigDocket.Group();
        group.setGroup("group1");
        group.setActionToMatch(List.of(OperationAction.RECEIVE));
        group.setChannelNameToMatch(List.of("channel-1"));
        when(configDocket.getGroupConfigs()).thenReturn(List.of(group));

        // when
        try {
            asyncApiGroupService.group(asyncAPI);
            fail();
        } catch (Exception e) {
            // then
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("AsyncApiGroup group1 must specify at most one filter criteria");
        }
    }

    @Test
    void shouldGroupByAction() {
        // given
        List<OperationAction> actions = List.of(OperationAction.RECEIVE);

        SpringwolfConfigProperties.ConfigDocket.Group group = new SpringwolfConfigProperties.ConfigDocket.Group();
        group.setGroup("group1");
        group.setActionToMatch(actions);
        when(configDocket.getGroupConfigs()).thenReturn(List.of(group));

        // when
        asyncApiGroupService.group(asyncAPI);

        // then
        verify(groupingService)
                .groupAPI(
                        any(),
                        eq(AsyncApiGroup.builder()
                                .groupName("group1")
                                .operationActionsToKeep(actions)
                                .build()));
    }

    @Test
    void shouldGroupByChannels() {
        // given
        List<String> channels = List.of("channel-1");

        SpringwolfConfigProperties.ConfigDocket.Group group = new SpringwolfConfigProperties.ConfigDocket.Group();
        group.setGroup("group1");
        group.setChannelNameToMatch(channels);
        when(configDocket.getGroupConfigs()).thenReturn(List.of(group));

        // when
        asyncApiGroupService.group(asyncAPI);

        // then
        ArgumentCaptor<AsyncApiGroup> captor = ArgumentCaptor.forClass(AsyncApiGroup.class);
        verify(groupingService).groupAPI(any(), captor.capture());

        AsyncApiGroup capturedGroup = captor.getValue();
        assertThat(capturedGroup.getGroupName()).isEqualTo("group1");

        Pattern actualPattern = capturedGroup.getChannelNamesToKeep().get(0);
        assertThat(actualPattern.pattern()).isEqualTo(channels.get(0));
    }

    @Test
    void shouldGroupByMessage() {
        // given
        List<String> messages = List.of("message-1");

        SpringwolfConfigProperties.ConfigDocket.Group group = new SpringwolfConfigProperties.ConfigDocket.Group();
        group.setGroup("group1");
        group.setMessageNameToMatch(messages);
        when(configDocket.getGroupConfigs()).thenReturn(List.of(group));

        // when
        asyncApiGroupService.group(asyncAPI);

        // then
        ArgumentCaptor<AsyncApiGroup> captor = ArgumentCaptor.forClass(AsyncApiGroup.class);
        verify(groupingService).groupAPI(any(), captor.capture());

        AsyncApiGroup capturedGroup = captor.getValue();
        assertThat(capturedGroup.getGroupName()).isEqualTo("group1");

        Pattern actualPattern = capturedGroup.getMessageNamesToKeep().get(0);
        assertThat(actualPattern.pattern()).isEqualTo(messages.get(0));
    }
}
