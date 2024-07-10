/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { UiService } from "../../service/ui.service";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})
export class HeaderComponent implements OnInit {
  isNewUi: boolean = true;
  title: string = "";

  constructor(
    private uiService: UiService,
    private asyncApiService: AsyncApiService
  ) {}

  ngOnInit() {
    this.uiService.isNewUi$.subscribe((value) => (this.isNewUi = value));

    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.title = asyncapi.info.title;
    });
  }

  toggleIsNewUi(value: boolean) {
    this.uiService.toggleIsNewUi(value);
  }
}
