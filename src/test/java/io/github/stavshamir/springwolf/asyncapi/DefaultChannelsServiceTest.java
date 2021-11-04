package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        DefaultChannelsService.class,
        DefaultChannelsServiceTest.FooChannelScanner.class,
        DefaultChannelsServiceTest.BarChannelScanner.class
})
public class DefaultChannelsServiceTest {

    @Autowired
    private DefaultChannelsService defaultChannelsService;

    @Autowired
    private FooChannelScanner fooChannelScanner;

    @Autowired
    private BarChannelScanner barChannelScanner;

    @Test
    public void getChannels() {
        Map<String, ChannelItem> actualChannels = defaultChannelsService.getChannels();

        assertThat(actualChannels)
                .containsAllEntriesOf(fooChannelScanner.scan())
                .containsAllEntriesOf(barChannelScanner.scan());
    }

    @Component
    static class FooChannelScanner implements ChannelsScanner {
        @Override
        public Map<String, ChannelItem> scan() {
            return ImmutableMap.of("foo", new ChannelItem());
        }
    }

    @Component
    static class BarChannelScanner implements ChannelsScanner {
        @Override
        public Map<String, ChannelItem> scan() {
            return ImmutableMap.of("bar", new ChannelItem());
        }
    }

}