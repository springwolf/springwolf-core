// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.fixtures;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ClasspathUtil {
    private ClasspathUtil() {}

    public static String readAsString(String resourceName) throws IOException {
        try (InputStream inputStream = ClasspathUtil.class.getResourceAsStream(resourceName)) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim();
        }
    }

    public static void writeAsActual(String resourceName, String content) throws IOException {
        String extension = resourceName.substring(resourceName.lastIndexOf('.'));
        String actualResource = resourceName.replace(extension, ".actual" + extension);

        try (OutputStream outputStream = Files.newOutputStream(Path.of("src", "test", "resources", actualResource))) {
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        }
    }
}
