package io.github.stavshamir.springwolf.common_converters.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema
public class MonetaryAmount {

    @JsonProperty("amount")
    @Schema(example = "99.99")
    private BigDecimal amount;

    @JsonProperty("currency")
    @Schema(example = "USD")
    private String currency;
}
