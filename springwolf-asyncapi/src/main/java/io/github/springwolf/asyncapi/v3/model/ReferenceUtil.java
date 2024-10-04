// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

public class ReferenceUtil {
    public static final String ID_POSTFIX = "_id";

    private static final String FORBIDDEN_ID_CHARACTER = "/";

    public static String toValidId(String name) {
        return name.replaceAll(FORBIDDEN_ID_CHARACTER, "_") + ID_POSTFIX;
    }
}
