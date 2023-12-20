# Springwolf AsyncAPI

### About

This library provides a JVM-friendly binding to generate [AsyncAPI document](https://www.asyncapi.com/). 
It allows to create specifications for AsyncAPI from code.

Even when this library can be use by any JVM system, it's main goal is to support Springwolf-Core.

### Usage

Add the following dependencies and configuration class to enable this plugin.

#### Dependencies

```groovy
dependencies {
    // Provides the JVM Builders to create an AsyncAPI Spec file    
    implementation 'io.github.springwolf:springwolf-asyncapi:<springwolf-version>'
}
```

### Configuration class

```java
        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(Info.builder()
                        .title("Account Service")
                        .version("1.0.0")
                        .description("This service is in charge of processing user signups")
                        .build())
                .channels(Map.of(
                        "userSignedup",
                        Channel.builder().build()))
                .operations(Map.of(
                        "sendUserSignedup",
                        Operation.builder().build()))
                .build();
```

## TODO:

* Improve readme description
* Review package name
  * core
  * serializer vs jackson
* Test that the serialization is always respecting the case format
* Test that the validations are applied
* Finish to implement and test all the Bindings
* Deserialize a JSON/YAML file to Java instances

## PENDING:

* [[Docs Bug 🐞 report]: JMS Server Binding Example doesn't match AsyncAPI v3 specification #232](https://github.com/asyncapi/bindings/issues/232) - In Review
* [Confusing Operation Object Example #1007](https://github.com/asyncapi/spec/issues/1007) - In Review

## Convert AsyncAPI v2.x to v3.0.0

See https://github.com/asyncapi/converter-js#conversion-2xx-to-3xx
