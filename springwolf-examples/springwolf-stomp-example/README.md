## Usage

This example project is slightly different compared to the other example projects.

It uses the
- `/myendpoint` path to connect to the application
- `/queue` prefix for 1 on 1 communication (client to server and vice versa)
- `/topic` prefix to broadcast to all clients

### Run with docker compose (recommended)
1. Copy the `docker-compose.yml` file to your machine.
2. Run `$ docker-compose up`.
3. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.
4. Play with the example at `localhost:8080` - even with multiple browser windows

### Run with gradle
Note: You need to execute the gradle `bootRun` task since the IntelliJ spring application will not include springwolf-ui correctly.

1. Clone this repository: `$ git clone https://github.com/springwolf/springwolf-core.git`.
2. Start the application: `$ cd springwolf-core && ./gradlew build -p springwolf-examples/springwolf-stomp-example bootRun`.
3. Visit `localhost:8080/springwolf/asyncapi-ui.html` or try the API: `$ curl localhost:8080/springwolf/docs`.
