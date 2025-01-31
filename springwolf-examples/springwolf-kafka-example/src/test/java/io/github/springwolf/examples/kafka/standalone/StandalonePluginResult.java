// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class StandalonePluginResult {
    @Singular
    private final List<ChannelsScanner> channelsScanners;

    @Singular
    private final List<OperationsScanner> operationsScanners;

    @Singular
    private final List<OperationBindingProcessor> operationBindingProcessors;

    @Singular
    private final List<MessageBindingProcessor> messageBindingProcessors;
}
