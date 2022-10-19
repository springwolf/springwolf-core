import { AsyncApi } from './models/asyncapi.model';
import { Server } from './models/server.model';
import {Channel, CHANNEL_ANCHOR_PREFIX, Message, Operation, OperationType} from './models/channel.model';
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
  $ref?: string;
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

export type ServerAsyncApiChannelMessage = ServerAsyncApiMessage | { oneOf: ServerAsyncApiMessage[] };
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
                message: ServerAsyncApiChannelMessage;
                bindings?: any;
            };
            publish?: {
                message: ServerAsyncApiChannelMessage;
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
    static BASE_URL = window.location.pathname + window.location.search + "#";

    constructor() {
    }

    public toAsyncApi(item: ServerAsyncApi): AsyncApi {
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
        serverOperation: ServerAsyncApi["channels"][""]["subscribe"] | ServerAsyncApi["channels"][""]["publish"],
        operationType: OperationType): Channel[]
    {
        if(serverOperation !== undefined) {
            let messages: Message[] = this.mapMessages(serverOperation.message)

            return messages.map(message => {
                const operation = this.mapOperation(operationType, message, serverOperation.bindings)
                return {
                    name: topicName,
                    anchorIdentifier: CHANNEL_ANCHOR_PREFIX + [operation.protocol, topicName, operation.operation, operation.message.title].join( "-"),
                    description: description,
                    operation: operation,
                }
            })
        }
        return [];
    }

    private mapMessages(message: ServerAsyncApiChannelMessage): Message[] {
      if('oneOf' in message) {
        return this.mapServerAsyncApiMessages(message.oneOf)
      }
      return this.mapServerAsyncApiMessages([message]);
    }

    private mapServerAsyncApiMessages(messages: ServerAsyncApiMessage[]): Message[] {
      return messages.map((v) => {
        return {
          name: v.name,
          title: v.title,
          description: v.description,
          payload: {
            name: v.payload.$ref,
            anchorUrl: AsyncApiMapperService.BASE_URL  +v.payload.$ref?.split('/')?.pop()
          },
          headers: {
            name: v.headers.$ref,
            anchorUrl: AsyncApiMapperService.BASE_URL + v.headers.$ref?.split('/')?.pop()
          }
        }
      })
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
      Object.entries(schemas).forEach(([k, v]) => s.set(k, this.mapSchema(k, v)));
      return s;
    }

    private mapSchema(schemaName: string, schema: ServerAsyncApiSchema): Schema {
      const properties = schema.properties !== undefined ? this.mapSchemas(schema.properties) : undefined
      const example = schema.example !== undefined ? new Example(schema.example) : undefined
      return {
        name: schema.$ref,
        description: schema.description,
        anchorIdentifier: '#' + schemaName,
        anchorUrl: AsyncApiMapperService.BASE_URL + schema.$ref?.split('/')?.pop(),
        type: schema.type,
        format: schema.format,
        enum: schema.enum,
        properties: properties,
        required: schema.required,
        example: example,
      }
    }
}
