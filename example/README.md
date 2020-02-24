### Usage
1. Verify zookeeper and kafka are running.
2. Define an environment variable with the bootstrap server details: `$ export BOOTSTRAP_SERVER=localhost:9092`.
3. If your kafka is not configured to automatically add topics, manually add a topic named `example-topic`.
4. Start the application and visit `localhost:8080/swagger4kafka-ui.html`.

_Tested with Kafka version 2.12_0.10.2.2. Changes to producer configuration may be needed for publishing to work with 
other versions (see instructions in [swagger4kafka](https://github.com/stavshamir/swagger4kafka#configuring-a-custom-producer))._