package io.github.stavshamir.swagger4kafka.asyncapi;

import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.Channel;
import io.github.stavshamir.swagger4kafka.configuration.AsyncApiDocket;
import io.github.stavshamir.swagger4kafka.configuration.protocol.AsyncApiProtocolConfiguration;
import io.github.stavshamir.swagger4kafka.configuration.protocol.KafkaProtocolConfiguration;
import io.github.stavshamir.swagger4kafka.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.swagger4kafka.asyncapi.scanners.channels.ScannerBeanNames;
import io.github.stavshamir.swagger4kafka.asyncapi.scanners.components.ComponentsScanner;
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

    private final AsyncApiDocket docket;
    private final ComponentsScanner componentsScanner;
    private final Map<String, ChannelsScanner> channelsScanners;

    private Map<String, Channel> channels;

    @PostConstruct
    void findChannels() {
        Set<? extends AsyncApiProtocolConfiguration> protocols = docket.getProtocols();

        if (protocols.size() == 0) {
            log.warn("No asyncapi protocol configured - no channels will be found. Please define and set a protocol in the Docket");
        }

        channels = protocols.stream()
                .map(this::findChannelsForProtocol)
                .map(Map::entrySet).flatMap(Collection::stream)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Channel> getChannels() {
        return channels;
    }

    private Map<String, Channel> findChannelsForProtocol(AsyncApiProtocolConfiguration protocol) {
        if (protocol instanceof KafkaProtocolConfiguration) {
            return findChannelsForProtocol(protocol.getBasePackage(), ScannerBeanNames.KAFKA_SCANNER);
        }

        return Collections.emptyMap();
    }

    private Map<String, Channel> findChannelsForProtocol(String basePackage, String beanName) {
        Set<Class<?>> componentClasses = componentsScanner.scanForComponents(basePackage);
        return channelsScanners.get(beanName).scanForChannels(componentClasses);
    }

}
