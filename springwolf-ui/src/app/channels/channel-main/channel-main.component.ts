import { Component, OnInit, Input } from '@angular/core';
import { AsyncApiService } from 'src/app/shared/asyncapi.service';
import { Example } from 'src/app/shared/models/example.model';

@Component({
  selector: 'app-channel-main',
  templateUrl: './channel-main.component.html',
  styleUrls: ['./channel-main.component.css']
})
export class ChannelMainComponent implements OnInit {

  @Input() payload: String;
  defaultExample: Example;
  schema: any;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.schema = this.asyncApiService.getAsyncApi().schemas.get(this.payload);
    this.defaultExample = this.schema.example;
  }

}
