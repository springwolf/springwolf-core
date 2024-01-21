/* SPDX-License-Identifier: Apache-2.0 */
import { Component, Input } from "@angular/core";
import { Schema } from "src/app/models/schema.model";

@Component({
  selector: "app-schema-range",
  templateUrl: "./schema-range.component.html",
  styleUrls: ["./schema-range.component.css"],
})
export class SchemaRangeComponent {
  @Input() schema: Schema;
}
