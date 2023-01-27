package io.github.stavshamir.springwolf.asyncapi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.EmptyOperationBinding;

import java.io.IOException;

public class EmptyOperationBindingSerializer extends StdSerializer<EmptyOperationBinding> {

    public EmptyOperationBindingSerializer() {
        this(null);
    }

    public EmptyOperationBindingSerializer(Class<EmptyOperationBinding> t) {
        super(t);
    }

    @Override
    public void serialize(
            EmptyOperationBinding value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }
}
