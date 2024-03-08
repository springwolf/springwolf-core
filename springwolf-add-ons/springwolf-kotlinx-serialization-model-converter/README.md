# Springwolf Kotlinx Serialization Converter Add-on

##### Common `Kotlinx Serialization` beans for springwolf

### Table Of Contents

- [About](#about)
- [Usage](#usage)
    - [Dependencies](#dependencies)

### About

This module implements Kotlin Serialization model converter to be used with Springwolf when needed.

Given a Kotlin class like

```kotlin
@Serializable
data class SomeData(
    @SerialName("id_value")
    val id: Int,
    @SerialName("some_name")
    val name: String,
    @SerialName("color_value")
    val color: Color,
)
```

will use the proper values from `@SerialName` to generate the AsyncAPI schema

### Usage

Add the following dependency:

#### Dependencies

```groovy
dependencies {
    implementation 'io.github.springwolf:springwolf-kotlinx-serialization-model-converters:<springwolf-version>'
}
```

