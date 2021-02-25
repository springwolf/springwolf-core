import { AsyncApi } from './models/asyncapi.model';
import { Server } from './models/server.model';
import { Channel, Message, Operation } from './models/channel.model';
import { Schema } from './models/schema.model';
import { Example } from './models/example.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Info } from './models/info.model';

interface ServerAsyncApi {
    asyncapi: string;
    info: Info;
    servers: {
        [key: string]: {
            url: string;
            protocol: string;
        };
    };
    channels: {
        [key: string]: {
            description?: string;
            subscribe?: {
                message: Message;
                bindings?: any;
            };
            publish?: {
                message: Message;
                bindings?: any;
            };
        };
    };
    components: {
        schemas: {
            [key: string]: {
                type: string;
                properties: object;
                example: object;
            };
        };
    };
}

@Injectable()
export class AsyncApiService {

    constructor(private http: HttpClient) { }

    public getAsyncApi(): Observable<AsyncApi> {
        return this.http
            .get<ServerAsyncApi>('/asyncapi/docs')
            .pipe(map(item => this.toAsyncApi(item)));
    }

    toAsyncApi(item: ServerAsyncApi): AsyncApi {
        return {
            info: item.info,
            servers: this.mapServers(item.servers),
            channels: this.mapChannels(item.channels),
            components: {
                schemas: this.mapSchemas(item.components.schemas)
            }
        };
    }

    private mapServers(servers: { [key: string]: Server }): Map<string, Server> {
        const s = new Map<string, Server>();
        Object.entries(servers).forEach(([k, v]) => s.set(k, v));
        return s;
    }

    private mapChannels(channels: {
        [key: string]: {
            description?: string;
            subscribe?: {
                message: Message;
                bindings?: any;
            };
            publish?: {
                message: Message;
                bindings?: any;
            };
        }
    }): Map<string, Channel> {
        const s = new Map<string, Channel>();
        Object.entries(channels).forEach(([k, v]) => s.set(k, {
            description: v.description,
            operation: this.mapOperation(v.subscribe, v.publish)
        }));
        return s;
    }

    private mapOperation(subscribe: { message: Message; bindings?: any; }, publish: { message: Message; bindings?: any; }): Operation {
        const isSubscribe = !!subscribe;

        if (isSubscribe) {
            return {
                type: this.getProtocol(subscribe) + " Consumer",
                message: subscribe.message,
                bindings: subscribe.bindings
            }
        } else {
            return {
                type: this.getProtocol(publish) + " Producer",
                message: publish.message,
                bindings: publish.bindings
            }
        }
    }

    private getProtocol(operation: { message: Message; bindings?: any; }): string {
        const protocol = Object.keys(operation.bindings)[0];
        return protocol.charAt(0).toUpperCase() + protocol.slice(1);
    }

    mapSchemas(schemas: { [key: string]: { type: string; properties: object; example: object; } }): Map<string, Schema> {
        const s = new Map<string, Schema>();
        Object.entries(schemas).forEach(([k, v]) => s.set(k, {
            type: v.type,
            properties: v.properties,
            example: new Example(v.example)
        }));
        return s;
    }

}
