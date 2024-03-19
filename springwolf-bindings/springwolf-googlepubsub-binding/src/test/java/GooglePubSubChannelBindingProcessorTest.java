// SPDX-License-Identifier: Apache-2.0
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageStoragePolicy;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchemaSettings;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncChannelBinding;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncSchemaSetting;
import io.github.springwolf.bindings.googlepubsub.scanners.channels.GooglePubSubChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ProcessedChannelBinding;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GooglePubSubChannelBindingProcessorTest {
    private final GooglePubSubChannelBindingProcessor processor = new GooglePubSubChannelBindingProcessor();

    @Test
    void processTest() throws NoSuchMethodException {
        Method method = GooglePubSubChannelBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedChannelBinding binding = processor.process(method).get();

        assertThat(binding.getType()).isEqualTo("googlepubsub");
        GooglePubSubMessageStoragePolicy googlePubsubMessageStoragePolicy =
                new GooglePubSubMessageStoragePolicy(List.of());
        GooglePubSubSchemaSettings googlePubSubSchemaSettings =
                new GooglePubSubSchemaSettings("BINARY", "", "", "project/test");
        assertThat(binding.getBinding())
                .isEqualTo(new GooglePubSubChannelBinding(
                        null, "", googlePubsubMessageStoragePolicy, googlePubSubSchemaSettings, "0.2.0"));
    }

    @Test
    void processWithoutAnnotationTest() throws NoSuchMethodException {
        Method method = GooglePubSubChannelBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedChannelBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @GooglePubSubAsyncChannelBinding(
            schemaSettings = @GooglePubSubAsyncSchemaSetting(encoding = "BINARY", name = "project/test"))
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
