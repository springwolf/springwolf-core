// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(
        description = "LMS payload model",
        type = "LearningEvent",
        defaultValue = "test",
        additionalProperties = Schema.AdditionalPropertiesValue.TRUE,
        //        discriminatorProperty = "eventKey" // does not matter

        // Option 2:
        allOf = {ParentEventDto.class})
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@JsonTypeName("LearningEvent")
public class LearningEvent extends ParentEventDto {
    @Schema(description = "the course type", example = "ILT", requiredMode = REQUIRED)
    private String courseType;
}
