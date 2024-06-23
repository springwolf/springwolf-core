/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { UiService } from "../../service/ui.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})
export class HeaderComponent implements OnInit {
  isNewUi: boolean;

  constructor(private uiService: UiService) {}

  ngOnInit() {
    this.uiService.isNewUi$.subscribe((value) => (this.isNewUi = value));
  }

  toggleIsNewUi(value: boolean) {
    this.uiService.toggleIsNewUi(value);
  }
}
