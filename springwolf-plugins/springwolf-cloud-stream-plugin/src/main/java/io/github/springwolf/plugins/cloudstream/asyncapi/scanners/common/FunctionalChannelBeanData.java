// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

/**
 * @param elementName        The simple name of the element (Method or Class).
 * @param annotatedElement   The element (Method or Class) from which this instance has been processed.
 * @param payloadType        The payload type of the Channel this bean is bound to.
 * @param beanType           Consumer or Supplier.
 * @param cloudStreamBinding The expected binding string of this bean.
 */
public record FunctionalChannelBeanData(
        String elementName,
        AnnotatedElement annotatedElement,
        Type payloadType,
        BeanType beanType,
        String cloudStreamBinding) {

    public enum BeanType {
        CONSUMER,
        SUPPLIER;

        public OperationAction toOperationAction() {
            return switch (this) {
                case CONSUMER -> OperationAction.RECEIVE;
                case SUPPLIER -> OperationAction.SEND;
            };
        }
    }
}
