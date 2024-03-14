// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import java.lang.annotation.Annotation;

public record FunctionalChannelBeanData(
        String beanName, Class<?> payloadType, BeanType beanType, String cloudStreamBinding, Annotation schemaSetting) {

    public enum BeanType {
        CONSUMER,
        SUPPLIER
    }
}
