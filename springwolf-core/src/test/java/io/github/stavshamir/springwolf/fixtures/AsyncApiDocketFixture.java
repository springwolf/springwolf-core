// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.fixtures;

import io.github.stavshamir.springwolf.asyncapi.v3.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;

public class AsyncApiDocketFixture {

    public static AsyncApiDocket createMinimal() {
        Info info = Info.builder().version("1.0").title("Test AsyncApi").build();

        return AsyncApiDocket.builder().info(info).build();
    }
}
