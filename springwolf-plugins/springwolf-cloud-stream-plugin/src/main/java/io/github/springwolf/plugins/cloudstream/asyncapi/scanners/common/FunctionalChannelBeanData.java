// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import java.lang.reflect.Method;

public record FunctionalChannelBeanData(
        Method method, Class<?> payloadType, BeanType beanType, String cloudStreamBinding) {

    public enum BeanType {
        CONSUMER,
        SUPPLIER
    }
}
