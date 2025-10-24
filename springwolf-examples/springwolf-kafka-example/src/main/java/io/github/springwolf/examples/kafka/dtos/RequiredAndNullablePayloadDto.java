// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Demonstrate required and nullable. Note, @Schema is only descriptive without nullability check")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequiredAndNullablePayloadDto {

    @Schema(
            requiredMode = REQUIRED,
            nullable = false,
            description = "This field must be present, and value cannot be null")
    private String requiredField;

    @Schema(requiredMode = REQUIRED, nullable = true, description = "This field must be present, but value can be null")
    private String requiredButNullableField;

    @Schema(
            requiredMode = NOT_REQUIRED,
            nullable = false,
            description = "This field can be skipped, but value cannot be null")
    private String notRequiredField;

    @Schema(
            requiredMode = NOT_REQUIRED,
            nullable = true,
            description = "This field can be skipped, or value can be null or present")
    private String requiredAndNullableField;

    @Schema(requiredMode = REQUIRED, nullable = true, description = "Follows OpenAPI 3.1 spec")
    private ComplexEnum enumField;

    @RequiredArgsConstructor
    public enum ComplexEnum {
        COMPLEX1,
        COMPLEX2,
    //
    ;
    }
}
