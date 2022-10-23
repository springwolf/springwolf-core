package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KafkaListenerUtilTest {

    @Test
    public void getChannelName() {
        // given
        KafkaListener annotation = mock(KafkaListener.class);
        when(annotation.topics()).thenReturn(Arrays.array("${topic-1}", "${topic-2}"));

        StringValueResolver resolver = mock(StringValueResolver.class);
        when(resolver.resolveStringValue("${topic-1}")).thenReturn("topic-1");
        when(resolver.resolveStringValue("${topic-2}")).thenReturn("topic-2");

        // when
        String channelName = KafkaListenerUtil.getChannelName(annotation, resolver);

        // then
        assertEquals(channelName, "topic-1");
    }

    @Test
    public void buildChannelBinding() {
        // when
        Map<String, ? extends ChannelBinding> channelBinding = KafkaListenerUtil.buildChannelBinding();

        // then
        assertEquals(channelBinding.size(), 1);
        assertEquals(channelBinding.keySet(), Sets.newTreeSet("kafka"));
        assertEquals(channelBinding.get("kafka"), new KafkaChannelBinding());
    }

    @Test
    public void buildOperationBinding() {
        // given
        KafkaListener annotation = mock(KafkaListener.class);
        when(annotation.groupId()).thenReturn("${group-id}");

        StringValueResolver resolver = mock(StringValueResolver.class);
        when(resolver.resolveStringValue("${group-id}")).thenReturn("group-id");

        // when
        Map<String, ? extends OperationBinding> operationBinding = KafkaListenerUtil.buildOperationBinding(annotation, resolver);

        // then
        assertEquals(operationBinding.size(), 1);
        assertEquals(operationBinding.keySet(), Sets.newTreeSet("kafka"));
        KafkaOperationBinding expectedOperationBinding = new KafkaOperationBinding();
        expectedOperationBinding.setGroupId("group-id");
        assertEquals(operationBinding.get("kafka"), expectedOperationBinding);
    }
}