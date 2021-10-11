import { Component, OnInit, Input } from '@angular/core';
import { AsyncApiService } from 'src/app/shared/asyncapi.service';
import { Example } from 'src/app/shared/models/example.model';
import { Schema } from 'src/app/shared/models/schema.model';
import { PublisherService } from 'src/app/shared/publisher.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Operation } from 'src/app/shared/models/channel.model';
import { Subscription } from 'rxjs';

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
  defaultExample: Example;
  exampleTextAreaLineCount: number;
  schemaRoot = "properties";
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
        this.schema = schemas.get(this.operation.message.title);
        this.defaultExample = this.schema.example;
        this.exampleTextAreaLineCount = this.defaultExample.lineCount;
      }
    );

    this.protocolName = Object.keys(this.operation.bindings)[0];
  }

  recalculateLineCount(text): void {
    this.exampleTextAreaLineCount = text.split('\n').length;
  }

  publish(example: string): void {
    try {
      const json = JSON.parse(example);

      this.publisherService.publish(this.protocolName, this.channelName, json).subscribe(
        _ => this.snackBar.open('Example payload sent to: ' + this.channelName, 'PUBLISHED', {
          duration: 3000
        })
      );
    } catch(error) {
      this.snackBar.open('Example payload is not valid', 'ERROR', {
        duration: 3000
      })
    }
  }

}
