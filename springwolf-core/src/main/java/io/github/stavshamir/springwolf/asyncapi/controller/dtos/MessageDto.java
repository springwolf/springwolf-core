// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.controller.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Builder
@Jacksonized
public class MessageDto {

    private final Map<String, String> bindings;

    private final Map<String, String> headers;

    @Builder.Default
    private final String payload = "";

    @Builder.Default
    private final String payloadType = String.class.getCanonicalName();
}
