// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageStoragePolicy;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchemaSettings;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncChannelBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ProcessedChannelBinding;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

public class GooglePubSubChannelBindingProcessor implements ChannelBindingProcessor {
    @Override
    public Optional<ProcessedChannelBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(GooglePubSubAsyncChannelBinding.class::isInstance)
                .map(GooglePubSubAsyncChannelBinding.class::cast)
                .findAny()
                .map(this::mapToChannelBinding);
    }

    private ProcessedChannelBinding mapToChannelBinding(GooglePubSubAsyncChannelBinding bindingAnnotation) {

        GooglePubSubMessageStoragePolicy.GooglePubSubMessageStoragePolicyBuilder policyBuilder =
                GooglePubSubMessageStoragePolicy.builder();
        if (bindingAnnotation.messageStoragePolicy().allowedPersistenceRegions().length > 0) {
            policyBuilder.allowedPersistenceRegions(
                    Arrays.stream(bindingAnnotation.messageStoragePolicy().allowedPersistenceRegions())
                            .toList());
        }

        GooglePubSubSchemaSettings.GooglePubSubSchemaSettingsBuilder schemaSettingsBuilder =
                GooglePubSubSchemaSettings.builder();
        if (StringUtils.isNotBlank(bindingAnnotation.schemaSettings().encoding())) {
            schemaSettingsBuilder.encoding(bindingAnnotation.schemaSettings().encoding());
        }
        if (StringUtils.isNotBlank(bindingAnnotation.schemaSettings().firstRevisionId())) {
            schemaSettingsBuilder.firstRevisionId(
                    bindingAnnotation.schemaSettings().firstRevisionId());
        }
        if (StringUtils.isNotBlank(bindingAnnotation.schemaSettings().lastRevisionId())) {
            schemaSettingsBuilder.lastRevisionId(
                    bindingAnnotation.schemaSettings().lastRevisionId());
        }
        if (StringUtils.isNotBlank(bindingAnnotation.schemaSettings().name())) {
            schemaSettingsBuilder.name(bindingAnnotation.schemaSettings().name());
        }

        GooglePubSubChannelBinding.GooglePubSubChannelBindingBuilder bindingBuilder =
                GooglePubSubChannelBinding.builder()
                        .messageStoragePolicy(policyBuilder.build())
                        .schemaSettings(schemaSettingsBuilder.build());
        if (StringUtils.isNotBlank(bindingAnnotation.messageRetentionDuration())) {
            bindingBuilder.messageRetentionDuration(bindingAnnotation.messageRetentionDuration());
        }
        if (StringUtils.isNotBlank(bindingAnnotation.bindingVersion())) {
            bindingBuilder.bindingVersion(bindingAnnotation.bindingVersion());
        }

        return new ProcessedChannelBinding("googlepubsub", bindingBuilder.build());
    }
}
