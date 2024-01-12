/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApiService } from "../shared/asyncapi.service";
import { Channel, CHANNEL_ANCHOR_PREFIX } from "../shared/models/channel.model";
import { Location } from "@angular/common";

@Component({
  selector: "app-channels",
  templateUrl: "./channels.component.html",
  styleUrls: ["./channels.component.css"],
})
export class ChannelsComponent implements OnInit {
  channels: Channel[];
  selectedChannel: string;
  docName: string;

  constructor(
    private asyncApiService: AsyncApiService,
    private location: Location
  ) {
    this.setChannelSelectionFromLocation();
  }

  ngOnInit(): void {
    this.location.subscribe((): void => this.setChannelSelectionFromLocation());

    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.channels = this.sortChannels(asyncapi.channels);
    });
  }

  private sortChannels(channels: Array<Channel>): Array<Channel> {
    return channels.sort((a, b) => {
      if (a.operation.protocol === b.operation.protocol) {
        if (a.operation.operation === b.operation.operation) {
          if (a.name === b.name) {
            return a.operation.message.name.localeCompare(
              b.operation.message.name
            );
          } else {
            return a.name.localeCompare(b.name);
          }
        } else {
          return a.operation.operation.localeCompare(b.operation.operation);
        }
      } else if (a.operation.protocol != null) {
        return a.operation.protocol.localeCompare(b.operation.protocol);
      } else {
        return 0;
      }
    });
  }

  setChannelSelection(channel: Channel): void {
    window.location.hash = channel.anchorIdentifier;
  }
  setChannelSelectionFromLocation(): void {
    const anchor = window.location.hash;
    if (anchor.startsWith(CHANNEL_ANCHOR_PREFIX)) {
      this.selectedChannel = anchor;
    }
  }
}
