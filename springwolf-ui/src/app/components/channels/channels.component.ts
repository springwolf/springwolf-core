/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { Channel } from "../../models/channel.model";
import { IUiService } from "../../service/ui.service";

@Component({
    selector: "app-channels",
    templateUrl: "./channels.component.html",
    styleUrls: ["./channels.component.css"],
    standalone: false
})
export class ChannelsComponent implements OnInit {
  channels: Channel[] = [];
  isShowBindings: boolean = IUiService.DEFAULT_SHOW_BINDINGS;
  JSON = JSON;

  constructor(
    private asyncApiService: AsyncApiService,
    private uiService: IUiService
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
