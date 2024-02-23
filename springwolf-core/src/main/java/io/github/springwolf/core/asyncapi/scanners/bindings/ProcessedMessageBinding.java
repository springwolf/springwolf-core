// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings;

import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import lombok.Data;

@Data
public class ProcessedMessageBinding {
    private final String type;
    private final MessageBinding binding;
}
