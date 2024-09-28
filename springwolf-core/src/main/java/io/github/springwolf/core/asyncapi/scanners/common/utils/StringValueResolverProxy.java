// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.utils;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * This proxy enables autowiring of the StringValueResolver
 */
public class StringValueResolverProxy implements StringValueResolver, EmbeddedValueResolverAware {
    private StringValueResolver delegate;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.delegate = stringValueResolver;
    }

    @Override
    public String resolveStringValue(String strVal) {
        return delegate.resolveStringValue(strVal);
    }
}
