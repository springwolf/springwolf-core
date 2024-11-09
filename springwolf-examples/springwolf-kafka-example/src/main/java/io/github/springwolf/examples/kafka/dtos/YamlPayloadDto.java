// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YamlPayloadDto {

    private String someString;

    private long someLong;

    private ExampleEnum someEnum;

    @RequiredArgsConstructor
    public enum ExampleEnum {
        FOO1(1),
        FOO2(2),
        FOO3(3);

        private final int code;
    }
}
