// SPDX-License-Identifier: Apache-2.0
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageStoragePolicy;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchemaSettings;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncChannelBinding;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncMessageStoragePolicy;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncSchemaSetting;
import io.github.springwolf.bindings.googlepubsub.scanners.channels.GooglePubSubChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ProcessedChannelBinding;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GooglePubSubChannelBindingProcessorTest {
    private final GooglePubSubChannelBindingProcessor processor = new GooglePubSubChannelBindingProcessor();

    @Test
    void processTest() throws NoSuchMethodException {
        // given
        Method method = GooglePubSubChannelBindingProcessorTest.class.getMethod("methodWithAnnotation");

        // when
        ProcessedChannelBinding binding = processor.process(method).get();

        // then
        assertThat(binding.getType()).isEqualTo("googlepubsub");
        assertThat(binding.getBinding())
                .isEqualTo(new GooglePubSubChannelBinding(
                        null,
                        "messageRetentionDuration",
                        new GooglePubSubMessageStoragePolicy(List.of("region1", "region2")),
                        new GooglePubSubSchemaSettings("BINARY", "firstRevisionId", "lastRevisionId", "project/test"),
                        "0.2.0"));
    }

    @Test
    void processWithoutAnnotationTest() throws NoSuchMethodException {
        // given
        Method method = GooglePubSubChannelBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        // when
        Optional<ProcessedChannelBinding> binding = processor.process(method);

        // then
        assertThat(binding).isNotPresent();
    }

    @GooglePubSubAsyncChannelBinding(
            messageRetentionDuration = "messageRetentionDuration",
            messageStoragePolicy =
                    @GooglePubSubAsyncMessageStoragePolicy(allowedPersistenceRegions = {"region1", "region2"}),
            schemaSettings =
                    @GooglePubSubAsyncSchemaSetting(
                            encoding = "BINARY",
                            firstRevisionId = "firstRevisionId",
                            lastRevisionId = "lastRevisionId",
                            name = "project/test"))
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
