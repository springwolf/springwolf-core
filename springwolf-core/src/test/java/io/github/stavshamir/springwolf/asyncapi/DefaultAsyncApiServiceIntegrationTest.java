// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.server.Server;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
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
            "springwolf.docket.id=urn:io:github:stavshamir:springwolf:example",
            "springwolf.docket.default-content-type=application/yaml",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.url=some-server:1234",
        })
class DefaultAsyncApiServiceIntegrationTest {

    @MockBean
    private ChannelsService channelsService;

    @MockBean
    private SchemasService schemasService;

    @Autowired
    private AsyncApiService asyncApiService;

    @BeforeEach
    public void setUp() {
        when(channelsService.findChannels())
                .thenReturn(Map.of(
                        "consumer-topic",
                        ChannelItem.builder()
                                .subscribe(Operation.builder()
                                        .bindings(Map.of("kafka", new KafkaOperationBinding()))
                                        .message(Message.builder().build())
                                        .build())
                                .build(),
                        "producer-topic",
                        ChannelItem.builder()
                                .publish(Operation.builder()
                                        .bindings(Map.of("kafka", new KafkaOperationBinding()))
                                        .message(Message.builder().build())
                                        .build())
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
        assertThat(actualServers.get("test-protocol").getUrl()).isEqualTo("some-server:1234");
    }

    @Test
    void getAsyncAPI_channels_should_be_correct() {
        Map<String, ChannelItem> actualChannels = asyncApiService.getAsyncAPI().getChannels();

        assertThat(actualChannels).hasSize(2);

        assertThat(actualChannels).isNotEmpty().containsKey("consumer-topic");
        final ChannelItem consumerChannel = actualChannels.get("consumer-topic");
        assertThat(consumerChannel.getSubscribe()).isNotNull();
        assertThat(consumerChannel.getSubscribe().getBindings())
                .isEqualTo(Map.of("kafka", new KafkaOperationBinding()));
        assertThat(((Message) consumerChannel.getSubscribe().getMessage()).getDescription())
                .isNull();

        assertThat(actualChannels).isNotEmpty().containsKey("producer-topic");
        final ChannelItem publishChannel = actualChannels.get("producer-topic");
        assertThat(publishChannel.getPublish()).isNotNull();
        assertThat(publishChannel.getPublish().getBindings()).isEqualTo(Map.of("kafka", new KafkaOperationBinding()));
        assertThat(((Message) publishChannel.getPublish().getMessage()).getDescription())
                .isNull();
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
