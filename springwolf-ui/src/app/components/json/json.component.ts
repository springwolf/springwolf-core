/* SPDX-License-Identifier: Apache-2.0 */
import { Component, input } from "@angular/core";

@Component({
  selector: "app-json",
  template: '<markdown [data]="data()"></markdown>',
})
export class JsonComponent {
  data = input.required<string, any>({
    transform: (value: any) =>
      "```json\n" + JSON.stringify(value, null, 2) + "\n```",
  });
}
