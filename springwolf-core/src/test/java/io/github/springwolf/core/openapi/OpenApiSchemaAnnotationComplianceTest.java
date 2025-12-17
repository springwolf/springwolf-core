// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.openapi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class OpenApiSchemaAnnotationComplianceTest {

    private static final List<DisallowedField> DISALLOWED_FIELDS =
            List.of(disallow("exclusiveMinimum"), disallow("exclusiveMaximum"));

    @Test
    void doesNotUseOpenApi30ExclusiveBounds() throws IOException {
        Path repositoryRoot = locateRepositoryRoot();
        List<String> violations = new ArrayList<>();

        try (Stream<Path> files = Files.walk(repositoryRoot)) {
            files.filter(OpenApiSchemaAnnotationComplianceTest::isSourceJavaFile)
                    .forEach(path -> collectViolations(path, violations));
        }

        Assertions.assertThat(violations)
                .as("No @Schema annotations should use OpenAPI 3.0 exclusive bound attributes")
                .isEmpty();
    }

    private static void collectViolations(Path path, List<String> violations) {
        String content;
        try {
            content = Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read " + path, e);
        }

        for (DisallowedField field : DISALLOWED_FIELDS) {
            if (field.pattern.matcher(content).find()) {
                violations.add(path + " contains forbidden OpenAPI 3.0 field '" + field.attribute + "'");
                break;
            }
        }
    }

    private static boolean isSourceJavaFile(Path path) {
        String normalized = path.toString().replace('\\', '/');
        return normalized.contains("/src/") && normalized.endsWith(".java");
    }

    private static Path locateRepositoryRoot() {
        Path current = Paths.get("").toAbsolutePath();
        while (current != null && !Files.exists(current.resolve("settings.gradle"))) {
            current = current.getParent();
        }

        if (current == null) {
            throw new IllegalStateException("Could not locate the project root containing settings.gradle");
        }
        return current;
    }

    private static DisallowedField disallow(String attribute) {
        Pattern pattern = Pattern.compile("\\b" + attribute + "\\b\\s*=");
        return new DisallowedField(attribute, pattern);
    }

    private record DisallowedField(String attribute, Pattern pattern) {}
}
