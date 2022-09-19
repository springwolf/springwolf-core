![version](https://img.shields.io/github/v/release/springwolf/springwolf-ui)
![springwolf-ui](https://github.com/springwolf/springwolf-ui/workflows/springwolf-ui/badge.svg)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Springwolf UI
##### Web UI for Springwolf

## Usage
Add the following dependencies:

```groovy
dependencies {
    runtimeOnly 'io.github.springwolf:springwolf-ui:0.5.0'
}
```

After starting the application, visit: `localhost:8080/springwolf/asyncapi-ui.html`.

## Development
1. Run `npm i`
2. Run `ng serve`

### Mock Data

The application renders content based on mock data in `src/app/shared/mock`.
It contains multiple mocks - including the ones from the springwolf-core examples projects.

To update the mock data, run `npm run update-mocks`.
