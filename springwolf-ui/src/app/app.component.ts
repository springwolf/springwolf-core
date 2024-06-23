/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { UiService } from "./service/ui.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent implements OnInit {
  isNewUi: boolean;

  constructor(private uiService: UiService) {}

  ngOnInit() {
    this.uiService.isNewUi$.subscribe((value) => (this.isNewUi = value));
  }
}
