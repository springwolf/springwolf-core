// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters;

import io.github.springwolf.addons.common_model_converters.converters.monetaryamount.MonetaryAmountConverter;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MonetaryAmountConverterTest {

    private MonetaryAmountConverter modelsConverter = new MonetaryAmountConverter();

    private ModelConverters converters = new ModelConverters();

    @Test
    void testMonetaryAmountConverter() {

        converters.addConverter(modelsConverter);

        final Schema model =
                converters.readAll(new AnnotatedType(MonetaryAmount.class)).get("MonetaryAmount");
        assertNotNull(model);
        assertEquals(2, model.getProperties().size());

        final Schema amountProperty = (Schema) model.getProperties().get("amount");
        assertNotNull(amountProperty);

        final Schema currencyProperty = (Schema) model.getProperties().get("currency");
        assertNotNull(currencyProperty);

        final Schema missingProperty = (Schema) model.getProperties().get("missing");
        assertNull(missingProperty);
    }
}
