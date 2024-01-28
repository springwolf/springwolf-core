// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.swagger.v3.oas.models.media.ObjectSchema;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerSchemaPostProcessorTest {
    private SwaggerSchemaPostProcessor swaggerSchemaPostProcessor = new SwaggerSchemaPostProcessor();

    @Test
    void shouldRemoveAdditionalProperties() {
        // given
        ObjectSchema schema = new ObjectSchema();
        schema.setAdditionalProperties(true);

        // when
        swaggerSchemaPostProcessor.process(schema, null);

        // then
        assertThat(schema.getAdditionalProperties()).isNull();
    }

    @Test
    void shouldConvertOpenApiExclusiveMinimumSetting() {
        // given
        ObjectSchema schema = new ObjectSchema();
        schema.setMinimum(BigDecimal.ONE);
        schema.setExclusiveMinimum(true);

        // when
        swaggerSchemaPostProcessor.process(schema, null);

        // then
        assertThat(schema.getMinimum()).isEqualTo(BigDecimal.ONE);
        assertThat(schema.getExclusiveMinimumValue()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void shouldConvertOpenApiExclusiveMaximumSetting() {
        // given
        ObjectSchema schema = new ObjectSchema();
        schema.setMaximum(BigDecimal.ONE);
        schema.setExclusiveMaximum(true);

        // when
        swaggerSchemaPostProcessor.process(schema, null);

        // then
        assertThat(schema.getMaximum()).isEqualTo(BigDecimal.ONE);
        assertThat(schema.getExclusiveMaximumValue()).isEqualTo(BigDecimal.ONE);
    }
}
