import { AsyncApi } from './models/asyncapi.model';
import { Server } from './models/server.model';
import { Channel, Message, Operation } from './models/channel.model';
import { Schema } from './models/schema.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Info } from './models/info.model';
import { Endpoints } from './endpoints';

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
                message: Message | { oneOf: Message[] };
                bindings?: any;
            };
            publish?: {
                message: Message | { oneOf: Message[] };
                bindings?: any;
            };
        };
    };
    components: {
        schemas: Map<string, Schema>;
    };
}

@Injectable()
export class AsyncApiService {

    private docs: AsyncApi;

    constructor(private http: HttpClient) {
    }

    public getAsyncApis(): Observable<AsyncApi> {
        if (this.docs) {
            return of(this.docs);
        }

        return this.http.get<ServerAsyncApi>(Endpoints.docs).pipe(map(item => {
            this.docs = this.toAsyncApi(item);
            return this.docs;
        }));
    }

    private toAsyncApi(item: ServerAsyncApi): AsyncApi {
        return {
            info: item.info,
            servers: this.mapServers(item.servers),
            channels: this.mapChannels(item.channels),
            components: {
                schemas: item.components.schemas
            }
        };
    }

    private mapServers(servers: ServerAsyncApi["servers"]): Map<string, Server> {
        const s = new Map<string, Server>();
        Object.entries(servers).forEach(([k, v]) => s.set(k, v));
        return s;
    }

    private mapChannels(channels: ServerAsyncApi["channels"]): Channel[] {
        const s = new Array<Channel>();
        Object.entries(channels).forEach(([k, v]) => {
            const subscriberChannels = this.mapChannel(k, v.description, v.subscribe, "subscribe")
            subscriberChannels.forEach(channel => s.push(channel))

            const publisherChannels = this.mapChannel(k, v.description, v.publish, "publish")
            publisherChannels.forEach(channel => s.push(channel))
        });
        return s;
    }

    private mapChannel(
        topicName: string,
        description: ServerAsyncApi["channels"][""]["description"],
        operation: ServerAsyncApi["channels"][""]["subscribe"] | ServerAsyncApi["channels"][""]["publish"],
        operationName: string): Channel[] {
        if (operation !== undefined) {
            let messages: Message[] = 'oneOf' in operation.message ? operation.message.oneOf : [operation.message];

            return messages.map(message => {
                return {
                    name: topicName,
                    description: description,
                    operation: this.mapOperation(operationName, message, operation.bindings)
                }
            })
        }
        return [];
    }

    private mapOperation(operationName: string, message: Message, bindings?: any): Operation {
        return {
            protocol: this.getProtocol(bindings),
            operation: operationName,
            message: message,
            bindings: bindings
        }
    }

    private getProtocol(bindings?: any): string {
        return Object.keys(bindings)[0];
    }

}
