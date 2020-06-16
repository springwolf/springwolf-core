import { Info } from './info.model';
import { Server } from './server.model';

export interface AsyncApi {
    info: Info;
    servers: Map<String, Server>;
}