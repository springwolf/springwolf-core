// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters.monetaryamount;

import io.github.springwolf.addons.common_model_converters.converters.SimpleClassModelConverter;

public class MonetaryAmountConverter extends SimpleClassModelConverter {
    public MonetaryAmountConverter() {
        super(MonetaryAmount.class, javax.money.MonetaryAmount.class);
    }
}
