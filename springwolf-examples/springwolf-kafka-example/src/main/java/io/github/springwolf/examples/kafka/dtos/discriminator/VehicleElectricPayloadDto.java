// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.dtos.discriminator;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonTypeName("VehicleElectricPayloadDto")
@Schema(
        description = "Electric vehicle implementation of VehicleBase"

        // Alternative, when you do not want to add the discriminator property vehicleType to VehicleBase
        // allOf = {VehicleBase.class}
        )
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class VehicleElectricPayloadDto extends VehicleBase {
    private int batteryCapacity;
    private int chargeTime;
}
