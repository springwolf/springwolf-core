/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApi } from "../../models/asyncapi.model";
import { Info } from "../../models/info.model";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { initInfo } from "../../service/mock/init-values";

@Component({
  selector: "app-info",
  templateUrl: "./info.component.html",
  styleUrls: ["./info.component.css"],
})
export class InfoComponent implements OnInit {
  asyncApiData: AsyncApi | undefined = undefined;
  info: Info = initInfo;

  constructor(private asyncApiService: AsyncApiService) {}

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.asyncApiData = asyncapi;
      this.info = asyncapi.info;
    });
  }

  download(): Boolean {
    if (this.asyncApiData === undefined) {
      return false;
    }

    const json = JSON.stringify(this.asyncApiData.info.asyncApiJson, null, 2);
    const bytes = new TextEncoder().encode(json);
    const blob = new Blob([bytes], { type: "application/json" });
    const url = window.URL.createObjectURL(blob);
    window.open(url);

    return false;
  }
}
