// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TextUtilsTest {

    @Test
    void textWithoutIndentShouldBeUntouched() {
        var description = """
                This is a string
                with two lines
                """;

        var result = TextUtils.trimIndent(description);

        assertThat(result).isEqualTo("This is a string\nwith two lines\n");
    }

    @Test
    void textWithSingleIndentShouldBeUntouched() {
        var description = """
                    This is a string
                with two lines
                """;

        var result = TextUtils.trimIndent(description);

        assertThat(result).isEqualTo("    This is a string\nwith two lines\n");
    }

    @Test
    void removeIndent() {
        var description = """
                    This is a string
                    with two lines
                """;

        var result = TextUtils.trimIndent(description);

        assertThat(result).isEqualTo("This is a string\nwith two lines\n");
    }

    @Test
    void simpleTextWithoutIndentShouldBeUntouched() {
        var description = "This is a string\nwith two lines";
        var result = TextUtils.trimIndent(description);

        assertThat(result).isEqualTo("This is a string\nwith two lines");
    }

    @Test
    void removeEmptyLinesUntilTextIsFound() {
        var description = """

                    This is a string

                    with two lines
                """;
        var result = TextUtils.trimIndent(description);

        assertThat(result).isEqualTo("This is a string\n\nwith two lines\n");
    }

    @Test
    void onlyEmptyLinesShouldBeUntouched() {
        var description = "\n\n\n";
        var result = TextUtils.trimIndent(description);

        assertThat(result).isEqualTo("\n\n\n");
    }

    @Test
    void nullTextShouldNotFail() {
        String nullDescription = null;
        var result = TextUtils.trimIndent(nullDescription);
        assertThat(result).isNull();
    }
}
