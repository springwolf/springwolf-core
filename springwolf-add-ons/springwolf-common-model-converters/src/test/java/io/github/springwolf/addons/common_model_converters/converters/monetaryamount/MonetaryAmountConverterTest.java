// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters.monetaryamount;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MonetaryAmountConverterTest {

    private final MonetaryAmountConverter modelsConverter = new MonetaryAmountConverter();

    private final ModelConverters converters = new ModelConverters();

    @Test
    void testMonetaryAmountConverter() {
        // given
        converters.addConverter(modelsConverter);

        // when
        final Map<String, Schema> models = converters.readAll(new AnnotatedType(javax.money.MonetaryAmount.class));

        // then
        final Schema model = models.get("MonetaryAmount");
        assertNotNull(model);

        assertEquals(2, model.getProperties().size());

        final Schema amountProperty = (Schema) model.getProperties().get("amount");
        assertNotNull(amountProperty);

        final Schema currencyProperty = (Schema) model.getProperties().get("currency");
        assertNotNull(currencyProperty);

        final Schema missingProperty = (Schema) model.getProperties().get("missing");
        assertNull(missingProperty);

        // then
        final Schema originalModel = models.get("javax.money.MonetaryAmount");
        assertNotNull(originalModel);

        assertNull(originalModel.getType());
        assertEquals("#/components/schemas/MonetaryAmount", originalModel.get$ref());
    }
}
