# <img src="src/assets/springwolf-logo.png" alt="Logo" width="100"/> Springwolf UI
##### Web UI for Springwolf

![version](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-ui?color=green&label=release&style=plastic)
![springwolf-ui](https://github.com/springwolf/springwolf-core/workflows/springwolf-ui/badge.svg)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Usage
Add the following dependencies:

```groovy
dependencies {
    runtimeOnly 'io.github.springwolf:springwolf-ui:<springwolf-version>'
}
```

After starting the application, visit: `localhost:8080/springwolf/asyncapi-ui.html`.

## Development

__**Note: Check out our [contribution guidelines](../CONTRIBUTING.md) before starting.**__

1. Run `npm i`
2. Run `npm start`

### Mock Data

In development mode, the application renders content based on mock data referenced in `src/app/service/mock`.

### Running tests

Run `npm test`

### Code Formatting

We use spotless to check the formatting and auto-fix many violations via `../gradlew spotlessApply`
