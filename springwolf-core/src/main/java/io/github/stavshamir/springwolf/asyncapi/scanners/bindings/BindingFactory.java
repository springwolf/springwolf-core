// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;

import java.util.Map;

public interface BindingFactory<T> {
    String getChannelName(T annotation);

    Map<String, ? extends ChannelBinding> buildChannelBinding(T annotation);

    Map<String, ? extends OperationBinding> buildOperationBinding(T annotation);

    Map<String, ? extends MessageBinding> buildMessageBinding(T annotation);
}
