/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { UiService } from "../../service/ui.service";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { IAssetService } from "../../service/asset.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})
export class HeaderComponent implements OnInit {
  isShowBindings: boolean = UiService.DEFAULT_SHOW_BINDINGS;
  isShowHeaders: boolean = UiService.DEFAULT_SHOW_HEADERS;
  title: string = "";

  constructor(
    private uiService: UiService,
    private asyncApiService: AsyncApiService,
    private assetService: IAssetService
  ) {}

  ngOnInit() {
    this.assetService.load();

    this.uiService.isShowBindings$.subscribe(
      (value) => (this.isShowBindings = value)
    );
    this.uiService.isShowHeaders$.subscribe(
      (value) => (this.isShowHeaders = value)
    );

    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.title = asyncapi.info.title;
    });
  }

  toggleIsShowBindings() {
    this.uiService.toggleIsShowBindings(!this.isShowBindings);
  }

  toggleIsShowHeaders() {
    this.uiService.toggleIsShowHeaders(!this.isShowHeaders);
  }
}
