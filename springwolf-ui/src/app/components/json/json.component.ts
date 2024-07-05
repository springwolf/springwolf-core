/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnChanges, Input } from "@angular/core";

@Component({
  selector: "app-json",
  template: '<markdown [data]="json"></markdown>',
})
export class JsonComponent implements OnChanges {
  @Input() data: any;
  json: string = "";

  ngOnChanges(): void {
    this.json = "```json\n" + JSON.stringify(this.data, null, 2) + "\n```";
  }
}
