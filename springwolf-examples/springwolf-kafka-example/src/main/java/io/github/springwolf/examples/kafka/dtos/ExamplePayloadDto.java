// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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

    @Schema(description = "Some long field", example = "5", minimum = "0")
    private long someLong;

    @Schema(description = "Some enum field", requiredMode = REQUIRED)
    private ExampleEnum someEnum;

    @Schema(enumAsRef = true)
    @Getter
    @RequiredArgsConstructor
    @ToString
    public enum ExampleEnum {
        OK(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        UNAUTHORIZED(401, "Unauthorized"),
        NOT_FOUND(404, "Not Found"),
        INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
        ;

        private final int value;
        private final String reasonPhrase;

        /**
         * @return true if messageStatus's value indicates an error (value >= 400)
         */
        public boolean isError() {
            return value >= 400;
        }
    }
}
