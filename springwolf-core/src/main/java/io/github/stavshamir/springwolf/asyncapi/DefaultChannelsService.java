package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelMerger;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service to detect AsyncAPI channels in the current spring context.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultChannelsService implements ChannelsService {

    private final List<? extends ChannelsScanner> channelsScanners;

    /**
     * Collects all AsyncAPI ChannelItems using the available {@link ChannelsScanner}
     * beans.
     * @return Map of channel names mapping to detected ChannelItems
     */
    @Override
    public Map<String, ChannelItem> findChannels() {
        List<Map.Entry<String, ChannelItem>> foundChannelItems = new ArrayList<>();

        for (ChannelsScanner scanner : channelsScanners) {
            try {
                Map<String, ChannelItem> channels = scanner.scan();
                foundChannelItems.addAll(channels.entrySet());
            } catch (Exception e) {
                log.error("An error was encountered during channel scanning with {}: {}", scanner, e.getMessage());
            }
        }
        return ChannelMerger.merge(foundChannelItems);
    }

}
