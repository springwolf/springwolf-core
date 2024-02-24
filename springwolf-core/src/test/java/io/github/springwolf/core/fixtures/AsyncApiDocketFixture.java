// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.fixtures;

import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.core.configuration.AsyncApiDocket;

public class AsyncApiDocketFixture {

    public static AsyncApiDocket createMinimal() {
        Info info = Info.builder().version("1.0").title("Test AsyncApi").build();

        return AsyncApiDocket.builder().info(info).build();
    }
}
