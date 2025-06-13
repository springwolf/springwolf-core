/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit, signal } from "@angular/core";
import { IUiService } from "../../service/ui.service";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { IAssetService } from "../../service/asset.service";

import { MatButtonModule } from "@angular/material/button";
import { MatMenuModule } from "@angular/material/menu";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatIconModule } from "@angular/material/icon";

interface Group {
  value: string;
  viewValue: string;
}

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
  imports: [MatToolbarModule, MatButtonModule, MatMenuModule, MatIconModule],
})
export class HeaderComponent implements OnInit {
  groups = signal<Group[]>([]);
  isGroup = signal<string>(IUiService.DEFAULT_GROUP);
  isShowBindings = signal<boolean>(IUiService.DEFAULT_SHOW_BINDINGS);
  isShowHeaders = signal<boolean>(IUiService.DEFAULT_SHOW_HEADERS);
  title = signal<string>("");

  constructor(
    private uiService: IUiService,
    private asyncApiService: AsyncApiService,
    private assetService: IAssetService
  ) {}

  ngOnInit() {
    this.assetService.load();

    this.uiService.isShowBindings$.subscribe((value) =>
      this.isShowBindings.set(value)
    );

    this.uiService.isShowHeaders$.subscribe((value) =>
      this.isShowHeaders.set(value)
    );

    this.uiService.isGroup$.subscribe((value) => this.isGroup.set(value));
    this.uiService.uiConfig.subscribe((value) => {
      if (value.groups.length > 0) {
        const groups = value.groups.map((group) => {
          return { value: group.name, viewValue: group.name };
        });
        this.groups.set([
          {
            value: IUiService.DEFAULT_GROUP,
            viewValue: IUiService.DEFAULT_GROUP,
          },
          ...groups,
        ]);
      }
    });

    this.asyncApiService.getAsyncApi().subscribe((asyncapi) => {
      this.title.set(asyncapi.info.title);
    });
  }

  toggleIsShowBindings() {
    this.uiService.toggleIsShowBindings(!this.isShowBindings());
  }

  toggleIsShowHeaders() {
    this.uiService.toggleIsShowHeaders(!this.isShowHeaders());
  }

  changeGroup(value: string) {
    this.uiService.changeGroup(value);
  }
}
