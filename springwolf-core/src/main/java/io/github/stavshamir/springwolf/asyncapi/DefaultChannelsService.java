package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.Channel;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultChannelsService implements ChannelsService {

    private final AsyncApiDocket docket;
    private final ComponentsScanner componentsScanner;
    private final ChannelsScanner channelsScanner;

    private Map<String, Channel> channels;

    @PostConstruct
    void findChannels() {
        String basePackage = docket.getProtocolConfiguration().getBasePackage();
        Set<Class<?>> componentClasses = componentsScanner.scanForComponents(basePackage);
        channels = channelsScanner.scanForChannels(componentClasses);
    }

    @Override
    public Map<String, Channel> getChannels() {
        return channels;
    }

}
