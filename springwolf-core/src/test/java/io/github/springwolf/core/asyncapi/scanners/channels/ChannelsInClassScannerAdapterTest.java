// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChannelsInClassScannerAdapterTest {

    private final ClassScanner classScanner = mock(ClassScanner.class);
    private final ChannelsInClassScanner channelsInClassScanner = mock(ChannelsInClassScanner.class);

    private final ChannelsInClassScannerAdapter channelsInClassScannerAdapter =
            new ChannelsInClassScannerAdapter(classScanner, channelsInClassScanner);

    @Test
    void noClassFoundTest() {
        // when
        List<ChannelObject> channels = channelsInClassScannerAdapter.scan();

        // then
        assertThat(channels).isEmpty();
    }

    @Test
    void processClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        ChannelObject channel1 = ChannelObject.builder().channelId("channel1").build();
        ChannelObject channel2 = ChannelObject.builder().channelId("channel2").build();
        when(channelsInClassScanner.scan(any())).thenReturn(List.of(channel1, channel2));

        // when
        List<ChannelObject> channels = channelsInClassScannerAdapter.scan();

        // then
        assertThat(channels).containsExactly(channel1, channel2);
    }

    @Test
    void sameChannelsAreReturnedUnmergedTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        ChannelObject channel1 = ChannelObject.builder().channelId("channel1").build();
        ChannelObject channel2 = ChannelObject.builder().channelId("channel1").build();
        when(channelsInClassScanner.scan(any())).thenReturn(List.of(channel1, channel2));

        // when
        List<ChannelObject> channels = channelsInClassScannerAdapter.scan();

        // then
        assertThat(channels).containsExactly(channel1, channel2);
    }

    @Test
    void processEmptyClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        when(channelsInClassScanner.scan(any())).thenReturn(List.of());

        // when
        List<ChannelObject> channels = channelsInClassScannerAdapter.scan();

        // then
        assertThat(channels).isEmpty();
    }
}
