## Usage
- Verify zookeeper and kafka are running.
- If your kafka is not configured to automatically add topics, manually add a topic named `example-topic`.

### Run with gradle
2. Define an environment variable with the bootstrap server details: `$ export BOOTSTRAP_SERVER=localhost:9092`.
4. Clone this repository: `$ git clone https://github.com/stavshamir/swagger4kafka.git`
4. Start the application: `$ cd swagger4kafka && ./gradlew build -p example bootRun`
5. Visit `localhost:8080/swagger4kafka-ui.html` or try the API: `$ curl localhost:8080/kafka-api/endpoints`

_Tested with Kafka version 2.12_0.10.2.2. Changes to producer configuration may be needed for publishing to work with 
other versions (see instructions in [swagger4kafka](https://github.com/stavshamir/swagger4kafka#configuring-a-custom-producer))._