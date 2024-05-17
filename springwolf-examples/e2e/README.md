# End-to-End tests for Springwolf

The end-to-end tests cover test cases that cannot be verified using junitk/jest unit/integration/system tests.
Typical examples are click interactions through `springwolf-ui`.

## Usage

This project uses [playwright](https://playwright.dev).

To install the dependencies, run `npm install` (or `../../gradlew npmInstall`)

To use the playwright ui, run
```bash
npm run start
```

For ci/cd or cli environments, use `../../gradlew npm_run_test` or
```bash
npm run test
```

### Example project
The end-to-end tests are run against one example project, which is automatically started using docker.

To test against the non-default project, specify the environment variable `SPRINGWOLF_EXAMPLE`
For example:
```bash
SPRINGWOLF_EXAMPLE=kafka npm run start
```

_Note: The example is re-used between tests. When switching example projects, the docker containers need to be stopped._
