// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
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
    public void noClassFoundTest() {
        // when
        Map<String, ChannelItem> channels = simpleChannelsScanner.scan();

        // then
        assertThat(channels).isEmpty();
    }

    @Test
    public void processClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Map.Entry<String, ChannelItem> channel1 = Map.entry(
                "channel1",
                ChannelItem.builder().publish(Operation.builder().build()).build());
        Map.Entry<String, ChannelItem> channel2 = Map.entry(
                "channel2",
                ChannelItem.builder().subscribe(Operation.builder().build()).build());
        when(classProcessor.process(any())).thenReturn(Stream.of(channel1, channel2));

        // when
        Map<String, ChannelItem> channels = simpleChannelsScanner.scan();

        // then
        assertThat(channels).containsExactly(channel1, channel2);
    }

    @Test
    public void sameChannelsAreMergedTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Map.Entry<String, ChannelItem> channel1 = Map.entry(
                "channel1",
                ChannelItem.builder()
                        .publish(Operation.builder().operationId("pub").build())
                        .build());
        Map.Entry<String, ChannelItem> channel2 = Map.entry(
                "channel1",
                ChannelItem.builder()
                        .subscribe(Operation.builder().operationId("sub").build())
                        .build());
        when(classProcessor.process(any())).thenReturn(Stream.of(channel1, channel2));

        // when
        Map<String, ChannelItem> channels = simpleChannelsScanner.scan();

        // then
        assertThat(channels)
                .containsExactly(Map.entry(
                        "channel1",
                        ChannelItem.builder()
                                .publish(Operation.builder().operationId("pub").build())
                                .subscribe(
                                        Operation.builder().operationId("sub").build())
                                .build()));
    }

    @Test
    public void processEmptyClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        when(classProcessor.process(any())).thenReturn(Stream.of());

        // when
        Map<String, ChannelItem> channels = simpleChannelsScanner.scan();

        // then
        assertThat(channels).isEmpty();
    }
}
