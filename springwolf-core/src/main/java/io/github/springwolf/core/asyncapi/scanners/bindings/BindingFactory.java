// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.common.BindingContext;

import java.util.Map;

public interface BindingFactory<T> {

    // maintainer note: replaced by #getChannelId(T, BindingContext)
    default String getChannelId(T annotation) {
        return ReferenceUtil.toValidId(getChannelName(annotation));
    }

    // maintainer note: replaced by #getChannelName(T, BindingContext)
    String getChannelName(T annotation);

    default String getChannelId(T annotation, BindingContext bindingContext) {
        return getChannelId(annotation);
    }

    default String getChannelName(T annotation, BindingContext bindingContext) {
        return getChannelName(annotation);
    }

    Map<String, ChannelBinding> buildChannelBinding(T annotation);

    Map<String, OperationBinding> buildOperationBinding(T annotation);

    Map<String, MessageBinding> buildMessageBinding(T annotation, SchemaObject headerSchema);
}
