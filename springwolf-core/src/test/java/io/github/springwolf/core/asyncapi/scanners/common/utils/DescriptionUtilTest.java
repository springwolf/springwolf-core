// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DescriptionUtilTest {

    @Test
    void textWithoutIndentShouldBeUntouched() {
        var description = """
                This is a string
                with two lines
                """;

        var result = DescriptionUtil.trimIndent(description);

        assertEquals("This is a string\nwith two lines\n", result);
    }

    @Test
    void textWithSingleIndentShouldBeUntouched() {
        var description = """
                    This is a string
                with two lines
                """;

        var result = DescriptionUtil.trimIndent(description);

        assertEquals("    This is a string\nwith two lines\n", result);
    }

    @Test
    void removeIndent() {
        var description =
                """
                    This is a string
                    with two lines
                """;

        var result = DescriptionUtil.trimIndent(description);

        assertEquals("This is a string\nwith two lines\n", result);
    }

    @Test
    void removeEmptyLinesUntilTextIsFound() {
        var description =
                """

                    This is a string

                    with two lines
                """;
        var result = DescriptionUtil.trimIndent(description);

        assertEquals("This is a string\n\nwith two lines\n", result);
    }
}
