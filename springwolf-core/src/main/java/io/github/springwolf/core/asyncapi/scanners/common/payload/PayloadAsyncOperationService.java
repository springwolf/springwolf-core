// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Optional;

@RequiredArgsConstructor
public class PayloadAsyncOperationService {
    private final PayloadExtractor payloadExtractor;
    private final PayloadService payloadService;

    public PayloadSchemaObject extractSchema(AsyncOperation operationData, Method method) {
        Optional<Type> payloadType = operationData.payloadType() != Object.class
                ? Optional.of(operationData.payloadType())
                : payloadExtractor.extractFrom(method);

        String contentType = operationData.message().contentType();

        return payloadType
                .map((type) -> payloadService.buildSchema(contentType, type))
                .orElseGet(payloadService::useUnusedPayload);
    }
}
