// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.modelconverters;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;

import java.util.Iterator;

/**
 * ModelConverter to create openapi 3.1 schema for {@link ConsumerRecordMetadata} which is used
 * as header in tests.
 */
public class ConsumerRecordMetadataModelConverter implements ModelConverter {

    @Override
    public Schema<?> resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        if (type.getType().equals(ConsumerRecordMetadata.class)) {
            return new ObjectSchema();
        } else {
            return chain.next().resolve(type, context, chain);
        }
    }

    @Override
    public boolean isOpenapi31() {
        return true;
    }
}
