// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.swagger.v3.oas.models.media.ObjectSchema;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerSchemaPostProcessorTest {
    private final SwaggerSchemaPostProcessor swaggerSchemaPostProcessor = new SwaggerSchemaPostProcessor();

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
    void shouldUpdateOpenApiTypesField() {
        // given
        ObjectSchema schema = new ObjectSchema();

        // when
        swaggerSchemaPostProcessor.process(schema, null);

        // then
        assertThat(schema.getTypes()).isEqualTo(Set.of("object"));
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
        assertThat(schema.getExclusiveMinimum()).isNull();
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
        assertThat(schema.getExclusiveMaximum()).isNull();
    }
}
