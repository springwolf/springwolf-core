// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.cloudstream.configuration;

import io.github.springwolf.examples.cloudstream.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class ConsumerClass implements Consumer<ExamplePayloadDto> {

    @Override
    public void accept(ExamplePayloadDto payload) {
        log.debug("Called with payload: {}", payload);
    }
}
