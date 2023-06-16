package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.info.Contact;
import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.info.License;
import com.asyncapi.v2._6_0.model.server.Server;
import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import com.asyncapi.v2.schema.Type;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.types.Components;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DefaultAsyncApiSerializerService.class})
class DefaultAsyncApiSerializerServiceTest {

    @Autowired
    private DefaultAsyncApiSerializerService serializer;

    private AsyncAPI getAsyncAPITestObject() {
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
                .bindings(Map.of("kafka", new KafkaMessageBinding(new StringSchema(), null, null, null, "binding-version-1")))
                .build();

        com.asyncapi.v2.schema.Schema groupId = new com.asyncapi.v2.schema.Schema();
        groupId.setEnumValue(List.of("myGroupId"));
        groupId.setType(Type.STRING);
        OperationBinding operationBinding = KafkaOperationBinding.builder().groupId(groupId).build();

        Operation newUserOperation = Operation.builder()
                .operationId("new-user_listenerMethod_subscribe")
                .message(message)
                .bindings(Map.of("kafka", operationBinding))
                .build();

        ChannelItem newUserChannel = ChannelItem.builder()
                .description("This channel is used to exchange messages about users signing up")
                .subscribe(newUserOperation)
                .build();

        Map<String, Schema> schemas = ModelConverters.getInstance().read(DefaultAsyncApiSerializerServiceTest.ExamplePayload.class);

        AsyncAPI asyncapi = AsyncAPI.builder()
                .info(info)
                .defaultContentType("application/json")
                .servers(Map.of("production", productionServer))
                .channels(Map.of("new-user", newUserChannel))
                .components(Components.builder().schemas(schemas).build())
                .build();

        return asyncapi;
    }

    @Test
    void AsyncAPI_should_map_to_a_valid_asyncapi_json() throws IOException, JSONException {
        var asyncapi = getAsyncAPITestObject();
        String actual = serializer.toJsonString(asyncapi);
        InputStream s = this.getClass().getResourceAsStream("/asyncapi/asyncapi.json");
        String expected = IOUtils.toString(s, StandardCharsets.UTF_8);
        assertEquals(expected, actual);
    }

    @Test
    void AsyncAPI_should_map_to_a_valid_asyncapi_yaml() throws IOException, JSONException {
        var asyncapi = getAsyncAPITestObject();
        String actual = serializer.toYaml(asyncapi);
        InputStream s = this.getClass().getResourceAsStream("/asyncapi/asyncapi.yaml");
        String expected = IOUtils.toString(s, StandardCharsets.UTF_8);
        assertEquals(expected, actual);
    }

    @Data
    static class ExamplePayload {
        String s;
    }

}
