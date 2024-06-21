// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import java.lang.reflect.Method;

public interface PayloadMethodService {
    NamedSchemaObject extractSchema(Method method);
}
