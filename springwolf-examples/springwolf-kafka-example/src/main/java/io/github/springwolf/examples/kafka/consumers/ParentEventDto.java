// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventKey", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = LearningEvent.class, name = "LearningEvent") // ,
    // @JsonSubTypes.Type(value = ExamplePayloadDto.class, name = "ExamplePayloadDto") // omitted in demo
    /*,@JsonSubTypes.Type(value = ParentEventDto.class, name = "events") */
    // omitted in demo
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(
        description = "parent Event DTO",
        subTypes = {LearningEvent.class},
        discriminatorMapping = {
            @DiscriminatorMapping(value = "ParentEventDto", schema = ParentEventDto.class),
            @DiscriminatorMapping(value = "LearningEvent", schema = LearningEvent.class)
        },
        discriminatorProperty = "eventKey")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize()
public class ParentEventDto {
    // Option 1: with extra property: eventKey
    //    @Schema(
    //            description = "this is the eventKey used as JsonTypeInfo and discriminatorProperty with visible=true",
    //            requiredMode = NOT_REQUIRED)
    //    private String eventKey;

    @Schema(description = "this is event type", example = "event Type", requiredMode = NOT_REQUIRED)
    private String eventType;

    @Schema(description = "the first name of the candidate", example = "test", requiredMode = NOT_REQUIRED)
    private String firstName;
}
