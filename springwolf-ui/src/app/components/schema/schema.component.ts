/* SPDX-License-Identifier: Apache-2.0 */
import { Component, input } from "@angular/core";
import { Schema } from "../../models/schema.model";
import "../code/prism.bridge";

@Component({
    selector: "app-schema",
    templateUrl: "./schema.component.html",
    styleUrls: ["./schema.component.css"],
    standalone: false
})
export class SchemaComponent {
  schema = input.required<Schema>();
  protected readonly Object = Object;
}
