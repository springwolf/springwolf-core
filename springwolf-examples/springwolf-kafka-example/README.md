## Usage

### Run with docker compose (recommended)
1. Copy the `docker-compose.yml` file to your machine.
2. Run `$ docker-compose up`.
3. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.

### Run with gradle
1. Verify zookeeper and kafka are running.
2. If your kafka is not configured to automatically add topics, manually add a topic named `example-topic`.
3. Define an environment variable with the bootstrap server details: `$ export BOOTSTRAP_SERVER=localhost:9092`.
4. Clone this repository: `$ git clone https://github.com/springwolf/springwolf-kafka.git`.
5. Start the application: `$ cd springwolf-kafka && ./gradlew build -p springwolf-kafka-example bootRun`.
6. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.

_Tested with Kafka version 2.12_0.10.2.2._