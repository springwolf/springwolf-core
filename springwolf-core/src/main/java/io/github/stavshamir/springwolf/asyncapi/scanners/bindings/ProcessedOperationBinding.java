// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import com.asyncapi.v2.binding.operation.OperationBinding;
import lombok.Data;

@Data
public class ProcessedOperationBinding {
    private final String type;
    private final OperationBinding binding;
}
