package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.OperationBinding;
import lombok.*;

import java.util.Map;

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
