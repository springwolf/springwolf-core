// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(
        description =
                """
                        Example payload model demonstrating markdown text styling:
                        **bold**, *cursive* and <u>underlined</u>
                        """)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamplePayloadDto {
    @Schema(
            description =
                    """
                            ###  Some string field with Markdown

                            - **bold**
                            - *cursive*
                            - images: <img src="./assets/springwolf-logo.png" alt="Springwolf" height="50"/>
                            - and code blocks (json, http, java)
                              ```json
                              {
                                "key1":"value1",
                                "key2":"value2"
                              }
                              ```
                            """,
            example = "some string value",
            requiredMode = REQUIRED)
    private String someString;

    @Schema(description = "Some long field", example = "5")
    private long someLong;

    @Schema(description = "Some enum field", example = "FOO2", requiredMode = REQUIRED)
    private ExampleEnum someEnum;

    public enum ExampleEnum {
        FOO1,
        FOO2,
        FOO3
    }
}
