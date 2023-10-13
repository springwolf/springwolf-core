// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.TestOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.EMPTY_MAP;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            AsyncPublisherAnnotationScanner.class,
            DefaultSchemasService.class,
            ExampleJsonGenerator.class,
            TestOperationBindingProcessor.class
        })
@TestPropertySource(properties = {"test.property.test-channel=test-channel", "test.property.description=description"})
class AsyncPublisherAnnotationScannerIntegrationTest {

    @Autowired
    private AsyncPublisherAnnotationScanner channelScanner;

    @MockBean
    private ComponentClassScanner componentClassScanner;

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentClassScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    void scan_componentHasNoPublisherMethods() {
        setClassToScan(ClassWithoutPublisherAnnotation.class);

        Map<String, ChannelItem> channels = channelScanner.scan();

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentHasPublisherMethod() {
        // Given a class with methods annotated with AsyncPublisher, where only the channel-name is set
        setClassToScan(ClassWithPublisherAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                // Message description is not supported yet
                //                .description("")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel_subscribe")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel =
                ChannelItem.builder().bindings(null).subscribe(operation).build();

        assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
    }

    @Test
    void scan_componentHasPublisherMethodWithAllAttributes() {
        // Given a class with method annotated with AsyncPublisher, where all attributes are set
        setClassToScan(ClassWithPublisherAnnotationWithAllAttributes.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                // Message description is not supported yet
                //                .description("description")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("TestSchema"))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation = Operation.builder()
                .description("description")
                .operationId("test-channel_subscribe")
                .bindings(Map.of(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING))
                .message(message)
                .build();

        ChannelItem expectedChannel =
                ChannelItem.builder().bindings(null).subscribe(operation).build();

        assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
    }

    @Test
    void scan_componentHasMultiplePublisherAnnotations() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithMultipleListenerAnnotations.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                // Message description is not supported yet
                //                .description("")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation1 = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel-1_subscribe")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel1 =
                ChannelItem.builder().bindings(null).subscribe(operation1).build();

        Operation operation2 = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel-2_subscribe")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel2 =
                ChannelItem.builder().bindings(null).subscribe(operation2).build();

        assertThat(actualChannels)
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        "test-channel-1", expectedChannel1,
                        "test-channel-2", expectedChannel2));
    }

    @Test
    void scan_componentHasPublisherMethodWithAsyncMessageAnnotation() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithMessageAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .messageId("simpleFoo")
                .name("SimpleFooPayLoad")
                .title("Message Title")
                .description("Message description")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .schemaFormat("application/schema+json;version=draft-07")
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel_subscribe")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel =
                ChannelItem.builder().bindings(null).subscribe(operation).build();

        assertThat(actualChannels).containsExactly(Map.entry("test-channel", expectedChannel));
    }

    private static class ClassWithoutPublisherAnnotation {

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithPublisherAnnotation {

        @AsyncPublisher(operation = @AsyncOperation(channelName = "test-channel"))
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithPublisherAnnotationWithAllAttributes {

        @AsyncPublisher(
                operation =
                        @AsyncOperation(
                                channelName = "${test.property.test-channel}",
                                description = "${test.property.description}",
                                headers =
                                        @AsyncOperation.Headers(
                                                schemaName = "TestSchema",
                                                values = {
                                                    @AsyncOperation.Headers.Header(name = "header", value = "value")
                                                })))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithMultipleListenerAnnotations {

        @AsyncPublisher(operation = @AsyncOperation(channelName = "test-channel-1"))
        @AsyncPublisher(operation = @AsyncOperation(channelName = "test-channel-2"))
        private void methodWithMultipleAnnotation(SimpleFoo payload) {}
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    private static class ClassWithMessageAnnotation {

        @AsyncPublisher(
                operation =
                        @AsyncOperation(
                                channelName = "test-channel",
                                message =
                                        @AsyncMessage(
                                                description = "Message description",
                                                messageId = "simpleFoo",
                                                name = "SimpleFooPayLoad",
                                                schemaFormat = "application/schema+json;version=draft-07",
                                                title = "Message Title")))
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }
}
