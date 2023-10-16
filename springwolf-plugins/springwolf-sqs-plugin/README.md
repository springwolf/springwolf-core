# Springwolf SQS Plugin

##### Automated documentation for Spring Boot application with AWS SQS (Simple Queue Service) consumers

### Table Of Contents

- [About](#about)
- [Usage](#usage)
    - [Dependencies](#dependencies)
    - [Configuration class](#configuration-class)
- [Verify](#verify)
- [Example Project](#example-project)

### About

This plugin generates an [AsyncAPI document](https://www.asyncapi.com/) from `@SqsListener` methods.

### Usage

Add the following dependencies and configuration class to enable this plugin.

#### Dependencies

```groovy
dependencies {
    // Provides the documentation API    
    implementation 'io.github.springwolf:springwolf-sqs:<springwolf-version>'

    // Provides the UI - optional (recommended)
    runtimeOnly 'io.github.springwolf:springwolf-ui:<springwolf-version>'
}
```

#### Verify

If you have included the UI dependency, access it with the following url: `localhost:8080/springwolf/asyncapi-ui.html`.
If not, try the following endpoint: `localhost:8080/springwolf/docs`.

### Example Project

See [springwolf-sqs-example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-sqs-example)
.
