# Springwolf Kafka Plugin

##### Automated documentation for Spring Boot application with Kafka consumers

### Table Of Contents

- [About](#about)
- [Usage](#usage)
    - [Dependencies](#dependencies)
    - [Configuration class](#configuration-class)
- [Verify](#verify)
- [Example Project](#example-project)

### About

This plugin generates an [AsyncAPI document](https://www.asyncapi.com/) from:
- `@KafkaListener` methods
- `@KafkaHandler` methods in classes annotated with `@KafkaListener`

### Usage

Add the following dependencies and configuration class to enable this plugin.

#### Dependencies

```groovy
dependencies {
    // Provides the documentation API    
    implementation 'io.github.springwolf:springwolf-kafka:0.13.0'

    // Provides the UI - optional (recommended)
    runtimeOnly 'io.github.springwolf:springwolf-ui:0.13.0'
}
```

### Configuration class

Add a configuration class and provide a `AsyncApiDocket` bean:

```java

@Configuration
public class AsyncApiConfiguration {

    private final static String BOOTSTRAP_SERVERS = "localhost:9092"; // Change to your actual bootstrap server

    @Bean
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                .version("1.0.0")
                .title("Springwolf example project")
                .build();

        // Producers are not picked up automatically - if you want them to be included in the asyncapi doc and the UI,
        // you will need to build a ProducerData and register it in the docket (line 65)
        ProducerData exampleProducerData = ProducerData.builder()
                .channelName("example-producer-topic")
                .binding(Map.of("kafka", new KafkaOperationBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        return AsyncApiDocket.builder()
                .basePackage("io.github.stavshamir.springwolf.example.kafka.consumers") // Change to your actual base package of listeners
                .info(info)
                .server("kafka", Server.builder().protocol("kafka").url(BOOTSTRAP_SERVERS).build())
                .producer(exampleProducerData)
                .build();
    }

}
```

The basePackage field must be set with the name of the package containing the classes to be scanned for `@KafkaListener`
annotated methods.

#### Verify

If you have included the UI dependency, access it with the following url: `localhost:8080/springwolf/asyncapi-ui.html`.
If not, try the following endpoint: `localhost:8080/springwolf/docs`.

### Example Project

See [springwolf-kafka-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-kafka-example).
