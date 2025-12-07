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
    implementation 'io.github.springwolf:springwolf-amqp:<springwolf-version>'

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

springwolf.docket.servers.amqp.protocol=amqp
springwolf.docket.servers.amqp.host=amqp:5672
```

The basePackage field must be set with the name of the package containing the classes to be scanned for `@RabbitListener`
annotated methods.

#### Verify

If you have included the UI dependency, access it with the following url: `localhost:8080/springwolf/asyncapi-ui.html`.
If not, try the following endpoint: `localhost:8080/springwolf/docs`.

### Example Project

See [springwolf-amqp-example](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-amqp-example)
.
