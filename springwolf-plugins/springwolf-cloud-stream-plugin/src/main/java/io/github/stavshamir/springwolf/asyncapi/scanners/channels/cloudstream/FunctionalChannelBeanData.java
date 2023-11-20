// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

record FunctionalChannelBeanData(String beanName, Class<?> payloadType, BeanType beanType, String cloudStreamBinding) {

    enum BeanType {
        CONSUMER,
        SUPPLIER
    }
}
