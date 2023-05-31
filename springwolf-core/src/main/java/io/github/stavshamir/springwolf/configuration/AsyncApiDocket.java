package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2._0_0.model.info.Info;
import com.asyncapi.v2._0_0.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.List;
import java.util.Map;

/**
 * Use to (manually) configure springwolf
 * <p>
 * This will not be the final AsyncApiDocket, use {@link AsyncApiDocketService#getAsyncApiDocket()} to get it.
 * This will not be the final api definition, use {@link io.github.stavshamir.springwolf.asyncapi.AsyncApiService} to get it.
 */
@Data
@Builder
public class AsyncApiDocket {

    /**
     * The base package containing the declarations of consumers and producer beans.
     */
    private final String basePackage;

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

    @Singular
    private final List<ConsumerData> consumers;

    /**
     * A string representing the default content type to use when encoding/decoding a message's payload.
     *
     * @see <a href="https://www.asyncapi.com/docs/reference/specification/v2.0.0#defaultContentTypeString">Default Content Type</a>
     */
    public final String defaultContentType;

    /**
     * A string representing the default content type to use when encoding/decoding a message's payload.
     *
     * @see <a href="https://www.asyncapi.com/docs/reference/specification/v2.0.0#A2SIdString">Identifier</a>
     */
    public final String id;
}
