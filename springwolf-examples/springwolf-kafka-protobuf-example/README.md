### Run with gradle
1. Verify zookeeper and kafka are running.
2. If your kafka is not configured to automatically add topics, manually add a topic named `protobuf-topic`.
3. Define an environment variable with the server details: `$ export BOOTSTRAP_SERVER=localhost:9092` and `$ export SCHEMA_SERVER=http://localhost:8080`.
4. Clone this repository: `$ git clone https://github.com/springwolf/springwolf-core.git`.
5. Start the application: `$ cd springwolf-core && ./gradlew build -p springwolf-examples/springwolf-kafka-protobuf-example bootRun`.
6. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.

_Tested with Kafka version 2.12_0.10.2.2._
"
