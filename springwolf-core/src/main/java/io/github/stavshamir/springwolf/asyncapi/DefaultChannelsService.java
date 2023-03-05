package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2.model.channel.ChannelItem;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelMerger;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultChannelsService implements ChannelsService {

    private final List<? extends ChannelsScanner> channelsScanners;
    private final Map<String, ChannelItem> channels = new HashMap<>();

    @PostConstruct
    void findChannels() {
        List<Map.Entry<String, ChannelItem>> foundChannelItems = new ArrayList<>();

        for (ChannelsScanner scanner : channelsScanners) {
            try {
                Map<String, ChannelItem> channels = scanner.scan();
                foundChannelItems.addAll(channels.entrySet());
            } catch (Exception e) {
                log.error("An error was encountered during channel scanning with {}: {}", scanner, e.getMessage());
            }
        }

        this.channels.putAll(ChannelMerger.merge(foundChannelItems));
    }

    @Override
    public Map<String, ChannelItem> getChannels() {
        return channels;
    }

}
