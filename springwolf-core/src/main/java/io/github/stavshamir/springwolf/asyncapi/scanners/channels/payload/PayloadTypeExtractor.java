// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload;

import java.lang.reflect.ParameterizedType;

// TODO use Type instead of ParameterizedType
public interface PayloadTypeExtractor {
    ParameterizedType extractType(ParameterizedType type) throws ClassNotFoundException;

    boolean canExtract(ParameterizedType type);
}
