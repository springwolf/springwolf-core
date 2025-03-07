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
        item.components.schemas,
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
        defaultContentType: item.defaultContentType,
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
    schemas: ServerComponents["schemas"],
    servers: ServerServers,
    defaultContentType: string
  ): Channel[] {
    const mappedChannels: { [key: string]: Channel } = {};
    for (let channelId in channels) {
      this.parsingErrorBoundary("channel " + channelId, () => {
        const channel = channels[channelId];
        const anchorIdentifier =
          "channel-" + this.toSafeAnchorIdentifier(channelId);
        mappedChannels[channelId] = {
          name: channel.address,
          anchorIdentifier,
          anchorUrl: AsyncApiMapperService.BASE_URL + anchorIdentifier,
          operations: [],
          bindings: channel.bindings || {},
        };
      });
    }

    for (let operationsKey in operations) {
      this.parsingErrorBoundary("operation " + operationsKey, () => {
        const operation = operations[operationsKey];
        const channelId = this.resolveRefId(operation.channel.$ref);
        const channelName = channels[channelId].address;

        this.verifyBindings(operation.bindings, "operation " + operationsKey);

        const operationMessages: Message[] = this.mapServerAsyncApiMessages(
          channelName,
          channels[channelId],
          messages,
          schemas,
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
    const serverIds =
      channel?.servers?.map((server) => this.resolveRefId(server.$ref)) ||
      Object.keys(servers);
    const mappedOperationServers: OperationServer[] = serverIds.map(
      (serverId) => {
        return {
          name: serverId,
          anchorIdentifier: SERVER_ANCHOR_PREFIX + serverId,
          anchorUrl:
            AsyncApiMapperService.BASE_URL + SERVER_ANCHOR_PREFIX + serverId,
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
        this.toSafeAnchorIdentifier(channelName),
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
    schemas: ServerComponents["schemas"],
    operationMessages: ServerOperationMessage[],
    defaultContentType: string
  ): Message[] {
    return operationMessages
      .map((operationMessage) => {
        return this.parsingErrorBoundary(
          "message of channel " + channelName,
          () => {
            const messageId = this.resolveRefId(operationMessage.$ref);
            const channelMessage = channel.messages!![messageId];
            const channelMessageId = this.resolveRefId(channelMessage.$ref);
            const message = messages[channelMessageId];

            this.verifyBindings(message.bindings, "message " + message.name);

            const headerName = this.resolveRefId(message.headers.$ref);
            const mappedMessage: Message = {
              name: message.name,
              title: message.title,
              description: message.description,
              contentType: message.contentType || defaultContentType,
              payload: this.mapPayload(
                message.name,
                message.payload.schema,
                schemas
              ),
              headers: {
                ts_type: "ref",
                name: headerName,
                title: headerName,
                anchorUrl: AsyncApiMapperService.BASE_URL + headerName,
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

  private mapPayload(
    payloadName: string,
    schema: { $ref: string } | ServerAsyncApiSchema,
    schemas: ServerComponents["schemas"]
  ): Message["payload"] {
    if ("$ref" in schema) {
      const payloadName = this.resolveRefId(schema.$ref);
      return {
        ts_type: "ref",

        name: payloadName,
        title: this.resolveTitleFromName(payloadName),
        anchorUrl: AsyncApiMapperService.BASE_URL + payloadName,
      };
    }

    return this.mapSchemaObj(payloadName, schema, schemas);
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

    const refChannelId = this.resolveRefId(reply.channel.$ref);
    const refChannelName = channels[refChannelId].address;
    const refMessageId = this.resolveRefId(reply.messages[0].$ref);
    return {
      channelAnchorUrl:
        AsyncApiMapperService.BASE_URL + CHANNEL_ANCHOR_PREFIX + refChannelId,
      channelName: refChannelName,
      messageAnchorUrl: AsyncApiMapperService.BASE_URL + refMessageId,
      messageName: refMessageId,
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
      ts_type: "object",

      name: schemaName,
      title: this.resolveTitleFromName(schemaName) || "undefined-title",
      usedBy: [],
      anchorIdentifier: this.toSafeAnchorIdentifier(schemaName),
      anchorUrl: AsyncApiMapperService.BASE_URL + schemaName,
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
      const refId = this.resolveRefId(actualSchema.$ref);
      const refSchema = schemas[refId];
      if (refSchema !== undefined) {
        actualSchema = refSchema;
      } else {
        throw new Error("Schema " + refId + " not found");
      }
    }
    return actualSchema;
  }

  private mapSchemaRef(schemaName: string, schema: { $ref: string }): Schema {
    let schemaRefId = this.resolveRefId(schema.$ref);
    return {
      ts_type: "object",

      name: schemaName,
      title: this.resolveTitleFromName(schemaName),
      usedBy: [],
      anchorIdentifier: this.toSafeAnchorIdentifier(schemaName),
      anchorUrl: AsyncApiMapperService.BASE_URL + schemaName,

      // type == ref
      refAnchorUrl: AsyncApiMapperService.BASE_URL + schemaRefId,
      refName: schemaRefId,
      refTitle: this.resolveTitleFromName(schemaRefId),
    };
  }

  private resolveRefId(ref: string): string {
    return ref.split("/").pop()!!;
  }
  private resolveTitleFromName(name: string): string {
    return name.split(".").pop()!!;
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
          if (channelOperation.operation.message.payload.name === schema.name) {
            schema.usedBy.push({
              name: channelOperation.name,
              anchorUrl: channelOperation.anchorUrl!!,
              type: "channel",
            });
          }
          if (channelOperation.operation.message.headers.name === schema.name) {
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
          if (property.refName === schema.name) {
            schema.usedBy.push({
              name: otherSchema.title,
              anchorUrl: otherSchema.anchorUrl,
              type: "schema",
            });
          }
        });
        if (otherSchema.items?.refName === schema.name) {
          schema.usedBy.push({
            name: otherSchema.title,
            anchorUrl: otherSchema.anchorUrl,
            type: "schema",
          });
        }
      });
    });
  }

  private toSafeAnchorIdentifier(name: string): string {
    return name.replaceAll(/\$/g, "§");
  }

  private parsingErrorBoundary<T>(path: string, f: () => T): T | undefined {
    return catchException(f, (e) => {
      this.notificationService.showError(
        "Error parsing AsyncAPI " + path + ": " + e.message
      );
    });
  }
}
