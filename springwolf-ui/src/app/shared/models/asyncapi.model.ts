import { Info } from './info.model';
import { Server } from './server.model';
import { Channel } from './channel.model';

export interface AsyncApi {
    info: Info;
    servers: Map<String, Server>;
    channels: Map<String, Channel>;
}