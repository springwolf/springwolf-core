/* SPDX-License-Identifier: Apache-2.0 */
import { Component, Input, OnInit } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { STATUS } from "angular-in-memory-web-api";
import { Binding } from "../../../../models/bindings.model";
import { Example } from "../../../../models/example.model";
import { Operation } from "../../../../models/operation.model";
import { Schema } from "../../../../models/schema.model";
import { AsyncApiService } from "../../../../service/asyncapi/asyncapi.service";
import { PublisherService } from "../../../../service/publisher.service";
import { wrapException } from "../../../../util/error-boundary";
import { Subscription } from "rxjs";

@Component({
  selector: "app-channel-main-new",
  templateUrl: "./channel-main.component.html",
  styleUrls: ["./channel-main.component.css"],
})
export class ChannelMainComponent implements OnInit {
  @Input() channelName: string;
  @Input() operation: Operation;

  schema: Schema;
  schemaIdentifier: string;
  defaultExample: Example;
  defaultExampleType: string;
  exampleTextAreaLineCount: number = 1;
  headers: Schema;
  headersSchemaIdentifier: string;
  headersExample: Example;
  headersTextAreaLineCount: number = 1;
  messageBindingExample?: Example;
  messageBindingExampleTextAreaLineCount: number = 1;
  canPublish: boolean = false;

  constructor(
    private asyncApiService: AsyncApiService,
    private publisherService: PublisherService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      const schemas: Map<string, Schema> = asyncapi.components.schemas;
      this.schemaIdentifier = this.operation.message.payload.name.slice(
        this.operation.message.payload.name.lastIndexOf("/") + 1
      );
      this.schema = schemas.get(this.schemaIdentifier);

      this.defaultExample = this.schema.example;
      this.exampleTextAreaLineCount = this.defaultExample?.lineCount || 1;
      this.defaultExampleType = this.operation.message.payload.type;

      this.headersSchemaIdentifier = this.operation.message.headers.name.slice(
        this.operation.message.headers.name.lastIndexOf("/") + 1
      );
      this.headers = schemas.get(this.headersSchemaIdentifier);
      this.headersExample = this.headers.example;
      this.headersTextAreaLineCount = this.headersExample?.lineCount || 1;
      this.messageBindingExampleTextAreaLineCount =
        this.messageBindingExample?.lineCount || 1;

      this.publisherService
        .canPublish(this.operation.protocol)
        .subscribe((response) => {
          this.canPublish = response;
        });
    });
  }

  isEmptyObject(object?: any): boolean {
    return (
      object === undefined ||
      object === null ||
      Object.keys(object).length === 0
    );
  }

  createMessageBindingExample(messageBinding?: Binding): Example | undefined {
    if (messageBinding === undefined || messageBinding === null) {
      return undefined;
    }

    const bindingExampleObject = {};
    Object.keys(messageBinding).forEach((bindingKey) => {
      if (bindingKey !== "bindingVersion") {
        bindingExampleObject[bindingKey] = this.getExampleValue(
          messageBinding[bindingKey]
        );
      }
    });

    const bindingExample = new Example(bindingExampleObject);

    this.messageBindingExampleTextAreaLineCount = bindingExample.lineCount;

    return bindingExample;
  }

  getExampleValue(bindingValue: string | Binding): any {
    if (typeof bindingValue === "string") {
      return bindingValue;
    } else if (typeof bindingValue.examples === "object") {
      return bindingValue.examples["0"];
    }
    return undefined;
  }

  recalculateLineCount(field: string, text: string): void {
    switch (field) {
      case "example":
        this.exampleTextAreaLineCount = text.split("\n").length;
        break;
      case "headers":
        this.headersTextAreaLineCount = text.split("\n").length;
        break;
      case "messageBindingExample":
        this.messageBindingExampleTextAreaLineCount = text.split("\n").length;
        break;
    }
  }

  publish(
    example: string,
    payloadType: string,
    headers?: string,
    bindings?: string
  ): void {
    try {
      const headersJson =
        headers === ""
          ? {}
          : wrapException(
              "Unable to convert headers to JSON object (nor is empty)",
              () => JSON.parse(headers)
            );
      const bindingsJson =
        bindings === ""
          ? {}
          : wrapException(
              "Unable to convert bindings to JSON object (nor is empty)",
              () => JSON.parse(bindings)
            );

      this.publisherService
        .publish(
          this.operation.protocol,
          this.channelName,
          example,
          payloadType,
          headersJson,
          bindingsJson
        )
        .subscribe(
          (_) => this.handlePublishSuccess(),
          (err) => this.handlePublishError(err)
        );
    } catch (error) {
      this.snackBar.open(
        "Unable to create publishing payload: " + error.message,
        "ERROR",
        {
          duration: 3000,
        }
      );
    }
  }

  private handlePublishSuccess() {
    return this.snackBar.open(
      "Example payload sent to: " + this.channelName,
      "PUBLISHED",
      {
        duration: 3000,
      }
    );
  }

  private handlePublishError(err: { status?: number }) {
    let msg = "Publish failed";
    if (err?.status === STATUS.NOT_FOUND) {
      msg += ": no publisher was provided for " + this.operation.protocol;
    }

    return this.snackBar.open(msg, "ERROR", {
      duration: 4000,
    });
  }
}
