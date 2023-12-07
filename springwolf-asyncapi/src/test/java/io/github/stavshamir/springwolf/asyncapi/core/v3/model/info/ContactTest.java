// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.info;

import io.github.stavshamir.springwolf.asyncapi.core.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.core.v3.jackson.DefaultAsyncApiSerializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class ContactTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeContact() throws IOException {
        Contact contact = Contact.builder()
                .name("API Support")
                .url("https://www.example.com/support")
                .email("support@example.com")
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/info/contact.json");
        assertThatJson(serializer.toJsonString(contact)).isEqualTo(example);
    }
}
