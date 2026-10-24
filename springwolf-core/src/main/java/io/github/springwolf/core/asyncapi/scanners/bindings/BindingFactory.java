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
    /**
     * @deprecated use {@link #getChannelId(BindingContext)} instead
     */
    @Deprecated
    default String getChannelId(T annotation) {
        return ReferenceUtil.toValidId(getChannelName(annotation));
    }

    /**
     * Same as {@link #getChannelId(Annotation)}, with access to the context in which the annotation was found.
     * <p>
     * Maintainer note: delegates to {@link #getChannelId(Annotation)} to stay compatible with existing implementations.
     */
    default String getChannelId(BindingContext<T> bindingContext) {
        return getChannelId(bindingContext.annotation());
    }

    /**
     * @deprecated use {@link #getChannelName(BindingContext)} instead
     */
    @Deprecated
    String getChannelName(T annotation);

    /**
     * Same as {@link #getChannelName(Annotation)}, with access to the context in which the annotation was found.
     * <p>
     * Maintainer note: delegates to {@link #getChannelName(Annotation)} to stay compatible with existing implementations.
     */
    default String getChannelName(BindingContext<T> bindingContext) {
        return getChannelName(bindingContext.annotation());
    }

    Map<String, ChannelBinding> buildChannelBinding(T annotation);

    /**
     * @deprecated use {@link #buildOperationBinding(BindingContext)} instead
     */
    @Deprecated
    Map<String, OperationBinding> buildOperationBinding(T annotation);

    /**
     * Same as {@link #buildOperationBinding(Annotation)}, with access to the context in which the annotation was found.
     * <p>
     * Maintainer note: delegates to {@link #buildOperationBinding(Annotation)} to stay compatible with existing implementations.
     */
    default Map<String, OperationBinding> buildOperationBinding(BindingContext<T> bindingContext) {
        return buildOperationBinding(bindingContext.annotation());
    }

    Map<String, MessageBinding> buildMessageBinding(T annotation, SchemaObject headerSchema);
}
