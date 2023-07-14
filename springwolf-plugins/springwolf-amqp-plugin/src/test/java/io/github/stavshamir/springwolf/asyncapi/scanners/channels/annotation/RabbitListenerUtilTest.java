package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RabbitListenerUtilTest {
    private static final String QUEUE = "test-queue";

    @Nested
    public class MinimalConfiguration {

        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithMinimalRabbitListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue(QUEUE)).thenReturn(QUEUE);

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, resolver);

            // then
            assertEquals(QUEUE, channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithMinimalRabbitListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue(QUEUE)).thenReturn(QUEUE);
            Map<String, Binding> bindingsMap = emptyMap();

            // when
            Map<String, ? extends ChannelBinding> channelBinding = RabbitListenerUtil.buildChannelBinding(annotation, resolver, bindingsMap);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(AMQPChannelBinding.builder()
                    .is("routingKey")
                    .exchange(AMQPChannelBinding.ExchangeProperties.builder().name("").build())
                    .build(), channelBinding.get("amqp"));
        }

        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithMinimalRabbitListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue(QUEUE)).thenReturn(QUEUE);
            Map<String, Binding> bindingsMap = emptyMap();

            // when
            Map<String, ? extends OperationBinding> operationBinding = RabbitListenerUtil.buildOperationBinding(annotation, resolver, bindingsMap);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder()
                    .cc(List.of(QUEUE))
                    .build(), operationBinding.get("amqp"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, ? extends MessageBinding> messageBinding = RabbitListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), messageBinding.keySet());
            assertEquals(new AMQPMessageBinding(), messageBinding.get("amqp"));
        }


        private static class ClassWithMinimalRabbitListenerConfiguration {

            @RabbitListener(queues = QUEUE)
            private void methodWithAnnotation(String payload) {
            }

        }
    }

    @Nested
    class FullConfiguration {
        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithFullRabbitListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, resolver);

            // then
            assertEquals("queue-1", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithFullRabbitListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            Map<String, Binding> bindingsMap = emptyMap();

            // when
            Map<String, ? extends ChannelBinding> channelBinding = RabbitListenerUtil.buildChannelBinding(annotation, resolver, bindingsMap);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(AMQPChannelBinding.builder()
                    .is("routingKey")
                    .exchange(AMQPChannelBinding.ExchangeProperties.builder().name("name").build())
                    .build(), channelBinding.get("amqp"));
        }

        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithFullRabbitListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            Map<String, Binding> bindingsMap = emptyMap();

            // when
            Map<String, ? extends OperationBinding> operationBinding = RabbitListenerUtil.buildOperationBinding(annotation, resolver, bindingsMap);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder()
                    .cc(List.of("key"))
                    .build(), operationBinding.get("amqp"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, ? extends MessageBinding> messageBinding = RabbitListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), messageBinding.keySet());
            assertEquals(new AMQPMessageBinding(), messageBinding.get("amqp"));
        }

        private static class ClassWithFullRabbitListenerConfiguration {

            @RabbitListener(bindings = {
                    @QueueBinding(
                            exchange = @Exchange(name = "name"),
                            key = "key",
                            value = @Queue(name = "${queue-1}"))
            })
            private void methodWithAnnotation(String payload) {
            }
        }
    }

    private static RabbitListener getAnnotation(Class<?> clazz) {
        return clazz.getDeclaredMethods()[0].getAnnotation(RabbitListener.class);
    }
}
