// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.grouping;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AsyncApiGroupNotFoundException extends RuntimeException {
    public AsyncApiGroupNotFoundException(String groupName) {
        super("AsyncAPI group %s was not found".formatted(groupName));
    }
}
