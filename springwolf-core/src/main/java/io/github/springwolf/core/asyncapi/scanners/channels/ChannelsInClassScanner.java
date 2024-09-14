// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;

import java.util.List;

public interface ChannelsInClassScanner {
    List<ChannelObject> scan(Class<?> clazz);
}
