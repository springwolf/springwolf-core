// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

public class ReferenceUtil {
    private static final String ALLOWED_ID_CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~!$&'()+,;=:@?%";

    public static String toValidId(String name) {
        StringBuilder sb = new StringBuilder();

        for (char c : name.toCharArray()) {
            if (ALLOWED_ID_CHARACTERS.indexOf(c) < 0) {
                sb.append("_");
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static String getLastSegment(String ref) {
        return ref.substring(ref.lastIndexOf('/') + 1);
    }
}
