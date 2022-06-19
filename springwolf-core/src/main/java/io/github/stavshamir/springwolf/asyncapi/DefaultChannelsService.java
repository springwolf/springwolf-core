package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2.model.channel.ChannelItem;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultChannelsService implements ChannelsService {

    private final Set<? extends ChannelsScanner> channelsScanners;
    private final Map<String, ChannelItem> channels = new HashMap<>();

    @PostConstruct
    void findChannels() {

        for (ChannelsScanner scanner : channelsScanners) {
            try {
                channels.putAll(scanner.scan());
            } catch (Exception e) {
                log.error("An error was encountered during channel scanning with {}: {}", scanner, e.getMessage());
            }
        }
    }

    @Override
    public Map<String, ChannelItem> getChannels() {
        return channels;
    }

}
