// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class DescriptionUtil {
    private DescriptionUtil() {}

    /**
     * Returns a string where all leading indentation has been removed from each line.
     * It detects the smallest common indentation of all the lines in the input string,
     * and removes it.
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
        if (firstNonEmptyLine != -1) {
            lines = Arrays.copyOfRange(lines, firstNonEmptyLine, lines.length);
        }
        int minIndent = resolveMinIndent(lines);
        var result = Arrays.stream(lines)
                .map(line -> line.substring(Math.min(line.length(), minIndent)))
                .reduce((a, b) -> a + newLine + b)
                .orElse(StringUtils.EMPTY);

        if (endsWithNewline(text)) {
            result = result.concat(newLine);
        }

        return result;
    }

    private static int findFirstNonEmptyIndex(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].trim().isEmpty()) {
                return i; // Return index of first non-empty line
            }
        }
        return -1; // If all lines are empty, return -1
    }

    private static boolean endsWithNewline(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        return text.charAt(text.length() - 1) == '\n';
    }

    private static int resolveMinIndent(String[] lines) {
        return Arrays.stream(lines)
                .filter(line -> !line.trim().isEmpty())
                .mapToInt(DescriptionUtil::countLeadingSpaces)
                .min()
                .orElse(0);
    }

    private static int countLeadingSpaces(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                return i;
            }
        }
        return 0;
    }
}
