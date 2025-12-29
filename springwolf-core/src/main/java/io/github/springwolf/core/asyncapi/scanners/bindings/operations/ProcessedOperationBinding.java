// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.operations;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;

public record ProcessedOperationBinding(String type, OperationBinding binding) {}
