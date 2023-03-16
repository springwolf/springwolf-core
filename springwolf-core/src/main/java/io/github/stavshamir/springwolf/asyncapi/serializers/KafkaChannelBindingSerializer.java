package io.github.stavshamir.springwolf.asyncapi.serializers;


import com.asyncapi.v2.binding.channel.kafka.KafkaChannelBinding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class KafkaChannelBindingSerializer extends StdSerializer<KafkaChannelBinding> {

    public KafkaChannelBindingSerializer() {
        this(null);
    }

    public KafkaChannelBindingSerializer(Class<KafkaChannelBinding> t) {
        super(t);
    }

    @Override
    public void serialize(KafkaChannelBinding value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }

}
