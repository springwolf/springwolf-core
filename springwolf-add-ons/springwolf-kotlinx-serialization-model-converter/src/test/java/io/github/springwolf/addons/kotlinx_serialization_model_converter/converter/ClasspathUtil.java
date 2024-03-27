// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.kotlinx_serialization_model_converter.converter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class ClasspathUtil {
    private ClasspathUtil() {}

    public static String readAsString(String resourceName) throws IOException {
        try (InputStream inputStream = ClasspathUtil.class.getResourceAsStream(resourceName)) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
