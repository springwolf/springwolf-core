import {AsyncApi} from './models/asyncapi.model';
import {Server} from './models/server.model';
import {Channel, CHANNEL_ANCHOR_PREFIX, Message, MessageBinding, Operation, OperationType} from './models/channel.model';
import {Schema} from './models/schema.model';
import {Injectable} from '@angular/core';
import {Example} from './models/example.model';
import {Info} from './models/info.model';

interface ServerAsyncApiSchema {
  description?: string;
  type: string;
  format: string;
  enum: string[];
  properties?: Map<string, ServerAsyncApiSchema>;
  items?: ServerAsyncApiSchema,
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
  bindings: {[protocol: string]: ServerAsyncApiMessageBinding};
}
interface ServerAsyncApiMessageBinding {
  [protocol: string]: ServerAsyncApiSchema | string;
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
        [server: string]: {
            url: string;
            protocol: string;
        };
    };
    channels: {
        [key: string]: {
            description?: string;
            subscribe?: {
                message: ServerAsyncApiChannelMessage;
                bindings?: {[protocol: string]: object};
            };
            publish?: {
                message: ServerAsyncApiChannelMessage;
                bindings?: {[protocol: string]: object};
            };
        };
    };
    components: {
        schemas: Map<string, ServerAsyncApiSchema>;
    };
}

@Injectable()
export class AsyncApiMapperService {
    static BASE_URL = window.location.pathname + window.location.search + '#';

    constructor() {
    }

    public toAsyncApi(item: ServerAsyncApi): AsyncApi {
        return {
            info: this.mapInfo(item),
            servers: this.mapServers(item.servers),
            channels: this.mapChannels(item.channels),
            components: {
                schemas: this.mapSchemas(item.components.schemas)
            }
        };
    }

    private mapInfo(item: ServerAsyncApi): Info {
      return {
        title: item.info.title,
        version: item.info.version,
        description: item.info.description,
        asyncApiJson: item,
      };
    }

    private mapServers(servers: ServerAsyncApi['servers']): Map<string, Server> {
        const s = new Map<string, Server>();
        Object.entries(servers).forEach(([k, v]) => s.set(k, v));
        return s;
    }

    private mapChannels(channels: ServerAsyncApi['channels']): Channel[] {
        const s = new Array<Channel>();
        Object.entries(channels).forEach(([k, v]) => {
            const subscriberChannels = this.mapChannel(k, v.description, v.subscribe, 'subscribe');
            subscriberChannels.forEach(channel => s.push(channel));

            const publisherChannels = this.mapChannel(k, v.description, v.publish, 'publish');
            publisherChannels.forEach(channel => s.push(channel));
        });
        return s;
    }

    private mapChannel(
        topicName: string,
        description: ServerAsyncApi['channels']['']['description'],
        serverOperation: ServerAsyncApi['channels']['']['subscribe'] | ServerAsyncApi['channels']['']['publish'],
        operationType: OperationType
    ): Channel[]
    {
        if (serverOperation !== undefined) {
            const messages: Message[] = this.mapMessages(serverOperation.message);

            return messages.map(message => {
                const operation = this.mapOperation(operationType, message, serverOperation.bindings);
                return {
                    name: topicName,
                    anchorIdentifier: CHANNEL_ANCHOR_PREFIX + [
                      operation.protocol, topicName, operation.operation, operation.message.title
                    ].join( '-'),
                    description,
                    operation,
                };
            });
        }
        return [];
    }

    private mapMessages(message: ServerAsyncApiChannelMessage): Message[] {
      if ('oneOf' in message) {
        return this.mapServerAsyncApiMessages(message.oneOf);
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
            anchorUrl: AsyncApiMapperService.BASE_URL  + v.payload.$ref?.split('/')?.pop()
          },
          headers: {
            name: v.headers.$ref,
            anchorUrl: AsyncApiMapperService.BASE_URL + v.headers.$ref?.split('/')?.pop()
          },
          bindings: this.mapServerAsyncApiMessageBindings(v.bindings),
          rawBindings: v.bindings,
        };
      });
    }

  private mapServerAsyncApiMessageBindings(
    serverMessageBindings?: { [protocol: string]: ServerAsyncApiMessageBinding }
  ): Map<string, MessageBinding> {
      const messageBindings = new Map<string, MessageBinding>();
      if (serverMessageBindings !== undefined) {
        Object.keys(serverMessageBindings).forEach((protocol) => {
          messageBindings.set(protocol, this.mapServerAsyncApiMessageBinding(serverMessageBindings[protocol]));
        });
      }
      return messageBindings;
  }

  private mapServerAsyncApiMessageBinding(serverMessageBinding: ServerAsyncApiMessageBinding): MessageBinding {
      const messageBinding: MessageBinding = {};

      Object.keys(serverMessageBinding).forEach((key) => {
        const value = serverMessageBinding[key];
        if (typeof value === 'object') {
          messageBinding[key] = this.mapSchema('MessageBinding', value);
        } else {
          messageBinding[key] = value;
        }
      });

      return messageBinding;
  }


  private mapOperation(operationType: OperationType, message: Message, bindings?: {[protocol: string]: object}): Operation {
        return {
            protocol: this.getProtocol(bindings),
            operation: operationType,
            message,
            bindings
        };
    }

    private getProtocol(bindings?: {[protocol: string]: object}): string {
        return Object.keys(bindings)[0];
    }

    private mapSchemas(schemas: Map<string, ServerAsyncApiSchema>): Map<string, Schema> {
      const s = new Map<string, Schema>();
      Object.entries(schemas).forEach(([k, v]) => s.set(k, this.mapSchema(k, v)));
      return s;
    }

    private mapSchema(schemaName: string, schema: ServerAsyncApiSchema): Schema {
      const anchorUrl = schema.$ref ? AsyncApiMapperService.BASE_URL + schema.$ref?.split('/')?.pop() : undefined
      const properties = schema.properties !== undefined ? this.mapSchemas(schema.properties) : undefined
      const items = schema.items !== undefined ? this.mapSchema(schema.$ref+"[]", schema.items) : undefined;
      const example = schema.example !== undefined ? new Example(schema.example) : undefined
      return {
        name: schema.$ref,
        description: schema.description,
        anchorIdentifier: '#' + schemaName,
        anchorUrl: anchorUrl,
        type: schema.type,
        items,
        format: schema.format,
        enum: schema.enum,
        properties,
        required: schema.required,
        example,
      };
    }
}
