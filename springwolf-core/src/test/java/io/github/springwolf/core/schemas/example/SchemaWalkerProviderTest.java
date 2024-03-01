// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.schemas.example;

import io.github.springwolf.core.asyncapi.components.examples.walkers.SchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchemaWalkerProviderTest {

    @Test
    void returnsSchemaWalkerForContentType() {
        // given
        SchemaWalker schemaWalker1 = mock(SchemaWalker.class);
        SchemaWalker schemaWalker2 = mock(SchemaWalker.class);
        SchemaWalker schemaWalker3 = mock(SchemaWalker.class);
        SchemaWalkerProvider provider = new SchemaWalkerProvider(List.of(schemaWalker1, schemaWalker2, schemaWalker3));

        when(schemaWalker2.canHandle("content-type")).thenReturn(true);

        // when
        Optional<SchemaWalker> schemaWalker = provider.generatorFor("content-type");

        // then
        assertThat(schemaWalker.isPresent()).isTrue();
        assertThat(schemaWalker.get()).isEqualTo(schemaWalker2);
    }

    @Test
    void doesNotReturnWalkerForUnknownContentType() {
        // given
        SchemaWalker schemaWalker1 = mock(SchemaWalker.class);
        SchemaWalker schemaWalker2 = mock(SchemaWalker.class);
        SchemaWalker schemaWalker3 = mock(SchemaWalker.class);
        SchemaWalkerProvider provider = new SchemaWalkerProvider(List.of(schemaWalker1, schemaWalker2, schemaWalker3));

        // when
        Optional<SchemaWalker> schemaWalker = provider.generatorFor("content-type");

        // then
        assertThat(schemaWalker.isPresent()).isFalse();
    }
}
