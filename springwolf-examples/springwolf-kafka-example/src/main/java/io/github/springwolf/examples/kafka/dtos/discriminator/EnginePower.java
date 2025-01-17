// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.dtos.discriminator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class EnginePower {
    private int hp;
    private int torque;
}
