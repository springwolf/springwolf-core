import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Channel } from '../shared/models/channel.model';
import { subscribeOn } from 'rxjs/operators';

@Component({
  selector: 'app-channels',
  templateUrl: './channels.component.html',
  styleUrls: ['./channels.component.css']
})
export class ChannelsComponent implements OnInit {

  channels: Map<String, Channel>;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe(
      asyncapi => this.channels = asyncapi.channels
    );
  }

}
