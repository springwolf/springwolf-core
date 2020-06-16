import { Info } from './info.model';
import { Server } from './server.model';
import { Channel } from './channel.model';
import { Example } from './example.model';

export interface AsyncApi {
    info: Info;
    servers: Map<String, Server>;
    channels: Map<String, Channel>;
    schemas: Map<String, { type: String, properties: any, example: Example }>;
}