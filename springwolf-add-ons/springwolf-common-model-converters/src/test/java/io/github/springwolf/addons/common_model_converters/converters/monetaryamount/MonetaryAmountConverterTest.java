// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters.monetaryamount;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MonetaryAmountConverterTest {

    private final MonetaryAmountConverter modelsConverter = new MonetaryAmountConverter();

    private final ModelConverters converters = new ModelConverters();

    @Test
    void monetaryAmountConverter() {
        // given
        converters.addConverter(modelsConverter);

        // when
        final Map<String, Schema> models = converters.readAll(new AnnotatedType(javax.money.MonetaryAmount.class));

        // then
        final Schema model = models.get("MonetaryAmount");
        assertThat(model).isNotNull();

        assertThat(model.getProperties().size()).isEqualTo(2);

        final Schema amountProperty = (Schema) model.getProperties().get("amount");
        assertThat(amountProperty).isNotNull();

        final Schema currencyProperty = (Schema) model.getProperties().get("currency");
        assertThat(currencyProperty).isNotNull();

        final Schema missingProperty = (Schema) model.getProperties().get("missing");
        assertThat(missingProperty).isNull();

        // then
        final Schema originalModel = models.get("javax.money.MonetaryAmount");
        assertThat(originalModel).isNotNull();

        assertThat(originalModel.getType()).isNull();
        assertThat(originalModel.get$ref()).isEqualTo("#/components/schemas/MonetaryAmount");
    }
}
