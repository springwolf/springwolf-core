package io.github.stavshamir.springwolf.asyncapi;

import java.util.Map;

public interface SpringwolfProducer {

    /**
     * Publish an object to a channel.
     *
     * @param channelName The name of the channel (topic, queue etc.) to publish the payload to.
     * @param payload     The object to publish.
     */
    void send(String channelName, Map<String, Object> payload);

}
