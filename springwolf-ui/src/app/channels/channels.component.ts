import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Channel } from '../shared/models/channel.model';

@Component({
  selector: 'app-channels',
  templateUrl: './channels.component.html',
  styleUrls: ['./channels.component.css']
})
export class ChannelsComponent implements OnInit {

  channels: Map<String, Channel>;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.channels = this.asyncApiService.getAsyncApi().channels;
  }

}
