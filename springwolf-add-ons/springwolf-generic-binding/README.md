# Springwolf Generic Binding Add-on

### Table Of Contents

- [About](#about)
- [Usage](#usage)
    - [Dependencies](#dependencies)
    - [Code](#code)

### About

This module allows to document any binding in a generic way.

It is intended to document fields that are not yet support by Springwolf and/or AsyncApi.
It can also be used to document vendor-specific protocols or properties.

There exists no validation on key or value names.

### Usage

Add the following dependency:

#### Dependencies

```groovy
dependencies {
    implementation 'io.github.springwolf:springwolf-generic-binding:0.1.1'
}
```

#### Code

```java
class TestClass {
    @AsyncPublisher(
            // ...
    )
    @AsyncGenericOperationBinding(
            type = "custom-sqs",
            fields = {"internal-field=customValue", "nested.key=nestedValue"})
    public void sendMessage(AnotherPayloadDto msg) {
             // ...
    }
}
```
