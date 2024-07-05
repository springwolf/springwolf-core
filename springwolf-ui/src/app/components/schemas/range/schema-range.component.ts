/* SPDX-License-Identifier: Apache-2.0 */
import { Component, Input } from "@angular/core";
import { Schema } from "../../../models/schema.model";
import { Example } from "../../../models/example.model";
import { initSchema } from "../../../service/mock/init-values";

@Component({
  selector: "app-schema-range",
  templateUrl: "./schema-range.component.html",
  styleUrls: ["./schema-range.component.css"],
})
export class SchemaRangeComponent {
  @Input() schema: Schema = initSchema;
}
