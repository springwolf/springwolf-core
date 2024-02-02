// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AsyncApiSerializerService {

    String toJsonString(Object asyncAPI) throws JsonProcessingException;

    String toYaml(Object asyncAPI) throws JsonProcessingException;
}
