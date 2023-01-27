package io.github.stavshamir.springwolf.asyncapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.stavshamir.springwolf.asyncapi.types.channel.bindings.EmptyChannelBinding;

import java.io.IOException;

public class EmptyChannelBindingSerializer extends StdSerializer<EmptyChannelBinding> {

    public EmptyChannelBindingSerializer() {
        this(null);
    }

    public EmptyChannelBindingSerializer(Class<EmptyChannelBinding> t) {
        super(t);
    }

    @Override
    public void serialize(EmptyChannelBinding value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }
}
