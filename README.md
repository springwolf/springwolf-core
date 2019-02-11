# swagger4kafka - like swagger, for Kafka!
##### Automated documentation for Kafka consumers built with Spring (with @KafkaListener). 

### About
This project is inspired by [Springfox](https://github.com/springfox/springfox), but instead of documenting REST APIs,
it documents Spring Kafka consumers.

It works by scanning the application once at runtime for methods annotated with ```@KafkaListener``` inside 
```@Component``` and ```@Service``` annotated classes, and providing a REST API to access the collected information.
A web-based UI can be added as a [separate dependency](https://github.com/stavshamir/swagger4kafka-ui).    

Both the library and the UI are designed to be mostly familiar to Swagger users.

### Usage

#### Dependencies
swagger4kafka is hosted on maven central.
##### Gradle
```groovy
dependencies {
    // Provides the documentation API    
    implementation 'com.stavshamir:swagger4kafka:1.1.0'
    
    // Provides the UI - optional (but useful)
    implementation 'com.stavshamir:swagger4kafka-ui:1.0.0'
}
```
##### Maven
```xml
<dependencies>
    <dependency>
      <groupId>com.stavshamir</groupId>
      <artifactId>swagger4kafka</artifactId>
      <version>1.1.0</version>
    </dependency>
    <dependency>
      <groupId>com.stavshamir</groupId>
      <artifactId>swagger4kafka-ui</artifactId>
      <version>1.0.0</version>
    </dependency>
</dependencies>
```

### Integrating swagger4kafka into a Spring Boot project
Add a configuration class and provide a ```Docket``` bean:
```java
@Configuration
@EnableSwagger4Kafka
public class Swagger4KafkaConfiguration {
    @Bean
    public Docket docket() {
        return Docket.builder()
                .serviceName("Swagger4Kafka Example Project")
                .basePackage("com.stavshamir.swagger4kafka.example.consumers")
                .bootstrapServers("localhost:9092")
                .build();
    }
}
```
The basePackage field must be set with the name of the package containing the classes to be scanned for ```@KafkaListener```
annotated methods.

Other fields are optional, and for some sensible defaults are provided. For more info, see the ```Docket``` Javadoc. 

### Example Project
 An example project can be found [here](https://github.com/stavshamir/swagger4kafka-example).