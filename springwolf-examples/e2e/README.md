# End-to-End tests for Springwolf

The end-to-end tests cover test cases that cannot be verified using junitk/jest unit/integration/system tests.
Typical examples are click interactions through `springwolf-ui`.

## Usage

This project uses playwright.

To use the playwright ui, run
```bash
npm run start
```

For ci/cd or cli environments, use
```bash
npm run test
```

### Example project
The end-to-end tests are run against one example project.

To test against the non-default project, specify the environment variable `SPRINGWOLF_EXAMPLE`
For example:
```bash
SPRINGWOLF_EXAMPLE=kafka npm run start
```
