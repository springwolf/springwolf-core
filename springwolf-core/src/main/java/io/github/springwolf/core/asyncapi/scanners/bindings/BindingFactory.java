// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;

import java.util.Map;

public interface BindingFactory<T> {
    default String getChannelId(T annotation) {
        return ReferenceUtil.toValidId(getChannelName(annotation));
    }

    String getChannelName(T annotation);

    Map<String, ChannelBinding> buildChannelBinding(T annotation);

    Map<String, OperationBinding> buildOperationBinding(T annotation);

    Map<String, MessageBinding> buildMessageBinding(T annotation, SchemaObject headerSchema);
}
