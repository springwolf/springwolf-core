import { Component, OnInit, Input } from '@angular/core';
import { AsyncApiService } from 'src/app/shared/asyncapi.service';
import { Example } from 'src/app/shared/models/example.model';
import { PublisherService } from 'src/app/shared/publisher.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Operation } from 'src/app/shared/models/channel.model';

@Component({
  selector: 'app-channel-main',
  templateUrl: './channel-main.component.html',
  styleUrls: ['./channel-main.component.css']
})
export class ChannelMainComponent implements OnInit {

  @Input() channelName: string;
  @Input() operation: Operation;
  defaultExample: Example;
  exampleTextAreaLineCount: number;
  schema: any;
  schemaRoot = "properties";
  protocolName: string;

  constructor(
    private asyncApiService: AsyncApiService,
    private publisherService: PublisherService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe(
      asyncapi => {
        this.schema = asyncapi.components.schemas.get(this.operation.message.title);
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
