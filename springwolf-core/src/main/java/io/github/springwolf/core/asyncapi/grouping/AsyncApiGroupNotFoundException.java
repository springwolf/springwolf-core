// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.grouping;

public class AsyncApiGroupNotFoundException extends RuntimeException {
    public AsyncApiGroupNotFoundException(String groupName) {
        super("AsyncAPI group %s was not found".formatted(groupName));
    }
}
