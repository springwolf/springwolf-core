/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApi } from "../../models/asyncapi.model";
import { Server } from "../../models/server.model";
import {
  ChannelOperation,
  CHANNEL_ANCHOR_PREFIX,
} from "../../models/channel.model";
import { Operation, OperationType } from "src/app/models/operation.model";
import { Message } from "src/app/models/message.model";
import { Schema } from "../../models/schema.model";
import { Injectable } from "@angular/core";
import { Example } from "../../models/example.model";
import { Info } from "../../models/info.model";
import { ServerAsyncApi } from "./models/asyncapi.model";
import { ServerAsyncApiSchema } from "./models/schema.model";
import { ServerBinding, ServerBindings } from "./models/bindings.model";
import {
  ServerOperationAction,
  ServerOperationMessage,
  ServerOperations,
} from "./models/operations.model";
import { ServerServers } from "./models/servers.model";
import { ServerChannel, ServerChannels } from "./models/channels.model";
import { Binding, Bindings } from "src/app/models/bindings.model";
import { NotificationService } from "../notification.service";
import { ServerMessages } from "./models/components.model";

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
          item.operations,
          item.components.messages
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
      contact: {
        url: item.info.contact?.url,
        email: item.info.contact?.email && {
          name: item.info.contact?.email,
          href: "mailto:" + item.info.contact?.email,
        },
      },
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
    operations: ServerOperations,
    messages: ServerMessages
  ): ChannelOperation[] {
    const s = new Array<ChannelOperation>();
    for (let operationsKey in operations) {
      this.parsingErrorBoundary("operation " + operationsKey, () => {
        const operation = operations[operationsKey];
        const channelName = this.resolveRef(operation.channel.$ref);

        this.verifyBindings(operation.bindings, "operation " + operationsKey);

        const operationMessages: Message[] = this.mapServerAsyncApiMessages(
          channelName,
          channels[channelName],
          messages,
          operation.messages
        );
        operationMessages.forEach((message) => {
          const channelOperation = this.parsingErrorBoundary(
            "channel with name " + channelName,
            () =>
              this.mapChannelOperation(
                channelName,
                channels[channelName],
                message,
                operation.action,
                operation.bindings
              )
          );

          if (channelOperation != undefined) {
            s.push(channelOperation);
          }
        });
      });
    }
    return s;
  }

  private mapChannelOperation(
    channelName: string,
    channel: ServerChannel,
    message: Message,
    operationType: ServerOperationAction,
    operationBinding: ServerBindings
  ): ChannelOperation {
    this.verifyBindings(channel.bindings, "channel " + channelName);

    const operation = this.mapOperation(
      operationType,
      message,
      operationBinding
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
      bindings: channel.bindings,
    };
  }

  private mapServerAsyncApiMessages(
    channelName: string,
    channel: ServerChannel,
    messages: ServerMessages,
    operationMessages: ServerOperationMessage[]
  ): Message[] {
    return operationMessages
      .map((operationMessage) => {
        return this.parsingErrorBoundary(
          "message of channel " + channelName,
          () => {
            const messageKey = this.resolveRef(operationMessage.$ref);
            const channelMessage = channel.messages[messageKey];
            const channelMessageRef = this.resolveRef(channelMessage.$ref);
            const message = messages[channelMessageRef];

            this.verifyBindings(message.bindings, "message " + message.name);

            return {
              name: message.name,
              title: message.title,
              description: message.description,
              payload: {
                name: message.payload.schema.$ref,
                title: this.resolveRef(message.payload.schema.$ref),
                anchorUrl:
                  AsyncApiMapperService.BASE_URL +
                  this.resolveRef(message.payload.schema.$ref),
              },
              headers: {
                name: message.headers.$ref,
                title: message.headers.$ref?.split("/")?.pop(),
                anchorUrl:
                  AsyncApiMapperService.BASE_URL +
                  this.resolveRef(message.headers.$ref),
              },
              bindings: this.mapServerAsyncApiMessageBindings(message.bindings),
              rawBindings: message.bindings,
            };
          }
        );
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
    operationType: ServerOperationAction,
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

  private getProtocol(bindings?: {
    [protocol: string]: object;
  }): string | undefined {
    if (bindings !== undefined) {
      return Object.keys(bindings)[0];
    }
    return undefined;
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

  private verifyBindings(
    bindings: ServerBindings | undefined,
    identifier: string
  ) {
    if (bindings == undefined || Object.keys(bindings).length == 0) {
      this.notificationService.showWarning(
        "No binding defined for " + identifier
      );
    }
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
