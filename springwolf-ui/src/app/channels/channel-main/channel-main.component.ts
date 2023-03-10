import { Component, OnInit, Input } from '@angular/core';
import { AsyncApiService } from 'src/app/shared/asyncapi.service';
import { Example } from 'src/app/shared/models/example.model';
import { Schema } from 'src/app/shared/models/schema.model';
import { PublisherService } from 'src/app/shared/publisher.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import {MessageBinding, Operation} from 'src/app/shared/models/channel.model';
import { STATUS } from 'angular-in-memory-web-api';

@Component({
  selector: 'app-channel-main',
  templateUrl: './channel-main.component.html',
  styleUrls: ['./channel-main.component.css']
})
export class ChannelMainComponent implements OnInit {

  @Input() docName: string;
  @Input() channelName: string;
  @Input() operation: Operation;

  schema: Schema;
  schemaName: string;
  defaultExample: Example;
  exampleTextAreaLineCount: number;
  headers: Schema;
  headersSchemaName: string;
  headersExample: Example;
  headersTextAreaLineCount: number;
  protocolName: string;
  messageBindingExample?: Example;
  messageBindingExampleTextAreaLineCount: number;

  constructor(
    private asyncApiService: AsyncApiService,
    private publisherService: PublisherService,
    private snackBar: MatSnackBar
  ) {}


  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe(
      asyncapi => {
        const schemas: Map<string, Schema> = asyncapi.components.schemas;
        this.schemaName = this.operation.message.payload.name.slice(this.operation.message.payload.name.lastIndexOf('/') + 1);
        this.schema = schemas.get(this.schemaName);

        this.defaultExample = this.schema.example;
        this.exampleTextAreaLineCount = this.defaultExample?.lineCount || 0;

        this.headersSchemaName = this.operation.message.headers.name.slice(this.operation.message.headers.name.lastIndexOf('/') + 1);
        this.headers = schemas.get(this.headersSchemaName);
        this.headersExample = this.headers.example;
        this.headersTextAreaLineCount = this.headersExample?.lineCount || 0;
        this.messageBindingExampleTextAreaLineCount = this.messageBindingExample?.lineCount || 0;
      }
    );

    this.protocolName = Object.keys(this.operation.bindings)[0];
  }

  isEmptyObject(object?: any): boolean {
    return (object === undefined || object === null) || Object.keys(object).length === 0;
  }

  createMessageBindingExample(messageBinding?: MessageBinding): Example | undefined {
    if (messageBinding === undefined || messageBinding === null) {
      return undefined;
    }

    const bindingExampleObject = {};
    Object.keys(messageBinding).forEach((bindingKey) => {
      if (bindingKey !== 'bindingVersion') {
        bindingExampleObject[bindingKey] = this.getExampleValue(messageBinding[bindingKey]);
      }
    });

    const bindingExample = new Example(bindingExampleObject);

    this.messageBindingExampleTextAreaLineCount = bindingExample.lineCount;

    return bindingExample;
  }

  getExampleValue(bindingValue: string | Schema): any {
    if (typeof bindingValue === 'string') {
      return bindingValue;
    } else {
      return bindingValue.example.value;
    }
  }

  recalculateLineCount(field: string, text: string): void {
    switch (field) {
      case 'example':
        this.exampleTextAreaLineCount = text.split('\n').length;
        break;
      case 'headers':
        this.headersTextAreaLineCount = text.split('\n').length;
        break;
      case 'massageBindingExample':
        this.messageBindingExampleTextAreaLineCount = text.split('\n').length;
        break;
    }
  }

  publish(example: string, headers?: string, bindings?: string): void {
    try {
      const payloadJson = JSON.parse(example);
      const headersJson = JSON.parse(headers);
      const bindingsJson = JSON.parse(bindings);

      this.publisherService.publish(this.protocolName, this.channelName, payloadJson, headersJson, bindingsJson).subscribe(
        _ => this.handlePublishSuccess(),
        err => this.handlePublishError(err)
      );
    } catch (error) {
      this.snackBar.open('Example payload is not valid', 'ERROR', {
        duration: 3000
      });
    }
  }

  private handlePublishSuccess() {
    return this.snackBar.open('Example payload sent to: ' + this.channelName, 'PUBLISHED', {
      duration: 3000
    });
  }

  private handlePublishError(err: {status?: number}) {
    let msg = 'Publish failed';
    if (err?.status === STATUS.NOT_FOUND) {
      msg += ': no publisher was provided for ' + this.protocolName;
    }

    return this.snackBar.open(msg, 'ERROR', {
      duration: 4000
    });
  }

}
