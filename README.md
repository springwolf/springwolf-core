[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![swagger4kafka](https://github.com/stavshamir/swagger4kafka/workflows/swagger4kafka-core/badge.svg)
![swagger4kafka-ui](https://github.com/stavshamir/swagger4kafka/workflows/swagger4kafka-ui/badge.svg)

# swagger4kafka - like swagger, for Kafka!
##### Automated documentation for Kafka consumers built with Spring (with @KafkaListener). 

### About
This project is inspired by [Springfox](https://github.com/springfox/springfox), but instead of documenting REST APIs,
it documents Spring Kafka consumers.

It works by scanning the application once at runtime for methods annotated with ```@KafkaListener``` inside 
```@Component``` and ```@Service``` annotated classes, and providing a REST API to access the collected information.
A web-based UI can be added as a separate dependency.    

Both the library and the UI are designed to be mostly familiar to Swagger users.

### Why should you use it
In projects using Kafka, you may often find yourself needing to manually send a message to some topic, whether if you
are manually testing a new feature, debugging or trying to understand some flow. This requires serializing your payload
object, and then sending it by the CLI or some other interface. swagger4kafka exploits the fact you already fully
described your Kafka consumer endpoint and automatically generates an example payload object for the appropriate payload
and allows you to publish it to the correct topic with a single click.

### What it's not
It is not another generic REST API for Kafka. 

### Usage
By applying the following instructions, methods annotated with ```@KafkaListener``` inside ```@Component``` and 
```@Service``` annotated classes will be scanned once in runtime. Of course, it is a requirement that the project is a
Spring Boot project with the ```spring-kafka``` library and its relevant configurations. 

#### Dependencies
swagger4kafka is hosted on jcenter.
##### Gradle
```groovy
repositories {
    jcenter()
}

dependencies {
    // Provides the documentation API    
    implementation 'io.github.stavshamir:swagger4kafka:0.0.3'
    
    // Provides the UI - optional (recommended)
    implementation 'io.github.stavshamir:swagger4kafka-ui:0.0.1'
}
```
##### Maven
```xml
<dependencies>
    <dependency>
      <groupId>io.github.stavshamir</groupId>
      <artifactId>swagger4kafka</artifactId>
      <version>0.0.3</version>
    </dependency>
    <dependency>
      <groupId>io.github.stavshamir</groupId>
      <artifactId>swagger4kafka-ui</artifactId>
      <version>0.0.1</version>
    </dependency>
</dependencies>
```

### Integrating swagger4kafka into a Spring Boot project
Add a configuration class and provide a ```AsyncApiDocket``` bean:
```java
@Bean
public AsyncApiDocket asyncApiDocket() {
    Info info = Info.builder()
            .version("1.0.0")
            .title("swagger4kafka example project")
            .build();

    KafkaProtocolConfiguration kafka = KafkaProtocolConfiguration.builder()
            .basePackage("io.github.stavshamir.swagger4kafka.example.consumers")
            .build();

    return AsyncApiDocket.builder()
            .info(info)
            .server("kafka", Server.kafka().url(BOOTSTRAP_SERVERS).build())
            .protocol(kafka)
            .build();
}
```
The basePackage field must be set with the name of the package containing the classes to be scanned for ```@KafkaListener```
annotated methods.

#### Verify
If you have included the UI dependency, access it with the following url: ```localhost:8080/swagger4kafka-ui.html```.
If not, try the following endpoint: ```localhost:8080/asyncapi-docs``.

### Example Project
 An example project can be found [here](https://github.com/stavshamir/swagger4kafka/tree/master/swagger4kafka-example).
