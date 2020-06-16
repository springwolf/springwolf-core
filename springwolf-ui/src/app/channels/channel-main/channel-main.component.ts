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

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    const schema = this.asyncApiService.getAsyncApi().schemas.get(this.payload);
    console.log(schema);

    this.defaultExample = schema.example;

    console.log(this.defaultExample);
  }

}
