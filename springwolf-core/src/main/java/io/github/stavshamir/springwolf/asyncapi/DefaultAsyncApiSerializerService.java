package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.stavshamir.springwolf.asyncapi.serializers.EmptyChannelBindingSerializer;
import io.github.stavshamir.springwolf.asyncapi.serializers.EmptyOperationBindingSerializer;
import io.github.stavshamir.springwolf.asyncapi.serializers.AmqpOperationBindingSerializer;
import io.github.stavshamir.springwolf.asyncapi.serializers.KafkaChannelBindingSerializer;
import io.github.stavshamir.springwolf.asyncapi.serializers.KafkaOperationBindingSerializer;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.types.channel.bindings.EmptyChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.EmptyOperationBinding;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DefaultAsyncApiSerializerService implements AsyncApiSerializerService {

    private ObjectMapper jsonMapper = new ObjectMapper();
    private PrettyPrinter printer = new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @PostConstruct
    void postConstruct() {
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        registerKafkaOperationBindingSerializer();
    }

    private void registerKafkaOperationBindingSerializer() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(EmptyChannelBinding.class, new EmptyChannelBindingSerializer());
        module.addSerializer(EmptyOperationBinding.class, new EmptyOperationBindingSerializer());
        module.addSerializer(AMQPOperationBinding.class, new AmqpOperationBindingSerializer());
        module.addSerializer(KafkaChannelBinding.class, new KafkaChannelBindingSerializer());
        module.addSerializer(KafkaOperationBinding.class, new KafkaOperationBindingSerializer());
        jsonMapper.registerModule(module);
    }

    @Override
    public String toJsonString(AsyncAPI asyncAPI) throws JsonProcessingException {
        return jsonMapper.writer(printer).writeValueAsString(asyncAPI);
    }

    /**
     * Get the current object mapper configuration.
     */
    public ObjectMapper getObjectMapper() {
        return jsonMapper;
    }
    
    /**
     * Allows to customize the used objectMapper
     * <p>
     * Use {@link #getObjectMapper()} as a starting point
     */
    public void setObjectMapper(ObjectMapper mapper) {
        jsonMapper = mapper;
    }

    /**
     * Allows to override the used PrettyPrinter
     */
    public void setPrettyPrinter(PrettyPrinter prettyPrinter) {
        printer = prettyPrinter;
    }

}
