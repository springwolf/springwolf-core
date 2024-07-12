/* SPDX-License-Identifier: Apache-2.0 */
import { Component, input, OnInit } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { STATUS } from "angular-in-memory-web-api";
import { Binding } from "../../../../models/bindings.model";
import { Example } from "../../../../models/example.model";
import { Operation } from "../../../../models/operation.model";
import { Schema } from "../../../../models/schema.model";
import { AsyncApiService } from "../../../../service/asyncapi/asyncapi.service";
import { PublisherService } from "../../../../service/publisher.service";
import { wrapException } from "../../../../util/error-boundary";
import { initExample, initSchema } from "../../../../service/mock/init-values";

@Component({
  selector: "app-channel-operation",
  templateUrl: "./channel-operation.component.html",
  styleUrls: ["./channel-operation.component.css"],
})
export class ChannelOperationComponent implements OnInit {
  channelName = input.required<string>();
  operation = input.required<Operation>();

  defaultSchema: Schema = initSchema;
  defaultExample: Example = initExample;
  defaultExampleType: string = "";
  originalDefaultExample: string | object = this.defaultExample.rawValue;

  headers: Schema = initSchema;
  headersExample: Example = initExample;
  originalHeadersExample: string | object = this.headersExample.rawValue;

  operationBindingExampleString?: string;
  messageBindingExampleString?: string;

  canPublish: boolean = false;

  constructor(
    private asyncApiService: AsyncApiService,
    private publisherService: PublisherService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      const schemas: Map<string, Schema> = asyncapi.components.schemas;

      const schemaIdentifier = this.operation().message.payload.name.slice(
        this.operation().message.payload.name.lastIndexOf("/") + 1
      );
      const schema = schemas.get(schemaIdentifier)!!;
      this.defaultSchema = schema;
      this.originalDefaultExample = schema.example!!.rawValue;
      this.defaultExampleType = this.operation().message.payload.type;

      const headersSchemaIdentifier =
        this.operation().message.headers.name.slice(
          this.operation().message.headers.name.lastIndexOf("/") + 1
        );
      this.headers = schemas.get(headersSchemaIdentifier)!!;
      this.originalHeadersExample = this.headers.example!!.rawValue;

      this.operationBindingExampleString = new Example(
        this.operation().bindings[this.operation().protocol]
      )?.value;
      this.messageBindingExampleString = this.createBindingExample(
        this.operation().message.bindings.get(this.operation().protocol)
      )?.value;

      this.reset();

      this.publisherService
        .canPublish(this.operation().protocol)
        .subscribe((response) => {
          this.canPublish = response;
        });
    });
  }

  createBindingExample(binding?: Binding): Example | undefined {
    if (binding === undefined || binding === null) {
      return undefined;
    }

    const bindingExampleObject: { [key: string]: any } = {};
    Object.keys(binding).forEach((bindingKey) => {
      if (bindingKey !== "bindingVersion") {
        bindingExampleObject[bindingKey] = this.getExampleValue(
          binding[bindingKey]
        );
      }
    });

    return new Example(bindingExampleObject);
  }

  getExampleValue(bindingValue: string | Binding): any {
    if (typeof bindingValue === "string") {
      return bindingValue;
    } else if (
      "examples" in bindingValue &&
      typeof bindingValue["examples"] === "object"
    ) {
      return bindingValue["examples"][0];
    }
    return undefined;
  }

  reset(): void {
    this.defaultExample = new Example(this.originalDefaultExample);
    this.headersExample = new Example(this.originalHeadersExample);
  }

  publish(): void {
    const example = this.defaultExample.value;
    const payloadType = this.defaultExampleType;
    const headers = this.headersExample.value;
    const bindings = this.messageBindingExampleString;
    try {
      const headersJson =
        headers === ""
          ? {}
          : wrapException(
              "Unable to convert headers to JSON object (nor is empty)",
              () => JSON.parse(headers || "")
            );
      const bindingsJson =
        bindings === ""
          ? {}
          : wrapException(
              "Unable to convert bindings to JSON object (nor is empty)",
              () => JSON.parse(bindings || "")
            );

      this.publisherService
        .publish(
          this.operation().protocol || "not-supported-protocol",
          this.channelName(),
          example,
          payloadType,
          headersJson,
          bindingsJson
        )
        .subscribe(
          (_) => this.handlePublishSuccess(),
          (err) => this.handlePublishError(err)
        );
    } catch (error: any) {
      this.snackBar.open(
        "Unable to create publishing payload: " + error?.message,
        "ERROR",
        {
          duration: 3000,
        }
      );
    }
  }

  private handlePublishSuccess() {
    return this.snackBar.open(
      "Example payload sent to: " + this.channelName(),
      "PUBLISHED",
      {
        duration: 3000,
      }
    );
  }

  private handlePublishError(err: { status?: number }) {
    let msg = "Publish failed";
    if (err?.status === STATUS.NOT_FOUND) {
      msg += ": no publisher was provided for " + this.operation().protocol;
    }

    return this.snackBar.open(msg, "ERROR", {
      duration: 4000,
    });
  }

  protected readonly JSON = JSON;
}
