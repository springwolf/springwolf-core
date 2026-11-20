// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

/**
 * Something gone wrong during the creation of the AsyncAPI document
 */
public class AsyncApiCreationException extends RuntimeException {
    public AsyncApiCreationException(Throwable throwable) {
        super("Error occurred during creation of AsyncAPI", throwable);
    }
}
