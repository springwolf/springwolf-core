package io.github.stavshamir.springwolf.fixtures;

import com.asyncapi.v2._6_0.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;

public class AsyncApiDocketFixture {

    public static AsyncApiDocket createMinimal() {
        Info info = Info.builder().version("1.0").title("Test AsyncApi").build();

        return AsyncApiDocket.builder().info(info).build();
    }
}
