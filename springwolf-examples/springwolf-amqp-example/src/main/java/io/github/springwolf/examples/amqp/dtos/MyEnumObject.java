// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum MyEnumObject {
    VALUE1,
    VALUE2
}
