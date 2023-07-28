# Releasing

The following lists describe the steps necessary to release a new version.

1. Update the version number in `.env`

## Springwolf

1. Run all tests (including all examples + integration)
2. Run github `Publish releases` pipeline
3. Release version in nexus
4. Update version number on website

## springwolf-ui

1. Update version number in `README.md`
2. Run github `Publish releases` pipeline
3. Release version in nexus
4. Update version number on website

## Plugins

### springwolf-amqp

1. Update version number in `springwolf-plugins/springwolf-amqp-plugin/README.md`
2. Run all tests (including all examples + integration)
3. Run docker compose and manually test ui
4. Run github `Publish releases` pipeline
5. Release version in nexus
6. Update version number on website

### springwolf-cloud-stream

1. Update version number in `springwolf-plugins/springwolf-cloud-stream-plugin/README.md`
2. Run all tests (including all examples + integration)
3. Run docker compose and manually test ui
4. Run github `Publish releases` pipeline
5. Release version in nexus
6. Update version number on website

### springwolf-kafka

1. Update version number in `springwolf-plugins/springwolf-kafka-plugin/README.md`
2. Run all tests (including all examples + integration)
3. Run docker compose and manually test ui
4. Run github `Publish releases` pipeline
5. Release version in nexus
6. Update version number on website
