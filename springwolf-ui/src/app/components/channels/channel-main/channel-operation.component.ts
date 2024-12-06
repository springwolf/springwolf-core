/* SPDX-License-Identifier: Apache-2.0 */
import {Component, computed, input, OnInit} from "@angular/core";
import {MatSnackBar} from "@angular/material/snack-bar";
import {STATUS} from "angular-in-memory-web-api";
import {Binding} from "../../../models/bindings.model";
import {Example} from "../../../models/example.model";
import {Operation} from "../../../models/operation.model";
import {Schema} from "../../../models/schema.model";
import {AsyncApiService} from "../../../service/asyncapi/asyncapi.service";
import {PublisherService} from "../../../service/publisher.service";
import {wrapException} from "../../../util/error-boundary";
import {initExample, initSchema, noExample,} from "../../../service/mock/init-values";
import {IUiService} from "../../../service/ui.service";
import {CommonModule} from "@angular/common";
import {MarkdownModule} from "ngx-markdown";
import {MatChipsModule} from "@angular/material/chips";
import {MatIconModule} from "@angular/material/icon";
import {PrismEditorComponent} from "../../code/prism-editor.component";
import {SchemaComponent} from "../../schema/schema.component";
import {CdkCopyToClipboard} from "@angular/cdk/clipboard";
import {MatButtonModule} from "@angular/material/button";

@Component({
  selector: "app-channel-operation",
  templateUrl: "./channel-operation.component.html",
  styleUrls: ["./channel-operation.component.css"],
  imports: [
    CommonModule,
    MarkdownModule,
    MatChipsModule,
    MatIconModule,
    PrismEditorComponent,
    SchemaComponent,
    CdkCopyToClipboard,
    MatButtonModule
  ]
})
export class ChannelOperationComponent implements OnInit {
  channelName = input.required<string>();
  operation = input.required<Operation>();

  defaultSchema: Schema = initSchema;
  defaultExample: Example = initExample;
  originalDefaultExample: Example = this.defaultExample;
  exampleContentType = computed(
    () => this.operation().message.contentType.split("/").pop() || "json"
  );

  headers: Schema = initSchema;
  headersExample: Example = initExample;
  originalHeadersExample: Example = this.headersExample;

  operationBindingExampleString?: string;
  messageBindingExampleString?: string;

  isShowBindings: boolean = IUiService.DEFAULT_SHOW_BINDINGS;
  isShowHeaders: boolean = IUiService.DEFAULT_SHOW_HEADERS;
  canPublish: boolean = false;

  constructor(
    private asyncApiService: AsyncApiService,
    private publisherService: PublisherService,
    private uiService: IUiService,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      const schemas: Map<string, Schema> = asyncapi.components.schemas;

      const payload = this.operation().message.payload;
      if (payload.ts_type === "ref") {
        const schemaIdentifier = payload.name.slice(
          payload.name.lastIndexOf("/") + 1
        );
        const schema = schemas.get(schemaIdentifier)!!;
        this.defaultSchema = schema;
        this.defaultExample = schema.example || noExample;
        this.originalDefaultExample = this.defaultExample;
      } else {
        this.defaultSchema = payload;
        this.defaultExample = payload.example || noExample;
        this.originalDefaultExample = this.defaultExample;
      }

      const headersSchemaIdentifier = this.operation().message.headers.name;
      this.headers = schemas.get(headersSchemaIdentifier)!!;
      this.originalHeadersExample = this.headers.example || noExample;

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

    this.uiService.isShowBindings$.subscribe(
      (value) => (this.isShowBindings = value)
    );
    this.uiService.isShowHeaders$.subscribe(
      (value) => (this.isShowHeaders = value)
    );
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
    this.defaultExample = new Example(this.originalDefaultExample.rawValue);
    this.headersExample = new Example(this.originalHeadersExample.rawValue);
  }

  publish(): void {
    const example = this.defaultExample.value;
    const payloadType = this.operation().message.payload.name;
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
}
