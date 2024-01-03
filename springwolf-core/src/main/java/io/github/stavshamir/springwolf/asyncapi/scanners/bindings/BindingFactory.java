// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;

import java.util.Map;

public interface BindingFactory<T> {
    String getChannelName(T annotation);

    Map<String, ChannelBinding> buildChannelBinding(T annotation);

    Map<String, OperationBinding> buildOperationBinding(T annotation);

    Map<String, MessageBinding> buildMessageBinding(T annotation);
}
