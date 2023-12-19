/* SPDX-License-Identifier: Apache-2.0 */
import {AsyncApi} from "../../models/asyncapi.model";
import {Server} from "../../models/server.model";
import {
  Channel,
  CHANNEL_ANCHOR_PREFIX,
  Message,
  MessageBinding,
  Operation,
  OperationType,
} from "../../models/channel.model";
import {Schema} from "../../models/schema.model";
import {Injectable} from "@angular/core";
import {Example} from "../../models/example.model";
import {Info} from "../../models/info.model";
import {ServerAsyncApi, ServerAsyncApiChannelMessage} from "./models/asyncapi.model";
import {ServerAsyncApiMessage} from "./models/message.model";
import {ServerAsyncApiSchema} from "./models/schema.model";
import {ServerBindings} from "./models/bindings.model";
import {ServerOperations} from "./models/operations.model";
import {ServerServers} from "./models/servers.model";
import {ServerChannel, ServerChannels} from "./models/channels.model";



@Injectable()
export class AsyncApiMapperService {
  static BASE_URL = window.location.pathname + window.location.search + "#";

  constructor() {}

  public toAsyncApi(item: ServerAsyncApi): AsyncApi {
    return {
      info: this.mapInfo(item),
      servers: this.mapServers(item.servers),
      channels: this.mapChannels(item.channels, item.operations),
      components: {
        schemas: this.mapSchemas(item.components.schemas),
      },
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

  private mapServers(servers: ServerServers): Map<string, Server> {
    const s = new Map<string, Server>();
    Object.entries(servers).forEach(([k, v]) => s.set(k, v));
    return s;
  }

  private mapChannels(channels: ServerChannels, operations: ServerOperations): Channel[] {
    const s = new Array<Channel>();
    for (let operationsKey in operations) {
      const operation = operations[operationsKey];
      const channelName = this.resolveRef(operation.channel.$ref)
      s.push(this.mapChannel(channelName, channels[channelName], operation.messages, operation.action));
    }
    return s;
  }

  private mapChannel(
    topicName: string,
    channel: ServerChannel,
    messages: ServerAsyncApiMessage[],
    operationType: ServerOperations["action"]
  ): Channel {
      const message: Message[] = this.mapServerAsyncApiMessages(messages);
// TODO: foreach for each message?

        const operation = this.mapOperation(
          operationType,
          message[0],
          channel.bindings
        );
        return {
          name: topicName,
          anchorIdentifier:
            CHANNEL_ANCHOR_PREFIX +
            [
              operation.protocol,
              topicName,
              operation.operation,
              operation.message.title,
            ].join("-"),
          description: channel.description,
          operation,
        };
  }

  private mapServerAsyncApiMessages(
    messages: ServerAsyncApiMessage[]
  ): Message[] {
    return messages.map((v) => {
      return {
        name: v.name,
        title: v.title,
        description: v.description,
        payload: {
          name: v.payload.$ref,
          title: this.resolveRef(v.payload.$ref),
          anchorUrl:
            AsyncApiMapperService.BASE_URL + this.resolveRef(v.payload.$ref),
        },
        headers: {
          name: v.headers.$ref,
          title: v.headers.$ref?.split("/")?.pop(),
          anchorUrl:
            AsyncApiMapperService.BASE_URL + this.resolveRef(v.headers.$ref),
        },
        bindings: this.mapServerAsyncApiMessageBindings(v.bindings),
        rawBindings: v.bindings,
      };
    });
  }

  private mapServerAsyncApiMessageBindings(serverMessageBindings?: {
    [protocol: string]: ServerBindings;
  }): Map<string, MessageBinding> {
    const messageBindings = new Map<string, MessageBinding>();
    if (serverMessageBindings !== undefined) {
      Object.keys(serverMessageBindings).forEach((protocol) => {
        messageBindings.set(
          protocol,
          this.mapServerAsyncApiMessageBinding(serverMessageBindings[protocol])
        );
      });
    }
    return messageBindings;
  }

  private mapServerAsyncApiMessageBinding(
    serverMessageBinding: ServerBindings
  ): MessageBinding {
    const messageBinding: MessageBinding = {};

    Object.keys(serverMessageBinding).forEach((key) => {
      const value = serverMessageBinding[key];
      if (typeof value === "object") {
        messageBinding[key] = this.mapSchema("MessageBinding", value);
      } else {
        messageBinding[key] = value;
      }
    });

    return messageBinding;
  }

  private mapOperation(
    operationType: ServerOperations["action"],
    message: Message,
    bindings?: { [protocol: string]: object }
  ): Operation {
    return {
      protocol: this.getProtocol(bindings),
      operation: operationType == "send" ? "subscribe" : "publish", // TODO
      message,
      bindings,
    };
  }

  private getProtocol(bindings?: { [protocol: string]: object }): string {
    return Object.keys(bindings)[0];
  }

  private mapSchemas(
    schemas: Map<string, ServerAsyncApiSchema>
  ): Map<string, Schema> {
    const s = new Map<string, Schema>();
    Object.entries(schemas).forEach(([k, v]) => s.set(k, this.mapSchema(k, v)));
    return s;
  }

  private mapSchema(schemaName: string, schema: ServerAsyncApiSchema): Schema {
    const anchorUrl = schema.$ref
      ? AsyncApiMapperService.BASE_URL + this.resolveRef(schema.$ref)
      : undefined;
    const properties =
      schema.properties !== undefined
        ? this.mapSchemas(schema.properties)
        : undefined;
    const items =
      schema.items !== undefined
        ? this.mapSchema(schema.$ref + "[]", schema.items)
        : undefined;
    const example =
      schema.example !== undefined ? new Example(schema.example) : undefined;
    return {
      name: schemaName,
      title: schemaName.split(".")?.pop(),
      description: schema.description,
      refName: schema.$ref,
      refTitle: this.resolveRef(schema.$ref),
      anchorIdentifier: "#" + schemaName,
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



  private resolveRef(ref: string) {
    return ref?.split("/")?.pop();
  }
}
