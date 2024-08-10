/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApi } from "../../models/asyncapi.model";
import { Server, SERVER_ANCHOR_PREFIX } from "../../models/server.model";
import {
  Channel,
  CHANNEL_ANCHOR_PREFIX,
  ChannelOperation,
} from "../../models/channel.model";
import { Schema } from "../../models/schema.model";
import { Injectable } from "@angular/core";
import { Example } from "../../models/example.model";
import { Info } from "../../models/info.model";
import { ServerAsyncApi } from "./models/asyncapi.model";
import {
  ServerAsyncApiSchema,
  ServerAsyncApiSchemaOrRef,
} from "./models/schema.model";
import { ServerBinding, ServerBindings } from "./models/bindings.model";
import {
  ServerOperation,
  ServerOperationMessage,
  ServerOperationReply,
  ServerOperations,
} from "./models/operations.model";
import { ServerServers } from "./models/servers.model";
import { ServerChannel, ServerChannels } from "./models/channels.model";
import { INotificationService } from "../notification.service";
import { ServerComponents } from "./models/components.model";
import { Binding, Bindings } from "../../models/bindings.model";
import { Message } from "../../models/message.model";
import {
  Operation,
  OperationReply,
  OperationServer,
} from "../../models/operation.model";
import { catchException } from "../../util/error-boundary";

@Injectable()
export class AsyncApiMapperService {
  static BASE_URL = window.location.pathname + window.location.search + "#";

  constructor(private notificationService: INotificationService) {}

