// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

public class ReferenceUtil {
    private static final String FORBIDDEN_ID_CHARACTER = "[#*/]";

    // Keeps valid characters, valid percent-encoded sequences,and changes forbidden to "_"
    public static String toValidId(String name) {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < name.length()) {
            char c = name.charAt(i);
            if (FORBIDDEN_ID_CHARACTER.indexOf(c) >= 0) {
                sb.append("_");
                i++;
            } else if (c == '%'
                    && i + 2 < name.length()
                    && isHexDigit(name.charAt(i + 1))
                    && isHexDigit(name.charAt(i + 2))) {
                sb.append(c);
                i += 3;
            } else {
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }

    private static boolean isHexDigit(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    public static String getLastSegment(String ref) {
        return ref.substring(ref.lastIndexOf('/') + 1);
    }
}
