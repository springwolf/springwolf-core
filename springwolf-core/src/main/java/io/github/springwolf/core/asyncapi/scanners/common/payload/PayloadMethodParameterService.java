// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Optional;

@RequiredArgsConstructor
public class PayloadMethodParameterService implements PayloadMethodService {
    private final PayloadExtractor payloadExtractor;
    private final PayloadService payloadService;

    public PayloadSchemaObject extractSchema(Method method) {
        Optional<Type> payloadType = payloadExtractor.extractFrom(method);

        return payloadType.map(payloadService::buildSchema).orElseGet(payloadService::useUnusedPayload);
    }
}
