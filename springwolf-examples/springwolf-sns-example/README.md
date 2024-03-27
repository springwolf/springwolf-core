## Usage

### Run with docker compose (recommended)
1. Copy the `docker-compose.yml` file to your machine.
2. Run `$ docker-compose up`.
3. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.

### Run with gradle
Note: You need to execute the gradle `bootRun` task since the IntelliJ spring application will not include springwolf-ui correctly.

1. Verify SNS is running.
2. Define an environment variable with the bootstrap server details: `$ export SPRING_CLOUD_AWS_ENDPOINT=http://localstack:4566`.
3. Clone this repository: `$ git clone https://github.com/springwolf/springwolf-core.git`.
4. Start the application: `$ cd springwolf-core && ./gradlew build -p springwolf-examples/springwolf-sns-example bootRun`.
5. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.
