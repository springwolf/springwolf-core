package io.github.stavshamir.springwolf.asyncapi.serializers;

import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class KafkaOperationBindingSerializerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(KafkaOperationBinding.class, new KafkaOperationBindingSerializer());
        objectMapper.registerModule(module);
    }

    @Test
    public void bindingWithClientIdTest() throws JsonProcessingException, JSONException {
        KafkaOperationBinding binding = KafkaOperationBinding.builder().clientId("testClientId").build();
        String actual = objectMapper.writeValueAsString(binding);
        String expected = "{\"clientId\":{\"type\":\"string\",\"enum\":[\"testClientId\"]}}";
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }


    @Test
    public void bindingWithClientIdEmptyTest() throws JsonProcessingException, JSONException {
        KafkaOperationBinding binding = KafkaOperationBinding.builder().clientId("").build();
        String actual = objectMapper.writeValueAsString(binding);
        String expected = "{\"clientId\":{\"type\":\"string\",\"enum\":[\"\"]}}";
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }


    @Test
    public void bindingWithGroupIdTest() throws JsonProcessingException, JSONException {
        KafkaOperationBinding binding = KafkaOperationBinding.builder().groupId("testGroupId").build();
        String actual = objectMapper.writeValueAsString(binding);
        String expected = "{\"groupId\":{\"type\":\"string\",\"enum\":[\"testGroupId\"]}}";
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }


    @Test
    public void bindingWithGroupIdEmptyTest() throws JsonProcessingException, JSONException {
        KafkaOperationBinding binding = KafkaOperationBinding.builder().groupId("").build();
        String actual = objectMapper.writeValueAsString(binding);
        String expected = "{\"groupId\":{\"type\":\"string\",\"enum\":[\"\"]}}";
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }


    @Test
    public void bindingWithBindingVersionTest() throws JsonProcessingException, JSONException {
        KafkaOperationBinding binding = KafkaOperationBinding.builder().bindingVersion("v1").build();
        String actual = objectMapper.writeValueAsString(binding);
        String expected = "{\"bindingVersion\":{\"type\":\"string\",\"enum\":[\"v1\"]}}";
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }


    @Test
    public void bindingWithBindingVersionEmptyTest() throws JsonProcessingException, JSONException {
        KafkaOperationBinding binding = KafkaOperationBinding.builder().bindingVersion("").build();
        String actual = objectMapper.writeValueAsString(binding);
        String expected = "{\"bindingVersion\":{\"type\":\"string\",\"enum\":[\"\"]}}";
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void emptyKafkaOperationBindingTest() throws JsonProcessingException, JSONException {
        KafkaOperationBinding binding = new KafkaOperationBinding();
        String actual = objectMapper.writeValueAsString(binding);
        String expected = "{}";
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void fullKafkaOperationBindingTest() throws JsonProcessingException, JSONException {
        KafkaOperationBinding binding = KafkaOperationBinding.builder().bindingVersion("v1").clientId("testClientId").groupId("testGroupId").build();
        String actual = objectMapper.writeValueAsString(binding);
        String expected = "{" +
                "\"clientId\":{\"type\":\"string\",\"enum\":[\"testClientId\"]}," +
                "\"groupId\":{\"type\":\"string\",\"enum\":[\"testGroupId\"]}," +
                "\"bindingVersion\":{\"type\":\"string\",\"enum\":[\"v1\"]}" +
                "}";
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }

}