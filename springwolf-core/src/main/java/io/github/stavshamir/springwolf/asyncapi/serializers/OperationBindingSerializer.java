package io.github.stavshamir.springwolf.asyncapi.serializers;


import com.asyncapi.v2.binding.OperationBinding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OperationBindingSerializer extends StdSerializer<OperationBinding> {

    public OperationBindingSerializer() {
        this(null);
    }

    public OperationBindingSerializer(Class<OperationBinding> t) {
        super(t);
    }

    @Override
    public void serialize(OperationBinding value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }

}
