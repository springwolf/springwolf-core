package io.github.stavshamir.springwolf.common_converters.converters;

import io.github.stavshamir.springwolf.common_converters.converters.monetaryamount.MonetaryAmountConverter;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;

import static org.junit.jupiter.api.Assertions.*;

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
