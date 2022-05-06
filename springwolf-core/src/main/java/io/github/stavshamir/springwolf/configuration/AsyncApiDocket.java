package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class AsyncApiDocket {

    /**
     * The base package to scan for listeners.
     */
    private String basePackage;

    private String configurationBasePackage;

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

    /**
     * Provides information about the producers.
     */
    @Singular
    private final List<ProducerData> producers;

}
