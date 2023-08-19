package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.channel.sqs.SQSChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.sqs.SQSMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.sqs.SQSOperationBinding;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SqsListenerUtilTest {
    private static final String QUEUE = "test-queue";

    @Nested
    class FullConfiguration {
        @Test
        void getChannelName() {
            // given
            SqsListener annotation = getAnnotation(ClassWithFullSqsListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            String channelName = SqsListenerUtil.getChannelName(annotation, resolver);

            // then
            assertEquals("queue-1", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            SqsListener annotation = getAnnotation(ClassWithFullSqsListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    SqsListenerUtil.buildChannelBinding(annotation, resolver);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("sqs"), channelBinding.keySet());
            assertEquals(new SQSChannelBinding(), channelBinding.get("sqs"));
        }

        @Test
        void buildOperationBinding() {
            // given
            SqsListener annotation = getAnnotation(ClassWithFullSqsListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);

            // when
            Map<String, ? extends OperationBinding> operationBinding =
                    SqsListenerUtil.buildOperationBinding(annotation, resolver);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("sqs"), operationBinding.keySet());
            assertEquals(new SQSOperationBinding(), operationBinding.get("sqs"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, ? extends MessageBinding> messageBinding = SqsListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("sqs"), messageBinding.keySet());
            assertEquals(new SQSMessageBinding(), messageBinding.get("sqs"));
        }

        private static class ClassWithFullSqsListenerConfiguration {

            @SqsListener("${queue-1}")
            private void methodWithAnnotation(String payload) {}
        }
    }

    private static SqsListener getAnnotation(Class<?> clazz) {
        return clazz.getDeclaredMethods()[0].getAnnotation(SqsListener.class);
    }
}
