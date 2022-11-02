package io.github.stavshamir.springwolf.asyncapi.serializers;


import com.asyncapi.v2.binding.ChannelBinding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ChannelBindingSerializer extends StdSerializer<ChannelBinding> {

    public ChannelBindingSerializer() {
        this(null);
    }

    public ChannelBindingSerializer(Class<ChannelBinding> t) {
        super(t);
    }

    @Override
    public void serialize(ChannelBinding value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }

}
