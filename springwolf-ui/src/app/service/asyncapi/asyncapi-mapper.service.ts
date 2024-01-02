/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApi } from "../../models/asyncapi.model";
import { Server } from "../../models/server.model";
import {
  ChannelOperation,
  CHANNEL_ANCHOR_PREFIX,
  Message,
  Operation,
  OperationType,
} from "../../models/channel.model";
import { Schema } from "../../models/schema.model";
import { Injectable } from "@angular/core";
import { Example } from "../../models/example.model";
import { Info } from "../../models/info.model";
import {
  ServerAsyncApi,
  ServerAsyncApiChannelMessage,
} from "./models/asyncapi.model";
import { ServerAsyncApiMessage } from "./models/message.model";
import { ServerAsyncApiSchema } from "./models/schema.model";
import { ServerBinding, ServerBindings } from "./models/bindings.model";
import { ServerOperations } from "./models/operations.model";
import { ServerServers } from "./models/servers.model";
import { ServerChannel, ServerChannels } from "./models/channels.model";
import { Binding, Bindings } from "src/app/models/bindings.model";
import { NotificationService } from "../notification.service";

@Injectable()
export class AsyncApiMapperService {
  static BASE_URL = window.location.pathname + window.location.search + "#";

  constructor(private notificationService: NotificationService) {}

  public toAsyncApi(item: ServerAsyncApi): AsyncApi {
    try {
      return {
        info: this.mapInfo(item),
        servers: this.mapServers(item.servers),
        channelOperations: this.mapChannelOperations(
          item.channels,
          item.operations
        ),
        components: {
          schemas: this.mapSchemas(item.components.schemas),
        },
      };
    } catch (e) {
      this.notificationService.showError(
        "Error parsing AsyncAPI: " + e.message
      );
      return undefined;
    }
  }

  private mapInfo(item: ServerAsyncApi): Info {
    return {
      title: item.info.title,
      version: item.info.version,
      description: item.info.description,
      license: {
        name: item.info.license?.name,
        url: item.info.license?.url,
      },
      asyncApiJson: item,
    };
  }

  private mapServers(servers: ServerServers): Map<string, Server> {
    const s = new Map<string, Server>();
    Object.entries(servers).forEach(([k, v]) => s.set(k, v));
    return s;
  }

  private mapChannelOperations(
    channels: ServerChannels,
    operations: ServerOperations
  ): ChannelOperation[] {
    const s = new Array<ChannelOperation>();
    for (let operationsKey in operations) {
      const operation = operations[operationsKey];
      const channelName = this.resolveRef(operation.channel.$ref);

      const messages: Message[] = this.mapServerAsyncApiMessages(
        operation.messages
      );
      messages.forEach((message) => {
        const channelOperation = this.parsingErrorBoundary(
          "channel with name " + channelName,
          () =>
            this.mapChannel(
              channelName,
              channels[channelName],
              message,
              operation.action
            )
        );

        if (channelOperation != undefined) {
          s.push(channelOperation);
        }
      });
    }
    return s;
  }

  private mapChannel(
    channelName: string,
    channel: ServerChannel,
    message: Message,
    operationType: ServerOperations["action"]
  ): ChannelOperation {
    if (
      channel.bindings == undefined ||
      Object.keys(channel.bindings).length == 0
    ) {
      this.notificationService.showWarning(
        "No binding defined for channel " + channelName
      );
    }

    const operation = this.mapOperation(
      operationType,
      message,
      channel.bindings
    );

    return {
      name: channelName,
      anchorIdentifier:
        CHANNEL_ANCHOR_PREFIX +
        [
          operation.protocol,
          channelName,
          operation.operationType,
          operation.message.title,
        ].join("-"),
      description: channel.description,
      operation,
    };
  }

  private mapServerAsyncApiMessages(
    messages: ServerAsyncApiMessage[]
  ): Message[] {
    return messages
      .map((v) => {
        const message = this.parsingErrorBoundary(
          "message with name " + v.name,
          () => {
            return {
              name: v.name,
              title: v.title,
              description: v.description,
              payload: {
                name: v.payload.$ref,
                title: this.resolveRef(v.payload.$ref),
                anchorUrl:
                  AsyncApiMapperService.BASE_URL +
                  this.resolveRef(v.payload.$ref),
              },
              headers: {
                name: v.headers.$ref,
                title: v.headers.$ref?.split("/")?.pop(),
                anchorUrl:
                  AsyncApiMapperService.BASE_URL +
                  this.resolveRef(v.headers.$ref),
              },
              bindings: this.mapServerAsyncApiMessageBindings(v.bindings),
              rawBindings: v.bindings,
            };
          }
        );

        return message;
      })
      .filter((el) => el != undefined);
  }

  private mapServerAsyncApiMessageBindings(
    serverMessageBindings?: ServerBindings
  ): Map<string, Binding> {
    const messageBindings = new Map<string, Binding>();
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
    serverMessageBinding: ServerBinding
  ): Binding {
    const messageBinding: Binding = {};

    Object.keys(serverMessageBinding).forEach((key) => {
      const value = serverMessageBinding[key];
      if (typeof value === "object") {
        messageBinding[key] = this.mapServerAsyncApiMessageBinding(value);
      } else {
        messageBinding[key] = value;
      }
    });

    return messageBinding;
  }

  private mapOperation(
    operationType: ServerOperations["action"],
    message: Message,
    bindings?: Bindings
  ): Operation {
    return {
      protocol: this.getProtocol(bindings),
      operationType: operationType,
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
    Object.entries(schemas).forEach(([k, v]) => {
      const schema = this.parsingErrorBoundary("schema with name " + k, () =>
        this.mapSchema(k, v)
      );

      if (schema != undefined) {
        s.set(k, schema);
      }
    });
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
      minimum: schema.minimum,
      maximum: schema.maximum,
    };
  }

  private resolveRef(ref: string) {
    return ref?.split("/")?.pop();
  }

  private parsingErrorBoundary<T>(path: string, f: () => T): T | undefined {
    try {
      return f();
    } catch (e) {
      this.notificationService.showError(
        "Error parsing AsyncAPI " + path + ": " + e.message
      );
      return undefined;
    }
  }
}
