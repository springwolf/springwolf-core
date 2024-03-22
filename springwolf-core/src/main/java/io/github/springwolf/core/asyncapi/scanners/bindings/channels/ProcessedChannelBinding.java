// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.channels;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import lombok.Data;

@Data
public class ProcessedChannelBinding {
    private final String type;
    private final ChannelBinding binding;
}