  public toAsyncApi(item: ServerAsyncApi): AsyncApi | undefined {
    try {
      const channels = this.mapChannels(
        item.channels,
        item.operations,
        item.components.messages,
        item.servers,
        item.defaultContentType
      );
      const asyncApi = {
        info: this.mapInfo(item),
        servers: this.mapServers(item.servers),
        channels: channels,
        channelOperations: channels.flatMap((c) => c.operations),
        components: {
          schemas: this.mapSchemas(item.components.schemas),
        },
      };
      this.postProcess(asyncApi);
      return asyncApi;
    } catch (e: any) {
      this.notificationService.showError(
        "Error parsing AsyncAPI: " + e?.message
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
        email:
          (item.info.contact?.email && {
            name: item.info.contact.email,
            href: "mailto:" + item.info.contact.email,
          }) ||
          undefined,
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
    Object.entries(servers).forEach(([k, v]) => {
      const server: Server = {
        host: v.host,
        protocol: v.protocol,
        anchorIdentifier: SERVER_ANCHOR_PREFIX + k,
        anchorUrl: AsyncApiMapperService.BASE_URL + SERVER_ANCHOR_PREFIX + k,
      };
      s.set(k, server);
    });
    return s;
  }

  private mapChannels(
    channels: ServerChannels,
    operations: ServerOperations,
    messages: ServerComponents["messages"],
    servers: ServerServers,
    defaultContentType: string
  ): Channel[] {
    const mappedChannels: { [key: string]: Channel } = {};
    for (let channelId in channels) {
      this.parsingErrorBoundary("channel " + channelId, () => {
        const channel = channels[channelId];
        mappedChannels[channelId] = {
          name: channel.address,
          anchorIdentifier: "channel-" + channelId,
          operations: [],
          bindings: channel.bindings || {},
        };
      });
    }

    for (let operationsKey in operations) {
      this.parsingErrorBoundary("operation " + operationsKey, () => {
        const operation = operations[operationsKey];
        const channelId = this.resolveRef(operation.channel.$ref);
        const channelName = channels[channelId].address;

        this.verifyBindings(operation.bindings, "operation " + operationsKey);

        const operationMessages: Message[] = this.mapServerAsyncApiMessages(
          channelName,
          channels[channelId],
          messages,
          operation.messages,
          defaultContentType
        );
        operationMessages.forEach((message) => {
          const channelOperation = this.parsingErrorBoundary(
            "channel with name " + channelName,
            () =>
              this.mapChannelOperation(
                channelName,
                channels[channelId],
                channels,
                operation,
                message,
                servers
              )
          );

          if (channelOperation != undefined) {
            mappedChannels[channelId].operations.push(channelOperation);
          }
        });
      });
    }

    Object.values(mappedChannels).forEach((channel) => {
      channel.operations = channel.operations.sort((a, b) => {
        if (a.operation.protocol === b.operation.protocol) {
          if (a.operation.operationType === b.operation.operationType) {
            if (a.name === b.name) {
              return a.operation.message.name.localeCompare(
                b.operation.message.name
              );
            } else {
              return a.name.localeCompare(b.name);
            }
          } else {
            return a.operation.operationType.localeCompare(
              b.operation.operationType
            );
          }
        } else if (
          a.operation.protocol != null &&
          b.operation.protocol != null
        ) {
          return a.operation.protocol.localeCompare(b.operation.protocol);
        } else {
          return 0;
        }
      });
    });

    return Object.values(mappedChannels).sort((a, b) =>
      a.name.localeCompare(b.name)
    );
  }
  private mapChannelOperation(
    channelName: string,
    channel: ServerChannel,
    channels: ServerChannels,
    operation: ServerOperation,
    message: Message,
    servers: ServerServers
  ): ChannelOperation {
    const mappedServers =
      channel?.servers?.map((server) => this.resolveRef(server.$ref)) ||
      Object.keys(servers);
    const mappedOperationServers: OperationServer[] = mappedServers.map(
      (serverKey) => {
        return {
          name: serverKey,
          anchorIdentifier: SERVER_ANCHOR_PREFIX + serverKey,
          anchorUrl:
            AsyncApiMapperService.BASE_URL + SERVER_ANCHOR_PREFIX + serverKey,
        };
      }
    );

    const mappedOperation = this.mapOperation(
      channelName,
      channels,
      operation.action,
      mappedOperationServers,
      message,
      operation.bindings,
      operation.description,
      operation.reply
    );

    const anchorIdentifier =
      CHANNEL_ANCHOR_PREFIX +
      [
        mappedOperation.protocol,
        channelName,
        mappedOperation.operationType,
        mappedOperation.message.title,
      ].join("-");
    return {
      name: channelName,
      anchorIdentifier: anchorIdentifier,
      anchorUrl: AsyncApiMapperService.BASE_URL + anchorIdentifier,
      description: channel.description,
      operation: mappedOperation,
      bindings: channel.bindings || {},
    };
  }

  private mapServerAsyncApiMessages(
    channelName: string,
    channel: ServerChannel,
    messages: ServerComponents["messages"],
    operationMessages: ServerOperationMessage[],
    defaultContentType: string
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

            const mappedMessage: Message = {
              name: message.name,
              title: message.title,
              description: message.description,
              contentType: message.contentType || defaultContentType,
              payload: {
                name: message.payload.schema.$ref,
                title: this.resolveRef(message.payload.schema.$ref),
                type: this.resolveRef(message.payload.schema.$ref),
                anchorUrl:
                  AsyncApiMapperService.BASE_URL +
                  this.resolveRef(message.payload.schema.$ref),
              },
              headers: {
                name: message.headers.$ref,
                title:
                  message.headers.$ref?.split("/")?.pop() ||
                  "undefined-header-title",
                anchorUrl:
                  AsyncApiMapperService.BASE_URL +
                  this.resolveRef(message.headers.$ref),
              },
              bindings: this.mapServerAsyncApiMessageBindings(message.bindings),
              rawBindings: message.bindings || {},
            };
            return mappedMessage;
          }
        );
      })
      .filter((el) => el !== undefined);
  }

  private mapServerAsyncApiMessageBindings(
    serverMessageBindings: ServerBindings | undefined
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
    channelName: string,
    channels: ServerChannels,
    operationType: ServerOperation["action"],
    servers: OperationServer[],
    message: Message,
    bindings: Bindings | undefined,
    description: string | undefined,
    reply: ServerOperationReply | undefined
  ): Operation {
    return {
      protocol: this.getProtocol(bindings) || "unsupported-protocol",
      bindings: bindings || {},
      servers: servers,
      operationType: operationType == "send" ? "send" : "receive",
      channelName: channelName,
      description,
      message,
      reply: this.mapOperationReply(reply, channels),
    };
  }

  private mapOperationReply(
    reply: ServerOperationReply | undefined,
    channels: ServerChannels
  ): OperationReply | undefined {
    if (!reply) {
      return undefined;
    }

    const refChannelId = this.resolveRef(reply.channel.$ref);
    const refChannelName = channels[refChannelId].address;
    return {
      channelAnchorUrl:
        AsyncApiMapperService.BASE_URL +
        CHANNEL_ANCHOR_PREFIX +
        this.resolveRef(reply.channel.$ref),
      channelName: refChannelName,
      messageAnchorUrl:
        AsyncApiMapperService.BASE_URL +
        this.resolveRef(reply.messages[0].$ref),
      messageName: this.resolveRef(reply.messages[0].$ref),
    };
  }

  private getProtocol(
    bindings:
      | {
          [protocol: string]: object;
        }
      | undefined
  ): string | undefined {
    if (bindings !== undefined) {
      return Object.keys(bindings)[0];
    }
    return undefined;
  }

  private mapSchemas(
    schemas: ServerComponents["schemas"]
  ): Map<string, Schema> {
    const mappedSchemas = new Map<string, Schema>();
    Object.entries(schemas).forEach(([schemaName, schema]) => {
      const mappedSchema = this.parsingErrorBoundary(
        "schema with name " + schemaName,
        () => this.mapSchema(schemaName, schema, schemas)
      );

      if (mappedSchema != undefined) {
        mappedSchemas.set(schemaName, mappedSchema);
      }
    });
    return new Map(
      [...mappedSchemas.entries()].sort((a, b) =>
        a[1].title.localeCompare(b[1].title)
      )
    );
  }

  private mapSchema(
    schemaName: string,
    schema: ServerAsyncApiSchemaOrRef,
    schemas: ServerComponents["schemas"]
  ): Schema {
    if ("$ref" in schema) {
      return this.mapSchemaRef(schemaName, schema);
    } else {
      return this.mapSchemaObj(schemaName, schema, schemas);
    }
  }

  private mapSchemaObj(
    schemaName: string,
    schema: ServerAsyncApiSchema,
    schemas: ServerComponents["schemas"]
  ): Schema {
    const properties = {};
    this.addPropertiesToSchema(schema, properties, schemas);
    if (schema.allOf !== undefined) {
      schema.allOf.forEach((schema) => {
        this.addPropertiesToSchema(schema, properties, schemas);
      });
    }
    if (schema.anyOf !== undefined && schema.anyOf.length > 0) {
      this.addPropertiesToSchema(schema.anyOf[0], properties, schemas);
    }
    if (schema.oneOf !== undefined && schema.oneOf.length > 0) {
      this.addPropertiesToSchema(schema.oneOf[0], properties, schemas);
    }

    const items =
      schema.items !== undefined
        ? this.mapSchema(schemaName + "[]", schema.items, schemas)
        : undefined;
    const example =
      schema.examples !== undefined && 0 < schema.examples.length
        ? new Example(schema.examples[0])
        : undefined;

    return {
      name: schemaName,
      title: schemaName.split(".")?.pop() || "undefined-title",
      usedBy: [],
      anchorIdentifier: schemaName,
      description: schema.description,
      deprecated: schema.deprecated,

      enum: schema.enum,
      example,

      type: schema.type,
      format: schema.format,
      // type == object
      properties,
      required: schema.required,
      // type == array
      items,
      minItems: schema.minItems,
      maxItems: schema.maxItems,
      uniqueItems: schema.uniqueItems,
      // type == string
      minLength: schema.minLength,
      maxLength: schema.maxLength,
      pattern: schema.pattern,
      // type == number
      minimum: schema.exclusiveMinimum
        ? schema.exclusiveMinimum
        : schema.minimum,
      maximum: schema.exclusiveMaximum
        ? schema.exclusiveMinimum
        : schema.maximum,
      exclusiveMinimum: schema.minimum == schema.exclusiveMinimum,
      exclusiveMaximum: schema.maximum == schema.exclusiveMaximum,
      multipleOf: schema.multipleOf,
    };
  }

  private addPropertiesToSchema(
    schema: ServerAsyncApiSchemaOrRef,
    properties: { [key: string]: any },
    schemas: ServerComponents["schemas"]
  ) {
    let actualSchema = this.resolveSchema(schema, schemas);

    if ("properties" in actualSchema && actualSchema.properties !== undefined) {
      Object.entries(actualSchema.properties).forEach(([key, value]) => {
        properties[key] = this.mapSchema(key, value, schemas);
      });
    }
  }

  private resolveSchema(
    schema: ServerAsyncApiSchemaOrRef,
    schemas: ServerComponents["schemas"]
  ): ServerAsyncApiSchema {
    let actualSchema = schema;

    while ("$ref" in actualSchema) {
      const refName = this.resolveRef(actualSchema.$ref);
      const refSchema = schemas[refName];
      if (refSchema !== undefined) {
        actualSchema = refSchema;
      } else {
        throw new Error("Schema " + refName + " not found");
      }
    }
    return actualSchema;
  }

  private mapSchemaRef(schemaName: string, schema: { $ref: string }): Schema {
    return {
      name: schemaName,
      title: schemaName.split(".").pop()!!,
      usedBy: [],
      anchorIdentifier: schemaName,

      // type == ref
      anchorUrl: AsyncApiMapperService.BASE_URL + this.resolveRef(schema.$ref),
      refName: schema.$ref,
      refTitle: this.resolveRef(schema.$ref),
    };
  }

  private resolveRef(ref: string): string {
    return ref.split("/").pop()!!;
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

  private postProcess(asyncApi: AsyncApi) {
    asyncApi.components.schemas.forEach((schema) => {
      asyncApi.channels.forEach((channel) => {
        channel.operations.forEach((channelOperation) => {
          if (
            channelOperation.operation.message.payload.title === schema.name
          ) {
            schema.usedBy.push({
              name: channelOperation.name,
              anchorUrl: channelOperation.anchorUrl!!,
              type: "channel",
            });
          }
        });
      });

      asyncApi.components.schemas.forEach((otherSchema) => {
        Object.values(otherSchema?.properties || {}).forEach((property) => {
          if (property.refTitle === schema.name) {
            schema.usedBy.push({
              name: otherSchema.title,
              anchorUrl: otherSchema.anchorUrl!!,
              type: "schema",
            });
          }
        });
        if (otherSchema.items?.refTitle === schema.name) {
          schema.usedBy.push({
            name: otherSchema.title,
            anchorUrl: otherSchema.anchorUrl!!,
            type: "schema",
          });
        }
      });
    });
  }

  private parsingErrorBoundary<T>(path: string, f: () => T): T | undefined {
    return catchException(f, (e) => {
      this.notificationService.showError(
        "Error parsing AsyncAPI " + path + ": " + e.message
      );
    });
  }
}
