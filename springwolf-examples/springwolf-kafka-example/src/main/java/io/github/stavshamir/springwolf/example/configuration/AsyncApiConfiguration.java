package io.github.stavshamir.springwolf.example.configuration;

import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncHeaderSchema;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.KafkaConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.KafkaProducerData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.EnableAsyncApi;
import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.AbstractJavaTypeMapper;

import static io.github.stavshamir.springwolf.example.configuration.KafkaConfiguration.CONSUMER_TOPIC;
import static io.github.stavshamir.springwolf.example.configuration.KafkaConfiguration.PRODUCER_TOPIC;
import static java.util.Arrays.asList;

@Configuration
@EnableAsyncApi
public class AsyncApiConfiguration {

    private final String BOOTSTRAP_SERVERS;

    public AsyncApiConfiguration(@Value("${kafka.bootstrap.servers}") String bootstrapServers) {
        this.BOOTSTRAP_SERVERS = bootstrapServers;
    }

    @Bean
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                .version("1.0.0")
                .title("Springwolf example project - Kafka")
                .build();

        KafkaProducerData exampleProducerData = KafkaProducerData.kafkaProducerDataBuilder()
                .topicName(PRODUCER_TOPIC)
                .payloadType(ExamplePayloadDto.class)
                .headers(createSpringDefaultHeaders())
                .build();

        KafkaProducerData anotherProducerData = KafkaProducerData.kafkaProducerDataBuilder()
                .topicName(PRODUCER_TOPIC)
                .description("Custom, optional description for this produced to topic")
                .payloadType(AnotherPayloadDto.class)
                .headers(createCloudEventHeaders())
                .build();

        KafkaConsumerData manuallyConfiguredConsumer = KafkaConsumerData.kafkaConsumerDataBuilder()
                .topicName(CONSUMER_TOPIC)
                .description("Custom, optional description for this consumed topic")
                .payloadType(ExamplePayloadDto.class)
                .build();

        return AsyncApiDocket.builder()
                .basePackage("io.github.stavshamir.springwolf.example.consumers")
                .info(info)
                .server("kafka", Server.builder().protocol("kafka").url(BOOTSTRAP_SERVERS).build())
                .producer(exampleProducerData)
                .producer(anotherProducerData)
                .consumer(manuallyConfiguredConsumer)
                .build();
    }

    private static AsyncHeaders createSpringDefaultHeaders() {
        AsyncHeaderSchema springTypeIdHeader = new AsyncHeaderSchema(AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME);
        springTypeIdHeader.setDescription("Spring Type Id Header");
        springTypeIdHeader.setExample("io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto");
        springTypeIdHeader.setEnum(asList(
                "io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto",
                "io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto")
        );

        AsyncHeaders springDefaultHeaders = new AsyncHeaders("SpringDefaultHeaders");
        springDefaultHeaders.addHeader(springTypeIdHeader);
        return springDefaultHeaders;
    }

    private static AsyncHeaders createCloudEventHeaders() {
        AsyncHeaders ceBaseHeaders = createCloudEventsBaseHeaders();
        AsyncHeaders endpointSpecificHeaders = AsyncHeaders.from(ceBaseHeaders, "CloudEventHeadersForAnotherPayloadDtoEndpoint");

        AsyncHeaderSchema ceTypeHeader = new AsyncHeaderSchema("ce_type");
        ceTypeHeader.setDescription("Payload Type Header");
        ceTypeHeader.setExample("io.github.stavshamir.springwolf.CloudEventHeadersForAnotherPayloadDtoEndpoint");
        endpointSpecificHeaders.addHeader(ceTypeHeader);

        AsyncHeaderSchema ceSourceHeader = new AsyncHeaderSchema("ce_source");
        ceSourceHeader.setDescription("Source Header");
        ceSourceHeader.setExample("springwolf-kafka-example/anotherPayloadDtoEndpoint");
        endpointSpecificHeaders.addHeader(ceSourceHeader);

        return endpointSpecificHeaders;
    }

    private static AsyncHeaders createCloudEventsBaseHeaders() {
        AsyncHeaderSchema ceContentTypeHeader = new AsyncHeaderSchema("content-type");
        ceContentTypeHeader.setDescription("Content-Type Header");
        ceContentTypeHeader.setExample("application/json");

        AsyncHeaderSchema ceSpecVersionHeader = new AsyncHeaderSchema("ce_specversion");
        ceSpecVersionHeader.setDescription("Spec Version Header");
        ceSpecVersionHeader.setExample("1.0");

        AsyncHeaderSchema ceIdHeader = new AsyncHeaderSchema("ce_id");
        ceIdHeader.setDescription("Id Header");
        ceIdHeader.setExample("1234-1234-1234");

        AsyncHeaderSchema ceTimeHeader = new AsyncHeaderSchema("ce_time");
        ceTimeHeader.setDescription("Time Header");
        ceTimeHeader.setExample("2015-07-20T15:49:04-07:00");

        AsyncHeaders ceBaseHeaders = new AsyncHeaders("CloudEventsBase");
        ceBaseHeaders.addHeader(ceContentTypeHeader);
        ceBaseHeaders.addHeader(ceSpecVersionHeader);
        ceBaseHeaders.addHeader(ceIdHeader);
        ceBaseHeaders.addHeader(ceTimeHeader);
        return ceBaseHeaders;
    }
}
