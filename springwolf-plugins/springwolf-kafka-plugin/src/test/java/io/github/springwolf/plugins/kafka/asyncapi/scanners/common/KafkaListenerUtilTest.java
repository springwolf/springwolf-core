// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.common;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.util.StringValueResolver;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KafkaListenerUtilTest {

    @Nested
    class ChannelName {
        @Test
        void fromTopics() {
            // given
            KafkaListener annotation = mock(KafkaListener.class);
            when(annotation.topics()).thenReturn(Arrays.array("${topic-1}", "${topic-2}"));

            StringValueResolver stringValueResolver = mock(StringValueResolver.class);
            when(stringValueResolver.resolveStringValue("${topic-1}")).thenReturn("topic-1");
            when(stringValueResolver.resolveStringValue("${topic-2}")).thenReturn("topic-2");

            // when
            String channelName = KafkaListenerUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertThat(channelName).isEqualTo("topic-1");
        }

        @Test
        void fromTopicPattern() {
            // given
            KafkaListener annotation = mock(KafkaListener.class);
            when(annotation.topics()).thenReturn(Arrays.array());
            when(annotation.topicPattern()).thenReturn("${topic-1}");

            StringValueResolver stringValueResolver = mock(StringValueResolver.class);
            when(stringValueResolver.resolveStringValue("${topic-1}")).thenReturn("topic-1");

            // when
            String channelName = KafkaListenerUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertThat(channelName).isEqualTo("topic-1");
        }
    }

    @Test
    void buildChannelBinding() {
        // when
        Map<String, ChannelBinding> channelBinding = KafkaListenerUtil.buildChannelBinding();

        // then
        assertThat(channelBinding.size()).isEqualTo(1);
        assertThat(channelBinding.keySet()).isEqualTo(Sets.newTreeSet("kafka"));
        assertThat(channelBinding.get("kafka")).isEqualTo(new KafkaChannelBinding());
    }

    @Test
    void buildOperationBinding() {
        // given
        KafkaListener annotation = mock(KafkaListener.class);
        when(annotation.groupId()).thenReturn("${group-id}");

        StringValueResolver stringValueResolver = mock(StringValueResolver.class);
        when(stringValueResolver.resolveStringValue("${group-id}")).thenReturn("group-id");

        // when
        Map<String, OperationBinding> operationBinding =
                KafkaListenerUtil.buildOperationBinding(annotation, stringValueResolver);

        // then
        assertThat(operationBinding.size()).isEqualTo(1);
        assertThat(operationBinding.keySet()).isEqualTo(Sets.newTreeSet("kafka"));

        KafkaOperationBinding expectedOperationBinding = KafkaOperationBinding.builder()
                .groupId(KafkaListenerUtil.buildKafkaGroupIdSchema("group-id"))
                .build();
        assertThat(operationBinding.get("kafka")).isEqualTo(expectedOperationBinding);
    }

    @Nested
    class MessageBindingTest {
        @Test
        void buildMessageBinding() {
            // when
            Map<String, MessageBinding> messageBinding = KafkaListenerUtil.buildMessageBinding(new SchemaObject());

            // then
            assertThat(messageBinding.size()).isEqualTo(1);
            assertThat(messageBinding.keySet()).isEqualTo(Sets.newTreeSet("kafka"));
            assertThat(messageBinding.get("kafka")).isEqualTo(new KafkaMessageBinding());
        }

        @Test
        void includeKafkaKeyHeader() {
            // given
            SchemaObject headerSchema = new SchemaObject();
            SchemaObject keySchema =
                    SchemaObject.builder().type(Set.of(SchemaType.STRING)).build();
            headerSchema.setProperties(Map.of(KafkaHeaders.RECEIVED_KEY, keySchema));

            // when
            Map<String, MessageBinding> messageBinding = KafkaListenerUtil.buildMessageBinding(headerSchema);

            // then
            assertThat(messageBinding.size()).isEqualTo(1);
            assertThat(messageBinding.keySet()).isEqualTo(Sets.newTreeSet("kafka"));
            assertThat(messageBinding.get("kafka"))
                    .isEqualTo(KafkaMessageBinding.builder().key(keySchema).build());
        }
    }
}
