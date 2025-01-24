// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import java.io.IOException;

public interface StandalonePluginContext {
    StandalonePluginResult load(StandaloneContext context) throws IOException;
}
