// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import lombok.Data;

@Data
public class ProcessedOperationBinding {
    private final String type;
    private final OperationBinding binding;
}
