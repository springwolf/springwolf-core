package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultChannelsService implements ChannelsService {

    private final Set<? extends ChannelsScanner> channelsScanners;
    private Map<String, Channel> channels;

    @PostConstruct
    void findChannels() {
        channels = channelsScanners.stream()
                .map(ChannelsScanner::scan)
                .map(Map::entrySet).flatMap(Collection::stream)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Channel> getChannels() {
        return channels;
    }

}
