// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.scanners.channels;

record FunctionalChannelBeanData(String beanName, Class<?> payloadType, BeanType beanType, String cloudStreamBinding) {

    enum BeanType {
        CONSUMER,
        SUPPLIER
    }
}
