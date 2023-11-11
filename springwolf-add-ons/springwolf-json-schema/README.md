# Springwolf Json Schema Add-on

### Table Of Contents

- [About](#about)
- [Usage](#usage)
    - [Dependencies](#dependencies)
    - [Result](#result)

### About

This module generates the [json-schema](https://json-schema.org) for all Springwolf detected schemas (payloads, headers, etc.).

No configuration needed, only add the dependency.

As Springwolf uses `swagger-parser` to create an `OpenApi` schema, this module maps the `OpenApi` schema to `json-schema`.

### Usage

Add the following dependency:

#### Dependencies

```groovy
dependencies {
    implementation 'io.github.springwolf:springwolf-json-schema:<springwolf-version>'
}
```

#### Result

The `x-json-schema` field is added for each `Schema`.

Example:

```json
{
  "MonetaryAmount-Header": {
    "...": "",
    "x-json-schema": {
      "$schema": "https://json-schema.org/draft-04/schema#",
      "name": "MonetaryAmount-Header",
      "properties": {
        "__TypeId__": {
          "description": "Spring Type Id Header",
          "enum": [
            "javax.money.MonetaryAmount"
          ],
          "type": "string"
        }
      },
      "type": "object"
    }
  }
}

```
