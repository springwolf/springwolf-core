## Usage

### Run with docker compose (recommended)
1. Copy the `docker-compose.yml` file to your machine.
2. Run `$ docker-compose up`.
3. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.
4. RabbitMQ Management: `http://localhost:15672` using `guest:guest` as login

### Run with gradle
Note: You need to execute the gradle `bootRun` task since the IntelliJ spring application will not include springwolf-ui correctly.

1. Start the application: `$ cd springwolf-core && ./gradlew build -p springwolf-examples/springwolf-amqp-example bootRun`.
2. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.
