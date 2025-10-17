// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PayloadMethodReturnServiceTest {
    private ComponentsService componentsService = mock(ComponentsService.class);
    private SpringwolfConfigProperties.ConfigDocket docket = mock(SpringwolfConfigProperties.ConfigDocket.class);
    private SpringwolfConfigProperties properties = mock(SpringwolfConfigProperties.class);

    private PayloadService payloadService;
    private PayloadMethodReturnService payloadMethodReturnService;

    @BeforeEach
    void setUp() {
        payloadService = new PayloadService(componentsService, properties);
        payloadMethodReturnService = new PayloadMethodReturnService(payloadService);
    }

    @Test
    void shouldExtractPayloadFromMethod() {
        // given
        when(properties.getDocket()).thenReturn(docket);
        when(docket.getDefaultContentType()).thenReturn("application/json");

        Method method = mock(Method.class);
        doReturn(String.class).when(method).getReturnType();

        String schemaName = "my-schema-name";
        when(componentsService.getSchemaName(String.class)).thenReturn(schemaName);

        ComponentSchema schemaObject = ComponentSchema.of(SchemaObject.builder().build());
        when(componentsService.resolvePayloadSchema(eq(String.class), any())).thenReturn(schemaObject);

        // when
        var result = payloadMethodReturnService.extractSchema(method);

        // then
        assertThat(result.name()).isEqualTo(schemaName);
        assertThat(result.schema()).isEqualTo(schemaObject);
    }
}
