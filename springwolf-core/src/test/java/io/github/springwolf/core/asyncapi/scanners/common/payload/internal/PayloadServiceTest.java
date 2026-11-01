// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload.internal;

import io.github.springwolf.core.asyncapi.schemas.SchemasService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PayloadServiceTest {

    private SchemasService schemasService = mock(SchemasService.class);
    private SpringwolfConfigProperties.ConfigDocket docket = mock(SpringwolfConfigProperties.ConfigDocket.class);
    private SpringwolfConfigProperties properties = mock(SpringwolfConfigProperties.class);
    private PayloadService payloadService = new PayloadService(schemasService, properties);

    @Test
    void shouldExtractSchemaForInteger() {
        // given
        when(properties.getDocket()).thenReturn(docket);
        when(docket.getDefaultContentType()).thenReturn("application/json");

        String schemaName = "my-schema-name";
        when(schemasService.getSchemaName(Integer.class)).thenReturn(schemaName);

        // when
        var result = payloadService.buildSchema(Integer.class);

        // then
        assertThat(result.name()).isEqualTo(schemaName);
    }
}
