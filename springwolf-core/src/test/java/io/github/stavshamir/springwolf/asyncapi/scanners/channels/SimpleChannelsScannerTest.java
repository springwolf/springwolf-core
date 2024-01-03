// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
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
        Map<String, ChannelObject> channels = simpleChannelsScanner.scan();

        // then
        assertThat(channels).isEmpty();
    }

    @Test
    void processClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Map.Entry<String, ChannelObject> channel1 = Map.entry(
                "channel1",
                ChannelObject.builder() /*.publish(Operation.builder().build())FIXME*/
                        .build());
        Map.Entry<String, ChannelObject> channel2 = Map.entry(
                "channel2",
                ChannelObject.builder() /*.subscribe(Operation.builder().build())FIXME*/
                        .build());
        when(classProcessor.process(any())).thenReturn(Stream.of(channel1, channel2));

        // when
        Map<String, ChannelObject> channels = simpleChannelsScanner.scan();

        // then
        assertThat(channels).containsExactly(channel1, channel2);
    }

    @Test
    void sameChannelsAreMergedTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Map.Entry<String, ChannelObject> channel1 = Map.entry(
                "channel1",
                ChannelObject.builder()
                        //                        .publish(Operation.builder().operationId("pub").build()) FIXME
                        .build());
        Map.Entry<String, ChannelObject> channel2 = Map.entry(
                "channel1",
                ChannelObject.builder()
                        //                        .subscribe(Operation.builder().operationId("sub").build()) FIXME
                        .build());
        when(classProcessor.process(any())).thenReturn(Stream.of(channel1, channel2));

        // when
        Map<String, ChannelObject> channels = simpleChannelsScanner.scan();

        // then
        assertThat(channels)
                .containsExactly(Map.entry(
                        "channel1",
                        ChannelObject.builder()
                                //
                                // .publish(Operation.builder().operationId("pub").build())  FIXME
                                //
                                // .subscribe(Operation.builder().operationId("sub").build()) FIXME
                                .build()));
    }

    @Test
    void processEmptyClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        when(classProcessor.process(any())).thenReturn(Stream.of());

        // when
        Map<String, ChannelObject> channels = simpleChannelsScanner.scan();

        // then
        assertThat(channels).isEmpty();
    }
}
