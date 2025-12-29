// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.messages;

import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;

public record ProcessedMessageBinding(String type, MessageBinding binding) {}
