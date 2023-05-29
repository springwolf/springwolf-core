# Releasing

The following lists describe the steps necessary to release a new version.

## springwolf-ui
1. Update version number in `build.gradle`
2. Update version number in `README.md`
3. Run `npm run update-mocks`
4. Run `npm run start` to test the ui with the updated mocks
5. Run github `Publish releases` pipeline
6. Release version in nexus
7. Update version number on website
