/* SPDX-License-Identifier: Apache-2.0 */
import { Component } from "@angular/core";
import { UiService } from "../../service/feature.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})
export class HeaderComponent {
  constructor(private uiService: UiService) {}

  toggleIsNewUi(value: boolean) {
    this.uiService.toggleIsNewUi(value);
  }
}
