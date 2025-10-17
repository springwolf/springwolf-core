// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.info;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class ContactTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeContact() throws Exception {
        Contact contact = Contact.builder()
                .name("API Support")
                .url("https://www.example.com/support")
                .email("support@example.com")
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/info/contact.json");
        assertThatJson(serializer.toJsonString(contact)).isEqualTo(example);
    }
}
