package io.github.stavshamir.springwolf.asyncapi.serializers;


import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AmqpOperationBindingSerializer extends StdSerializer<AMQPOperationBinding> {

    public AmqpOperationBindingSerializer() {
        this(null);
    }

    public AmqpOperationBindingSerializer(Class<AMQPOperationBinding> t) {
        super(t);
    }

    @Override
    public void serialize(AMQPOperationBinding value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeNumberField("expiration", value.getExpiration());

        if (value.getCc() != null) {
            gen.writeArrayFieldStart("cc");
            for (String cc : value.getCc()) {
                gen.writeString(cc);
            }
            gen.writeEndArray();
        }

        gen.writeNumberField("priority", value.getPriority());
        gen.writeNumberField("deliveryMode", value.getDeliveryMode());
        gen.writeBooleanField("mandatory", value.isMandatory());
        gen.writeBooleanField("timestamp", value.isTimestamp());
        gen.writeBooleanField("ack", value.isAck());

        gen.writeEndObject();
    }
}
