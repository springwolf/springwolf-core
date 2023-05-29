# <img src="src/assets/springwolf-logo.png" alt="Logo" width="100"/> Springwolf UI
##### Web UI for Springwolf

![version](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-ui?color=green&label=release&style=plastic)
![springwolf-ui](https://github.com/springwolf/springwolf-core/workflows/springwolf-ui/badge.svg)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Usage
Add the following dependencies:

```groovy
dependencies {
    runtimeOnly 'io.github.springwolf:springwolf-ui:0.7.0'
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

## Release

Releasing is done by running the gradle task `publish`. For local development, use `publishToMavenLocal`.

### Setup the signing keys

If you do not have gpg keys yet, generate one with: `gpg --full-gen-key` You will need to set password.

Use the following environment variables:
- ORG_GRADLE_PROJECT_SIGNINGKEY: Output of `gpg --armor --export-secret-key`
- ORG_GRADLE_PROJECT_SIGNINGPASSWORD: Password for the gpg key
