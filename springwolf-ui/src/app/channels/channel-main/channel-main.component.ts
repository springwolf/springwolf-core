import { Component, OnInit, Input } from '@angular/core';
import { AsyncApiService } from 'src/app/shared/asyncapi.service';
import { Example } from 'src/app/shared/models/example.model';
import { Schema } from 'src/app/shared/models/schema.model';
import { PublisherService } from 'src/app/shared/publisher.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Operation } from 'src/app/shared/models/channel.model';
import { Subscription } from 'rxjs';
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
  nameSubscription: Subscription;

  constructor(
    private asyncApiService: AsyncApiService,
    private publisherService: PublisherService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApis().subscribe(
      asyncapi => {
        let schemas: Map<string, Schema> = asyncapi.get(this.docName).components.schemas;
        this.schemaName = this.operation.message.payload.$ref.slice(this.operation.message.payload.$ref.lastIndexOf('/') + 1)
        this.schema = schemas[this.schemaName];

        this.defaultExample = new Example(this.schema.example);
        this.exampleTextAreaLineCount = this.defaultExample.lineCount;

        this.headersSchemaName = this.operation.message.headers.$ref.slice(this.operation.message.headers.$ref.lastIndexOf('/') + 1)
        this.headers = schemas[this.headersSchemaName];
        this.headersExample = new Example(this.headers.example);
        this.headersTextAreaLineCount = this.headersExample.lineCount;
      }
    );

    this.protocolName = Object.keys(this.operation.bindings)[0];
  }

  recalculateLineCount(field: string, text: string): void {
    switch (field) {
      case 'example':
        this.exampleTextAreaLineCount = text.split('\n').length;
        break;
      case 'headers':
        this.headersTextAreaLineCount = text.split('\n').length
        break;
    }
  }

  publish(example: string): void {
    try {
      const json = JSON.parse(example);

      this.publisherService.publish(this.protocolName, this.channelName, json).subscribe(
        _ => this.handlePublishSuccess(),
        err => this.handlePublishError(err)
      );
    } catch(error) {
      this.snackBar.open('Example payload is not valid', 'ERROR', {
        duration: 3000
      })
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
