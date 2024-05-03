// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Builder
@Jacksonized
public class MessageDto {
    public static final String EMPTY = "";

    private final Map<String, String> bindings;

    private final Map<String, String> headers;

    @Builder.Default
    private final String payload = EMPTY;

    @Builder.Default
    private final String payloadType = String.class.getCanonicalName();
}
