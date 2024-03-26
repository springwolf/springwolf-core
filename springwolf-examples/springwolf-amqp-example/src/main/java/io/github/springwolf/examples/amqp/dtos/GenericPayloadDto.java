// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericPayloadDto<T> {

    private T payload;
}
