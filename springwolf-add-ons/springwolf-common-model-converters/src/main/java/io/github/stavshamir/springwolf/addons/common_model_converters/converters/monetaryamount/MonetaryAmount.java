// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.addons.common_model_converters.converters.monetaryamount;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema
class MonetaryAmount {

    @JsonProperty("amount")
    @Schema(example = "99.99", minimum = "0.1")
    private BigDecimal amount;

    @JsonProperty("currency")
    @Schema(example = "USD")
    private String currency;
}
