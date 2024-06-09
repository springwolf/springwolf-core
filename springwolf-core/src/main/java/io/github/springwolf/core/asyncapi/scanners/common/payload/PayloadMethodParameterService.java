// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.Optional;

@RequiredArgsConstructor
public class PayloadMethodParameterService implements PayloadMethodService {
    private final PayloadClassExtractor payloadClassExtractor;
    private final PayloadService payloadService;

    public NamedSchemaObject extractSchema(Method method) {
        Optional<Class<?>> payloadType = payloadClassExtractor.extractFrom(method);

        return payloadType.map(payloadService::buildSchema).orElseGet(payloadService::useUnusedPayload);
    }
}
