// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService.PAYLOAD_NOT_USED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PayloadAsyncOperationServiceTest {
    private PayloadClassExtractor payloadClassExtractor = mock(PayloadClassExtractor.class);
    private ComponentsService componentsService = mock(ComponentsService.class);
    private SpringwolfConfigProperties properties = mock(SpringwolfConfigProperties.class);

    private PayloadService payloadService;
    private PayloadAsyncOperationService payloadAsyncOperationService;

    @BeforeEach
    void setUp() {
        payloadService = new PayloadService(componentsService, properties);
        payloadAsyncOperationService = new PayloadAsyncOperationService(payloadClassExtractor, payloadService);
    }

    @Test
    public void shouldUsePayloadFromAsyncOperationAnnotation() {
        // given
        AsyncMessage asyncMessage = mock(AsyncMessage.class);
        when(asyncMessage.contentType()).thenReturn("application/json");

        AsyncOperation asyncOperation = mock(AsyncOperation.class);
        doReturn(String.class).when(asyncOperation).payloadType();
        when(asyncOperation.message()).thenReturn(asyncMessage);

        String schemaName = "my-schema-name";
        when(componentsService.getSchemaName(String.class)).thenReturn(schemaName);

        ComponentSchema schemaObject = ComponentSchema.of(SchemaObject.builder().build());
        when(componentsService.resolvePayloadSchema(eq(String.class), any())).thenReturn(schemaObject);

        // when
        var result = payloadAsyncOperationService.extractSchema(asyncOperation, null);

        // then
        assertThat(result.name()).isEqualTo(schemaName);
        assertThat(result.schema()).isEqualTo(schemaObject);
    }

    @Test
    public void shouldExtractPayloadFromMethodWithAnnotation() {
        // given
        AsyncMessage asyncMessage = mock(AsyncMessage.class);
        when(asyncMessage.contentType()).thenReturn("application/json");

        AsyncOperation asyncOperation = mock(AsyncOperation.class);
        doReturn(Object.class).when(asyncOperation).payloadType();
        when(asyncOperation.message()).thenReturn(asyncMessage);

        Method method = mock(Method.class);
        when(payloadClassExtractor.extractFrom(method)).thenReturn(Optional.of(String.class));

        String schemaName = "my-schema-name";
        when(componentsService.getSchemaName(String.class)).thenReturn(schemaName);

        ComponentSchema schemaObject = ComponentSchema.of(SchemaObject.builder().build());
        when(componentsService.resolvePayloadSchema(eq(String.class), any())).thenReturn(schemaObject);

        // when
        var result = payloadAsyncOperationService.extractSchema(asyncOperation, method);

        // then
        assertThat(result.name()).isEqualTo(schemaName);
        assertThat(result.schema()).isEqualTo(schemaObject);
    }

    @Test
    public void shouldReturnPayloadNotUsed() {
        // given
        AsyncMessage asyncMessage = mock(AsyncMessage.class);
        when(asyncMessage.contentType()).thenReturn("application/json");

        AsyncOperation asyncOperation = mock(AsyncOperation.class);
        doReturn(Object.class).when(asyncOperation).payloadType();
        when(asyncOperation.message()).thenReturn(asyncMessage);

        Method method = mock(Method.class);
        when(payloadClassExtractor.extractFrom(method)).thenReturn(Optional.empty());

        // when
        var result = payloadAsyncOperationService.extractSchema(asyncOperation, method);

        // then
        assertThat(result.name()).isEqualTo("PayloadNotUsed");
        assertThat(result.schema()).isNotNull();
        SchemaObject schema = result.schema().getSchema();
        assertThat(schema.getTitle()).isEqualTo("PayloadNotUsed");
        assertThat(schema.getDescription()).isEqualTo("No payload specified");
        verify(componentsService).registerSchema(PAYLOAD_NOT_USED.schema().getSchema());
    }
}
