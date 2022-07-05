# Springwolf AMQP Plugin

##### Automated documentation for Spring Boot application with AMQP (RabbitMQ) consumers

### Table Of Contents

- [About](#about)
- [Usage](#usage)
    - [Dependencies](#dependencies)
    - [Configuration class](#configuration-class)
- [Verify](#verify)
- [Example Project](#example-project)

### About

This plugin generates an [AsyncAPI document](https://www.asyncapi.com/) from `@RabbitListener` methods.

### Usage

Add the following dependencies and configuration class to enable this plugin.

#### Dependencies

```groovy
dependencies {
    // Provides the documentation API    
    implementation 'io.github.springwolf:springwolf-cloud-rabbit-binder-plugin:0.1.0'

    // Provides the UI - optional (recommended)
    runtimeOnly 'io.github.springwolf:springwolf-ui:0.4.0'
}
```

### Configuration class

Add a configuration class and provide a `AsyncApiDocket` bean:

```java

@Configuration
@EnableAsyncApi
public class AsyncApiConfiguration {

    private final String amqpHost = "localhost";
    private final String amqpPort = "5672";

    @Bean
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                .version("1.0.0")
                .title("Springwolf example project - AMQP")
                .build();

        Server amqp = Server.builder()
                .protocol("amqp")
                .url(String.format("%s:%s", amqpHost, amqpPort))
                .build();

        return AsyncApiDocket.builder()
                .basePackage("io.github.stavshamir.springwolf.example.consumers")
                .info(info)
                .server("amqp", amqp)
                .build();
    }

}

```

The basePackage field must be set with the name of the package containing the classes to be scanned for `@Bean`
annotated methods with return type Consumer<T> that are identified as spring cloud functions.  rabbit bindings must be specified in application.properties including exchange (dstination), queue (group), and routing key

#### Verify

If you have included the UI dependency, access it with the following url: `localhost:8080/springwolf/asyncapi-ui.html`.
If not, try the following endpoint: `localhost:8080/springwolf/docs`.

### Example Project

See [springwolf-amqp-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-cloud-rabbit-binder-example)
.
