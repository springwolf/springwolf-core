// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.utils;

import java.util.Arrays;

/**
 * Auxiliary class to manage Description or long texts.
 */
public class TextUtils {
    private TextUtils() {}

    /**
     * This method removes the smallest common indentation from all the lines in the input string,
     * and removes it.
     * Any leading empty line will be also removed.
     *
     * @param text The original string with possible leading indentation.
     * @return The string with leading indentation removed from each line.
     */
    public static String trimIndent(String text) {
        if (text == null) {
            return null;
        }
        final String newLine = System.lineSeparator();
        String[] lines = text.split(newLine);
        int firstNonEmptyLine = findFirstNonEmptyIndex(lines);
        if (firstNonEmptyLine == -1) {
            return text;
        }
        lines = Arrays.copyOfRange(lines, firstNonEmptyLine, lines.length);
        int minIndent = resolveMinIndent(lines);
        var result = Arrays.stream(lines)
                .map(line -> line.substring(Math.min(line.length(), minIndent)))
                .reduce((a, b) -> a + newLine + b)
                .orElse("");

        if (text.endsWith("\n")) {
            result = result.concat(newLine);
        }

        return result;
    }

    /**
     * @return The index of the first non-empty line, or {@code -1} if all lines are empty
     */
    private static int findFirstNonEmptyIndex(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].trim().isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    private static int resolveMinIndent(String[] lines) {
        return Arrays.stream(lines)
                .filter(line -> !line.trim().isEmpty())
                .mapToInt(TextUtils::indexOfNonSpaceCharacter)
                .filter(i -> i >= 0)
                .min()
                .orElse(0);
    }

    private static int indexOfNonSpaceCharacter(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                return i;
            }
        }
        return -1;
    }
}
