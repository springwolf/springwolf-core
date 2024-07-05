/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApi } from "../../models/asyncapi.model";
import { Server } from "../../models/server.model";
import {
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
  ServerOperations,
} from "./models/operations.model";
import { ServerServers } from "./models/servers.model";
import { ServerChannel, ServerChannels } from "./models/channels.model";
import { INotificationService } from "../notification.service";
import { ServerComponents } from "./models/components.model";
import { Binding, Bindings } from "../../models/bindings.model";
import { Message } from "../../models/message.model";
import { Operation } from "../../models/operation.model";
import { catchException } from "../../util/error-boundary";

@Injectable()
export class AsyncApiMapperService {
  static BASE_URL = window.location.pathname + window.location.search + "#";

  constructor(private notificationService: INotificationService) {}

  public toAsyncApi(item: ServerAsyncApi): AsyncApi | undefined {
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
            name: item.info.contact?.email,
            href: "mailto:" + item.info.contact?.email,
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
    Object.entries(servers).forEach(([k, v]) => s.set(k, v));
    return s;
  }

  private mapChannelOperations(
    channels: ServerChannels,
    operations: ServerOperations,
    messages: ServerComponents["messages"]
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
                operation.bindings,
                operation.description
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
    operationType: ServerOperation["action"],
    operationBinding: ServerBindings | undefined,
    description?: string
  ): ChannelOperation {
    const operation = this.mapOperation(
      operationType,
      message,
      operationBinding,
      description
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
      bindings: channel.bindings || {},
    };
  }

  private mapServerAsyncApiMessages(
    channelName: string,
    channel: ServerChannel,
    messages: ServerComponents["messages"],
    operationMessages: ServerOperationMessage[]
  ): Message[] {
    const result = operationMessages
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
    return result as Message[];
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
    operationType: ServerOperation["action"],
    message: Message,
    bindings?: Bindings,
    description?: string
  ): Operation {
    return {
      protocol: this.getProtocol(bindings) || "unsupported-protocol",
      operationType: operationType == "send" ? "send" : "receive",
      description,
      message,
      bindings: bindings || {},
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
    return mappedSchemas;
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
      schema.allOf.forEach((schema, index) => {
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
      description: schema.description,
      anchorIdentifier: schemaName,

      type: schema.type,
      required: schema.required,
      format: schema.format,

      properties,
      items,
      enum: schema.enum,

      example,

      minimum: schema.exclusiveMinimum
        ? schema.exclusiveMinimum
        : schema.minimum,
      maximum: schema.exclusiveMaximum
        ? schema.exclusiveMinimum
        : schema.maximum,
      exclusiveMinimum:
        schema.minimum == schema.exclusiveMinimum ? true : false,
      exclusiveMaximum:
        schema.maximum == schema.exclusiveMaximum ? true : false,
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
      title: schemaName.split(".")?.pop() as string,

      refName: schema.$ref,
      refTitle: this.resolveRef(schema.$ref),

      anchorIdentifier: schemaName,
      anchorUrl: AsyncApiMapperService.BASE_URL + this.resolveRef(schema.$ref),
    };
  }

  private resolveRef(ref: string) {
    return ref?.split("/")?.pop()!!;
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
    return catchException(f, (e) => {
      this.notificationService.showError(
        "Error parsing AsyncAPI " + path + ": " + e.message
      );
    });
  }
}
