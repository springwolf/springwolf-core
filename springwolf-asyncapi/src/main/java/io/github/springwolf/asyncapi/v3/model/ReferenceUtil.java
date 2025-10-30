// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

public class ReferenceUtil {
    private static final String FORBIDDEN_ID_CHARACTERS = "[/#*]";

    public static String toValidId(String name) {
        return name.replaceAll(FORBIDDEN_ID_CHARACTERS, "_");
    }

    public static String getLastSegment(String ref) {
        return ref.substring(ref.lastIndexOf('/') + 1);
    }
}
