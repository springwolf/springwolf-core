# Springwolf Generic Binding Add-on

### Table Of Contents

- [About](#about)
- [Usage](#usage)
    - [Dependencies](#dependencies)
    - [Code](#code)

### About

This module allows to document any binding in a generic way.

It is intended to document fields that are not yet support by Springwolf and/or AsyncAPI.
It can also be used to document vendor-specific protocols or properties.

There exists no validation on key or value names.

### Usage

Add the following dependency:

#### Dependencies

```groovy
dependencies {
    implementation 'io.github.springwolf:springwolf-generic-binding:<springwolf-version>'
}
```

#### Code

```java
class TestClass {
    @AsyncPublisher(
            // ...
    )
    @AsyncGenericOperationBinding(
            type = "custom-binding",
            fields = {
                    "internal-field=customValue", 
                    "nested.key=nestedValue"
            })
    public void sendMessage(AnotherPayloadDto msg) {
             // ...
    }
}
```
