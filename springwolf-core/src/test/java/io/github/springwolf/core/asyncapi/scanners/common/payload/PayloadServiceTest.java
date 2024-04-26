// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.Optional;

import static io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadService.PAYLOAD_NOT_USED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PayloadServiceTest {

    @Mock
    private PayloadClassExtractor payloadClassExtractor;

    @Mock
    private ComponentsService componentsService;

    @Mock
    private SpringwolfConfigProperties.ConfigDocket docket;

    @Mock
    private SpringwolfConfigProperties properties;

    @InjectMocks
    private PayloadService payloadService;

    @Test
    public void shouldUsePayloadFromAsyncOperationAnnotation() {
        // given
        AsyncMessage asyncMessage = mock(AsyncMessage.class);
        when(asyncMessage.contentType()).thenReturn("application/json");

        AsyncOperation asyncOperation = mock(AsyncOperation.class);
        doReturn(String.class).when(asyncOperation).payloadType();
        when(asyncOperation.message()).thenReturn(asyncMessage);

        String schemaName = "my-schema-name";
        when(componentsService.registerSchema(any(), any())).thenReturn(schemaName);

        SchemaObject schemaObject = SchemaObject.builder().build();
        when(componentsService.resolveSchema(schemaName)).thenReturn(schemaObject);

        // when
        var result = payloadService.extractSchema(asyncOperation, null);

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
        when(componentsService.registerSchema(any(), any())).thenReturn(schemaName);

        SchemaObject schemaObject = SchemaObject.builder().build();
        when(componentsService.resolveSchema(schemaName)).thenReturn(schemaObject);

        // when
        var result = payloadService.extractSchema(asyncOperation, method);

        // then
        assertThat(result.name()).isEqualTo(schemaName);
        assertThat(result.schema()).isEqualTo(schemaObject);
    }

    @Test
    public void shouldExtractPayloadFromMethod() {
        // given
        when(properties.getDocket()).thenReturn(docket);
        when(docket.getDefaultContentType()).thenReturn("application/json");

        Method method = mock(Method.class);
        when(payloadClassExtractor.extractFrom(method)).thenReturn(Optional.of(String.class));

        String schemaName = "my-schema-name";
        when(componentsService.registerSchema(any(), any())).thenReturn(schemaName);

        SchemaObject schemaObject = SchemaObject.builder().build();
        when(componentsService.resolveSchema(schemaName)).thenReturn(schemaObject);

        // when
        var result = payloadService.extractSchema(method);

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
        var result = payloadService.extractSchema(asyncOperation, method);

        // then
        assertThat(result.name()).isEqualTo("PayloadNotUsed");
        assertThat(result.schema().getTitle()).isEqualTo("PayloadNotUsed");
        assertThat(result.schema().getDescription()).isEqualTo("No payload specified");
        verify(componentsService).registerSchema(PAYLOAD_NOT_USED.schema());
    }

    @Test
    public void shouldExtractSchemaForInteger() {
        // given
        when(properties.getDocket()).thenReturn(docket);
        when(docket.getDefaultContentType()).thenReturn("application/json");

        String schemaName = "my-schema-name";
        when(componentsService.registerSchema(any(), any())).thenReturn(schemaName);

        // when
        var result = payloadService.extractSchemaForName(Integer.class);

        // then
        assertThat(result).isEqualTo(schemaName);
        verifyNoMoreInteractions(componentsService);
    }
}
