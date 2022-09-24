package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.model.Tag;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * This is the root document object for the API specification. It combines resource listing and API declaration together into one document.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-a2sobject-a-asyncapi-object">AsyncAPI specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsyncAPI {
    /**
     * <b>Required.</b>
     * Specifies the AsyncAPI Specification version being used.
     * It can be used by tooling Specifications and clients to interpret the version.
     * The structure shall be major.minor.patch, where patch versions must be compatible
     * with the existing major.minor tooling.
     * Typically patch versions will be introduced to address errors in the documentation,
     * and tooling should typically be compatible with the corresponding major.minor (1.0.*).
     * Patch versions will correspond to patches of this document.
     */
    @NonNull
    @Builder.Default
    private String asyncapi = "2.0.0";

    /**
     * <b>Required.</b>
     * Provides metadata about the API. The metadata can be used by the clients if needed.
     */
    @NonNull
    private Info info;

    /**
     * A string representing the default content type to use when encoding/decoding a message's payload.
     * The value MUST be a specific media type (e.g. application/json).
     * This value MUST be used by schema parsers when the contentType property is omitted.
     * In case a message can't be encoded/decoded using this value, schema parsers MUST use their default content type.
     */
    private String defaultContentType;

    /**
     * Provides connection details of servers.
     */
    private Map<String, Server> servers;

    /**
     * <b>Required.</b>
     * The available channels and messages for the API.
     * Channels are also known as "topics", "routing keys", "event types" or "paths".
     */
    @NonNull
    private Map<String, ChannelItem> channels;

    /**
     * Holds a set of reusable objects for different aspects of the AsyncAPI specification.
     * All objects defined within the components object will have no effect on the API unless they are explicitly
     * referenced from properties outside the components object.
     */
    private Components components;

    /**
     * <b>Required.</b>
     * A set of tags used by the specification with additional metadata. Each tag name in the set MUST be unique.
     */
    @NotNull
    @Builder.Default
    private Set<Tag> tags = Collections.emptySet();

}
