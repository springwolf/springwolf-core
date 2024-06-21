// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

public class ReferenceUtil {
    private static final String FORBIDDEN_ID_CHARACTER = "/";

    public static String toValidId(String name) {
        return name.replaceAll(FORBIDDEN_ID_CHARACTER, "_");
    }
}
