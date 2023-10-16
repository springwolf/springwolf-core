// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import com.asyncapi.v2.binding.message.MessageBinding;
import lombok.Data;

@Data
public class ProcessedMessageBinding {
    private final String type;
    private final MessageBinding binding;
}
