// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelBinding;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.asyncapi.channels.ChannelsService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.grouping.AsyncApiGroupService;
import io.github.springwolf.core.asyncapi.operations.OperationsService;
import io.github.springwolf.core.configuration.docket.DefaultAsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            SpringwolfConfigProperties.class,
            DefaultAsyncApiDocketService.class,
            DefaultAsyncApiService.class,
        })
@Import({
    DefaultAsyncApiServiceIntegrationTest.TestDescriptionCustomizer.class,
    DefaultAsyncApiServiceIntegrationTest.TestDescriptionCustomizer2.class
})
@EnableConfigurationProperties
@TestPropertySource(
        properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.id=urn:io:github:springwolf:example",
            "springwolf.docket.default-content-type=application/json",
            "springwolf.docket.base-package=io.github.springwolf.core.example",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.host=some-server:1234",
        })
class DefaultAsyncApiServiceIntegrationTest {

    @MockitoBean
    private ChannelsService channelsService;

    @MockitoBean
    private OperationsService operationsService;

    @MockitoBean
    private ComponentsService componentsService;

    @MockitoBean
    private AsyncApiGroupService groupService;

    @Autowired
    private AsyncApiService asyncApiService;

    @BeforeEach
    void setUp() {
        when(channelsService.findChannels())
                .thenReturn(Map.of(
                        "consumer-topic",
                        ChannelObject.builder()
                                .bindings(Map.of("kafka", new KafkaChannelBinding()))
                                .messages(Map.of("receiveId", MessageReference.toComponentMessage("receiveId")))
                                .build(),
                        "producer-topic",
                        ChannelObject.builder()
                                .bindings(Map.of("kafka", new KafkaChannelBinding()))
                                .messages(Map.of("sendId", MessageReference.toComponentMessage("sendId")))
                                .build()));
    }

    @Test
    void getAsyncAPI_info_should_be_correct() {
        Info actualInfo = asyncApiService.getAsyncAPI().getInfo();

        assertThat(actualInfo.getTitle()).isEqualTo("Info title was loaded from spring properties");
        assertThat(actualInfo.getVersion()).isEqualTo("1.0.0");
        assertThat(actualInfo.getDescription()).isEqualTo("AsyncApiInfoDescriptionCustomizer2");
    }

    @Test
    void getAsyncAPI_servers_should_be_correct() {
        Map<String, Server> actualServers = asyncApiService.getAsyncAPI().getServers();

        assertThat(actualServers.get("test-protocol").getProtocol()).isEqualTo("test");
        assertThat(actualServers.get("test-protocol").getHost()).isEqualTo("some-server:1234");
    }

    @Test
    void getAsyncAPI_channels_should_be_correct() {
        Map<String, ChannelObject> actualChannels =
                asyncApiService.getAsyncAPI().getChannels();

        assertThat(actualChannels).hasSize(2);

        assertThat(actualChannels).isNotEmpty().containsKey("consumer-topic");
        final ChannelObject consumerChannel = actualChannels.get("consumer-topic");
        assertThat(consumerChannel.getBindings()).isEqualTo(Map.of("kafka", new KafkaChannelBinding()));
        assertThat(consumerChannel.getMessages()).hasSize(1);
        MessageReference receiveMessage =
                (MessageReference) consumerChannel.getMessages().get("receiveId");
        assertThat(receiveMessage.getRef()).isEqualTo("#/components/messages/receiveId");

        assertThat(actualChannels).isNotEmpty().containsKey("producer-topic");
        final ChannelObject publishChannel = actualChannels.get("producer-topic");
        assertThat(publishChannel.getBindings()).isEqualTo(Map.of("kafka", new KafkaChannelBinding()));
        assertThat(publishChannel.getMessages()).hasSize(1);
        MessageReference sendMessage =
                (MessageReference) publishChannel.getMessages().get("sendId");
        assertThat(sendMessage.getRef()).isEqualTo("#/components/messages/sendId");
    }

    @Order(TestDescriptionCustomizer.CUSTOMIZER_ORDER)
    public static class TestDescriptionCustomizer implements AsyncApiCustomizer {
        public static final int CUSTOMIZER_ORDER = 0;

        @Override
        public void customize(AsyncAPI asyncAPI) {
            asyncAPI.getInfo().setDescription("AsyncApiInfoDescriptionCustomizer");
        }
    }

    /**
     * Using a high @Order value, this customizer overwrites the previous customizers value
     */
    @Order(TestDescriptionCustomizer2.CUSTOMIZER_ORDER)
    public static class TestDescriptionCustomizer2 implements AsyncApiCustomizer {
        public static final int CUSTOMIZER_ORDER = TestDescriptionCustomizer.CUSTOMIZER_ORDER + 1;

        @Override
        public void customize(AsyncAPI asyncAPI) {
            asyncAPI.getInfo().setDescription("AsyncApiInfoDescriptionCustomizer2");
        }
    }
}
