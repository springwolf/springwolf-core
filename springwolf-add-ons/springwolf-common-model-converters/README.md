# Springwolf Common Model Converters Add-on

##### Common `ModelConverter` beans for springwolf

### Table Of Contents

- [About](#about)
- [Usage](#usage)
    - [Dependencies](#dependencies)

### About

This module implements common model converters to be used with Sringwolf when needed.

Currently, the module includes a model converter that fixes an issue with the `javax.money.MonetaryAmount` type.

NOTE: If Springwolf is used together with [springdoc-openapi](https://github.com/springdoc/springdoc-openapi),
there is no need to use this module, springdoc-openapi implements them internally.


### Usage

Add the following dependency:

#### Dependencies

```groovy
dependencies {
    implementation 'io.github.springwolf:springwolf-common-model-converters:0.1.0'
}
```
