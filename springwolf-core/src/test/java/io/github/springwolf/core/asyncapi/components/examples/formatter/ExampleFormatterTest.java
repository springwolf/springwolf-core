// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.formatter;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleFormatterTest {

    @Test
    void formatsDateAsIsoDateString() {
        // given
        Date date = new Date(0); // 1970-01-01 UTC

        // when
        Object result = ExampleFormatter.processExampleObject(date);

        // then
        assertThat(result).isInstanceOf(String.class);
        assertThat((String) result).matches("1970-01-01");
    }

    @Test
    void formatsOffsetDateTimeAsIsoOffsetDateTimeString() {
        // given
        OffsetDateTime dateTime = OffsetDateTime.of(2024, 6, 15, 10, 30, 0, 0, ZoneOffset.UTC);

        // when
        Object result = ExampleFormatter.processExampleObject(dateTime);

        // then
        assertThat(result).isEqualTo("2024-06-15T10:30:00Z");
    }

    @Test
    void returnsOtherObjectsUnchanged() {
        // given
        Object input = "some-string";

        // when
        Object result = ExampleFormatter.processExampleObject(input);

        // then
        assertThat(result).isSameAs(input);
    }

    @Test
    void handlesNull() {
        // when
        Object result = ExampleFormatter.processExampleObject(null);

        // then
        assertThat(result).isNull();
    }
}
