# Springwolf AsyncAPI

### About

This library provides a JVM-friendly binding to generate the AsyncAPI spec 3.0. The spec 3.0 is documented on
[AsyncAPI](https://www.asyncapi.com/) in a human friendly way, with [AsyncAPI spec & examples](https://github.com/asyncapi/spec) 
being the concrete specification.

All bindings are documented in a separate [AsyncAPI Bindings](https://github.com/asyncapi/bindings) repo. This repo
contains example files, which are heavily used in the tests to ensure conformity of this library to the AsyncAPI spec.

Even when this library can be used by any JVM system, it's main goal is to support Springwolf-Core.

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

## PENDING:

* [[Docs Bug 🐞 report]: JMS Server Binding Example doesn't match AsyncAPI v3 specification #232](https://github.com/asyncapi/bindings/issues/232) - In Review
* [Confusing Operation Object Example #1007](https://github.com/asyncapi/spec/issues/1007) - In Review

## Convert AsyncAPI v2.x to v3.0.0

See https://github.com/asyncapi/converter-js#conversion-2xx-to-3xx

# Contributions

Any contributions are welcome, including, but not limited to:

* Add the pending bindings and tests
* Fixes
* Test that the serialization is always respecting the case format
* Test that the validations are applied
* Deserialize a JSON/YAML file to Java instances
