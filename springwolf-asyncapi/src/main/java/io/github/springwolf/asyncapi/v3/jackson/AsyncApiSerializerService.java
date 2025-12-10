// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.jackson;

import com.fasterxml.jackson.core.JacksonException;

public interface AsyncApiSerializerService {

    String toJsonString(Object asyncAPI) throws JacksonException;

    String toYaml(Object asyncAPI) throws JacksonException;
}
