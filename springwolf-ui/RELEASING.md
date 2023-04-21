# Releasing

The following lists describe the steps necessary to release a new version.

## springwolf-ui
1. Update version number in `build.gradle`
2. Update readme version number
3. run `npm run update-mocks`
4. run docker compose and manually test ui
4. Run github `Publish releases` pipeline
5. Release version in nexus
6. Update version number on website
