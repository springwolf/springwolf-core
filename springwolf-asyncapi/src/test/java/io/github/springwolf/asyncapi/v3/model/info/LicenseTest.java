// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.info;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class LicenseTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeLicense() throws IOException {
        License license = License.builder()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/info/license.json");
        assertThatJson(serializer.toJsonString(license)).isEqualTo(example);
    }
}
