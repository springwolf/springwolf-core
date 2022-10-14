package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestChannelScanner.class, DefaultSchemasService.class})
public class AbstractChannelScannerTest {

    @Autowired
    private TestChannelScanner channelScanner;

    @MockBean
    private ComponentsScanner componentsScanner;

    @MockBean
    private AsyncApiDocket docket;

    @Before
    public void setUp() throws Exception {
        when(docket.getComponentsScanner())
                .thenReturn(componentsScanner);
    }

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scanForComponents()).thenReturn(classesToScan);
    }

    @Test
    public void scan_componentHasNoListenerMethods() {
        setClassToScan(ClassWithoutListenerAnnotation.class);

        Map<String, ChannelItem> channels = channelScanner.scan();

        assertThat(channels).isEmpty();
    }

    @Test
    public void scan_componentHasListenerMethod() {
        // Given a class with methods annotated with TestChannelListener, whose topics attribute is hard coded
        setClassToScan(ClassWithListenerAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = channelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("test-operation-binding", new TestChannelScanner.TestOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(ImmutableMap.of("test-channel-binding", new TestChannelScanner.TestChannelBinding()))
                .publish(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry("test-channel", expectedChannel));
    }

    private static class ClassWithoutListenerAnnotation {

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithListenerAnnotation {

        @TestChannelListener
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

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestChannelListener {
    }

}
