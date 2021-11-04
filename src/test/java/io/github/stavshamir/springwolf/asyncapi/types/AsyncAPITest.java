package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.asyncapi.v2.model.info.Contact;
import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.info.License;
import com.asyncapi.v2.model.server.Server;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AsyncAPITest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void AsyncAPI_should_map_to_a_valid_asyncapi_json() throws IOException, JSONException {
        Info info = Info.builder()
                .title("AsyncAPI Sample App")
                .version("1.0.1")
                .description("This is a sample server.")
                .termsOfService("http://asyncapi.org/terms/")
                .contact(Contact.builder()
                        .name("API Support")
                        .url("http://www.asyncapi.org/support")
                        .email("support@asyncapi.org")
                        .build())
                .license(License.builder()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html")
                        .build())
                .build();

        Server productionServer = Server.builder()
                .url("development.gigantic-server.com")
                .description("Development server")
                .protocol("kafka")
                .protocolVersion("1.0.0")
                .build();

        Message message = Message.builder()
                .name("io.github.stavshamir.springwolf.ExamplePayload")
                .title("Example Payload")
                .payload(PayloadReference.fromModelName("ExamplePayload"))
                .build();

        OperationBinding operationBinding = KafkaOperationBinding.builder().groupId("myGroupId").build();

        Operation newUserOperation = Operation.builder()
                .message(message)
                .bindings(ImmutableMap.of("kafka", operationBinding))
                .build();

        ChannelItem newUserChannel = ChannelItem.builder()
                .description("This channel is used to exchange messages about users signing up")
                .subscribe(newUserOperation)
                .build();

        Map<String, Schema> schemas = ModelConverters.getInstance().read(ExamplePayload.class);

        AsyncAPI asyncapi = AsyncAPI.builder()
                .info(info)
                .defaultContentType("application/json")
                .servers(ImmutableMap.of("production", productionServer))
                .channels(ImmutableMap.of("new-user", newUserChannel))
                .components(Components.builder().schemas(schemas).build())
                .build();

        String actual = objectMapper.writeValueAsString(asyncapi);
        InputStream s = this.getClass().getResourceAsStream("/asyncapi/asyncapi.json");
        String expected = IOUtils.toString(s, StandardCharsets.UTF_8);
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Data
    static class ExamplePayload {
        String s;
    }

}