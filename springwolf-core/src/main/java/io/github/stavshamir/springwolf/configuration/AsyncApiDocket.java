// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.server.Server;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.springframework.http.MediaType;

import java.util.Map;

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
     * A string representing the default content type to use when encoding/decoding a message's payload.
     *
     * @see <a href="https://www.asyncapi.com/docs/reference/specification/v2.0.0#defaultContentTypeString">Default Content Type</a>
     */
    @Builder.Default
    private final String defaultContentType = MediaType.APPLICATION_JSON_VALUE;

    /**
     * A string representing the default content type to use when encoding/decoding a message's payload.
     *
     * @see <a href="https://www.asyncapi.com/docs/reference/specification/v2.0.0#A2SIdString">Identifier</a>
     */
    private final String id;
}
