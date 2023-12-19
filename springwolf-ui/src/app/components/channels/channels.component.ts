/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import {
  ChannelOperation,
  CHANNEL_ANCHOR_PREFIX,
} from "../../models/channel.model";
import { Location } from "@angular/common";

@Component({
  selector: "app-channels",
  templateUrl: "./channels.component.html",
  styleUrls: ["./channels.component.css"],
})
export class ChannelsComponent implements OnInit {
  channels: ChannelOperation[];
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
      this.channels = this.sortChannels(asyncapi.channelOperations);
    });
  }

  private sortChannels(
    channels: Array<ChannelOperation>
  ): Array<ChannelOperation> {
    return channels.sort((a, b) => {
      if (a.operation.protocol === b.operation.protocol) {
        if (a.operation.operationType === b.operation.operationType) {
          if (a.name === b.name) {
            return a.operation.message.name.localeCompare(
              b.operation.message.name
            );
          } else {
            return a.name.localeCompare(b.name);
          }
        } else {
          return a.operation.operationType.localeCompare(
            b.operation.operationType
          );
        }
      } else if (a.operation.protocol != null) {
        return a.operation.protocol.localeCompare(b.operation.protocol);
      } else {
        return 0;
      }
    });
  }

  setChannelSelection(channel: ChannelOperation): void {
    window.location.hash = channel.anchorIdentifier;
  }
  setChannelSelectionFromLocation(): void {
    const anchor = window.location.hash;
    if (anchor.startsWith(CHANNEL_ANCHOR_PREFIX)) {
      this.selectedChannel = anchor;
    }
  }
}
