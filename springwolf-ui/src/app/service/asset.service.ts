/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import { MatIconRegistry } from "@angular/material/icon";
import { DomSanitizer } from "@angular/platform-browser";

export abstract class IAssetService {
  abstract load(): void;
}

@Injectable()
export class AssetService implements IAssetService {
  constructor(
    private iconRegistry: MatIconRegistry,
    private sanitizer: DomSanitizer
  ) {}

  public load() {
    this.iconRegistry.addSvgIcon(
      "github",
      this.sanitizer.bypassSecurityTrustResourceUrl("assets/github.svg")
    );
  }
}
