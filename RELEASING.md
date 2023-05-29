# Releasing

The following lists describe the steps necessary to release a new version.

## springwolf-core
1. Update version number in `springwolf-core/build.gradle`
2. Run all tests (including all examples + integration)
3. Run github `Publish releases` pipeline
4. Release version in nexus
5. Update version number on website

## springwolf-ui
1. Update version number in `build.gradle`
2. Update version number in `README.md`
3. Run `npm run update-mocks`
4. Run `npm run start` to test the ui with the updated mocks
5. Run github `Publish releases` pipeline
6. Release version in nexus
7. Update version number on website

## Plugins

### springwolf-amqp
1. Update version number in
   1. `springwolf-plugins/springwolf-amqp-plugin/build.gradle`
   2. `springwolf-plugins/springwolf-amqp-plugin/README.md`
   3. `springwolf-examples/springwolf-amqp-example/build.gradle`
   4. `springwolf-examples/springwolf-amqp-example/docker-compose.yml`
2. Run all tests (including all examples + integration)
3. Run docker compose and manually test ui
4. Run github `Publish releases` pipeline
5. Release version in nexus
6. Update version number on website

### springwolf-cloud-stream
1. Update version number in
   1. `springwolf-plugins/springwolf-cloud-stream-plugin/build.gradle`
   2. `springwolf-examples/springwolf-cloud-stream-example/build.gradle`
   3. `springwolf-examples/springwolf-cloud-stream-example/docker-compose.yml`
2. Run all tests (including all examples + integration)
3. Run docker compose and manually test ui
4. Run github `Publish releases` pipeline
5. Release version in nexus
6. Update version number on website

### springwolf-kafka
1. Update version number in
   1. `springwolf-plugins/springwolf-kafka-plugin/build.gradle`
   2. `springwolf-plugins/springwolf-kafka-plugin/README.md`
   3. `springwolf-examples/springwolf-kafka-example/build.gradle`
   4. `springwolf-examples/springwolf-kafka-example/docker-compose.yml`
2. Run all tests (including all examples + integration)
3. Run docker compose and manually test ui
4. Run github `Publish releases` pipeline
5. Release version in nexus
6. Update version number on website
