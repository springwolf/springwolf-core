// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.extractors;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadTypeExtractor;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class ListExtractor implements PayloadTypeExtractor {
    @Override
    public ParameterizedType extractType(ParameterizedType type) {
        // Resolve generic type for batch listeners
        return (ParameterizedType) type.getActualTypeArguments()[0];
    }

    @Override
    public boolean canExtract(ParameterizedType type) {
        return type.getRawType() == List.class;
    }
}
