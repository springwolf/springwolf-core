/* SPDX-License-Identifier: Apache-2.0 */
import { Component, input } from "@angular/core";
import { Schema } from "../../../models/schema.model";

@Component({
  selector: "app-schema-range",
  templateUrl: "./schema-range.component.html",
  styleUrls: ["./schema-range.component.css"],
})
export class SchemaRangeComponent {
  schema = input.required<Schema>();
}
