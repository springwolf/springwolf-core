package io.github.stavshamir.swagger4kafka.configuration;

import io.github.stavshamir.swagger4kafka.asyncapi.types.info.Info;
import io.github.stavshamir.swagger4kafka.asyncapi.types.server.Server;
import io.github.stavshamir.swagger4kafka.configuration.protocol.Protocols;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;

@Data
@Builder
public class AsyncApiDocket {

    /**
     * <b>Required.</b>
     * Provide metadata about the API. The metadata can be used by the clients if needed.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#infoObject">Info specification</a>
     */
    @NonNull
    private final Info info;

    /**
     * Provides connection details of servers.
     */
    @Singular
    private final Map<String, Server> servers;

    @NonNull
    private final Protocols protocols;

}
