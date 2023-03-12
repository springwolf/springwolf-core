package io.github.stavshamir.springwolf.asyncapi.serializers;


import com.asyncapi.v2.binding.amqp.AMQPChannelBinding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AmqpChannelBindingSerializer extends StdSerializer<AMQPChannelBinding> {

    public AmqpChannelBindingSerializer() {
        this(null);
    }

    public AmqpChannelBindingSerializer(Class<AMQPChannelBinding> t) {
        super(t);
    }

    @Override
    public void serialize(AMQPChannelBinding value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("is", value.getIs());
        if(value.getExchange() != null) {
            writeExchange(value.getExchange(), gen);
        }
        if(value.getQueue() != null) {
            writeQueue(value.getQueue(), gen);
        }

        gen.writeEndObject();
    }

    private void writeExchange(AMQPChannelBinding.ExchangeProperties exchange, JsonGenerator gen) throws IOException {
        gen.writeObjectFieldStart("exchange");

        if(exchange.getName() != null) {
            gen.writeStringField("name", exchange.getName());
        }
        if(exchange.getType() != null) {
            gen.writeStringField("type", exchange.getType());
        }
        if(exchange.getDurable() != null) {
            gen.writeStringField("durable", exchange.getDurable());
        }
        gen.writeBooleanField("autoDelete", exchange.isAutoDelete());
        if(exchange.getVhost() != null) {
            gen.writeStringField("vhost", exchange.getVhost());
        }

        gen.writeEndObject();
    }

    private void writeQueue(AMQPChannelBinding.QueueProperties queue, JsonGenerator gen) throws IOException {
        gen.writeObjectFieldStart("queue");

        if(queue.getName() != null) {
            gen.writeStringField("name", queue.getName());
        }
        gen.writeBooleanField("durable", queue.isDurable());
        gen.writeBooleanField("exclusive", queue.isExclusive());
        gen.writeBooleanField("autoDelete", queue.isAutoDelete());
        if(queue.getVhost() != null) {
            gen.writeStringField("vhost", queue.getVhost());
        }

        gen.writeEndObject();
    }

}
