import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Channel } from '../shared/models/channel.model';
import { subscribeOn } from 'rxjs/operators';
import { Subscription } from 'rxjs';
import {Location} from "@angular/common";

@Component({
  selector: 'app-channels',
  templateUrl: './channels.component.html',
  styleUrls: ['./channels.component.css']
})
export class ChannelsComponent implements OnInit {

  channels: Channel[];
  selectedChannel: string;
  nameSubscription: Subscription;
  docName: string;

  constructor(private asyncApiService: AsyncApiService, private location: Location) {
    this.setChannelSelectionFromLocation()
  }

  ngOnInit(): void {
    this.location.subscribe(() : void => this.setChannelSelectionFromLocation())

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

  setChannelSelection(channel: Channel): void {
    window.location.hash = '#' + this.getChannelIdentifier(channel)
  }
  setChannelSelectionFromLocation(): void {
    this.selectedChannel = window.location.hash.substr(1);
  }
  resetChannelSelection(channel: Channel): void {
    if(this.selectedChannel == this.getChannelIdentifier(channel)) {
      this.selectedChannel = undefined
    }
  }

  getChannelIdentifier(channel: Channel) {
    return channel.name + "-" + channel.operation.type.replace(' ', '-')
  }
}
