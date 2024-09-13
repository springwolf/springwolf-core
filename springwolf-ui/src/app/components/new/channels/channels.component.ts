/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import { Channel } from "../../../models/channel.model";
import { UiService } from "../../../service/ui.service";

@Component({
  selector: "app-channels-new",
  templateUrl: "./channels.component.html",
  styleUrls: ["./channels.component.css"],
})
export class ChannelsNewComponent implements OnInit {
  channels: Channel[] = [];
  isShowBindings: boolean = UiService.DEFAULT_SHOW_BINDINGS;
  JSON = JSON;

  constructor(
    private asyncApiService: AsyncApiService,
    private uiService: UiService
  ) {}

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.channels = asyncapi.channels;
    });

    this.uiService.isShowBindings$.subscribe(
      (value) => (this.isShowBindings = value)
    );
  }
}
