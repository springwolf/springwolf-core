// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common;

import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;

public interface AsyncAnnotationProvider<A> {
    Class<A> getAnnotation();

    AsyncOperation getAsyncOperation(A annotation);

    OperationAction getOperationType();
}
