import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Channel } from '../shared/models/channel.model';
import { Location } from "@angular/common";

@Component({
  selector: 'app-channels',
  templateUrl: './channels.component.html',
  styleUrls: ['./channels.component.css']
})
export class ChannelsComponent implements OnInit {
  static CHANNEL_ANCHOR_PREFIX = "channel-"

  channels: Channel[];
  selectedChannel: string;
  docName: string;

  constructor(private asyncApiService: AsyncApiService, private location: Location) {
    this.setChannelSelectionFromLocation()
  }

  ngOnInit(): void {
    this.location.subscribe((): void => this.setChannelSelectionFromLocation())

    this.asyncApiService.getAsyncApis().subscribe(asyncapi => {
      this.channels = this.sortChannels(asyncapi.channels);
    });
  }

  private sortChannels(channels: Array<Channel>): Array<Channel> {
    return channels.sort((a, b) => {
      if (a?.operation?.protocol === b?.operation?.protocol) {
        if (a?.operation?.operation === b?.operation.operation) {
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
    const anchor = window.location.hash.substr(1);
    if (anchor.startsWith(ChannelsComponent.CHANNEL_ANCHOR_PREFIX)) {
      this.selectedChannel = anchor;
    }
  }

  getChannelIdentifier(channel: Channel) {
    return ChannelsComponent.CHANNEL_ANCHOR_PREFIX + channel.name + "-" + channel.operation.protocol + "-" + channel.operation.operation;
  }
}
