// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

public record FunctionalChannelBeanData(
        String beanName, Class<?> payloadType, BeanType beanType, String cloudStreamBinding) {

    public enum BeanType {
        CONSUMER,
        SUPPLIER
    }
}
