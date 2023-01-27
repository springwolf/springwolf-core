package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.OperationBinding;
import lombok.Data;

@Data
public class ProcessedOperationBinding {
    private final String type;
    private final OperationBinding binding;
}
