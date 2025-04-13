package io.github.springwolf.plugins.asb.asyncapi;


import com.azure.core.util.BinaryData;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import io.github.springwolf.core.asyncapi.AsyncApiService;

import java.util.List;
import java.util.Optional;

/**
 * SpringwolfAsbProducer.
 */
public class SpringwolfAsbProducer {

    private final AsyncApiService asyncApiService;
    private final Optional<ServiceBusSenderAsyncClient> senderAsyncClient;

    public SpringwolfAsbProducer(final AsyncApiService asyncApiService, final List<ServiceBusSenderAsyncClient> senderAsyncClient) {
        this.asyncApiService = asyncApiService;
        this.senderAsyncClient = senderAsyncClient.isEmpty() ? Optional.empty() : Optional.of(senderAsyncClient.getFirst());
    }

    public boolean isEnabled() {
        return senderAsyncClient.isPresent();
    }

    public void send(String channelName, Object payload) {
        if (senderAsyncClient.isEmpty()) return;

        ServiceBusMessage serviceBusMessage = new ServiceBusMessage(BinaryData.fromObject(payload));
        senderAsyncClient.get().sendMessage(serviceBusMessage).block();
    }
}
