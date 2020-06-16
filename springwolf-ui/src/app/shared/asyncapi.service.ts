import { AsyncApi } from './models/asyncapi.model';
import { Server } from './models/server.model';
import { Channel } from './models/channel.model';
import { Example } from './models/example.model';

export class AsyncApiService {

    public getAsyncApi(): AsyncApi {
        return {
            info: {
                title: 'Example AsyncAPI Document',
                version: '1.0.0',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'
            },
            servers: new Map<String, Server>([
                ["kafka", { url: "kafka:9092", protocol: "kafka" }]
            ]),
            channels: new Map<String, Channel>([
                [
                    "example-topic", {
                        operation: {
                            type: "SUBSCRIBE",
                            message: {
                                payloadReference: "#/components/schemas/Foo",
                                name: "io.github.stavshamir.swagger4kafka.services.AsyncApiDocServiceTest$Foo",
                                title: "Foo"
                            }
                        }
                    }
                ]
            ]),
            schemas: new Map<String, { type: String, properties: any, example: Example }>([
                [
                    "Foo", {
                        type: "object",
                        properties: null,
                        example: new Example({
                            "s": "stringstringstringstringstringstringstringstringstring",
                            "b": true
                        })
                    }
                ]
            ])
        }
    }

}