package io.github.stavshamir.swagger4kafka.asyncapi.types;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.Channel;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.Operation;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.bindings.kafka.KafkaOperationBinding;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.swagger4kafka.asyncapi.types.info.Contact;
import io.github.stavshamir.swagger4kafka.asyncapi.types.info.Info;
import io.github.stavshamir.swagger4kafka.asyncapi.types.info.License;
import io.github.stavshamir.swagger4kafka.asyncapi.types.server.Server;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

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
                .name("io.github.stavshamir.swagger4kafka.ExamplePayload")
                .title("Example Payload")
                .payload(PayloadReference.fromModelName("ExamplePayload"))
                .build();

        Operation newUserOperation = Operation.builder()
                .message(message)
                .bindings(ImmutableMap.of("kafka", KafkaOperationBinding.withGroupId("myGroupId")))
                .build();

        Channel newUserChannel = Channel.builder()
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
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
    }

    @Data
    static class ExamplePayload {
        String s;
    }

}