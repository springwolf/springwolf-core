package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.EMPTY_MAP;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {AsyncPublisherAnnotationScanner.class, DefaultSchemasService.class, TestOperationBindingProcessor.class})
@TestPropertySource(properties = {"test.property.test-channel=test-channel", "test.property.description=description"})
public class AsyncPublisherAnnotationScannerTest {

    @Autowired
    private AsyncPublisherAnnotationScanner channelScanner;

    @MockBean
    private ComponentClassScanner componentClassScanner;

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentClassScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    public void scan_componentHasNoPublisherMethods() {
        setClassToScan(ClassWithoutPublisherAnnotation.class);

        Map<String, ChannelItem> channels = channelScanner.scan();

        assertThat(channels).isEmpty();
    }


    @Test
    public void scan_componentHasPublisherMethod() {
        // Given a class with methods annotated with AsyncPublisher, where only the channel-name is set
        setClassToScan(ClassWithPublisherAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .description(null)
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel_subscribe")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(null)
                .subscribe(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry("test-channel", expectedChannel));
    }

    @Test
    public void scan_componentHasPublisherMethodWithAllAttributes() {
        // Given a class with method annotated with AsyncPublisher, where all attributes are set
        setClassToScan(ClassWithPublisherAnnotationWithAllAttributes.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .description("description")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("TestSchema"))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel_subscribe")
                .bindings(ImmutableMap.of(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(null)
                .subscribe(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry("test-channel", expectedChannel));
    }


    private static class ClassWithoutPublisherAnnotation {

        private void methodWithoutAnnotation() {
        }
    }

    private static class ClassWithPublisherAnnotation {

        @AsyncPublisher(operation = @AsyncOperation(
                channelName = "test-channel"
        ))
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithPublisherAnnotationWithAllAttributes {

        @AsyncPublisher(operation = @AsyncOperation(
                channelName = "${test.property.test-channel}",
                description = "${test.property.description}",
                headers = @AsyncOperation.Headers(
                        schemaName = "TestSchema",
                        values = {@AsyncOperation.Headers.Header(name = "header", value = "value")}
                )
        ))
        @TestOperationBindingProcessor.TestOperationBinding()
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }
}