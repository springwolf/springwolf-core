/* SPDX-License-Identifier: Apache-2.0 */
import { Component, Input } from "@angular/core";
import { Schema } from "../../../models/schema.model";
import { Example } from "../../../models/example.model";
import { initSchema } from "../../../service/mock/init-values";

@Component({
  selector: "app-schema",
  templateUrl: "./schema.component.html",
  styleUrls: ["./schema.component.css"],
})
export class SchemaComponent {
  @Input() schema: Schema = initSchema;
}
