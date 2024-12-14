/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { Channel } from "../../models/channel.model";
import { IUiService } from "../../service/ui.service";
import { PrismEditorComponent } from "../code/prism-editor.component";
import { CommonModule } from "@angular/common";
import { MatCardModule } from "@angular/material/card";
import { ChannelOperationComponent } from "./channel-main/channel-operation.component";
import { NavigationTargetDirective } from "../sidenav/navigation.directive";

@Component({
  selector: "app-channels",
  templateUrl: "./channels.component.html",
  styleUrls: ["./channels.component.css"],
  imports: [
    PrismEditorComponent,
    CommonModule,
    MatCardModule,
    ChannelOperationComponent,
    NavigationTargetDirective,
  ],
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
