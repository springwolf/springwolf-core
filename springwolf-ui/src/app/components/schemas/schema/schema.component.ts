/* SPDX-License-Identifier: Apache-2.0 */
import { Component, Input } from "@angular/core";
import { Schema } from "src/app/service/asyncapi/models/schema.model";

@Component({
  selector: "app-schema",
  templateUrl: "./schema.component.html",
  styleUrls: ["./schema.component.css"],
})
export class SchemaComponent {
  @Input() schema: Schema;
}
