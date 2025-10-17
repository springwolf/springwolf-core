// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.info;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.springwolf.asyncapi.v3.model.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class InfoTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeInfo() throws Exception {
        Info info = Info.builder()
                .title("AsyncAPI Sample App")
                .version("1.0.1")
                .description("This is a sample app.")
                .termsOfService("https://asyncapi.org/terms/")
                .contact(Contact.builder()
                        .name("API Support")
                        .url("https://www.asyncapi.org/support")
                        .email("support@asyncapi.org")
                        .build())
                .license(License.builder()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        .build())
                .externalDocs(ExternalDocumentation.builder()
                        .description("Find more info here")
                        .url("https://www.asyncapi.org")
                        .build())
                .tags(List.of(Tag.builder().name("e-commerce").build()))
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/info/info.json");
        assertThatJson(serializer.toJsonString(info)).isEqualTo(example);
    }
}
