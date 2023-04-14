package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2._0_0.model.channel.ChannelItem;
import com.asyncapi.v2._0_0.model.channel.operation.Operation;
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
@ContextConfiguration(classes = {AsyncListenerAnnotationScanner.class, DefaultSchemasService.class, TestOperationBindingProcessor.class})
@TestPropertySource(properties = {"test.property.test-channel=test-channel", "test.property.description=description"})
public class AsyncListenerAnnotationScannerTest {

    @Autowired
    private AsyncListenerAnnotationScanner channelScanner;

    @MockBean
    private ComponentClassScanner componentClassScanner;

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentClassScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    public void scan_componentHasNoListenerMethods() {
        setClassToScan(ClassWithoutListenerAnnotation.class);

        Map<String, ChannelItem> channels = channelScanner.scan();

        assertThat(channels).isEmpty();
    }


    @Test
    public void scan_componentHasListenerMethod() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithListenerAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .description("")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel_publish")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(null)
                .publish(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry("test-channel", expectedChannel));
    }

    @Test
    public void scan_componentHasListenerMethodWithAllAttributes() {
        // Given a class with method annotated with AsyncListener, where all attributes are set
        setClassToScan(ClassWithListenerAnnotationWithAllAttributes.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .description("description")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("TestSchema"))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel_publish")
                .bindings(ImmutableMap.of(TestOperationBindingProcessor.TYPE, TestOperationBindingProcessor.BINDING))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(null)
                .publish(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry("test-channel", expectedChannel));
    }

    @Test
    public void scan_componentHasMultipleListenerAnnotations() {
        // Given a class with methods annotated with AsyncListener, where only the channel-name is set
        setClassToScan(ClassWithMultipleListenerAnnotations.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .description("")
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(EMPTY_MAP)
                .build();

        Operation operation1 = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel-1_publish")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel1 = ChannelItem.builder()
                .bindings(null)
                .publish(operation1)
                .build();

        Operation operation2 = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-channel-2_publish")
                .bindings(EMPTY_MAP)
                .message(message)
                .build();

        ChannelItem expectedChannel2 = ChannelItem.builder()
                .bindings(null)
                .publish(operation2)
                .build();

        assertThat(actualChannels)
                .containsExactlyEntriesOf(
                        ImmutableMap.of(
                                "test-channel-1", expectedChannel1,
                                "test-channel-2", expectedChannel2));
    }


    private static class ClassWithoutListenerAnnotation {

        private void methodWithoutAnnotation() {
        }
    }

    private static class ClassWithListenerAnnotation {

        @AsyncListener(operation = @AsyncOperation(
                channelName = "test-channel"
        ))
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithListenerAnnotationWithAllAttributes {

        @AsyncListener(operation = @AsyncOperation(
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

    private static class ClassWithMultipleListenerAnnotations {

        @AsyncListener(operation = @AsyncOperation(
                channelName = "test-channel-1"
        ))
        @AsyncListener(operation = @AsyncOperation(
                channelName = "test-channel-2"
        ))
        private void methodWithMultipleAnnotation(SimpleFoo payload) {
        }
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }
}
