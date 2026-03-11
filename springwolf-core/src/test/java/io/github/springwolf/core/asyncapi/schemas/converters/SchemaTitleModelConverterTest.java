// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas.converters;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchemaTitleModelConverterTest {

    private final SchemaTitleModelConverter converter = new SchemaTitleModelConverter();

    private ModelConverterContext context;

    @BeforeEach
    void setUp() {
        context = mock(ModelConverterContext.class);
    }

    @Test
    void returnsNullWhenChainIsEmpty() {
        // given
        AnnotatedType type = new AnnotatedType(SimpleClass.class);
        Iterator<ModelConverter> emptyChain = Collections.emptyIterator();

        // when
        Schema<?> result = converter.resolve(type, context, emptyChain);

        // then
        assertThat(result).isNull();
    }

    @Test
    void returnsNullWhenChainReturnsNull() {
        // given
        AnnotatedType type = new AnnotatedType(SimpleClass.class);
        ModelConverter nextConverter = mock(ModelConverter.class);
        when(nextConverter.resolve(any(), any(), any())).thenReturn(null);
        Iterator<ModelConverter> chain =
                Collections.singletonList(nextConverter).iterator();

        // when
        Schema<?> result = converter.resolve(type, context, chain);

        // then
        assertThat(result).isNull();
    }

    @Test
    void returnsSchemaWithoutModificationForPrimitiveType() {
        // given - int is a primitive type recognized by PrimitiveType.createProperty
        AnnotatedType type = new AnnotatedType(Integer.class);
        Schema<?> primitiveSchema = new Schema<>();
        ModelConverter nextConverter = mock(ModelConverter.class);
        when(nextConverter.resolve(any(), any(), any())).thenReturn(primitiveSchema);
        Iterator<ModelConverter> chain =
                Collections.singletonList(nextConverter).iterator();

        // when
        Schema<?> result = converter.resolve(type, context, chain);

        // then
        assertThat(result).isSameAs(primitiveSchema);
        assertThat(result.getTitle()).isNull();
    }

    @Test
    void setsTitleOnSchemaWithoutRefForComplexType() {
        // given
        AnnotatedType type = new AnnotatedType(SimpleClass.class);
        Schema<?> schema = new Schema<>();
        // no $ref, no title set
        ModelConverter nextConverter = mock(ModelConverter.class);
        when(nextConverter.resolve(any(), any(), any())).thenReturn(schema);
        Iterator<ModelConverter> chain =
                Collections.singletonList(nextConverter).iterator();

        // when
        Schema<?> result = converter.resolve(type, context, chain);

        // then
        assertThat(result).isSameAs(schema);
        assertThat(result.getTitle()).isEqualTo("SimpleClass");
    }

    @Test
    void doesNotOverwriteExistingTitleOnSchema() {
        // given
        AnnotatedType type = new AnnotatedType(SimpleClass.class);
        Schema<?> schema = new Schema<>();
        schema.setTitle("ExistingTitle");
        ModelConverter nextConverter = mock(ModelConverter.class);
        when(nextConverter.resolve(any(), any(), any())).thenReturn(schema);
        Iterator<ModelConverter> chain =
                Collections.singletonList(nextConverter).iterator();

        // when
        Schema<?> result = converter.resolve(type, context, chain);

        // then
        assertThat(result).isSameAs(schema);
        assertThat(result.getTitle()).isEqualTo("ExistingTitle");
    }

    @Test
    void setsTitleOnDefinedModelWhenSchemaHasRef() {
        // given
        AnnotatedType type = new AnnotatedType(SimpleClass.class);
        Schema<?> schemaWithRef = new Schema<>();
        schemaWithRef.set$ref("#/components/schemas/SimpleClass");
        Schema<?> definedModel = new Schema<>();
        // definedModel has no title yet

        ModelConverter nextConverter = mock(ModelConverter.class);
        when(nextConverter.resolve(any(), any(), any())).thenReturn(schemaWithRef);
        when(context.resolve(type)).thenReturn(definedModel);
        Iterator<ModelConverter> chain =
                Collections.singletonList(nextConverter).iterator();

        // when
        Schema<?> result = converter.resolve(type, context, chain);

        // then
        assertThat(result).isSameAs(schemaWithRef);
        assertThat(definedModel.getTitle()).isEqualTo("SimpleClass");
    }

    @Test
    void doesNotOverwriteExistingTitleOnDefinedModelWhenSchemaHasRef() {
        // given
        AnnotatedType type = new AnnotatedType(SimpleClass.class);
        Schema<?> schemaWithRef = new Schema<>();
        schemaWithRef.set$ref("#/components/schemas/SimpleClass");
        Schema<?> definedModel = new Schema<>();
        definedModel.setTitle("ExistingTitle");

        ModelConverter nextConverter = mock(ModelConverter.class);
        when(nextConverter.resolve(any(), any(), any())).thenReturn(schemaWithRef);
        when(context.resolve(type)).thenReturn(definedModel);
        Iterator<ModelConverter> chain =
                Collections.singletonList(nextConverter).iterator();

        // when
        Schema<?> result = converter.resolve(type, context, chain);

        // then
        assertThat(result).isSameAs(schemaWithRef);
        assertThat(definedModel.getTitle()).isEqualTo("ExistingTitle");
    }

    @Test
    void doesNotSetTitleWhenDefinedModelIsNullForRefSchema() {
        // given
        AnnotatedType type = new AnnotatedType(SimpleClass.class);
        Schema<?> schemaWithRef = new Schema<>();
        schemaWithRef.set$ref("#/components/schemas/SimpleClass");

        ModelConverter nextConverter = mock(ModelConverter.class);
        when(nextConverter.resolve(any(), any(), any())).thenReturn(schemaWithRef);
        when(context.resolve(type)).thenReturn(null);
        Iterator<ModelConverter> chain =
                Collections.singletonList(nextConverter).iterator();

        // when
        Schema<?> result = converter.resolve(type, context, chain);

        // then
        assertThat(result).isSameAs(schemaWithRef);
    }

    static class SimpleClass {
        public String name;
    }
}
