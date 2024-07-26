// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.dtos.discriminator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "vehicleType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = VehicleElectricPayloadDto.class, name = "VehicleElectricPayloadDto"),
    @JsonSubTypes.Type(value = VehicleGasolinePayloadDto.class, name = "VehicleGasolinePayloadDto"),
})
@Schema(
        subTypes = {VehicleElectricPayloadDto.class, VehicleGasolinePayloadDto.class},
        discriminatorProperty = "vehicleType",
        discriminatorMapping = {
            @DiscriminatorMapping(value = "VehicleElectricPayloadDto", schema = VehicleElectricPayloadDto.class),
            @DiscriminatorMapping(value = "VehicleGasolinePayloadDto", schema = VehicleGasolinePayloadDto.class),
        },
        description = "Demonstrates the use of discriminator for polymorphic deserialization (not publishable)")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class VehicleBase {
    private String vehicleType; // added for discriminator

    private String powerSource;
    private int topSpeed;
}
