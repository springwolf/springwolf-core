// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageStoragePolicy;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchemaSettings;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncChannelBinding;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncSchemaSetting;
import io.github.springwolf.bindings.googlepubsub.configuration.SpringwolfGooglePubSubBindingAutoConfiguration;
import io.github.springwolf.core.asyncapi.components.DefaultComponentsService;
import io.github.springwolf.core.asyncapi.components.SwaggerSchemaUtil;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;
import io.github.springwolf.core.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ConfigurationClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.core.configuration.docket.DefaultAsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanBuilder;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.operations.CloudStreamFunctionOperationsScanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.config.BindingProperties;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            SpringwolfGooglePubSubBindingAutoConfiguration.class,
            ConfigurationClassScanner.class,
            ComponentClassScanner.class,
            DefaultBeanMethodsScanner.class,
            DefaultComponentsService.class,
            SwaggerSchemaUtil.class,
            PayloadClassExtractor.class,
            DefaultSchemaWalker.class,
            SchemaWalkerProvider.class,
            ExampleJsonValueGenerator.class,
            DefaultAsyncApiDocketService.class,
            CloudStreamFunctionChannelsScanner.class,
            CloudStreamFunctionOperationsScanner.class,
            FunctionalChannelBeanBuilder.class,
            SpringwolfConfigProperties.class
        })
@TestPropertySource(
        properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Test",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.base-package=io.github.springwolf.plugins.cloudstream.asyncapi.scanners.channels",
            "springwolf.docket.servers.googlepubsub.protocol=googlepubsub",
            "springwolf.docket.servers.googlepubsub.host=kafka:9092",
        })
@EnableConfigurationProperties
@Import(CloudStreamFunctionChannelsBindingSchemaSettingIntegrationTest.Configuration.class)
public class CloudStreamFunctionChannelsBindingSchemaSettingIntegrationTest {

    @MockBean
    private BindingServiceProperties bindingServiceProperties;

    @Autowired
    private CloudStreamFunctionChannelsScanner channelsScanner;

    private GooglePubSubSchemaSettings googlePubSubSchemaSettings =
            new GooglePubSubSchemaSettings("BINARY", "", "", "project/test");

    private GooglePubSubMessageStoragePolicy googlePubsubMessageStoragePolicy =
            new GooglePubSubMessageStoragePolicy(List.of());
    private Map<String, ChannelBinding> channelBindingGooglePubSub = Map.of(
            "googlepubsub",
            new GooglePubSubChannelBinding(
                    null, "", googlePubsubMessageStoragePolicy, googlePubSubSchemaSettings, "0.2.0"));

    @Test
    void testConsumerBinding() {
        // Given a binding "spring.cloud.stream.bindings.testConsumer-in-0.destination=test-consumer-input-topic"
        BindingProperties testConsumerInBinding = new BindingProperties();
        String topicName = "test-consumer-input-topic";
        testConsumerInBinding.setDestination(topicName);
        when(bindingServiceProperties.getBindings())
                .thenReturn(Map.of("testPubSubConsumer-in-0", testConsumerInBinding));

        // When scan is called
        Map<String, ChannelObject> actualChannels = channelsScanner.scan();

        // Then the returned channels contain a ChannelItem with the correct data
        MessageObject message = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(MessagePayload.of(MultiFormatSchema.builder()
                        .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                        .build()))
                .headers(MessageHeaders.of(MessageReference.toSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                .bindings(Map.of("googlepubsub", new GooglePubSubMessageBinding()))
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(channelBindingGooglePubSub)
                .messages(Map.of(message.getName(), MessageReference.toComponentMessage(message)))
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(topicName, expectedChannel));
    }

    @TestConfiguration
    public static class Configuration {
        @Bean
        @GooglePubSubAsyncChannelBinding(
                schemaSettings = @GooglePubSubAsyncSchemaSetting(encoding = "BINARY", name = "project/test"))
        public Consumer<String> testPubSubConsumer() {
            return System.out::println;
        }
    }
}
