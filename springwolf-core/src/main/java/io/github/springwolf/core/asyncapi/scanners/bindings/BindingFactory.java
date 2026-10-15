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

    /**
     * Same as {@link #getChannelId(Object)}, with access to the context in which the annotation was found.
     * <p>
     * Maintainer note: delegates to {@link #getChannelId(Object)} to stay compatible with existing implementations.
     */
    default String getChannelId(T annotation, BindingContext bindingContext) {
        return getChannelId(annotation);
    }

    String getChannelName(T annotation);

    /**
     * Same as {@link #getChannelName(Object)}, with access to the context in which the annotation was found.
     * <p>
     * Maintainer note: delegates to {@link #getChannelName(Object)} to stay compatible with existing implementations.
     */
    default String getChannelName(T annotation, BindingContext bindingContext) {
        return getChannelName(annotation);
    }

    Map<String, ChannelBinding> buildChannelBinding(T annotation);

    Map<String, OperationBinding> buildOperationBinding(T annotation);

    /**
     * Same as {@link #buildOperationBinding(Object)}, with access to the context in which the annotation was found.
     * <p>
     * Maintainer note: delegates to {@link #buildOperationBinding(Object)} to stay compatible with existing implementations.
     */
    default Map<String, OperationBinding> buildOperationBinding(T annotation, BindingContext bindingContext) {
        return buildOperationBinding(annotation);
    }

    Map<String, MessageBinding> buildMessageBinding(T annotation, SchemaObject headerSchema);
}
