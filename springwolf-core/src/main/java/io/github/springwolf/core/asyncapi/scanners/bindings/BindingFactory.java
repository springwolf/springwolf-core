// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;

import java.lang.annotation.Annotation;
import java.util.Map;

public interface BindingFactory<T extends Annotation> {

    default String getChannelId(BindingContext<T> bindingContext) {
        return ReferenceUtil.toValidId(getChannelName(bindingContext));
    }

    String getChannelName(BindingContext<T> bindingContext);

    Map<String, ChannelBinding> buildChannelBinding(BindingContext<T> bindingContext);

    Map<String, OperationBinding> buildOperationBinding(BindingContext<T> bindingContext);

    Map<String, MessageBinding> buildMessageBinding(BindingContext<T> bindingContext, SchemaObject headerSchema);
}
