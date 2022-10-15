import { AsyncApi } from './models/asyncapi.model';
import { Server } from './models/server.model';
import {Channel, Message, Operation, OperationType} from './models/channel.model';
import { Schema } from './models/schema.model';
import { Injectable } from '@angular/core';
import {Example} from "./models/example.model";

interface ServerAsyncApiSchema {
  description?: string;
  type: string;
  format: string;
  enum: string[];
  properties?: Map<string, ServerAsyncApiSchema>;
  example?: {
    [key: string]: object;
  };
  required?: string[];
}

interface ServerAsyncApiMessage {
  name: string;
  title: string;
  description?: string;
  payload: { $ref: string };
  headers: { $ref: string };
}

interface ServerAsyncApiInfo {
  title: string;
  version: string;
  description?: string;
}

export interface ServerAsyncApi {
    asyncapi: string;
    info: ServerAsyncApiInfo;
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
                message: ServerAsyncApiMessage | { oneOf: ServerAsyncApiMessage[] };
                bindings?: any;
            };
            publish?: {
                message: ServerAsyncApiMessage | { oneOf: ServerAsyncApiMessage[] };
                bindings?: any;
            };
        };
    };
    components: {
        schemas: Map<string, ServerAsyncApiSchema>;
    };
}

@Injectable()
export class AsyncApiMapperService {

    constructor() {
    }

    public toAsyncApiMap(serverAsyncApi: Map<string, ServerAsyncApi>): Map<string, AsyncApi> {
        const docs = new Map<string, AsyncApi>();
        Object.entries(serverAsyncApi).forEach(([docName, doc]) => docs.set(docName, this.toAsyncApi(doc)));
        return docs;
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
        operationType: OperationType): Channel[]
    {
        if(operation !== undefined) {
            let messages: Message[] = 'oneOf' in operation.message ? operation.message.oneOf : [operation.message];

            return messages.map(message => {
                return {
                    name: topicName,
                    description: description,
                    operation: this.mapOperation(operationType, message, operation.bindings)
                }
            })
        }
        return [];
    }

    private mapOperation(operationType: OperationType, message: Message, bindings?: any): Operation {
        return {
            protocol: this.getProtocol(bindings),
            operation: operationType,
            message: message,
            bindings: bindings
        }
    }

    private getProtocol(bindings?: any): string {
        return Object.keys(bindings)[0];
    }

    private mapSchemas(schemas: Map<string, ServerAsyncApiSchema>): Map<string, Schema> {
      const s = new Map<string, Schema>();
      Object.entries(schemas).forEach(([k, v]) => s.set(k, this.mapSchema(v)));
      return s;
    }

    private mapSchema(schema: ServerAsyncApiSchema): Schema {
      const properties = schema.properties !== undefined ? this.mapSchemas(schema.properties) : undefined
      const example = schema.example !== undefined ? new Example(schema.example) : undefined
      return {
        description: schema.description,
        type: schema.type,
        format: schema.format,
        enum: schema.enum,
        properties: properties,
        required: schema.required,
        example: example,
      }
    }
}
