## Usage

### Run with docker compose (recommended)
1. Copy the `docker-compose.yml` file to your machine.
2. Run `$ docker-compose up`.
3. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.

Note: kafka-schema-registry (avro) and akhq (a kafka ui) are not started by default

### Run with gradle
Note: You need to execute the gradle `bootRun` task since the IntelliJ spring application will not include springwolf-ui correctly.

1. Clone this repository: `$ git clone https://github.com/springwolf/springwolf-core.git`.
2. Switch to folder: `$ cd springwolf-core/springwolf-examples/springwolf-kafka-example`.
3. Start the infrastructure: `$ docker compose up kafka -d` (to run integration tests, stop the infrastructure).
   1. If you want to test avro as well, start the schema registry: `$ docker compose up kafka-schema-registry -d`.
4. Start the application: `$ ../../gradlew bootRun`.
5. Visit `http://localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl http://localhost:8080/springwolf/docs`.
