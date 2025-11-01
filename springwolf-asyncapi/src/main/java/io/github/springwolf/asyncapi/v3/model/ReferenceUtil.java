// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

import java.util.Set;
import java.util.stream.Collectors;

public class ReferenceUtil {
    /**
     * Allowed characters in RFC 3986, section 3.5 fragments (used for AsyncAPI $ref values)
     * Excluding '~' and '/', since they have a special meaning JSON Pointer (RFC 6901)
     */
    private static final Set<Character> ALLOWED_CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._!$&'()+,;=:@?%"
                    .chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toSet());

    public static String toValidId(String name) {
        StringBuilder sb = new StringBuilder();

        for (char c : name.toCharArray()) {
            if (ALLOWED_CHARACTERS.contains(c)) {
                sb.append(c);
            } else {
                sb.append("_");
            }
        }

        return sb.toString();
    }

    public static String getLastSegment(String ref) {
        return ref.substring(ref.lastIndexOf('/') + 1);
    }
}
