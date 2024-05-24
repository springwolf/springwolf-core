// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.dtos.discriminator;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonTypeName("VehicleGasolinePayloadDto")
@Schema(
        description = "Gasoline vehicle implementation of VehicleBase"

        // Alternative, when you do not want to add the discriminator property vehicleType to VehicleBase
        // allOf = {VehicleBase.class}
        )
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class VehicleGasolinePayloadDto extends VehicleBase {
    private int fuelCapacity;
}
