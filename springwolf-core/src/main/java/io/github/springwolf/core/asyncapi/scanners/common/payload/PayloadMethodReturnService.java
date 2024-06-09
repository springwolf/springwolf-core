// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class PayloadMethodReturnService implements PayloadMethodService {
    private final PayloadService payloadService;

    @Override
    public NamedSchemaObject extractSchema(Method method) {
        return payloadService.buildSchema(method.getReturnType());
    }
}
