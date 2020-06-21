import { Component, OnInit, Input } from '@angular/core';
import { AsyncApiService } from 'src/app/shared/asyncapi.service';
import { Example } from 'src/app/shared/models/example.model';

@Component({
  selector: 'app-channel-main',
  templateUrl: './channel-main.component.html',
  styleUrls: ['./channel-main.component.css']
})
export class ChannelMainComponent implements OnInit {

  @Input() payload: string;
  defaultExample: Example;
  exampleTextAreaLineCount: number;
  schema: any;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe(
      asyncapi => {
        this.schema = asyncapi.components.schemas.get(this.payload);
        this.defaultExample = this.schema.example;
        this.exampleTextAreaLineCount = this.defaultExample.lineCount;
      }
    );
  }

  recalculateLineCount(text): void {
    this.exampleTextAreaLineCount = text.split('\n').length;
  }

}
