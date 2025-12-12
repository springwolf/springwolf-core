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
    implementation 'io.github.springwolf:springwolf-kafka:<springwolf-version>'

    // Provides the UI - optional (recommended)
    runtimeOnly 'io.github.springwolf:springwolf-ui:<springwolf-version>'
}
```

### Configuration

Add a `application.properties` file:

```properties
springwolf.docket.base-package=io.github.springwolf.examples

springwolf.docket.info.title=${spring.application.name}
springwolf.docket.info.version=1.0.0

springwolf.docket.servers.kafka.protocol=kafka
springwolf.docket.servers.kafka.host=${kafka.bootstrap.servers:localhost:29092}
```

The basePackage field must be set with the name of the package containing the classes to be scanned for `@KafkaListener`
annotated methods.

#### Verify

If you have included the UI dependency, access it with the following url: `localhost:8080/springwolf/asyncapi-ui.html`.
If not, try the following endpoint: `localhost:8080/springwolf/docs`.

### Example Project

See [springwolf-kafka-example](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-kafka-example).
