// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.annotation;

import java.lang.reflect.Method;

public record MethodAndAnnotation<A>(Method method, A annotation) {}
