package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;

import java.util.Map;

public interface OperationData {
    String getChannelName();
    String getDescription();
    Map<String, ? extends ChannelBinding> getChannelBinding();
    Class<?> getPayloadType();
    AsyncHeaders getHeaders();
    Map<String, ? extends OperationBinding> getOperationBinding();

    enum OperationType {
        PUBLISH("publish"), SUBSCRIBE("subscribe");

        public final String operationName;
        OperationType(String operationName) {
            this.operationName = operationName;
        }
    }
}
