// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimpleChannelsScannerTest {

    private final ClassScanner classScanner = mock(ClassScanner.class);
    private final SimpleChannelsScanner.ClassProcessor classProcessor =
            mock(SimpleChannelsScanner.ClassProcessor.class);

    private final SimpleChannelsScanner simpleChannelsScanner = new SimpleChannelsScanner(classScanner, classProcessor);

    @Test
    void noClassFoundTest() {
        // when
        Map<String, ChannelObject> channels = simpleChannelsScanner.scanChannels();
        Map<String, Operation> operations = simpleChannelsScanner.scanOperations();

        // then
        assertThat(channels).isEmpty();
        assertThat(operations).isEmpty();
    }

    @Test
    void processClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Map.Entry<String, ChannelObject> channel1 =
                Map.entry("channel1", ChannelObject.builder().build());
        Map.Entry<String, ChannelObject> channel2 =
                Map.entry("channel2", ChannelObject.builder().build());
        when(classProcessor.processChannels(any())).thenReturn(Stream.of(channel1, channel2));

        // when
        Map<String, ChannelObject> channels = simpleChannelsScanner.scanChannels();

        // then
        assertThat(channels).containsExactly(channel1, channel2);
    }

    @Test
    void sameChannelsAreMergedTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Map.Entry<String, ChannelObject> channel1 =
                Map.entry("channel1", ChannelObject.builder().build());
        Map.Entry<String, ChannelObject> channel2 =
                Map.entry("channel1", ChannelObject.builder().build());
        when(classProcessor.processChannels(any())).thenReturn(Stream.of(channel1, channel2));

        // when
        Map<String, ChannelObject> channels = simpleChannelsScanner.scanChannels();

        // then
        assertThat(channels)
                .containsExactly(Map.entry("channel1", ChannelObject.builder().build()));
    }

    @Test
    void processEmptyClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        when(classProcessor.processChannels(any())).thenReturn(Stream.of());

        // when
        Map<String, ChannelObject> channels = simpleChannelsScanner.scanChannels();

        // then
        assertThat(channels).isEmpty();
    }
}
