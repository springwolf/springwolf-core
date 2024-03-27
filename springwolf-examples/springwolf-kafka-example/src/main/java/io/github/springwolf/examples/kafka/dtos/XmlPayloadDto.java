// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XmlPayloadDto {

    private String someString;

    private long someLong;

    private ExampleEnum someEnum;

    public enum ExampleEnum {
        FOO1,
        FOO2,
        FOO3
    }
}
