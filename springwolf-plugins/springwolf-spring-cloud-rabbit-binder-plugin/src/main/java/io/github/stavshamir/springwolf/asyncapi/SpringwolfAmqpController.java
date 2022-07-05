package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.producer.SpringwolfAmqpProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/springwolf/amqp")
@RequiredArgsConstructor
public class SpringwolfAmqpController {

    private final SpringwolfAmqpProducer amqpProducer;

    private final DefaultChannelsService channelsService;

    private final ObjectMapper objectMapper;

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody String objectJson) {
        if (amqpProducer == null) {
            log.warn("AMQP producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMQP producer is not enabled");
        }

        ChannelItem channelItem = channelsService.getChannels().get(topic);
        if(channelItem == null) {
            log.warn("Message channel is not known");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "channel "+topic+" is not a known channel.");
        }
        Message message = (Message) channelItem.getPublish().getMessage();
        try {
            Class<?> messageBodyClass = Class.forName(message.getName());
            Object parsedObject = objectMapper.readValue(objectJson, messageBodyClass);
            log.info("Publishing to amqp queue {}: {}", topic, objectJson);
            amqpProducer.send(topic, objectJson);
        } catch (Exception e) {
            log.warn("Error mapping object json to type class before sending to ampq");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message payload could not be mapped to "+message.getName());
        }
    }

}
