// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.Optional;

@RequiredArgsConstructor
public class PayloadAsyncOperationService {
    private final PayloadClassExtractor payloadClassExtractor;
    private final PayloadService payloadService;

    public NamedSchemaObject extractSchema(AsyncOperation operationData, Method method) {
        Optional<Class<?>> payloadType = operationData.payloadType() != Object.class
                ? Optional.of(operationData.payloadType())
                : payloadClassExtractor.extractFrom(method);

        String contentType = operationData.message().contentType();

        return payloadType
                .map((type) -> payloadService.buildSchema(contentType, type))
                .orElseGet(payloadService::useUnusedPayload);
    }
}
