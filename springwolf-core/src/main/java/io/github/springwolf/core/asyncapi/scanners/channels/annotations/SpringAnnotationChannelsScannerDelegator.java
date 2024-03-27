// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;

import java.util.Map;
import java.util.stream.Stream;

public interface SpringAnnotationChannelsScannerDelegator {
    Stream<Map.Entry<String, ChannelObject>> scan(Class<?> clazz);
}
