// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class StandaloneTest {

    @Test
    public void scanApplication() throws IOException {
        // given
        AsyncApiService asyncApiService =
                new StandaloneContext().create("io.github.springwolf.core.integrationtests.application.listener");

        // when
        AsyncAPI asyncApi = asyncApiService.getAsyncAPI();

        // then
        assertThat(asyncApi).isNotNull();

        AsyncApiSerializerService serializerService = new DefaultAsyncApiSerializerService();
        System.out.println(serializerService.toJsonString(asyncApi));
    }
}
