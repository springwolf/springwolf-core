/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit, signal } from "@angular/core";
import { AsyncApi } from "../../models/asyncapi.model";
import { Info } from "../../models/info.model";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { initInfo } from "../../service/mock/init-values";
import { MatChipsModule } from "@angular/material/chips";
import { MatIconModule } from "@angular/material/icon";
import { MarkdownModule } from "ngx-markdown";

@Component({
  selector: "app-info",
  templateUrl: "./info.component.html",
  styleUrls: ["./info.component.css"],
  imports: [MatChipsModule, MatIconModule, MarkdownModule],
})
export class InfoComponent implements OnInit {
  asyncApiData: AsyncApi | undefined = undefined;
  info = signal<Info>(initInfo);

  constructor(private asyncApiService: AsyncApiService) {}

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.asyncApiData = asyncapi;
      this.info.set(asyncapi.info);
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
