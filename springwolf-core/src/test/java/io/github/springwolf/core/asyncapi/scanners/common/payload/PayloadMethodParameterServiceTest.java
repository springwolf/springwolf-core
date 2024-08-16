// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PayloadMethodParameterServiceTest {
    private PayloadClassExtractor payloadClassExtractor = mock(PayloadClassExtractor.class);
    private ComponentsService componentsService = mock(ComponentsService.class);
    private SpringwolfConfigProperties.ConfigDocket docket = mock(SpringwolfConfigProperties.ConfigDocket.class);
    private SpringwolfConfigProperties properties = mock(SpringwolfConfigProperties.class);

    private PayloadService payloadService;
    private PayloadMethodParameterService payloadMethodParameterService;

    @BeforeEach
    void setUp() {
        payloadService = new PayloadService(componentsService, properties);
        payloadMethodParameterService = new PayloadMethodParameterService(payloadClassExtractor, payloadService);
    }

    @Test
    public void shouldExtractPayloadFromMethod() {
        // given
        when(properties.getDocket()).thenReturn(docket);
        when(docket.getDefaultContentType()).thenReturn("application/json");

        Method method = mock(Method.class);
        when(payloadClassExtractor.extractFrom(method)).thenReturn(Optional.of(String.class));

        String schemaName = "my-schema-name";
        when(componentsService.resolvePayloadSchema(any(), any())).thenReturn(schemaName);

        SchemaObject schemaObject = SchemaObject.builder().build();
        when(componentsService.resolveSchema(schemaName)).thenReturn(schemaObject);

        // when
        var result = payloadMethodParameterService.extractSchema(method);

        // then
        assertThat(result.name()).isEqualTo(schemaName);
        assertThat(result.schema()).isEqualTo(schemaObject);
    }
}
