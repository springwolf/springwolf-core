package io.github.stavshamir.springwolf.asyncapi.types;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.OperationBinding;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Holds information about the Producers.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerData {

    @NonNull
    private String channelName;

    @NonNull
    private Class<?> payloadType;

    @NonNull
    private Map<String, ? extends OperationBinding> binding;
}
