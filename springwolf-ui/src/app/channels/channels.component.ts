import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Channel } from '../shared/models/channel.model';
import { subscribeOn } from 'rxjs/operators';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-channels',
  templateUrl: './channels.component.html',
  styleUrls: ['./channels.component.css']
})
export class ChannelsComponent implements OnInit {

  channels: Channel[];
  nameSubscription: Subscription;
  docName: string;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.nameSubscription = this.asyncApiService.getCurrentAsyncApiName().subscribe(name => {
      this.docName = name;
      this.asyncApiService.getAsyncApis().subscribe(asyncapi => {
        this.channels = this.sortChannels(asyncapi.get(name).channels);
      });
    });
  }

  private sortChannels(channels: Array<Channel>): Array<Channel> {
    return channels.sort((a,b) => {
      if(a?.operation?.protocol === b?.operation?.protocol) {
        if(a?.operation?.operation === b?.operation.operation) {
          return a.name?.localeCompare(b.name);
        } else {
          return a?.operation?.operation?.localeCompare(b?.operation?.operation); 
        }
      } else {
        return a?.operation?.protocol?.localeCompare(b?.operation?.protocol);
      }
    });
  }

}
