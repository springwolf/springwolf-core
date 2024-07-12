/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import { Channel } from "../../../models/channel.model";

@Component({
  selector: "app-channels-new",
  templateUrl: "./channels.component.html",
  styleUrls: ["./channels.component.css"],
})
export class ChannelsNewComponent implements OnInit {
  channels: Channel[] = [];

  constructor(private asyncApiService: AsyncApiService) {}

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.channels = asyncapi.channels;
    });
  }
}
