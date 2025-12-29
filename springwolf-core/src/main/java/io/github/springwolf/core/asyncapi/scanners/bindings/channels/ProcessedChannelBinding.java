// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.channels;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;

public record ProcessedChannelBinding(String type, ChannelBinding binding) {}
