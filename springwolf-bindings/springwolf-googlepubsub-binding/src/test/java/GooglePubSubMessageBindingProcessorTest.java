// SPDX-License-Identifier: Apache-2.0
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchema;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncMessageBinding;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncMessageSchema;
import io.github.springwolf.bindings.googlepubsub.scanners.messages.GooglePubSubMessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GooglePubSubMessageBindingProcessorTest {
    private final GooglePubSubMessageBindingProcessor processor = new GooglePubSubMessageBindingProcessor();

    @Test
    void processTest() throws NoSuchMethodException {
        // given
        Method method = GooglePubSubMessageBindingProcessorTest.class.getMethod("methodWithAnnotation");

        // when
        ProcessedMessageBinding binding = processor.process(method).get();

        // then
        assertThat(binding.getType()).isEqualTo("googlepubsub");
        assertThat(binding.getBinding())
                .isEqualTo(
                        new GooglePubSubMessageBinding(null, "key", new GooglePubSubSchema("project/test"), "0.2.0"));
    }

    @Test
    void processWithoutAnnotationTest() throws NoSuchMethodException {
        // given
        Method method = GooglePubSubChannelBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        // when
        Optional<ProcessedMessageBinding> binding = processor.process(method);

        // then
        assertThat(binding).isNotPresent();
    }

    @GooglePubSubAsyncMessageBinding(
            orderingKey = "key",
            schema = @GooglePubSubAsyncMessageSchema(name = "project/test"))
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
