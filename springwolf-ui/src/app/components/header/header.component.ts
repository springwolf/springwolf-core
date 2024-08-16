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
  isNewUi: boolean = UiService.DEFAULT_NEW_UI;
  isShowBindings: boolean = UiService.DEFAULT_SHOW_BINDINGS;
  title: string = "";

  constructor(
    private uiService: UiService,
    private asyncApiService: AsyncApiService,
    private assetService: IAssetService
  ) {}

  ngOnInit() {
    this.assetService.load();

    this.uiService.isNewUi$.subscribe((value) => (this.isNewUi = value));
    this.uiService.isShowBindings$.subscribe(
      (value) => (this.isShowBindings = value)
    );

    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.title = asyncapi.info.title;
    });
  }

  toggleIsNewUi(value: boolean) {
    this.uiService.toggleIsNewUi(value);
  }

  toggleIsShowBindings() {
    this.uiService.toggleIsShowBindings(!this.isShowBindings);
  }
}
