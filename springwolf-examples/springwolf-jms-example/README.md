## Usage

### Run with docker compose (recommended)
1. Copy the `docker-compose.yml` file to your machine.
2. Run `$ docker-compose up`.
3. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.
4. ActiveMQ Management: `http://localhost:8161` using `artemis:artemis` as login

### Run with gradle
Note: You need to execute the gradle `bootRun` task since the IntelliJ spring application will not include springwolf-ui correctly.

1. Verify JMS is running.
2. Define an environment variable with the bootstrap server details: `$ export BOOTSTRAP_SERVER=tcp://activemq:61616`.
3. Clone this repository: `$ git clone https://github.com/springwolf/springwolf-core.git`.
4. Start the application: `$ cd springwolf-core && ./gradlew build -p springwolf-examples/springwolf-jms-example bootRun`.
5. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.
