// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class ConsumerOperationDataScanner implements ChannelsScanner {

    private final AsyncApiDocketService asyncApiDocketService;
    private final OperationDataScannerUtils operationDataScannerUtils;

    @Override
    public Map<String, ChannelItem> scan() {
        List<OperationData> consumerData =
                new ArrayList<>(asyncApiDocketService.getAsyncApiDocket().getConsumers());
        return operationDataScannerUtils.buildChannelsFromOperationData(
                consumerData, OperationData.OperationType.PUBLISH);
    }
}
